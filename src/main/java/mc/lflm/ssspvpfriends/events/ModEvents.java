package mc.lflm.ssspvpfriends.events;

import mc.lflm.ssspvpfriends.SSSPVPFriends;
import mc.lflm.ssspvpfriends.commands.SSSPVPStart;
import mc.lflm.ssspvpfriends.timer.Timer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = SSSPVPFriends.MOD_ID)
public class ModEvents {
    public static Timer timer = new Timer(false);
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        new SSSPVPStart(event.getDispatcher(), timer);
        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent event) {
        if (timer.isActive()) {
            timer.setServerTicks(timer.getServerTicks() + 0.5f);
            timer.setTimer(timer.getTimer() - 0.000833333333333333333333f/2);
            if (timer.getServerTicks() % 1200 == 0)
                event.getServer().sendSystemMessage(Component.literal("Another minute has passed. Time left : " + timer.getTimer() + ", ticks : " + timer.getServerTicks()));
            if (timer.getTimer() <= 0) {
                timer = new Timer(false);
                event.getServer().sendSystemMessage(Component.literal(timer.getInitalTimerSeconds() + " minutes passees"));
            }
        }
    }
}
