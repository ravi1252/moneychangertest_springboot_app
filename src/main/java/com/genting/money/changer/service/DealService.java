package com.genting.money.changer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genting.money.changer.domain.Transaction;
import com.genting.money.changer.repo.DealRepo;

@Service
public class DealService {
	
	@Autowired
    private DealRepo dealRepo;
	
	public Transaction save(Transaction transaction) {
		return dealRepo.save(transaction);
	}
	
}
