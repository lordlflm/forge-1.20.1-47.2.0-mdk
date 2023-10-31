package mc.lflm.ssspvpfriends.timer;

public class Timer {
    private boolean isActive;

    //this is the second equivalent of the intial timer
    private int initalTimerSeconds;

    //remaining time in seconds
    private float timer;
    private float serverTicks;

    public Timer() {}

    public Timer(boolean isActive) {
        this.isActive = isActive;
    }

    public Timer(int initalTimerSeconds) {
        this.initalTimerSeconds = initalTimerSeconds;
        serverTicks = 0;
        timer = initalTimerSeconds;
    }

    public int getInitalTimerSeconds() {
        return initalTimerSeconds;
    }

    public void setInitalTimerSeconds(int initalTimerSeconds) {
        this.initalTimerSeconds = initalTimerSeconds;
    }

    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
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
}
