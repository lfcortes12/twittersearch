package com.blobsolutions.searchtweets.analyzer;

import java.io.Serializable;

public class Tweet implements Serializable {

	private static final long serialVersionUID = 5548764041487005216L;
	
	private String tweet;
	private int mainSentiment;
	private String sentiment;
	private String source;
	private String date;
	private String sourceUrl;
	private String lat;
	private String lng;
	
	public Tweet(String tweet) {
		super();
		this.tweet = tweet;
	}

	public Tweet(String tweet, int mainSentiment, String sentiment) {
		super();
		this.tweet = tweet;
		this.mainSentiment = mainSentiment;
		this.sentiment = sentiment;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public int getMainSentiment() {
		return mainSentiment;
	}

	public void setMainSentiment(int mainSentiment) {
		this.mainSentiment = mainSentiment;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

}
