package com.blobsolutions.searchtweets.analyzer;

public enum SentimentEnum {
	VERY_NEGATIVE("Very Negative", "921313"),
	NEGATIVE("Negative", "C70000"), 
	POSITIVE("Positive", "669900"),
	VERY_POSITIVE("Very positive", "5A7C19"), 
	NEUTRAL("Neutral", "ADCD6D");

	private final String name;
	private final String color;

	private SentimentEnum(String name, String color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

}
