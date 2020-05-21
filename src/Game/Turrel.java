package Game;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Turrel extends Rectangle implements Enemy {


    private float initialX;
    private float initialY;
    private boolean goRight = true;
    private boolean toRight;


    private boolean alive = true;


    public Turrel(int x, int y) throws SlickException {
        super(x, y, 80, 80);
        initialX = x;
        initialY = y;
    }


    public void update() {

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

    public Injection shoot() throws SlickException {
        Injection injection = new Injection((int) this.getCenterX(), (int) this.getCenterY());
        injection.setPresent(true);
        return injection;
    }

    public void setRight(){
        toRight=true;
    }
    public void setLeft(){
        toRight=false;
    }

    public boolean isToRight(){
        return toRight;
    }

}