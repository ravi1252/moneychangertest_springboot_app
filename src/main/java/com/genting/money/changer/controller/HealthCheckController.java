package com.genting.money.changer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genting.money.changer.utils.AppResponse;
import com.genting.money.changer.utils.ValidResponse;

@RestController
public class HealthCheckController {
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public AppResponse getExchangeRates() {
		return new AppResponse(ValidResponse.success, null, "Server is up and running");
	}
	
}
