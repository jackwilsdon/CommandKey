package com.jackwilsdon.commandkey;

import org.bukkit.plugin.java.JavaPlugin;

public class CommandKey extends JavaPlugin {
	public void onEnable()
	{
		CommandKeyExecutor executor = new CommandKeyExecutor(this);
		this.getCommand("commandkey").setExecutor(executor);
		this.getCommand("activate").setExecutor(executor);
	}
}
