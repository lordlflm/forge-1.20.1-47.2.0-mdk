package mc.lflm.ssspvpfriends.match;

public class Match {
    private int boxBorderLenght;
    private int playerAmount;
    private int timer;


    public Match(){
    }
    public Match(int boxBorderLenght, int playerAmount, int timer) {
        this.boxBorderLenght = boxBorderLenght;
        this.playerAmount = playerAmount;
        this.timer = timer;
    }

    @Override
    public String toString() {
        return "Match{" +
                "boxBorderLenght=" + boxBorderLenght +
                ", playerAmount=" + playerAmount +
                ", timer=" + timer +
                '}';
    }

    public int getBoxBorderLenght() {
        return boxBorderLenght;
    }

    public void setBoxBorderLenght(int boxBorderLenght) {
        this.boxBorderLenght = boxBorderLenght;
    }

    public int getPlayerAmount() {
        return playerAmount;
    }

    public void setPlayerAmount(int playerAmount) {
        this.playerAmount = playerAmount;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
