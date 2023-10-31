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
import net.minecraft.world.level.border.WorldBorder;

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
        matchInit(source, boxBorderLenght, pList);
        worldBorderInit(source, boxBorderLenght);
        timerInit(timer, timerObject);

        // OPTIONAL : tp players (teams) to random locations within the box. Option for team of 2,3,4,etc.



        // TODO : algorithm -- pList.size() = player amount (ready chest content here)



        timerObject.setActive(true);
        ServerMessage.sendAllPlayerMessage(Component.literal("-------------------------------------------\n" +
                        "The courage of a soldier is heightened by his knowledge of his profession." +
                        "\n― Flavius Vegetius Renatus\n\nThe timer is " + timer + " minutes.\n\nOnly one shall remain and claim the crown.\n" +
                        "-------------------------------------------"),
                pList);
        return 0;
    }

    private static void worldBorderInit(CommandSourceStack source, int boxBorderLenght) {
        WorldBorder wb = source.getLevel().getWorldBorder();
        wb.setCenter(Match.match.getStartCoordinates()[0], Match.match.getStartCoordinates()[2]);
        wb.setSize(boxBorderLenght);
    }

    private static void timerInit(Integer timer, Timer timerObject) {
        timerObject.setInitalTimerSeconds(timer *60);
        timerObject.setTimerSeconds(timer *60);
        timerObject.setServerTicks(0);
    }

    private static void matchInit(CommandSourceStack source, int boxBorderLenght,List<ServerPlayer> pList) {
        Match.match.setWorldBorderClosing(false);
        Match.match.setBoxBorderLenght(boxBorderLenght);
        Match.match.setpList(pList);
        Match.match.setWorldBorderRef(source.getLevel().getWorldBorder());
        double[] coord = {
                Math.ceil(source.getPosition().x),
                Math.ceil(source.getPosition().y),
                Math.ceil(source.getPosition().z)
        };
        Match.match.setStartCoordinates(coord);

    }
}
