package com.fizzed.prometheus.ninja;

import ninja.Router;

public class NinjaPrometheusRoutes {
 
    /**
     * Initializes routes for prometheus exports, by binding default to "/metrics".
     * 
     * @param router The ninja router to init
     */
    static public void init(
            Router router) {
        
        init(router, null);
    }
    
    /**
     * Initializes routes for prometheus exports, by binding default to the prefix
     * plus "/metrics".
     * 
     * @param router The ninja router to init
     * @param prefix The prefix of the url to bind to, which will have "/metrics"
     *      appended to it.
     */
    static public void init(
            Router router,
            String prefix) {
        
        final String endpoint = "/metrics";
        final String path = prefix != null ? prefix + endpoint : endpoint;
        
        router.GET().route(path).with(PrometheusController::metrics_show);
    }
    
}