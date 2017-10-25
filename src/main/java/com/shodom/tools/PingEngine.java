package com.shodom.tools;

import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;
import org.icmp4j.IcmpPingUtil;

public class PingEngine {

	public static boolean getPing(String ip) {
		boolean result = false;
		try {
			IcmpPingRequest request = IcmpPingUtil.createIcmpPingRequest();
			request.setHost (ip);
			IcmpPingResponse response = IcmpPingUtil.executePingRequest (request);
			result = response.getSuccessFlag();
			
		} catch (Exception e) {
			result = false;
		}
		
		return result;

	}

}
