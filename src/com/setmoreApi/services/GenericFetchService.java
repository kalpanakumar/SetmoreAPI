package com.setmoreApi.services;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;
public class GenericFetchService {
	AccessTokenGenerator acctckGen = new AccessTokenGenerator();
	Logger log 	= Logger.getLogger(GenericFetchService.class.getName());
	
	public String communication (String endPoint, String payLoad) {
		String responseJsonString = "";
		String requestMethod = "";
		if(endPoint.equalsIgnoreCase("staffs") || endPoint.equalsIgnoreCase("services"))
			requestMethod = "GET";
		else
			requestMethod = "POST";
		
		try {
			

			
			URL url 				= 	new URL(Constants.SETMORE_HOST+endPoint);
			HttpURLConnection con 	= 	(HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod(requestMethod);
			con.setConnectTimeout(30000);
			con.setReadTimeout(30000);
			con.setRequestProperty("Content-Type","application/json");
			con.setRequestProperty("Authorization","Bearer "+  acctckGen.getAccessToken());
			
			if(payLoad != null)
			{
				OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
		        writer.write(StringEscapeUtils.unescapeHtml(payLoad));
		        writer.close();
			}
			
			log.info(payLoad);
			
			String responseString = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8" ));
			while ((responseString = reader.readLine()) != null) 
			{
				 responseJsonString += responseString;
			}
			log.info(responseJsonString);
			
			reader.close();
			return responseJsonString;
		
		}catch(Exception e) {
			log.warning(e.toString());
			return "exception";
		}
	}
	
	String getParamfromJSON(String input, String param) throws JSONException{
		JSONObject JSON = new JSONObject(input);
		return JSON.getString(param);
	}
	
}
