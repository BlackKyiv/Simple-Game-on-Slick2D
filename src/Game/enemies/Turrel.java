package Game.enemies;

import Game.interactiveObjects.Injection;
import Game.Timer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.time.OffsetTime;


public class Turrel extends Rectangle implements Enemy {
    private float initialX;
    private float initialY;
    private boolean goRight = true;
    private boolean toRight;



    private float rangeOfSight = 10000;
    private Timer shootGap;



    private boolean alive = true;


    public Turrel(int x, int y) throws SlickException {
        super(x, y, 80, 80);
        initialX = x;
        initialY = y;


        shootGap = new Timer(200);
        shootGap.start();
    }


    public void update(int delta) {
        shootGap.update(delta);

    }

    public boolean isReadyToShoot(Rectangle target){
        return isAlive()&& shootGap.isFinished()&&intersectsLineOfSight(target);
    }

    public Injection shoot() throws SlickException {
        Injection injection = new Injection((int) this.getCenterX(), (int) this.getCenterY());
        if(!isToRight() )injection.setLeft();
        else injection.setRight();
        injection.setSpeed(13);
        injection.setPresent(true);
        injection.setDoctor(false);
        shootGap.restart();
        shootGap.start();
        return injection;
    }


    public boolean intersectsLineOfSight(Rectangle target){
        if(target.getY()>= this.getY() && target.getY()<= this.getY()+this.getHeight() && isToRight() && target.getX()>this.getCenterX() &&
            target.getCenterX()-this.getCenterX()<=rangeOfSight){

            return true;

        }
        else if(target.getY()>= this.getY() && target.getY()<= this.getY()+this.getHeight() && !isToRight() && target.getX()<this.getCenterX() &&
                this.getCenterX()-target.getX()<=rangeOfSight){

            return true;
        }
        else {

            return false;
        }
    }





    private void move() {
    }


    public void die(){
        alive = false;
    }
    public boolean isAlive(){return alive;}

    public void checkForCollisionBabka(Rectangle platform)   {
        Rectangle headDie = new Rectangle(this.getX()+8, this.getY(), width-16, 1);
        if (headDie.intersects(platform)) {
            die();
        }
    }

    public void checkForCollisionWall(Rectangle platform) {
    }


    public void setRight(){
        toRight=true;
    }
    public void setLeft(){
        toRight=false;
    }

    public float getRangeOfSight(){
        return rangeOfSight;
    }
    public void setRangeOfSight(float rangeOfSight) {
        this.rangeOfSight = rangeOfSight;
    }

    public boolean isToRight(){
        return toRight;
    }

}