package de.mxro.webapiexample.internal;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;

import de.mxro.webapiexample.WebApiServer;
import de.mxro.webapiexample.internal.services.ApplicationMetadataServlet;
import de.mxro.webapiexample.internal.services.HealthServlet;
import de.mxro.webapiexample.internal.services.HelloWorldServlet;

public class WebApiServerImpl implements WebApiServer {

	private Server server;

	public void start(int port) {
		server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(port);
		server.setConnectors(new Connector[] { connector });

		// Servlet API is used instead of Jetty API since it is sufficient and a shared
		// standard between multiple implementations.
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		context.addServlet(HealthServlet.class, "/health");
		context.addServlet(HelloWorldServlet.class, "/hello-world");
		context.addServlet(ApplicationMetadataServlet.class, "/meta");
		
		try {
			server.start();

			

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void stop() {

		try {
			server.stop();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
