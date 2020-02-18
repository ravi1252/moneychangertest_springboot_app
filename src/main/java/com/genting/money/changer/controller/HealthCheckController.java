package com.genting.money.changer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genting.money.changer.utils.AppResponse;
import com.genting.money.changer.utils.AppResponseStatus;

@RestController
public class HealthCheckController {
	
	/**
	 * <pre>
	 * Default url mapping to 
	 * return if the server is healthly
	 * </pre>
	 * 
	 * @return
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public AppResponse getExchangeRates() {
		return new AppResponse(AppResponseStatus.success, null, "Server is up and running");
	}
	
}
