package mc.lflm.ssspvpfriends.events;

import mc.lflm.ssspvpfriends.SSSPVPFriends;
import mc.lflm.ssspvpfriends.commands.SSSPVPStart;
import mc.lflm.ssspvpfriends.match.Match;
import mc.lflm.ssspvpfriends.timer.Timer;
import net.minecraft.network.chat.Component;
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
        new SSSPVPStart(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }

    //Called every tick
    @SubscribeEvent
    public static void onServerTick(ServerTickEvent event) {
        onTimerActive(event, Match.timer);
        onWorldBorderClosing(event, Match.timer, Match.match);
    }

    private static void onTimerActive(ServerTickEvent event, Timer timer) {
        if (timer.isActive()) {
            timer.incrementTimer();
            timer.timerMessage(event);
            timer.resetTimer(event);
        }
    }

    private static void onWorldBorderClosing(ServerTickEvent event, Timer timer, Match match) {
        if (match.isWorldBorderClosing()) {
            timer.incrementTicksSinceWBClosing();
            // Closing border by 10 every 5 seconds
            if (match.getWorldBorderRef().getSize() > 200) {
                if (timer.getServerTicksSinceWBClosing() % 100 == 0)
                    match.getWorldBorderRef().setSize(match.getWorldBorderRef().getSize() - 10);
                event.getServer().sendSystemMessage(Component.literal("tick: " + timer.getServerTicksSinceWBClosing()));
            // Closing border by 1 every 5 seconds
            } else if (match.getWorldBorderRef().getSize() > 50) {
                if (timer.getServerTicksSinceWBClosing() % 200 == 0)
                    match.getWorldBorderRef().setSize(match.getWorldBorderRef().getSize() - 2);
            } else {
                match.setWorldBorderClosing(false);
            }
        }
    }
}
