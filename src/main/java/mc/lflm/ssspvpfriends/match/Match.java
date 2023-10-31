package mc.lflm.ssspvpfriends.match;

import mc.lflm.ssspvpfriends.timer.Timer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.border.WorldBorder;

import java.util.List;

public class Match {

    //static instance
    public static Match match = new Match();
    //static Timer instance
    public static Timer timer = new Timer(false);


    private boolean isWorldBorderClosing = false;
    private int boxBorderLenght;
    private List<ServerPlayer> pList;
    private double[] startCoordinates;

    private WorldBorder worldBorderRef;



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

    public double[] getStartCoordinates() {
        return startCoordinates;
    }

    public void setStartCoordinates(double[] startCoordinates) {
        this.startCoordinates = startCoordinates;
    }

    public boolean isWorldBorderClosing() {
        return isWorldBorderClosing;
    }

    public void setWorldBorderClosing(boolean worldBorderClosing) {
        isWorldBorderClosing = worldBorderClosing;
    }

    public WorldBorder getWorldBorderRef() {
        return worldBorderRef;
    }

    public void setWorldBorderRef(WorldBorder worldBorderRef) {
        this.worldBorderRef = worldBorderRef;
    }
}
