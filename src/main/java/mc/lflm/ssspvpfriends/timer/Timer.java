package mc.lflm.ssspvpfriends.timer;

import mc.lflm.ssspvpfriends.match.Match;
import mc.lflm.ssspvpfriends.servermessages.ServerMessage;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.TickEvent;

public class Timer {

    private boolean isActive;

    //this is the second equivalent of the intial timer
    private int initalTimerSeconds;

    //remaining time in seconds
    private float timerSeconds;

    //ticks since timer start
    private float serverTicks;

    // minute interval between remaining time display in chat (final for now could be optionnalized with a command + setter)
    final private float intervalMinute = 0.5f;


    public Timer() {}

    public Timer(boolean isActive) {
        this.isActive = isActive;
        this.serverTicks = 0;
    }

    public int getInitalTimerSeconds() {
        return initalTimerSeconds;
    }

    public void setInitalTimerSeconds(int initalTimerSeconds) {
        this.initalTimerSeconds = initalTimerSeconds;
    }

    public float getTimerSeconds() {
        return timerSeconds;
    }

    public void setTimerSeconds(float timerSeconds) {
        this.timerSeconds = timerSeconds;
    }

    public float getServerTicks() {
        return serverTicks;
    }

    public void setServerTicks(float serverTicks) {
        this.serverTicks = serverTicks;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void incrementTimer() {
        Match.timer.setServerTicks(Match.timer.getServerTicks() + 0.5f);
        Match.timer.setTimerSeconds(Match.timer.getTimerSeconds() - 0.025f);
    }

    public void timerMessage(TickEvent.ServerTickEvent event) {
        // TODO : Display time remaining every 10 minutes (1min = 1200tick). Make it a settable option?
        if (Match.timer.getServerTicks() % (1200 * intervalMinute) == 0)
            // TODO : this should go in all chat (confirm done)
            ServerMessage.sendAllPlayerMessage(Component.literal(intervalMinute + " minutes has passed. Time left (minutes) : " + (Match.timer.getTimerSeconds() / 60) + ", ticks : " + Match.timer.getServerTicks()), Match.match.getpList());
    }

    // TODO : refactor this into reset match?
    public void resetTimer(TickEvent.ServerTickEvent event) {
        if (Match.timer.getTimerSeconds() <= 0) {
            // TODO : this should go in all chat (confirm done)
            ServerMessage.sendAllPlayerMessage(Component.literal("Done. " + (Match.timer.getInitalTimerSeconds() / 60) + " minutes passed (" + Match.timer.getInitalTimerSeconds() + " seconds)"), Match.match.getpList());
            Match.timer = new Timer(false);

            // TODO : Spawn chest in middle of the box
        }
    }
}
