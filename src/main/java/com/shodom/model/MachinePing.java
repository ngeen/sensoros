package com.shodom.model;

import org.springframework.data.annotation.Id;

public class MachinePing {
	
	@Id
	public String id;
	
	public Machine machine;
	
	public boolean access;
	
	public long pingSeq;
	

}
