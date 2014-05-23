package com.blobsolutions.searchtweets.utils;

import java.util.Date;

import com.blobsolutions.searchtweets.analyzer.SentimentEnum;

public class Utils {
	
	private static final int SECOND_MILLIS = 1000;
	private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
	private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
	private static final int DAY_MILLIS = 24 * HOUR_MILLIS;
	
	public static String getColorByGroup(String groupName) {
		if(groupName.contains(" ")) {
			groupName = groupName.replace(" ", "_");
		}
		return SentimentEnum.valueOf(groupName.toUpperCase()).getColor();
	}
	
	public static String getTimeAgo(long time, Date ctx) {
	    if (time < 1000000000000L) {
	        // if timestamp given in seconds, convert to millis
	        time *= 1000;
	    }

	    long now = ctx.getTime();
	    if (time > now || time <= 0) {
	        return null;
	    }

	    // TODO: localize
	    final long diff = now - time;
	    if (diff < MINUTE_MILLIS) {
	        return "just now";
	    } else if (diff < 2 * MINUTE_MILLIS) {
	        return "a minute ago";
	    } else if (diff < 50 * MINUTE_MILLIS) {
	        return diff / MINUTE_MILLIS + " minutes ago";
	    } else if (diff < 90 * MINUTE_MILLIS) {
	        return "an hour ago";
	    } else if (diff < 24 * HOUR_MILLIS) {
	        return diff / HOUR_MILLIS + " hours ago";
	    } else if (diff < 48 * HOUR_MILLIS) {
	        return "yesterday";
	    } else {
	        return diff / DAY_MILLIS + " days ago";
	    }
	}

}
