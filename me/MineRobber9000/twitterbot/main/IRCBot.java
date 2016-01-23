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
			if (parts[1].equalsIgnoreCase("read")) {
				try {
					Query q = new Query("from:" + parts[2]);
					QueryResult result = t.search(q);
					List<Status> tweets = result.getTweets();
					boolean first = true;
					for (Status tweet : tweets) {
						if (first) {
							StringBuilder sb = new StringBuilder();
							sb.append("@");
							sb.append(tweet.getUser().getScreenName());
							sb.append(": ");
							sb.append(tweet.getText());
							sendMessage(channel, sb.toString());
							first = false;
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				if (parts[1].equalsIgnoreCase("leave")) {
					if (sender.equals("ImANoob")) {
						this.quitServer("Bye!!1!!11!");
						System.exit(0);
					}
				} else {
					beCocky(channel);
				}
			}
		}
	}

	private void beCocky(String channel) {
		sendMessage(channel, "I can't read minds! What do you want?");
		sendMessage(channel, "Commands are: 'read <user>' (ex; 'read sonicretro')");
	}
	
}
