package top.vlsiion.buda.demo.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import top.vlsiion.buda.demo.batch.domain.Employee;
import top.vlsiion.buda.demo.batch.listener.PkslowProcessListener;
import top.vlsiion.buda.demo.batch.listener.PkslowReadListener;
import top.vlsiion.buda.demo.batch.listener.PkslowWriteListener;

/**
 * @author : zhanghuang
 * @date : 2022-03-23 11:20
 */
@Configuration
public class JobConfiguration {
    @Value("classpath:input/inputData*.csv")
    private Resource[] inputResources;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    private Resource outputResource = new FileSystemResource("output/outputData.csv");

    @Bean
    public MultiResourceItemReader<Employee> multiResourceItemReader() {
        MultiResourceItemReader<Employee> resourceItemReader = new MultiResourceItemReader<Employee>();
        resourceItemReader.setResources(inputResources);
        resourceItemReader.setDelegate(reader());
        return resourceItemReader;
    }

    @Bean
    public FlatFileItemReader<Employee> reader() {
        FlatFileItemReader<Employee> reader = new FlatFileItemReader<Employee>();
        //跳过csv文件第一行，为表头
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        //字段名
                        setNames(new String[]{"id", "firstName", "lastName"});
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {
                    {
                        //转换化后的目标类
                        setTargetType(Employee.class);
                    }
                });
            }
        });
        return reader;
    }

    public ItemProcessor<Employee, Employee> itemProcessor() {
        return employee -> {
            employee.setLastName(employee.getLastName().toUpperCase());
            return employee;
        };
    }

    @Bean
    public FlatFileItemWriter<Employee> writer() {
        FlatFileItemWriter<Employee> writer = new FlatFileItemWriter<>();
        writer.setResource(outputResource);
        //是否为追加模式
        writer.setAppendAllowed(true);
        writer.setLineAggregator(new DelimitedLineAggregator<Employee>() {
            {
                //设置分割符
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<Employee>() {
                    {
                        //设置字段
                        setNames(new String[]{"id", "firstName", "lastName"});
                    }
                });
            }
        });
        return writer;
    }

    @Bean
    public Step csvStep() {
        return stepBuilderFactory.get("csvStep").<Employee, Employee>chunk(5)
                .reader(multiResourceItemReader())
                .listener(new PkslowReadListener())
                .processor(itemProcessor())
                .listener(new PkslowProcessListener())
                .writer(writer())
                .listener(new PkslowWriteListener())
                .build();
    }
    @Bean
    public Job pkslowCsvJob() {
        return jobBuilderFactory
                .get("pkslowCsvJob")
                .incrementer(new RunIdIncrementer())
                .start(csvStep())
                .build();
    }
}
