package com.genting.money.changer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "currency")
	private String currency;

	@Column(name = "buy")
	private Double buy;

	@Column(name = "sell")
	private Double sell;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getBuy() {
		return buy;
	}

	public void setBuy(Double buy) {
		this.buy = buy;
	}

	public Double getSell() {
		return sell;
	}

	public void setSell(Double sell) {
		this.sell = sell;
	}

	@Override
	public String toString() {
		return "ExchangeRate [id=" + id + ", currency=" + currency + ", buy=" + buy + ", sell=" + sell + "]";
	}
}
