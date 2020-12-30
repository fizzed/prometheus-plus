package com.fizzed.prometheus.ninja;

import io.prometheus.client.CollectorRegistry;
import org.junit.Test;

public class PrometheusExporterTest {
 
    @Test
    public void startTwice() {
        final PrometheusExporter exporter = new PrometheusExporter(() -> CollectorRegistry.defaultRegistry);
        
        // two starts in a row needs to work!
        exporter.start();
        // without proper catching of exceptions, this can break
        exporter.start();
    }
    
}