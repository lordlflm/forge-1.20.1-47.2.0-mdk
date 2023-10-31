package mc.lflm.ssspvpfriends.servermessages;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public class ServerMessage {
    public static void sendAllPlayerMessage(Component msgC, List<ServerPlayer> pList) {
        for (ServerPlayer p : pList) {
            p.sendSystemMessage(msgC);
        }
    }
}
