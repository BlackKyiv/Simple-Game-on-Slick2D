package Game;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Coronavirus extends Rectangle implements Enemy {

    private boolean blockedLeft = false;
    private boolean blockedRight = false;
    private boolean blockedUp = false;
    private boolean blockedDown = false;

    private float initialX;
    private float initialY;
    private boolean goRight = true;


    float babkaX;
    float babkaY;

    private boolean babkaNoticed = false;
    private boolean dead = false;
    private boolean babkaFound = false;
    private boolean alive = true;


    public Coronavirus(float x, float y, float width, float height) throws SlickException {
        super(x, y, width, height);
        initialX = x;
        initialY = x;
    }


    public void update() {

        move();


        blockedLeft = false;
        blockedRight = false;
        blockedDown = false;
        blockedLeft = false;
    }


    private void move() {
        if (babkaNoticed == false) {

            if (goRight) {
                if (this.getX() >= initialX + 150 || blockedRight) {
                    goRight = false;
                } else {
                    this.setCenterX(getCenterX() + 1);
                }
            } else {
                if (this.getX() <= initialX - 150 || blockedLeft) {
                    goRight = true;
                } else {
                    this.setCenterX(getCenterX() - 1);
                }
            }



        }

        ///// babka noticed
        else {
            if (Math.abs(babkaX-this.getX())>350||Math.abs(babkaY-this.getY())>150){
                babkaNoticed=false;
                System.out.println(("babka bye"));
            }else {
                if (this.getX() < babkaX && blockedRight == false) {
                        this.setCenterX(getCenterX() + 2);

                } else if (this.getX() > babkaX && blockedLeft == false) {
                    this.setCenterX(getCenterX() - 2);
                }


            }
        }
    }

    public void die(){
        alive = false;
    }
    public boolean isAlive(){return alive;}

    public void checkForCollision(Rectangle platform, boolean isBabka, boolean notice) {

        if (notice) {

            Rectangle leg = new Rectangle(this.getX(), this.getY() + this.width, width, 1);

            if (leg.intersects(platform)) {
                blockedDown = true;
            }


            Rectangle arm1 = new Rectangle(this.getX(), this.getY() + 1, 1, height - 2);
            if ((arm1.intersects(platform))) {
                blockedLeft = true;
            }

            Rectangle arm2 = new Rectangle(this.getX() + this.getWidth(), this.getY() + 1, 1, height - 2);
            if ((arm2.intersects(platform))) {
                blockedRight = true;
            }

            Rectangle head = new Rectangle(this.getX(), this.getY(), width, 1);
            if (head.intersects(platform)) {
                blockedUp = true;
            }
            if (isBabka) {
                babkaX = platform.getX();
                babkaY = platform.getY()+platform.getHeight();
                Rectangle legB = new Rectangle(this.getX(), this.getY() + this.height, width, 250);
                Rectangle arm1B = new Rectangle(this.getX() - 250, this.getY() + 1, 250, height - 2);
                Rectangle arm2B = new Rectangle(this.getX() + this.getWidth(), this.getY() + 1, 250, height - 2);
                Rectangle headB = new Rectangle(this.getX() - 250, this.getY(), width, 250);

                if (legB.intersects(platform) || headB.intersects(platform) || arm1B.intersects(platform) || arm2B.intersects(platform)) {
                    babkaNoticed = true;
                }

                Rectangle headDie = new Rectangle(this.getX()+8, this.getY(), width-16, 1);
                if (headDie.intersects(platform)) {
                   die();
                }

            }


        }
    }


}