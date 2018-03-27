package de.mxro.webapiexample.tests.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Utilities for testing JSON responses.
 * @author Max
 *
 */
public class JSONUtils {
	/**
	 * Parses a response into a JsonObject using GSON.
	 * 
	 * @param json The JSON to be parsed
	 * @return The JsonObject parsed from the source provided.
	 */
	public static JsonObject parse(String json) {
		return (JsonObject) new JsonParser().parse(json);
	}
}
