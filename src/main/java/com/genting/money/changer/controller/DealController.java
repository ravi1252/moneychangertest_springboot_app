package com.genting.money.changer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genting.money.changer.domain.Deal;
import com.genting.money.changer.service.DealService;
import com.genting.money.changer.utils.AppResponse;
import com.genting.money.changer.utils.ValidResponse;

@RestController
public class DealController {
	
	@Autowired
	DealService dealService;
	
	@RequestMapping(value = "/deals", method = RequestMethod.POST)
	public AppResponse saveDeal(@RequestBody Deal deal) throws Exception {
		return new AppResponse(ValidResponse.success, null, dealService.save(deal));
	}
	
}
