package me.MineRobber9000.twitterbot;

import me.MineRobber9000.twitterbot.main.*;
//import me.MineRobber9000.twitterbot.utils.*;

public class exec {

	public static void main(String[] args) throws Exception {
		IRCBot bot = new IRCBot("TweetBot");
		bot.setVerbose(true);
		bot.connect("irc.badnik.net");
		bot.joinChannel("#Lil-G|bot");
	}

}
