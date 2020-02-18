package com.genting.money.changer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genting.money.changer.domain.ExchangeRate;
import com.genting.money.changer.repo.ExchangeRateRepo;

@Service
public class ExchangeRateService {
	
	@Autowired
    private ExchangeRateRepo exchangeRateRepo;
	
	public List<ExchangeRate> list() {
        return exchangeRateRepo.findAll();
	}
}
