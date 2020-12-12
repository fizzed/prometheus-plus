package com.fizzed.prometheus.ninja;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.hotspot.DefaultExports;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import ninja.lifecycle.Start;

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
        DefaultExports.register(registry);
    }
    
}