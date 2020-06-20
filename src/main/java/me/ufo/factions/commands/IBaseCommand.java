package me.ufo.factions.commands;

import org.bukkit.command.CommandSender;

public interface IBaseCommand {

  void setup();

  boolean execute(CommandSender sender, String label, String[] args);

}
