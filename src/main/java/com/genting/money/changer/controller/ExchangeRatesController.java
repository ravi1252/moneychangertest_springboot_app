package com.genting.money.changer.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genting.money.changer.domain.ExchangeRate;
import com.genting.money.changer.exception.InvalidInputDataException;
import com.genting.money.changer.service.ExchangeRateService;
import com.genting.money.changer.utils.AppResponse;
import com.genting.money.changer.utils.ValidResponse;

import net.sf.json.JSONObject;

@RestController
public class ExchangeRatesController {

	private static final Logger logger = LoggerFactory.getLogger(ExchangeRatesController.class);

	@Autowired
	ExchangeRateService exchangeRateService;
	
	private static List<ExchangeRate> exchangeRatesList ;

	@RequestMapping(value = "/exchangerates", method = RequestMethod.GET)
	public AppResponse getExchangeRates() throws Exception {
		try {
			logger.info("Request came to get exchangerates");
			exchangeRatesList = exchangeRateService.list();
			return new AppResponse(ValidResponse.success, null, exchangeRatesList);
		} catch (Exception e) {
			throw new Exception("Error in processing the request: " + e.getMessage());
		}
	}

	@RequestMapping(value = "/exchangerates/{inputCurrency}", method = RequestMethod.GET)
	public AppResponse getRateByCurrency(
			@PathVariable String inputCurrency,
			@RequestParam Map<String, String> customQuery
		) throws Exception {
		
		logger.info("customQuery = brand " + customQuery.containsKey("amt"));
		logger.info("customQuery = brand " + customQuery.containsKey("returnCurrency"));
		
		Double amt = 0.0;
		String returnCurrency = "";
		if(customQuery.containsKey("amt")) {
			amt = Double.valueOf(customQuery.get("amt"));
		}
		
		if(customQuery.containsKey("returnCurrency")) {
			returnCurrency = customQuery.get("returnCurrency");
		}
		 
		if (StringUtils.isEmpty(inputCurrency)) {
			throw new InvalidInputDataException("Input currency in the path is mandatory");
		} else {
			inputCurrency = inputCurrency.toUpperCase();
		}
		
		logger.info("amt : " + amt + " returnCurrency " + returnCurrency);
		
		if(amt <= 0) {
			throw new InvalidInputDataException("Requesting amount must be greater than 0");
		}
		
		try {
			
			JSONObject messagesJsonObj = new JSONObject();
			if(inputCurrency.equalsIgnoreCase("SGD")) {
				// this is the sell request
				if(StringUtils.isEmpty(returnCurrency)) {
					throw new InvalidInputDataException("Incase of SELL request, return currency is mandatory");
				}
				Double sellRate = getSellRate(returnCurrency);
				Double amountToDispense = sellRate * amt;
				messagesJsonObj.put("sellRate", sellRate);
				messagesJsonObj.put("amountToDispense", amountToDispense);
				messagesJsonObj.put("dispenseCurrencyType", returnCurrency);
			} else {
				// this is the buy request
				// query db with input currency and get the buy price
				// skiping for now
				Double buyRate = getBuyRate(inputCurrency);
				logger.info("Retrieved buy rate for {}  is {}", inputCurrency, buyRate);
				Double amountToDispense = buyRate * amt;
				messagesJsonObj.put("buyRate", buyRate);
				messagesJsonObj.put("amountToDispense", amountToDispense);
				messagesJsonObj.put("dispenseCurrencyType", "SGD");
			}
			
			return new AppResponse(ValidResponse.success, null, messagesJsonObj);
			
		} catch (Exception e) {
			throw new Exception("Error in processing the request: " + e.getMessage());
		}
	}

	private Double getSellRate(String returnCurrency) {
		Double sellRate = 0.0;
		for (ExchangeRate exchangeRate : exchangeRatesList) {
			if(exchangeRate.getCurrency().equalsIgnoreCase(returnCurrency)) {
				sellRate = exchangeRate.getSell();
				break;
			}
		}
		return sellRate;
	}

	private Double getBuyRate(String inputCurrency) {
		Double buyRate = 0.0;
		for (ExchangeRate exchangeRate : exchangeRatesList) {
			if(exchangeRate.getCurrency().equalsIgnoreCase(inputCurrency)) {
				buyRate = exchangeRate.getBuy();
				break;
			}
		}
		return buyRate;
	}

}
