package io.github.ddmonk.config;

import com.google.inject.Singleton;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import io.github.ddmonk.filter.CrossOriginFilter;
import io.github.ddmonk.resource.SimpleResource;


import java.util.HashMap;
import java.util.Map;

public class JerseyModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {

        bind(CrossOriginFilter.class).in(Singleton.class);
        filter("/*").through(CrossOriginFilter.class);

        bind(SimpleResource.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put("com.sun.jersey.api.json.POJOMappingFeature", "true");

        serve("/api/*").with(GuiceContainer.class, params);
    }
}
