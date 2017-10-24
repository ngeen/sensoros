package com.shodom.tools;

import java.net.InetAddress;

public class PingEngine {

	public static boolean getPing(String ip) {
		boolean result = false;

		try {
			InetAddress address = InetAddress.getByName(ip);

			if (address.isReachable(5000)) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			result = false;
		}

		return result;

	}

}
