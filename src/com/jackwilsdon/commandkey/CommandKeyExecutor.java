package com.jackwilsdon.commandkey;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandKeyExecutor implements CommandExecutor {
	private CommandKey plugin = null;
	
	public CommandKeyExecutor(CommandKey plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (label.equalsIgnoreCase("commandkey") || label.equalsIgnoreCase("ck"))
		{
			if (!sender.hasPermission("commandkey.create"))
			{
				sender.sendMessage(ChatColor.RED+"You don't have permission to do that!");
				return true;
			}
			
			if (args.length != 1 && args.length != 2)
			{
				sender.sendMessage(ChatColor.RED+"Usage: /"+label+" <command> [key]");
				return true;
			}
			
			if (args.length == 1)
			{
				SecureRandom srandom = new SecureRandom();
				String rand = new BigInteger(176, srandom).toString(32).substring(0, 5);
				
				this.plugin.getConfig().set("CommandKey.keys."+rand, args[0].replaceAll("_", " "));
				sender.sendMessage("CommandKey created with the key '"+rand+"'");
				
				this.plugin.saveConfig();
				return true;
			}
			
			if (args.length == 2)
			{
				this.plugin.getConfig().set("CommandKey.keys."+args[1], args[0].replaceAll("_", " "));
				sender.sendMessage("CommandKey created with the key '"+args[1]+"'");
			
				this.plugin.saveConfig();
				return true;
			}
		} else if (label.equalsIgnoreCase("activate")) {
			if (!sender.hasPermission("commandkey.activate"))
			{
				sender.sendMessage(ChatColor.RED+"You don't have permission to do that!");
				return true;
			}
			
			if (args.length != 1)
			{
				sender.sendMessage(ChatColor.RED+"Usage: /"+label+" <key>");
				return true;
			}
			
			if (this.plugin.getConfig().get("CommandKey.keys."+args[0]) != null)
			{
				String cmd = this.plugin.getConfig().getString("CommandKey.keys."+args[0]);
				cmd = cmd.replaceAll("[user]", sender.getName());
				
				this.plugin.getConfig().set("CommandKey.keys."+args[0], null);
				this.plugin.getServer().dispatchCommand(this.plugin.getServer().getConsoleSender(), cmd);
				return true;
			} else {
				sender.sendMessage(ChatColor.RED+"Invalid key!");
				return true;
			}
		}
		return true;
	}

}
