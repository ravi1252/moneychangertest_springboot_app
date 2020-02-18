package com.genting.money.changer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genting.money.changer.domain.ExchangeRate;

@Repository
public interface ExchangeRateRepo extends JpaRepository<ExchangeRate, Long> {
	
	List<ExchangeRate> findBycurrency(String currency);
	
}
