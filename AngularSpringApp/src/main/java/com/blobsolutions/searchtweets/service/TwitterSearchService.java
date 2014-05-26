package com.blobsolutions.searchtweets.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.blobsolutions.searchtweets.analyzer.Tweet;
import com.blobsolutions.searchtweets.utils.Utils;
 
public class TwitterSearchService {
 
	private static final String HTTP_TWITTER_COM = "http://twitter.com/";
    private static final String TWITTER_OAUTH_CONSUMER_KEY = "2lgWk7zXgAoOq4KivY6UQ";
	private static final String TWITTER_OAUTH_CONSUMER_SECRET = "s7FDSdqNUbBDI1HTq7aeAdirPScKpTN6Q2jg4zQR2IA";
	private static final String TWITTER_OAUTH_ACCESS_TOKEN = "142923388-fsp3eNz7YEbJVkqU9Y1besJXvccHytYwF3uGVDKU";
	private static final String TWITTER_OAUTH_ACCESS_TOKEN_SECRET = "UhvwQBkMjlfgSvLDsy4yZausXdCY00LPZ9E0XM3k0voBX";
	private Query query;
	private TwitterFactory tf;
	private List<Tweet> results;
	private ConfigurationBuilder cb;
	
	
	
	public TwitterSearchService() {
		super();
		cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(TWITTER_OAUTH_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_OAUTH_CONSUMER_SECRET)
                .setOAuthAccessToken(TWITTER_OAUTH_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_OAUTH_ACCESS_TOKEN_SECRET);
        tf = new TwitterFactory(cb.build());
        setResults(new ArrayList<Tweet>());
	}

	public QueryResult search() {

        Twitter twitter = tf.getInstance();
        
        QueryResult queryResult = null;
        try {
            queryResult = twitter.search(getQuery());
        } catch (TwitterException e) {
            // ignore
            e.printStackTrace();
        }
        return queryResult;
	}

	public QueryResult search(String keyword, int page, int size, String since, String language) {
        
        Twitter twitter = tf.getInstance();
        int max = 20;
        int counter = 0;
        
        setQuery(new Query(keyword + ""));
        getQuery().setCount(100);
        getQuery().setSince(since);
        getQuery().setGeoCode(new GeoLocation(4.605042,-74.092691), 1000.0, Query.KILOMETERS);
        getQuery().setLang(language);
        
        Query local = null;
        QueryResult queryResult = null;
        
        do {
	        try {
	            queryResult = twitter.search(getQuery());
	            loadResults(queryResult.getTweets());
	            local = queryResult.nextQuery();
	        } catch (TwitterException e) {
	            // ignore
	            e.printStackTrace();
	        }
	        counter++;
        } while(local != null && counter < max);
        return queryResult;
 
    }
	
	
	public void searchStream(String keyword, int page, int size, String since, String language) {
        
		FilterQuery fq = new FilterQuery();
		double[][] locations = {{4.605042,-74.092691}};
        String keywords[] = {keyword};
        String languages[] = {language};
        TwitterStream twitterStream = new TwitterStreamFactory(tf.getInstance().getConfiguration()).getInstance();
        
        StatusListener statusListener = new StatusListener() {
			
			@Override
			public void onException(Exception ex) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStatus(Status status) {
				List<Status> results = new ArrayList<Status>();
				results.add(status);
				loadResults(results);
			}
			
			@Override
			public void onStallWarning(StallWarning warning) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				// TODO Auto-generated method stub
				
			}
		};
        
        fq.track(keywords);
        fq.count(100);
        fq.locations(locations);
        fq.language(languages);

        twitterStream.addListener(statusListener);
        twitterStream.filter(fq);  
    }
	
	private void loadResults(List<Status> tweets) {
		Date today = new Date();
		
		for (Status status : tweets) {
			Tweet tweet = new Tweet(status.getText());
			tweet.setSource(status.getUser().getScreenName());
			tweet.setSourceUrl(HTTP_TWITTER_COM + status.getUser().getScreenName());
			tweet.setDate(Utils.getTimeAgo(status.getCreatedAt().getTime(), today));
			if (status.getGeoLocation() != null) {
				tweet.setLat(Double.toString(status.getGeoLocation().getLatitude()));
				tweet.setLng(Double.toString(status.getGeoLocation().getLongitude()));
				if (tweet != null) {
					getResults().add(tweet);
				}
			}
			
		}
	}
	
	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public List<Tweet> getResults() {
		return results;
	}

	public void setResults(List<Tweet> results) {
		this.results = results;
	}
	
	
    
    
 
 
}