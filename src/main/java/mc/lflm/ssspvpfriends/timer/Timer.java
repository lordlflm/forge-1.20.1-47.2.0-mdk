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

    private float serverTicksSinceWBClosing;

    // minute interval between remaining time display in chat (final for now could be optionnalized with a command + setter)
    final private float intervalMinute = 10;


    public Timer() {}

    public Timer(boolean isActive) {
        this.isActive = isActive;
        this.serverTicks = 0;
    }

    public float getServerTicksSinceWBClosing() {
        return serverTicksSinceWBClosing;
    }

    public void setServerTicksSinceWBClosing(float serverTicksSinceWBClosing) {
        this.serverTicksSinceWBClosing = serverTicksSinceWBClosing;
    }

    public float getIntervalMinute() {
        return intervalMinute;
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
        if (Match.timer.getServerTicks() % (1200 * intervalMinute) == 0 && !(Match.timer.getTimerSeconds() <= 0))
            ServerMessage.sendAllPlayerMessage(Component.literal(intervalMinute + " minutes has passed. Time left (minutes) : " + (Match.timer.getTimerSeconds() / 60) + ", ticks : " + Match.timer.getServerTicks()), Match.match.getpList());
    }

    // TODO : refactor this into reset match?
    public void resetTimer(TickEvent.ServerTickEvent event) {
        if (Match.timer.getTimerSeconds() <= 0) {
            ServerMessage.sendAllPlayerMessage(Component.literal("A tooth is much more to be prized than a diamond.\n―Don Quixote\n\nBe the first team to reach the center of the map (" +
                    Match.match.getStartCoordinates()[0] + ", " +
                    Match.match.getStartCoordinates()[1] + ", " +
                    Match.match.getStartCoordinates()[2] + ")\n\nWorld borders closing in..."),
                    Match.match.getpList());
            Match.timer = new Timer(false);
            Match.match.setWorldBorderClosing(true);
            Match.timer.setServerTicksSinceWBClosing(0);

            // TODO : Spawn chest in middle of the box

        }
    }

    public void incrementTicksSinceWBClosing() {
        Match.timer.setServerTicksSinceWBClosing(Match.timer.getServerTicksSinceWBClosing() + 0.5f);
    }
}