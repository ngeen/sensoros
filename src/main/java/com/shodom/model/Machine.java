package com.shodom.model;

import org.springframework.data.annotation.Id;

public class Machine {

	@Id
	public String id;
	
	public String name;
	
	public String ip;
	
    public Machine() {}

    public Machine(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }
	
	
    @Override
    public String toString() {
        return String.format(
                "Machine[id=%s, name='%s', ip='%s']",
                id, name, ip);
    }
	
	
}
