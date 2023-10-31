package mc.lflm.ssspvpfriends.events;

import mc.lflm.ssspvpfriends.SSSPVPFriends;
import mc.lflm.ssspvpfriends.commands.SSSPVPStart;
import mc.lflm.ssspvpfriends.timer.Timer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = SSSPVPFriends.MOD_ID)
public class ModEvents {

    //Register custom command
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        new SSSPVPStart(event.getDispatcher(), Timer.timer);
        ConfigCommand.register(event.getDispatcher());
    }

    //Called every tick
    @SubscribeEvent
    public static void onServerTick(ServerTickEvent event) {
        if (Timer.timer.isActive()) {
            Timer.timer.incrementTimer();
            Timer.timer.timerMessage(event);
            Timer.timer.resetTimer(event);
        }
    }
}
