package com.shodom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.shodom.model.PingSequences;

@Service
public class NextSequenceService {

	@Autowired
	private MongoOperations mongo;

	public long getNextSequence(String key) {
		Query query = new Query(Criteria.where("_id").is(key));

		Update update = new Update();
		update.inc("seq", 1);

		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		PingSequences seqId = mongo.findAndModify(query, update, options, PingSequences.class);
		
		if (seqId == null) {
			seqId = new PingSequences();
			seqId.id="pingSeq";
			seqId.seq = 0;
			mongo.save(seqId);
		  }

		return seqId.seq;
	}

}
