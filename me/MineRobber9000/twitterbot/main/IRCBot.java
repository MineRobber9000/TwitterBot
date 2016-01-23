package me.MineRobber9000.twitterbot.main;

import java.util.List;

import org.jibble.pircbot.*;

import twitter4j.*;

public class IRCBot extends PircBot {
	
	Twitter t = TwitterFactory.getSingleton();
	
	public IRCBot(String name) {
		this.setName(name);
	}
	
	protected void onMessage(String channel, String sender, String login, String hostname, String message){
		if (message.startsWith("!tb")) {
			String[] parts = message.split(" ");
			if (parts[1].equals("read")) {
				try {
					Query q = new Query("source:twitter4j @" + parts[2]);
					QueryResult result = t.search(q);
					List<Status> tweets = result.getTweets();
					boolean first = false;
					for (Status tweet : tweets) {
						if (!first) {
							StringBuilder sb = new StringBuilder();
							sb.append("@");
							sb.append(tweet.getUser().getScreenName());
							sb.append(": ");
							sb.append(tweet.getText());
							sendMessage(channel, sb.toString());
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
}
