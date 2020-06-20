package me.ufo.factions.commands.impl;

import java.util.Arrays;

import me.ufo.factions.commands.BaseCommand;
import org.bukkit.command.CommandSender;

public final class ClaimCommand extends BaseCommand {

  public ClaimCommand(BaseCommand parent) {
    super(parent, "claim");
  }

  @Override
  public void setup() {
    setPermission("claim");
  }

  @Override
  public boolean execute(CommandSender sender, String label, String[] args) {
    sender.sendMessage("claims command");
    sender.sendMessage("label: " + label);
    sender.sendMessage("args: " + Arrays.toString(args));
    return true;
  }

}
