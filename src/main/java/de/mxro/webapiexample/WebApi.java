package de.mxro.webapiexample;

import de.mxro.webapiexample.internal.WebApiServerImpl;

/**
 * <p>This class provides the facade for creating and starting servers.
 * @author Max
 *
 */
public class WebApi {
	
	/**
	 * This main method will be called upon running the executable jar assembled by the build.
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 8989;
		if (args.length == 1) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException ex) {
				System.out.println("Please provide a valid port number as a parameter.");
				return;
			}
		}
		
		
		WebApiServer server = createServer();
		server.start(port);
	}
		
	/**
	 * This method allows creating a new web server.
	 * 
	 * @return A {@link WebApiServer} instance which can be used to start and stop the server.
	 */
	public static WebApiServer createServer() {
		
		return new WebApiServerImpl();
		
	}
	
}
