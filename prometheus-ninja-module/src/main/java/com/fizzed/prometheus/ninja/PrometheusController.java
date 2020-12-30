package com.fizzed.prometheus.ninja;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.utils.ResponseStreams;

@Singleton
public class PrometheusController {

    private final CollectorRegistry registry;
    
    @Inject
    public PrometheusController(
            CollectorRegistry registry) {
        
        this.registry = registry;
    }
  
    private Set<String> parseIncludedNames(Context context) {
        List<String> names = context.getParameterValues("name[]");
        if (names == null) {
            return Collections.emptySet();
        } else {
            return new HashSet<>(names);
        }
    }
    
    public Result metrics_show(Context context) {
        final Set<String> includedNames = this.parseIncludedNames(context);

        return Results.ok()
            .contentType(TextFormat.CONTENT_TYPE_004)
            .render((Context context1, Result result) -> {
                ResponseStreams streams = context.finalizeHeaders(result);
                try (Writer writer = streams.getWriter()) {
                    TextFormat.write004(writer, registry.filteredMetricFamilySamples(includedNames));
                    writer.flush();
                }
                catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
    }
    
}