package me.ufo.factions.commands;

import java.util.Arrays;

import me.ufo.factions.UFactionsPlugin;
import me.ufo.factions.commands.impl.ClaimCommand;
import me.ufo.factions.commands.impl.HelpCommand;
import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.command.CommandSender;

public final class FCommand extends BaseCommand {

  public FCommand(UFactionsPlugin plugin) {
    super(plugin, "f");
  }

  @Override
  public void setup() {
    // TODO: "factions." + permission
    setPermission("factions.command");
    new HelpCommand(this);
    new ClaimCommand(this);
  }

  @Override
  public boolean execute(CommandSender sender, String label, String[] args) {
    sender.sendMessage("label: " + label);
    sender.sendMessage("args: " + Arrays.toString(args));
    if (args.length != 0) {
      final BaseCommand command = this.getSubCommand(args[0]);
      if (command != null) {
        // execute command
        return command.execute(
            sender,
            label,
            // remove subcommand argument
            ArrayUtils.remove(args, 0));
      }
    }

    sender.sendMessage("Factions default command");
    sender.sendMessage(this.getSubCommands());
    return true;
  }
}
