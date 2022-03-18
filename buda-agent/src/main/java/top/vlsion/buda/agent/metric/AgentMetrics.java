package top.vlsion.buda.agent.metric;

import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.*;
import io.micrometer.core.instrument.binder.system.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.system.FileDescriptorMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. 采集运行时硬件性能参数
 * 2. 采集运行时jvm相关参数
 *
 * @author : zhanghuang
 * @date : 2022-01-25 13:14
 */
@Component
public class AgentMetrics {
    private static final MeterRegistry jvmMeterRegistry = new SimpleMeterRegistry();
    private static final MeterRegistry hardwareMeterRegistry = new SimpleMeterRegistry();

    static {
        //硬件相关
        new FileDescriptorMetrics().bindTo(hardwareMeterRegistry);
        new ProcessorMetrics().bindTo(hardwareMeterRegistry);
        new UptimeMetrics().bindTo(hardwareMeterRegistry);
        new DiskSpaceMetrics(new File(".")).bindTo(hardwareMeterRegistry);

        //jvm
        new ClassLoaderMetrics().bindTo(jvmMeterRegistry);
        new JvmMemoryMetrics().bindTo(jvmMeterRegistry);
        new JvmCompilationMetrics().bindTo(jvmMeterRegistry);
        new JvmGcMetrics().bindTo(jvmMeterRegistry);
        new JvmHeapPressureMetrics().bindTo(jvmMeterRegistry);
        new JvmThreadMetrics().bindTo(jvmMeterRegistry);
    }

    public static Map<String, Object> jvmMetricsData() {
        return parseMetricsData(jvmMeterRegistry.getMeters());
    }

    public static Map<String, Object> hardwareMetricsData() {
        return parseMetricsData(hardwareMeterRegistry.getMeters());
    }

    private static Map<String, Object> parseMetricsData(List<Meter> meters) {
        Map<String, Object> metricMap = new HashMap<>();
        meters.forEach(meter -> metricMap.put(meter.getId().getName(), measurementJson(meter.measure())));
        return metricMap;
    }

    private static Object measurementJson(Iterable<Measurement> measurementIterable) {

        int size = CollectionUtils.size(measurementIterable);
        if (size == 0) {
            return new HashMap<>();
        }
        if (size == 1) {
            Measurement measurement = IterableUtils.get(measurementIterable, 0);
            return measureData(measurement);
        }

        List<Map<String, Object>> dataList = new ArrayList<>();
        measurementIterable.forEach((measurement) -> {
            dataList.add(measureData(measurement));
        });
        return dataList;
    }


    private static Map<String, Object> measureData(Measurement measurement) {
        Map<String, Object> measureData = new HashMap<>();
        measureData.put("statistic", measurement.getStatistic().getTagValueRepresentation());
        measureData.put("value", measurement.getValue());
        return measureData;
    }

}
