package com.shodom.tools;

import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;
import org.icmp4j.IcmpPingUtil;

import com.shodom.model.Machine;
import com.shodom.model.MachinePing;

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
	
	public static MachinePing getMachinePing(Machine mac) {
		MachinePing mp = new MachinePing();
		mp.machine = mac;
		try {
			IcmpPingRequest request = IcmpPingUtil.createIcmpPingRequest();
			request.setHost (mac.ip);
			IcmpPingResponse response = IcmpPingUtil.executePingRequest (request);
			mp.access = response.getSuccessFlag();
			mp.ttl = response.getTtl();
			mp.size = response.getSize();
			mp.time = response.getDuration();
			
		} catch (Exception e) {
		}
		
		return mp;

	}

}
