package top.vlsiion.buda.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.vlsion.buda.agent.metric.AgentMetrics;
import top.vlsion.buda.encryption.annotation.BudaEncryption;
import top.vlsion.buda.encryption.annotation.EnableBudaEncrypt;

import java.util.Map;

import static java.lang.String.format;

@RestController
@SpringBootApplication
@EnableBudaEncrypt
@EnableBatchProcessing
public class BudaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudaDemoApplication.class, args);
    }

    @RequestMapping("/getCode")
    @BudaEncryption(decryptionKey = "0123456789abcdef",encryptionKey = "0123456789abcdef")
    public String getCode(@RequestBody String json) {
        return json;
    }

}
