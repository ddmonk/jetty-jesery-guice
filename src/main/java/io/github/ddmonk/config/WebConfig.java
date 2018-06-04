package io.github.ddmonk.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class WebConfig extends GuiceServletContextListener{

    protected Injector getInjector() {
        Injector injector = Guice.createInjector(new JerseyModule());
        return injector;
    }
}
