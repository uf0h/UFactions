package me.ufo.factions.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import me.ufo.factions.UFactionsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/*
 TODO: override methods
 TODO: description, aliases, permissions
*/
public abstract class BaseCommand extends Command {

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

  public BaseCommand(BaseCommand parent, String label) {
    this(parent, label, new String[0]);
  }

  public BaseCommand(BaseCommand parent, String label, String... aliases) {
    super(label, "", "", Arrays.asList(aliases));

    parent.addSubCommand(label, this);
    setAliases(Arrays.asList(aliases));

    this.setup();
  }

  public abstract void setup();

  public abstract boolean execute(CommandSender sender, String label, String[] args);

  private void addSubCommand(String label, BaseCommand command) {
    this.subCommands.put(label, command);
  }

  BaseCommand getSubCommand(String label) {
    return subCommands.get(label);
  }

  // debug
  String getSubCommands() {
    return subCommands.toString();
  }
}
