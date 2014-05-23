package com.blobsolutions.searchtweets.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import twitter4j.Status;

import com.blobsolutions.searchtweets.analyzer.SentimentAnalyzer;
import com.blobsolutions.searchtweets.analyzer.TweetWithSentiment;
import com.blobsolutions.searchtweets.beans.SearchVo;
import com.blobsolutions.searchtweets.service.TwitterSearchService;
import com.blobsolutions.searchtweets.utils.Utils;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	private static final String HTTP_TWITTER_COM = "http://twitter.com/";
	final static Logger logger = LoggerFactory.getLogger(SearchController.class);
	

	@RequestMapping(value = "/tweets/{queryText}", method = RequestMethod.GET)
	@ResponseBody
	public List<TweetWithSentiment> twitterSearch(
			@PathVariable("queryText") String queryText) {
		TwitterSearchService search = new TwitterSearchService();
		SearchVo searchVo = new SearchVo();
		searchVo.setQueryText(queryText);
		searchVo.setSize(300);
		searchVo.setPage(1);
		List<Status> results = search.search(searchVo.getQueryText(), searchVo.getSize(), searchVo.getSize(), searchVo.getLang());
		List<TweetWithSentiment> tweets = new ArrayList<TweetWithSentiment>();
		
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		StanfordCoreNLP pipeline = new StanfordCoreNLP();
		SentimentAnalyzer analyzer = new SentimentAnalyzer(pipeline);
		Date today = new Date();
		for (Status status : results) {
			TweetWithSentiment findSentiment = analyzer.findSentiment(status.getText());
			findSentiment.setSource(status.getUser().getScreenName());
			findSentiment.setSourceUrl(HTTP_TWITTER_COM + status.getUser().getScreenName());
			findSentiment.setDate(Utils.getTimeAgo(status.getCreatedAt().getTime(), today));
			if(status.getGeoLocation() != null) {
				findSentiment.setLat(Double.toString(status.getGeoLocation().getLatitude()));
				findSentiment.setLat(Double.toString(status.getGeoLocation().getLongitude()));
			}
			if (findSentiment != null) {
				tweets.add(findSentiment);
				logger.debug("Sentiment: " + findSentiment.getMainSentiment() + " - " + findSentiment.getSentiment() + " Tweet: " + findSentiment.getTweet());
			}
		}
		logger.debug("Cantidad: " + tweets.size());
		
		//HashMap<String, Group> calculateStatistics = calculateStatistics(tweets);
		
		return tweets;
	}
	
	/*private HashMap<String, Group> calculateStatistics(List<TweetWithSentiment> tweets) {
		
		HashMap<String, Group> statistics = new HashMap<String, Group>();
		
		for (TweetWithSentiment tweetWithSentiment : tweets) {
			if(!statistics.containsKey(tweetWithSentiment.getSentiment())) {
				Group group = new Group();
				group.setName(tweetWithSentiment.getSentiment());
				group.setAmount("1");
				statistics.put(tweetWithSentiment.getSentiment(), group);
			} else {
				Group group = statistics.get(tweetWithSentiment.getSentiment());
				group.setAmount((new Integer(group.getAmount()) + 1) + "");
				statistics.put(tweetWithSentiment.getSentiment(),  group);
			}
		}
		
		return statistics;
	}
	
	
	private String createGraphic(Collection<Group> groups) {
		List<Slice> slices = new ArrayList<Slice>();
		
		for (Group group : groups) {
			Slice slice = Slice.newSlice(new Integer(group.getAmount()).intValue(), Color.newColor(Utils.getColorByGroup(group.getName())), group.getName(), group.getName() + " (" + group.getAmount() + ") ");
			slices.add(slice);
		}
		
		PieChart chart = GCharts.newPieChart(slices);
		chart.setSize(480, 180);
		chart.setBackgroundFill(Fills.newSolidFill(Color.newColor("F5F5F5")));
		
		return chart.toURLString();
	}*/
	
	@RequestMapping("/layout")
    public String getSearchPartialPage() {
        return "search/layout";
    }
	
}
