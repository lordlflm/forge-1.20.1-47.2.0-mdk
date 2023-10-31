package mc.lflm.ssspvpfriends.match;

import mc.lflm.ssspvpfriends.timer.Timer;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public class Match {
    //static instance
    public static Match match = new Match();
    //static Timer instance
    public static Timer timer = new Timer(false);

    private int boxBorderLenght;
    private List<ServerPlayer> pList;

    public Match(){
    }

    public int getBoxBorderLenght() {
        return boxBorderLenght;
    }

    public void setBoxBorderLenght(int boxBorderLenght) {
        this.boxBorderLenght = boxBorderLenght;
    }

    public List<ServerPlayer> getpList() {
        return pList;
    }

    public void setpList(List<ServerPlayer> pList) {
        this.pList = pList;
    }
}
