package com.genting.money.changer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genting.money.changer.domain.Deal;
import com.genting.money.changer.repo.DealRepo;

@Service
public class DealService {
	
	@Autowired
    private DealRepo dealRepo;
	
	public Deal save(Deal deal) {
		return dealRepo.save(deal);
	}
	
}
