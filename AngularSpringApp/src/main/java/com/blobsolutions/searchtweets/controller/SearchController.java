package com.blobsolutions.searchtweets.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blobsolutions.searchtweets.analyzer.Tweet;
import com.blobsolutions.searchtweets.beans.SearchVo;
import com.blobsolutions.searchtweets.service.TwitterSearchService;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	final static Logger logger = LoggerFactory.getLogger(SearchController.class);
	

	@RequestMapping(value = "/tweets/{queryText}", method = RequestMethod.GET)
	@ResponseBody
	public List<Tweet> twitterSearch(@PathVariable("queryText") String queryText) {
		
		
		TwitterSearchService search = new TwitterSearchService();
		
		SearchVo searchVo = new SearchVo();
		searchVo.setQueryText(queryText);
		searchVo.setSize(100);
		searchVo.setPage(1);
		searchVo.setSince("2014-05-24");
		
		search.search(searchVo.getQueryText(), 1, searchVo.getSize(), searchVo.getSince(), searchVo.getLang());
		//search.searchStream(searchVo.getQueryText(), 1, searchVo.getSize(), searchVo.getSince(), searchVo.getLang());
		
		logger.debug("Cantidad: " + search.getResults().size());
		
		
		return search.getResults();
	}
	
	@RequestMapping("/layout")
    public String getSearchPartialPage() {
        return "search/layout";
    }
	
}
