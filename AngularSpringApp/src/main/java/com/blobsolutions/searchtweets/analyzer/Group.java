package com.blobsolutions.searchtweets.analyzer;

import java.io.Serializable;

public class Group implements Serializable {

	private static final long serialVersionUID = 5548764041487005216L;

	private String name;
	private String amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}
