package conf;

import com.fizzed.prometheus.ninja.NinjaPrometheusModule;
import com.google.inject.AbstractModule;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        install(new NinjaPrometheusModule());
    }

}