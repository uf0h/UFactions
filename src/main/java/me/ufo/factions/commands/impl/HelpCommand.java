package me.ufo.factions.commands.impl;

import java.util.Arrays;

import me.ufo.factions.commands.BaseCommand;
import org.bukkit.command.CommandSender;

public final class HelpCommand extends BaseCommand {

  public HelpCommand(BaseCommand parent) {
    super(parent, "help");
  }

  @Override
  public void setup() {
    setPermission("factions.help");
  }

  @Override
  public boolean execute(CommandSender sender, String label, String[] args) {

    sender.sendMessage("Help command");
    sender.sendMessage("label: " + label);
    sender.sendMessage("args: " + Arrays.toString(args));

    return false;
  }
}
