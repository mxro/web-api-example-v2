package de.mxro.webapiexample.tests;

import org.junit.After;
import org.junit.Before;

import de.mxro.webapiexample.WebApi;
import de.mxro.webapiexample.WebApiServer;
import de.mxro.webapiexample.tests.utils.PortUtils;

/**
 * <p>Base class for all tests against the server.
 * 
 * @author Max
 *
 */
public abstract class AbstractServerTest {

	protected WebApiServer server;
	protected String serverUrl;
	
	@Before
	public void setUp() {
		
		int nextFreePort = PortUtils.nextAvailablePort(12611);
		
		server = WebApi.createServer();
		server.start(nextFreePort);
		
		serverUrl = "http://localhost:"+nextFreePort;
	}
	
	@After
	public void tearDown() {
		server.stop();
	}
	
}
