package me.ufo.factions.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import me.ufo.factions.UFactionsPlugin;
import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/*
 TODO: override methods
 TODO: description, aliases, permissions
*/
public abstract class BaseCommand extends Command implements IBaseCommand {
  @Getter private UFactionsPlugin plugin;

  private Map<String, BaseCommand> subCommands;

  protected BaseCommand(UFactionsPlugin plugin, String label) {
    this(label);
    this.plugin = plugin;
  }

  protected BaseCommand(String label) {
    super(label);
    this.subCommands = new HashMap<>();

    this.setup();
  }

  protected BaseCommand(BaseCommand parent, String label) {
    this(parent, label, new String[0]);
  }

  protected BaseCommand(BaseCommand parent, String label, String... aliases) {
    super(label, "", "", Arrays.asList(aliases));
    super.setAliases(Arrays.asList(aliases));

    parent.addSubCommand(label, this);
    this.setup();
  }

  @Override
  public boolean execute(CommandSender sender, String label, String[] args) {
    sender.sendMessage("base command");
    final BaseCommand command = this.getSubCommand(args[0]);
    if (command != null) {
      // execute command
      return command.execute(
          sender,
          label,
          // remove subcommand argument
          ArrayUtils.remove(args, 0));
    } else {
      this.subCommands.get("help").execute(sender, label, args);
    }
    return true;
  }

  private void addSubCommand(String label, BaseCommand command) {
    this.subCommands.put(label, command);
  }

  protected BaseCommand getSubCommand(String label) {
    return this.subCommands.get(label);
  }

  @Override
  public void setPermission(String permission) {
    super.setPermission("factions." + permission);
  }

}
