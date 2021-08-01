package net.edhum.bukkit.api.scheduler;

import com.google.inject.Inject;
import net.edhum.common.scheduler.Scheduler;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public class BukkitScheduler implements Scheduler {

    private final Plugin plugin;
    private final org.bukkit.scheduler.BukkitScheduler scheduler;

    @Inject
    public BukkitScheduler(Plugin plugin, org.bukkit.scheduler.BukkitScheduler scheduler) {
        this.plugin = plugin;
        this.scheduler = scheduler;
    }

    @Override
    public void runTaskAsynchronously(Runnable runnable) {
        this.scheduler.runTaskAsynchronously(this.plugin, runnable);
    }

    @Override
    public void runTaskLater(Runnable runnable, long delay, TimeUnit timeUnit) {
        this.scheduler.runTaskLater(this.plugin, runnable, toTicks(delay, timeUnit));
    }

    @Override
    public void runRepeatingTask(Runnable runnable, long delay, long period, TimeUnit timeUnit) {
        this.scheduler.runTaskTimer(this.plugin, runnable, toTicks(delay, timeUnit), toTicks(period, timeUnit));
    }

    @Override
    public void runRepeatingTask(Runnable runnable, long period, TimeUnit timeUnit) {
        this.runRepeatingTask(runnable, period, 0, timeUnit);
    }

    private long toTicks(long duration, TimeUnit unit) {
        return unit.toSeconds(duration) * 20;
    }
}
