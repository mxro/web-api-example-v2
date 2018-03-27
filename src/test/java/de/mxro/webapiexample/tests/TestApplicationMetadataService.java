package de.mxro.webapiexample.tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.mxro.webapiexample.tests.utils.JSONUtils;

public class TestApplicationMetadataService extends AbstractServerTest {

	@Test
	public void test_get_request() throws UnirestException {

		HttpResponse<String> response = Unirest.get(serverUrl + "/meta").asString();

		// check response code
		Assert.assertEquals("Invalid HTTP status", 200, response.getStatus());

		// check content type
		Assert.assertEquals("Wrong content type", "application/json", response.getHeaders().getFirst("Content-Type"));
			
		// parse response using GSON to assert it is valid JSON and has the correct content
		JsonObject root = JSONUtils.parse(response.getBody());
		
		// check that version number supplied
		Assert.assertTrue("Version invlid", root.get("version").getAsString().contains("."));
		
		// check that description supplied
		Assert.assertTrue("Descriptin not provided", root.get("description").getAsString().length() > 5);
			
		
		
		// check that commit hash is supplied
		Assert.assertTrue("Commit info not provided", root.get("lastcommit").getAsString().length() > 10);
		
		

	}

}
