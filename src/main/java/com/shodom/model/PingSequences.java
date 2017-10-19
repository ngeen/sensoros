package com.shodom.model;

import org.springframework.data.annotation.Id;

public class PingSequences {

	@Id
	public String id;
	
	public long seq;
}
