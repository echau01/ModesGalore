package me.echau.myfirstplugin.Commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.echau.myfirstplugin.Main;

public class MemeCommand implements CommandExecutor {
	private final Main plugin;

	public MemeCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender theSender, Command cmd, String commandLabel, String args[]) {
		if (theSender instanceof Player) {
			Player player = (Player) theSender;
			if (commandLabel.equalsIgnoreCase("meme")) {
				if (player.hasPermission("myfirstplugin.meme")) {
					File memeFile = new File(plugin.getDataFolder(), "memes.txt");
					try (
						BufferedReader lineCounter = new BufferedReader(new FileReader(memeFile));
						BufferedReader reader = new BufferedReader(new FileReader(memeFile))
					) {
						//Represents the number of lines in the memes.txt file
						int numLines = 0;
						
						//Count the number of lines in the memes.txt file.
						try {
							while (lineCounter.readLine() != null) {
								numLines++;
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						if ((numLines - 8 + 1) <= 0) {
							player.sendMessage(ChatColor.RED + "There are no memes to be displayed! "
									+ "Ask an administrator to add memes.");
						} else {
							//Selected line number from 8 to the number of lines
							//Line number refers to the line number in the memes.txt file
							//The line number is used to get the program to read that specific line number from the file.
							final int lineNumber = new Random().nextInt(numLines - 8 + 1) + 8;
							
							//For example, if the line number is 8, then the meme number is 8 - 8 + 1 = 1.
							//If the line number is 20, then the meme number is 20 - 8 + 1 = 13.
							//This integer is used as part of a message sent to the player.
							final int memeNumber = lineNumber - 8 + 1;
							
							//Read through the first lineNumber - 1 lines of the file.
							for (int i = 1; i <= lineNumber - 1; i++) {
								reader.readLine();
							}
							
							//Reads and assigns the contents of the next line (the line we want) to the retrievedMeme.
							//The string is split at the characters "\n"
							//The program interprets \n as \\n because \ is reserved in Java
							//In order to split the string properly, 2 more backslashes must be added to escape the other 2.
							//Therefore, the string must be split at \\\\n
							final String[] retrievedMeme = reader.readLine().split("\\\\n");
							
							//Sends messages to the player
							player.sendMessage(ChatColor.GOLD + "Meme #" + ChatColor.RED + String.valueOf(memeNumber) + ChatColor.GOLD + ":");
							for (String s : retrievedMeme) {
								player.sendMessage(ChatColor.AQUA + s);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					player.sendMessage(ChatColor.RED + "Insufficient permissions!");
				}
			}
		}
		return true;
	}
}
