package com.setmoreApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.setmoreApi.services.GenericFetchService;
import com.setmoreApi.services.ServicesEnums;

@Controller
public class FetchServices {
	GenericFetchService  genericFetchService  = new GenericFetchService ();
	
	@RequestMapping(value="/{endpoints}",method=RequestMethod.GET)
	@ResponseBody String genericGet (@PathVariable String endpoints){

		if(ServicesEnums.getServicesEnums(endpoints) == null ) {
			return "Not existing ";
		}
		return genericFetchService.communication(endpoints, null);
	}
	
	@RequestMapping(value="/{endpoints}",method=RequestMethod.POST)
	@ResponseBody String genericPost (@PathVariable String endpoints,@RequestBody String payload){
		if(ServicesEnums.getServicesEnums(endpoints) == null ) {
			return "Not existing ";
		}
		if(endpoints.equalsIgnoreCase("slots")) {
			return genericFetchService.communication(endpoints, payload);	
		}else {
			return genericFetchService.communication(endpoints+"/create", payload);
		}
		
	}
}
