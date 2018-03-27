package de.mxro.webapiexample.internal;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;

import de.mxro.webapiexample.WebApiServer;
import de.mxro.webapiexample.internal.services.ApplicationMetadataServlet;
import de.mxro.webapiexample.internal.services.HealthServlet;
import de.mxro.webapiexample.internal.services.HelloWorldServlet;

/**
 * <p>The implementation of the main server API.
 * @author Max
 *
 */
public class WebApiServerImpl implements WebApiServer {

	private Boolean started;
	private Server server;

	public void start(int port) {
		// simple way to protect against the same instance being started multiple times
		synchronized (started) {
			if (started) {
				throw new IllegalStateException("This server is already started.");
			}
			started = true;
		}

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
		synchronized (started) {
			if (!started) {
				throw new IllegalStateException("This server is not running.");
			}
		}

		// since started is true, it is assured that server is not null
		synchronized (server) {

			try {
				server.stop();
				server = null;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	public WebApiServerImpl() {
		super();
		this.started = false;
		this.server = null;
	}

}
