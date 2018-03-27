package de.mxro.webapiexample.tests;

import org.junit.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class TestApplicationMetadataService extends AbstractServerTest {

	@Test
	public void test_get_request() throws UnirestException {
		
		HttpResponse<String> response = Unirest.get(serverUrl + "/meta").asString();
		
		System.out.println(response.getBody());
		
	}
	
}
