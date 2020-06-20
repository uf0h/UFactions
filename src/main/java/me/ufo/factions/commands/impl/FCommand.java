package me.ufo.factions.commands.impl;

import me.ufo.factions.UFactionsPlugin;
import me.ufo.factions.commands.BaseCommand;
import org.bukkit.command.CommandSender;

public final class FCommand extends BaseCommand {

  public FCommand(UFactionsPlugin plugin) {
    super(plugin, "f");
  }

  @Override
  public void setup() {
    setPermission("basecommand");

    new HelpCommand(this);
    new ClaimCommand(this);
  }

  @Override
  public boolean execute(CommandSender sender, String label, String[] args) {
    // handle possible subcommands
    if (args.length != 0) return super.execute(sender, label, args);

    // send help command
    return super.getSubCommand("help").execute(sender, label, args);
  }

}
