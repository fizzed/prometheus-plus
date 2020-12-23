package com.fizzed.prometheus.ninja;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.hotspot.DefaultExports;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import ninja.lifecycle.Start;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

@Singleton
public class PrometheusExporter {
 
    private final Provider<CollectorRegistry> registryProvider;

    @Inject
    public PrometheusExporter(
            Provider<CollectorRegistry> registryProvider) {
        
        this.registryProvider = registryProvider;
    }
    
    @Start
    public void start() {
        final CollectorRegistry registry = this.registryProvider.get();
        // this is actually only allowed to happen once per JVM
        try {
            DefaultExports.register(registry);
        } catch (IllegalArgumentException e) {
            if (!containsIgnoreCase(e.getMessage(), "Collector already registered that provides")) {
                throw e;
            }
        }
    }
    
}