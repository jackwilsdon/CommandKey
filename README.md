CommandKey
=========
Single use keys for running Bukkit commands.

Usage
-------
`/commandkey <command> [key]` - Creates a command key. If [key] is not provided, a random one is generated. (requires **commandkey.create**)

`/ck <command> [key]` - An alias of `/commandkey`, see above.

Replace all spaces in your command with `_`, so `say Testing` would become `say_Testing`. Commands can use the variable **[user]**, which will be replaced with the user who activates the key.

`/activate <key>` - Activate a key (runs the command as console) (requires **commandkey.activate**)

Created for igloo22225 on [BukkitDev](http://forums.bukkit.org/threads/command-serial-run-a-server-command-with-a-single-use-key.123015/)

CommandKey can be downloaded [here](https://github.com/jackwilsdon/CommandKey/raw/master/jars/CommandKey.jar).