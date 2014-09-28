package uhc.audicy.saturationfix;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class SaturationFix
        extends JavaPlugin
        implements Listener
{
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event)
    {
        final Player player = event.getPlayer();
        final float before = player.getSaturation();

        new BukkitRunnable()
        {
            public void run()
            {
                float change = player.getSaturation() - before;
                player.setSaturation((float)(before + change * 2.5D));
            }
        }.runTaskLater(this, 1L);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event)
    {
        if (event.getFoodLevel() < ((Player)event.getEntity()).getFoodLevel()) {
            event.setCancelled(new Random().nextInt(100) < 66);
        }
    }
}
