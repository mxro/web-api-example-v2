package de.mxro.webapiexample.tests.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Utilities to find a free port for testing.
 * 
 * @author Max
 *
 */
public class PortUtils {

	/**
	 * Checks to see if a specific port is available.
	 * 
	 * @param port
	 *            The port to check for availability
	 */
	public static boolean portAvailable(final int port) {
		if (port < 1 || port > 30000) {
			throw new IllegalArgumentException("Invalid start port: " + port);
		}

		try (java.net.ServerSocket ss = new java.net.ServerSocket(port);
				java.net.DatagramSocket ds = new java.net.DatagramSocket(port)) {

			ss.setReuseAddress(true);
			ds.setReuseAddress(true);
			
			return true;
		} catch (final java.io.IOException e) {
			return false;
		}

	}

	private static Set<Integer> used = new HashSet<Integer>();

	/**
	 * <p>
	 * Searches for the next available port starting with the defined start port.
	 * <p>
	 * This method also keeps track of ports that were used before and tries to
	 * avoid using these again.
	 * 
	 * @param start
	 * @return
	 */
	public static int nextAvailablePort(int start) {
		synchronized (used) {
			while (used.contains(start) || !portAvailable(start)) {
				start++;
			}
			used.add(start);
			if (used.size() > 10000) {
				used.clear();
			}
		}
		return start;
	}
}
