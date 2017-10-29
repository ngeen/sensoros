package com.shodom.controller;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeatController {

	@RequestMapping(value = "/getHeat", method = RequestMethod.GET)
	public ResponseEntity<?> getHeat() {
		String heatJson = "";

		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet("http://localhost:5000/");
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			InputStream instream = entity.getContent();
			heatJson = IOUtils.toString(instream, "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}

		return new ResponseEntity<String>(heatJson, HttpStatus.CREATED);
	}
}
