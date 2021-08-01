package net.edhum.bukkit.api.scheduler;

import com.google.inject.AbstractModule;
import net.edhum.common.scheduler.Scheduler;

public class SchedulerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Scheduler.class).to(BukkitScheduler.class);
    }
}
