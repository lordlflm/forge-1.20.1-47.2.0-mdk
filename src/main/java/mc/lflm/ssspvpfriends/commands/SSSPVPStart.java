package mc.lflm.ssspvpfriends.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import mc.lflm.ssspvpfriends.timer.Timer;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class SSSPVPStart {
    public SSSPVPStart(CommandDispatcher<CommandSourceStack> dispatcher, Timer timer) {
        dispatcher.register(
                Commands.literal("ssspvp").then(Commands.literal("start")
                .then(Commands.argument("boxBorderLenght", IntegerArgumentType.integer(50))
                .then(Commands.argument("timer", IntegerArgumentType.integer())
                .executes((commandContext) ->
                    start(commandContext.getSource().getPlayerOrException(),
                            IntegerArgumentType.getInteger(commandContext, "boxBorderLenght"),
                            IntegerArgumentType.getInteger(commandContext,"timer"),
                            timer
                    )

                ))))
        );
    }

    private int start(CommandSource source, int boxBorderLenght, Integer timer, Timer timerObject) {
        // Display some combat starting message and information to all players
        source.sendSystemMessage(Component.literal("The courage of a soldier is heightened by his knowledge of his profession.\n― Flavius Vegetius Renatus\n"));

        // TODO : find player amount


        // TODO : Handle/start timer
        timerObject.setInitalTimerSeconds(timer*60);
        timerObject.setTimer(timer);
        timerObject.setServerTicks(0);
        timerObject.setActive(true);

        // TODO : Spawn map borders


        // tp players(team) to random locations within the box


        return 0;
    }
}
