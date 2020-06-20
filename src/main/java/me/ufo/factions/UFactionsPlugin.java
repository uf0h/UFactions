package me.ufo.factions;

import java.lang.reflect.Field;

import lombok.Getter;
import me.ufo.factions.commands.BaseCommand;
import me.ufo.factions.commands.FCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public final class UFactionsPlugin extends JavaPlugin {
  @Getter private static UFactionsPlugin instance;

  @Override
  public void onEnable() {
    instance = this;

    this.registerCommand(new FCommand(this));
  }

  @Override
  public void onDisable() {
    instance = null;
  }

  private void registerCommand(BaseCommand command) {
    try {
      final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
      commandMapField.setAccessible(true);
      final SimpleCommandMap commandMap =
          (SimpleCommandMap) commandMapField.get(Bukkit.getServer());

      commandMap.register(command.getLabel(), command);
    } catch (Exception exception) {
      this.getLogger().severe("Failed to register command.");
      this.getServer().getPluginManager().disablePlugin(this);
    }
  }
}
