package de.mxro.webapiexample;

/**
 * The interface for interacting with servers.
 * 
 * @author Max
 *
 */
public interface WebApiServer {
	
	/**
	 * <p>Starts the server on the specified port.
	 * 
	 * @param port The port at which the server is to be started.
	 */
	public void start(int port);
	
	/**
	 * <p>Stops the server and releases the port.
	 */
	public void stop();
	
}
