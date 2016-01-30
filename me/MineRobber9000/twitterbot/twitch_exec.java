package me.MineRobber9000.twitterbot;

import me.MineRobber9000.twitterbot.main.*;

public class twitch_exec {

	public static void main(String[] args) throws Exception {
		IRCBot bot = new IRCBot("MineRobberBot".toLowerCase());
		bot.setVerbose(false);
		bot.connect("irc.twitch.tv", 6667, args[0]);
		bot.joinChannel("#minerobber___t");
	}

}
