package mc.lflm.ssspvpfriends.timer;

import net.minecraft.network.chat.Component;
import net.minecraftforge.event.TickEvent;

public class Timer {
    //static instance
    public static Timer timer = new Timer(false);

    private boolean isActive;

    //this is the second equivalent of the intial timer
    private int initalTimerSeconds;

    //remaining time in seconds
    private float timerSeconds;
    private float serverTicks;

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
        timer.setServerTicks(timer.getServerTicks() + 0.5f);
        timer.setTimerSeconds(timer.getTimerSeconds() - 0.025f);
    }

    public void timerMessage(TickEvent.ServerTickEvent event) {
        // TODO : Display time remaining every 10 minutes (1min = 1200tick)
        if (timer.getServerTicks() % 1200 == 0)
            //this should go in all chat
            event.getServer().sendSystemMessage(Component.literal("Another minute has passed. Time left (minutes) : " + (timer.getTimerSeconds() / 60) + ", ticks : " + timer.getServerTicks()));
    }

    public void resetTimer(TickEvent.ServerTickEvent event) {
        if (timer.getTimerSeconds() <= 0) {
            //this should go in all chat
            event.getServer().sendSystemMessage(Component.literal("Done. " + (timer.getInitalTimerSeconds() / 60) + " minutes passed (" + timer.getInitalTimerSeconds() + " seconds)"));
            timer = new Timer(false);

            // TODO : Spawn chest in middle of the box
        }
    }
}
