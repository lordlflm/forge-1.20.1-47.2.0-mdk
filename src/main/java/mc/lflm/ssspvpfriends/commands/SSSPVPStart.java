package mc.lflm.ssspvpfriends.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import mc.lflm.ssspvpfriends.match.Match;
import mc.lflm.ssspvpfriends.servermessages.ServerMessage;
import mc.lflm.ssspvpfriends.timer.Timer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public class SSSPVPStart {
    public SSSPVPStart(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("ssspvp").then(Commands.literal("start")
                .then(Commands.argument("boxBorderLenght", IntegerArgumentType.integer(50))
                .then(Commands.argument("timerMinutes", IntegerArgumentType.integer())
                .executes((commandContext) ->
                    start(commandContext.getSource(),
                            IntegerArgumentType.getInteger(commandContext, "boxBorderLenght"),
                            IntegerArgumentType.getInteger(commandContext,"timerMinutes"),
                            Match.timer
                    )

                ))))
        );
    }

    private int start(CommandSourceStack source, int boxBorderLenght, Integer timer, Timer timerObject) {
        List<ServerPlayer> pList = source.getServer().getPlayerList().getPlayers();
        Match.match.setBoxBorderLenght(boxBorderLenght);
        Match.match.setpList(pList);

        // Display some combat starting message and information to all players
        ServerMessage.sendAllPlayerMessage(Component.literal("The courage of a soldier is heightened by his knowledge of his profession." +
                "\n― Flavius Vegetius Renatus\n\nThe timer is " + timer + " minutes.\n\nOnly one shall remain and claim the crown."),
                pList);

        // TODO : find player amount for chest loot algorithm (ready chest content here)


        // TODO : Handle/start timer
        timerObject.setInitalTimerSeconds(timer*60);
        timerObject.setTimerSeconds(timer*60);
        timerObject.setServerTicks(0);
        timerObject.setActive(true);

        // TODO : Spawn map borders




        // OPTIONAL : tp players (teams) to random locations within the box. Option for team of 2,3,4,etc.


        return 0;
    }
}
