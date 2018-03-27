package de.mxro.webapiexample.tests;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.mxro.webapiexample.tests.utils.JSONUtils;

public class TestHealthService extends AbstractServerTest{
	
	
	@Test
	public void test_perform_get_request() throws UnirestException {
		HttpResponse<String> response = Unirest.get(serverUrl + "/health").asString();
		
		// check response code
		Assert.assertEquals("Invalid HTTP status", 200, response.getStatus());
		
		// check content type
		Assert.assertEquals("Wrong content type", "application/json", response.getHeaders().getFirst("Content-Type"));
		
		// parse response using GSON to assert it is valid JSON and has the correct content
		JsonObject root = JSONUtils.parse(response.getBody());
	    Assert.assertTrue("Invalid response", root.get("uptime").getAsInt() > 0);
	}
	
	@Test
	public void test_perform_head_request() throws UnirestException {
		HttpResponse<String> response = Unirest.head(serverUrl + "/health").asString();
		
		// check response code
		Assert.assertEquals("Invalid HTTP status", 200, response.getStatus());
		
	}
	
}
