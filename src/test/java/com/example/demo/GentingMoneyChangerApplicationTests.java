package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.genting.money.changer.GentingMoneyChangerApplication;
import com.genting.money.changer.domain.ExchangeRate;
import com.genting.money.changer.service.ExchangeRateService;

@SpringBootTest(classes=GentingMoneyChangerApplication.class)
@RunWith(SpringRunner.class)
public class GentingMoneyChangerApplicationTests {
	
	@Autowired
	ExchangeRateService exchangeRateService;

	@Test
	public void findCouponList() {
		List<ExchangeRate> exchangeRateList = exchangeRateService.list();
		System.out.println("couponList size " + exchangeRateList.size());
		assertThat(exchangeRateList.size()).isGreaterThan(0);
	}

}
