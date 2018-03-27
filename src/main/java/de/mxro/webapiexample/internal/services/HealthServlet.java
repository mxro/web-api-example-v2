package de.mxro.webapiexample.internal.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>This service can be used to check if the server can be reached.
 * <p>Upon a GET request, it will return a JSON document which reports the time in ms since the server was started.
 * 
 * @author Max
 *
 */
public class HealthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * The time when this servlet was initialized.
	 */
	private long serverStartedAt;
	
	@Override
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		assert serverStartedAt != -1;
		
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("{ \"uptime\": "+(System.currentTimeMillis() - serverStartedAt)+"}");
		
	}

	
	@Override
	public void init() throws ServletException {
		serverStartedAt = System.currentTimeMillis();
	}

	public HealthServlet() {
		super();
		serverStartedAt = -1;
	}
	
	
	
	
	
}
