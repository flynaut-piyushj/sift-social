package com.sift.apis.crons;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.sift.apis.beans.CampaignDefaultForList;
import com.sift.apis.beans.ContactForList;
import com.sift.apis.beans.MailChimpList;
import com.sift.apis.beans.MergeField;
import com.sift.apis.beans.MergeField.Type;

@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private RestTemplate doGetRestTemplate;
	@Autowired
	private Properties mailchimpProperties;
	/**
	 * Description : Cron job to clean the OTP details table
	 */
	@Scheduled(cron = "0 0 1 * * ?", zone="IST")
	public void cleanOTPDetailsTable() {
		try {
			jdbcTemplate.update("truncate table otp_dtls");
			logger.info("OTP Details Truncated Successfully at : " + new Date().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendForgetPasswordCampaign()
	{
		String listId = createList();
		
	}
	
	public String createList()
	{
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String,String>();
		map.add("apiKey", mailchimpProperties.getProperty("apiKey"));
		ContactForList contact=new ContactForList("RepleteInc", "Unity Gold Complex, Phulachiwadi, Deccan - Pune", "Pune", "MH", "411004", "INDIA");
		CampaignDefaultForList defaulCampaign =new CampaignDefaultForList("Piyush", "officialakshayc@gmail.com", "SIFT", "en");
		MailChimpList list=new MailChimpList("ForgotPassword-List", contact, "Forgot Password", defaulCampaign, true);
		HttpEntity<MailChimpList> entity = new HttpEntity<MailChimpList>(list,map);
		String response  = doGetRestTemplate.postForObject(mailchimpProperties.getProperty("createList"), entity, String.class);
		JSONObject jsonList = null;
		try {
			jsonList = new JSONObject(response);
			return jsonList.getString("id");
		}catch (JSONException json) {
			json.printStackTrace();
			return null;
		}
	}
	public void createMergeField()
	{
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String,String>();
		header.add("apiKey", mailchimpProperties.getProperty("apiKey"));
		MergeField field = new MergeField("FPC","forgetPassCode",Type.text);
		HttpEntity<MergeField> mergeField = new HttpEntity<MergeField>(field,header);
		String response = doGetRestTemplate.postForObject(mailchimpProperties.getProperty("createMergeField"), mergeField, String.class);
	}
	
	public List<String[]> getForgetPasswordSenderList()
	{
		return jdbcTemplate.query("select email_dtls.EMAIL,email_dtls.CREATED_TS, from email_dtls where email_dtls.REEPORT_STATUS = null and email_dtls.EMAIL_TYPE = 'FP' order by CREATED_TS asc limit 500", new RowMapper<String[]>()
		{
			@Override
			public String[] mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new String[] {rs.getString(1),rs.getString(2)};
			}
		});
	}
}
