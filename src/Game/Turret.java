package Game;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Turret extends Rectangle implements  Enemy{

    private boolean blockedLeft = false;
    private boolean blockedRight = false;
    private boolean blockedUp = false;
    private boolean blockedDown = false;

    private float initialX;
    private float initialY;
    private boolean goRight = true;


    float babkaX;
    float babkaY;
    float visionHorizontal;
    float visionVertical;
    float notVisionHorizontal;
    float notVisionVertical;
    float space;

    private boolean babkaNoticed = false;
    private boolean alive = true;
    private boolean moveable;

    public Turret(int x, int y, boolean moveable) throws SlickException {
        super(x,y,80,50);
        initialX=x;
        initialY=y;
        this.moveable=moveable;
    }

    public void update() {

        if(moveable) move();

        blockedLeft = false;
        blockedRight = false;
        blockedDown = false;
        blockedLeft = false;
    }

    public void move(){

    }

    @Override
    public void checkForCollision(Rectangle platform, boolean isBabka, boolean notice) {

    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void die() {

    }
}
