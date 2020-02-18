package com.genting.money.changer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "inputcurrency")
	private String inputCurrency;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "dispensed")
	private String dispensed;
	
	@Column(name = "buyrate")
	private Double buyRate;

	@Column(name = "sellrate")
	private Double sellRate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if(this.getInputCurrency().equalsIgnoreCase("SGD")) {
			this.type = "SELL";
		} else {
			this.type = "BUY";
		}
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDispensed() {
		return dispensed;
	}

	public void setDispensed(String dispensed) {
		this.dispensed = dispensed;
	}

	public Double getBuyRate() {
		return buyRate;
	}

	public void setBuyRate(Double buyRate) {
		this.buyRate = buyRate;
	}

	public Double getSellRate() {
		return sellRate;
	}

	public void setSellRate(Double sellRate) {
		this.sellRate = sellRate;
	}

	public String getInputCurrency() {
		return inputCurrency;
	}

	public void setInputCurrency(String inputCurrency) {
		this.inputCurrency = inputCurrency;
	}
	
}