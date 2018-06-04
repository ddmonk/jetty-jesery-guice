package io.github.ddmonk.server;

import com.google.inject.servlet.GuiceFilter;
import io.github.ddmonk.config.WebConfig;
import io.github.ddmonk.filter.CrossOriginFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class JettyServer {

    private static final Logger LOG = LoggerFactory.getLogger(JettyServer.class);

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        int port = 8090;

        LOG.info("Jetty Server: Binding server to port " + port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        context.addFilter(GuiceFilter.class,"/*", EnumSet.of(DispatcherType.REQUEST));
        context.setWelcomeFiles(new String[]{"index.html"});
        context.setResourceBase("./src/main/webapp");
        /* *
        just work for Resource like js css less html
         * */
        context.addFilter(CrossOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        context.addEventListener(new WebConfig());
        context.addServlet(DefaultServlet.class,"/*");

        Server server = new Server(port);
        server.setHandler(context);
        server.start();
        double startupTime = (System.currentTimeMillis() - startTime) / 1000;
        LOG.info("Jetty Server: Server started in " + startupTime + "seconds");
        server.join();

    }
}
