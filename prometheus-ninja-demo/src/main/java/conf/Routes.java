package conf;

import com.fizzed.prometheus.ninja.NinjaPrometheusRoutes;
import ninja.AssetsController;
import ninja.Results;
import ninja.Router;
import ninja.application.ApplicationRoutes;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {
        
        NinjaPrometheusRoutes.init(router, null);
        
        router.GET().route("/").with(() -> Results.redirect("/metrics"));
        
        router.GET().route("/assets/w/{fileName: .*}").with(AssetsController.class, "serveWebJars");
        router.GET().route("/assets/{fileName: .*}").with(AssetsController.class, "serveStatic");
    }
    
}