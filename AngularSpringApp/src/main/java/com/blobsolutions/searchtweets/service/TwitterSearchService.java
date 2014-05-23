package com.blobsolutions.searchtweets.service;
import java.util.Collections;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
 
public class TwitterSearchService {
 
    private static final String TWITTER_OAUTH_CONSUMER_KEY = "2lgWk7zXgAoOq4KivY6UQ";
	private static final String TWITTER_OAUTH_CONSUMER_SECRET = "s7FDSdqNUbBDI1HTq7aeAdirPScKpTN6Q2jg4zQR2IA";
	private static final String TWITTER_OAUTH_ACCESS_TOKEN = "142923388-fsp3eNz7YEbJVkqU9Y1besJXvccHytYwF3uGVDKU";
	private static final String TWITTER_OAUTH_ACCESS_TOKEN_SECRET = "UhvwQBkMjlfgSvLDsy4yZausXdCY00LPZ9E0XM3k0voBX";

	public List<Status> search(String keyword, int page, int size, String language) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(TWITTER_OAUTH_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_OAUTH_CONSUMER_SECRET)
                .setOAuthAccessToken(TWITTER_OAUTH_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_OAUTH_ACCESS_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        
        Paging paging = new Paging();
        paging.setCount(size);
        paging.setPage(page);
        
        Query query = new Query(keyword + "");
        query.setCount(page);
        query.setGeoCode(new GeoLocation(4.605042,-74.092691), 1000.0, Query.KILOMETERS);
        query.setLang(language);
        try {
            QueryResult queryResult = twitter.search(query);
            return queryResult.getTweets();
        } catch (TwitterException e) {
            // ignore
            e.printStackTrace();
        }
        return Collections.emptyList();
 
    }
	
	
    
    
 
 
}