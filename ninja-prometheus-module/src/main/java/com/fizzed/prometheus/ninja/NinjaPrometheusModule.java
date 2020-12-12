package com.fizzed.prometheus.ninja;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.OptionalBinder;
import io.prometheus.client.CollectorRegistry;

public class NinjaPrometheusModule extends AbstractModule {

    @Override
    protected void configure() {
        OptionalBinder.newOptionalBinder(binder(), CollectorRegistry.class)
            .setDefault()
            .toInstance(CollectorRegistry.defaultRegistry);
        bind(PrometheusExporter.class);
    }
    
}