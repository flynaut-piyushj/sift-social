package com.sift.web.test;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import antlr.collections.List;

public class Consume {

	public static void main(String[] args) {
		
		
		String plainCreds = "99152fa5f98de4f3bec49c0730243942-us16";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		
		RestTemplate restTemplate=new RestTemplate();
		
		/*
		restTemplate.postForObject("https://us16.api.mailchimp.com/3.0/campaigns/16c49b9134/actions/send", null, List.class);
		*/
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<List> response = restTemplate.exchange("https://us16.api.mailchimp.com/3.0/campaigns/16c49b9134/actions/send", HttpMethod.POST, request, List.class);
		
	}
}
