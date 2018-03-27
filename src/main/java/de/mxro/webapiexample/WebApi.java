package de.mxro.webapiexample;

import de.mxro.webapiexample.internal.WebApiServerImpl;

public class WebApi {

	
	public static WebApiServer createServer() {
		
		return new WebApiServerImpl();
		
	}
	
}
