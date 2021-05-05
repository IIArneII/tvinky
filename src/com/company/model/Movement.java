package com.company.model;

public class Movement {
    Client client;
    BackForthListener backForthListener;
    RightLeftListiner rightLeftListiner;
    TurnRightLeftListiner turnRightLeftListiner;
    ShotListener shotListener;
    private boolean movementLaunched;
    private boolean movementOnPause;

    public Movement(Client client){
        this.client = client;
        this.backForthListener = new BackForthListener(this);
        this.rightLeftListiner = new RightLeftListiner(this);
        this.turnRightLeftListiner = new TurnRightLeftListiner(this);
        this.shotListener = new ShotListener(this);
        movementLaunched = false;
        movementOnPause = false;
    }

    public boolean isMovementLaunched() {
        return movementLaunched;
    }

    public void setMovementLaunched(boolean movementLaunched) {
        this.movementLaunched = movementLaunched;
    }

    public boolean isMovementOnPause() {
        return movementOnPause;
    }

    public void setMovementOnPause(boolean movementOnPause) {
        this.movementOnPause = movementOnPause;
    }

    public void start(){
        movementLaunched = true;
        backForthListener.start();
        rightLeftListiner.start();
        turnRightLeftListiner.start();
        shotListener.start();
    }

}
