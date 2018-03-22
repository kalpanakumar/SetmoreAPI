package com.setmoreApi.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;


public class AccessTokenGenerator {

	public String url1=  "https://developer.setmore.com/api/v1/o/oauth2/token?refreshToken="+Constants.REFRESH_TOKEN;

	public String getAccessToken(){
	try {
			URL url 				= 	new URL(url1);
			 HttpURLConnection con 	= 	(HttpURLConnection) url.openConnection();
			 con.setDoInput(true);
			 con.setDoOutput(true);
			 con.setRequestMethod("GET");
			 con.setConnectTimeout(30000);
			 con.setReadTimeout(30000);
			 String responseString = "";
			 String responseJsonString	=	"";		 
			 BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8" ));
				while ((responseString = reader.readLine()) != null) 
				{
					responseJsonString += responseString;
				}
			reader.close();
			return getParamfromJSON(getParamfromJSON(getParamfromJSON(responseJsonString,"data"),"token"),"access_token");
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	String getParamfromJSON(String input, String param) throws JSONException{
		JSONObject JSON = new JSONObject(input);
		return JSON.getString(param);
	}
	
}
