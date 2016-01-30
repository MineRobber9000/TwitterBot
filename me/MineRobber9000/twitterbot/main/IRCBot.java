package me.MineRobber9000.twitterbot.main;

import java.util.*;

import org.jibble.pircbot.*;

import twitter4j.*;
import twitter4j.User;

public class IRCBot extends PircBot {
	
	Twitter t = TwitterFactory.getSingleton();
	boolean debug = false;
	
	public IRCBot(String name) {
		this.setName(name);
		this.setLogin(name);
	}
	
	protected void onMessage(String channel, String sender, String login, String hostname, String message){
		if (message.startsWith("!tb") && !message.equalsIgnoreCase("!tbdebug")) {
			String[] parts = message.split(" ");
			if (parts.length < 2) {
				beCocky(channel, sender);
				return;
			}
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
				} finally {
					if (debug) {
						sendMessage("ImANoob", sender + " requested the latest tweet from: " + parts[2]);
					}
				}
			} else {
				if (parts[1].equalsIgnoreCase("leave")) {
					if (sender.equals("ImANoob")) {
						this.quitServer("Bye!!1!!11!");
						System.exit(0);
					} else {
						sendMessage(channel, getNick() + " is here to stay! Kappa");
						if (debug) {
							sendMessage("ImANoob", sender + " tried to make " + getNick() + " leave.");
						}
					}
				} else {
					if (parts[1].equalsIgnoreCase("git")) {
						sendMessage(channel, "My source code is at: http://www.github.com/MineRobber9000/TwitterBot");
						if (debug) {
							sendMessage("ImANoob", sender + " requested the source code.");
						}
					} else {
						if (parts[1].equalsIgnoreCase("debug")) {
							if (parts[2].equalsIgnoreCase("user")) {
								try {
									User debUser = t.showUser(parts[3]);
									sendMessage(channel, "Username: " + debUser.getScreenName() + "; Display Name: " + debUser.getName() + "; ID: " + debUser.getScreenName() + ";");
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else {
							beCocky(channel, sender);
						}
					}
						
				}
			}
		}
		if (message.equalsIgnoreCase("!tbdebug")) {
			if (sender.equals("ImANoob")) {
				debug = !debug;
			}
		}
	}

	private void beCocky(String channel, String sender) {
		sendMessage(channel, "I can't read minds! What do you want?");
		sendMessage(channel, "Commands are: 'read <user>' (ex; 'read sonicretro')");
		if (debug) {
			sendMessage("ImANoob", "I was cocky to " + sender + " in " + channel);
		}
	}
	
}
