package com.genting.money.changer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genting.money.changer.domain.Transaction;
import com.genting.money.changer.domain.ExchangeRate;
import com.genting.money.changer.exception.InvalidInputDataException;
import com.genting.money.changer.service.DealService;
import com.genting.money.changer.service.ExchangeRateService;
import com.genting.money.changer.utils.AppResponse;
import com.genting.money.changer.utils.AppResponseStatus;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);

	@Autowired
	ExchangeRateService exchangeRateService;
	
	@Autowired
	DealService dealService;
	
	/**
	 * <pre>
	 * Provides the list of all currencies present
	 * in the system and their exchange rates
	 * </pre>
	 *  
	 * @return AppResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/exchangerates", method = RequestMethod.GET)
	public AppResponse getExchangeRates() throws Exception {
		try {
			return new AppResponse(AppResponseStatus.success, null, exchangeRateService.list());
		} catch (Exception e) {
			throw new Exception("Error in processing the request: " + e.getMessage());
		}
	}
	
	/**
	 * Method to convert the currencies
	 * 
	 * @param amount 
	 * @param toCurrency
	 * @param inputCurrency
	 * @return AppResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/convert/{inputCurrency}", method = RequestMethod.GET)
	public AppResponse convertCurrency(@RequestParam(required = true) Double amount,
			@RequestParam(required = false) String toCurrency,
			@PathVariable String inputCurrency) throws Exception {
		
		logger.info("Input data : Currency : {}, amount : {}, toCurrency : {}", inputCurrency, amount, toCurrency);
		
		/**
		 *  - Validate input data. If not valid, throw exception
		 *  - Mandatory fields : inputCurrency, amount
		 *  - Convert the inputCurrency to upper case
		 */
		if(StringUtils.isEmpty(inputCurrency)) {
			throw new InvalidInputDataException("Input currency in the path is mandatory");
		} else {
			inputCurrency = inputCurrency.toUpperCase().trim();
		}
		
		if(amount <= 0) {
			throw new InvalidInputDataException("Amount requesting for must be greater than 0");
		}
		
		/**
		 * 
		 * Logic is as follows:
		 * - If the inputCurrency is SGD,
		 *   it means the customer is selling SGD. 
		 *   Here, we also need to know the currency customer is expecting.
		 *   For example, customer give 1 SGD and take USD
		 *
		 * - If the inputCurrency is any other currency than SGD,
		 *   then, customer is buying SGD.
		 *   For example, customer give 1 USD and take SGD currency
		 * 
		 */
		try {
			JSONObject returningJsonObject = new JSONObject();
			
			if(inputCurrency.equalsIgnoreCase("SGD")) {
				if(StringUtils.isEmpty(toCurrency)) {
					throw new InvalidInputDataException("Incase if customer wants to sell SGD, "
							+ "he/she has to mention the currency expected in return");
				}
				ExchangeRate rate = exchangeRateService.findBycurrency(toCurrency);
				logger.info("Retrieved rate details for : {} is : {}", toCurrency, rate);
				if(rate == null) {
					throw new InvalidInputDataException("Input currency for conversion is not present "
							+ "in the system. Please contact admnistrator");
				}
				returningJsonObject.put("sellRate", rate.getSell());
				returningJsonObject.put("amountToDispense", (amount / rate.getSell()));
				returningJsonObject.put("dispenseCurrencyType", toCurrency);
			} else {
				ExchangeRate rate = exchangeRateService.findBycurrency(inputCurrency);
				logger.info("Retrieved rate details for : {} is : {}", inputCurrency, rate);
				if(rate == null) {
					throw new InvalidInputDataException("Input currency for conversion is not present "
							+ "in the system. Please contact admnistrator");
				}
				returningJsonObject.put("buyRate", rate.getBuy());
				returningJsonObject.put("amountToDispense", (amount * rate.getBuy()));
				returningJsonObject.put("dispenseCurrencyType", "SGD");
			}
			
			return new AppResponse(AppResponseStatus.success, null, returningJsonObject);
		
		} catch (Exception e) {
			throw new Exception("Error in processing the request: " + e.getMessage());
		}
	}
	
	/**
	 * To save the customer transactions
	 * NOTE : Assuming the sell rate, amount are coming from UI, which we
	 * retrieved in the previous call 
	 * 
	 * @param transaction
	 * @return AppResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public AppResponse saveTransaction(@RequestBody Transaction transaction) throws Exception {
		try {
			return new AppResponse(AppResponseStatus.success, null, dealService.save(transaction));
		} catch (Exception e) {
			throw new Exception("Error in saving the transaction: " + e.getMessage());
		}
	}
	
}
