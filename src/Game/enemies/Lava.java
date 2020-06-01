package Game.enemies;

import Game.SetupGame;
import Game.Timer;
import org.newdawn.slick.geom.Rectangle;

import java.util.Set;

public class Lava extends Rectangle {

    private float peakY;

    private float bottomY;

    private float speed = 2;

    private Timer delay;

    private Timer atPeak;

    public Lava(float bottomY, float peakY) {
        super(0, bottomY, SetupGame.width, SetupGame.height-bottomY);
        this.peakY = peakY;
        this.bottomY = bottomY;
        delay = new Timer(8000);
        delay.start();
        atPeak = new Timer(6000);
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public void update(float delta){
        delay.update(delta);
        atPeak.update(delta);

        if(atPeak.isFinished()) {
            atPeak.restart();
            delay.restart();
            delay.start();
        }



        if(delay.isFinished()) {
            if(!atPeak.isRunning()){
                atPeak.restart();
                atPeak.start();
            }
            moveUp();
        }
        else {
            moveDown();
        }


    }

    public void setDelay(float delay){
        this.delay = new Timer(delay);
        this.delay.start();
    }

    public void setAtPeak(float atPeak){
        this.atPeak = new Timer(atPeak);
    }

    private void moveUp(){
        if(getY()> peakY)this.setY(getY()-speed);
        setHeight(SetupGame.height - getY());
    }

    private void moveDown(){
        if(getY()< bottomY)this.setY(getY()+speed);
        setHeight(SetupGame.height - getY());
    }





}
