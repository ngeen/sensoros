package com.shodom.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shodom.model.MachinePing;

public interface MachinePingRepository extends MongoRepository<MachinePing, String>{

}
