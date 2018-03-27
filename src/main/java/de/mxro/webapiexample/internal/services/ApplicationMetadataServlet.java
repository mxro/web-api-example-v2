package de.mxro.webapiexample.internal.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delight.json.JSON;
import delight.json.JSONObject;

/**
 * <p>
 * This service provides some metadata information about this server.
 * 
 * @author Max
 *
 */
public class ApplicationMetadataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// this service will always return the same response, so we can pre-render it at service initialisation.
	private String cachedResponse = null;
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		
		response.getWriter().println(cachedResponse);
		
	}

	@Override
	public void init() throws ServletException {
		super.init();

		readPropertiesAndRenderResponse();

	}

	private void readPropertiesAndRenderResponse() {
		Properties prop = new Properties();
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		try (InputStream input = loader.getResourceAsStream("versioninfo.txt")) {

			// load a properties file
			prop.load(input);
			
			JSONObject jsonObject = JSON.create();
			jsonObject.add("version", prop.getProperty("version"));
			jsonObject.add("description", prop.getProperty("description"));
			jsonObject.add("lastcommit", prop.getProperty("lastcommit"));
			
			cachedResponse = jsonObject.render();
			

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
