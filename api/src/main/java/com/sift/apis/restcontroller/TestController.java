package com.sift.apis.restcontroller;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sift.apis.beans.CampaignDefaultForList;
import com.sift.apis.beans.ContactForList;
import com.sift.apis.beans.MailChimpList;

@RestController
public class TestController {
	
	@Autowired
	private RestTemplate doGetRestTemplate;
	@Autowired
	private Properties configProperties;	
	@RequestMapping("/testCont")
	public void test(@com.fasterxml.jackson.annotation.JsonProperty("yes") String name)
	{
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String,String>();
		map.add("apiKey", configProperties.getProperty("apiKey"));
		ContactForList contact=new ContactForList("RepleteInc", "Unity Gold Complex, Phulachiwadi, Deccan - Pune", "Pune", "MH", "411004", "INDIA");
		CampaignDefaultForList defaulCampaign =new CampaignDefaultForList("Piyush", "officialakshayc@gmail.com", "SIFT", "en");
		MailChimpList list=new MailChimpList("ForgotPassword-List", contact, "Forgot Password", defaulCampaign, true);
		HttpEntity<MailChimpList> entity = new HttpEntity<MailChimpList>(list,map);
		String res = doGetRestTemplate.postForObject("http://localhost:2000/mail/create/list", entity, String.class);
		System.out.println("res: "+res);
	}
	

}
