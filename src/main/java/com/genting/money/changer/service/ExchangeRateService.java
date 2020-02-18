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
	
	public ExchangeRate findBycurrency(String currency) {
		List<ExchangeRate> exchangeRateRows = exchangeRateRepo.findBycurrency(currency);
		if(exchangeRateRows != null && exchangeRateRows.size() > 0) {
			return exchangeRateRows.get(0);
		}
        return null;
	}
}
