package com.fizzed.prometheus.ninja;

import com.codahale.metrics.MetricRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import ninja.lifecycle.Start;

@Singleton
public class NinjaPrometheusDropwizardBridge {
 
    private final Provider<CollectorRegistry> registryProvider;
    private final Provider<MetricRegistry> metricsProvider;

    @Inject
    public NinjaPrometheusDropwizardBridge(
            Provider<CollectorRegistry> registryProvider,
            Provider<MetricRegistry> metricsProvider) {
        
        this.registryProvider = registryProvider;
        this.metricsProvider = metricsProvider;
    }
    
    @Start
    public void start() {
        final CollectorRegistry registry = this.registryProvider.get();
        final MetricRegistry metrics = this.metricsProvider.get();
        new DropwizardExports(metrics).register(registry);
    }
    
}