package Game;


import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Doctor extends Rectangle implements Enemy {

    private boolean blockedLeft = false;
    private boolean blockedRight = false;
    private boolean blockedUp = false;
    private boolean blockedDown = false;

    private float initialX;
    private float initialY;
    private boolean goRight = true;


    float babkaX;
    float babkaY;
    float babkaWidth;
    float babkaHeight;

    float visionHorizontal;
    float visionVertical;
    float notVisionHorizontal;
    float notVisionVertical;
    float space;
    float distance=200;

    private boolean babkaNoticed = false;
    private boolean alive = true;


    public Doctor(int x, int y) throws SlickException {
        super(x, y, 50, 80);
        initialX = x;
        initialY = y;
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
                if (this.getX() >= initialX + space|| blockedRight) {
                    goRight = false;
                } else {
                    this.setCenterX(getCenterX() + 1);
                }
            } else {
                if (this.getX() <= initialX - space || blockedLeft) {
                    goRight = true;
                } else {
                    this.setCenterX(getCenterX() - 1);
                }
            }



        }

        ///// babka noticed
        else {
            if (Math.abs(babkaX-this.getX())>notVisionHorizontal||Math.abs(babkaY-this.getY())>notVisionVertical){
                babkaNoticed=false;

            }



                if (this.getX()+this.getWidth()+distance < babkaX||(this.getX() < babkaX+babkaWidth+distance&&this.getX() > babkaX+babkaWidth)&&blockedRight==false) {
                    this.setCenterX(getCenterX() + 2);
                }
        if (this.getX() > babkaX+this.getWidth()+distance||(this.getX()+this.getWidth()+distance > babkaX&&this.getX() +this.getWidth()< babkaX)&&blockedLeft==false) {
            this.setCenterX(getCenterX() - 2);

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
                babkaY = platform.getY();
                 babkaWidth=+platform.getWidth();
                babkaHeight=platform.getHeight();
                Rectangle legB = new Rectangle(this.getX(), this.getY() + this.height, width, visionVertical);
                Rectangle arm1B = new Rectangle(this.getX() - visionHorizontal, this.getY() + 1, visionHorizontal, height - 2);
                Rectangle arm2B = new Rectangle(this.getX() + this.getWidth(), this.getY() + 1, visionHorizontal, height - 2);
                Rectangle headB = new Rectangle(this.getX() , this.getY()- visionVertical, width, visionVertical);

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

    public void setVisionHorizontal( float visionHorizontal){
        this.visionHorizontal=visionHorizontal;
    }
    public void setVisionVertical( float visionVertical){
        this.visionVertical=visionVertical;
    }

    public void setNotVisionHorizontal( float notVisionHorizontal){
        this.notVisionHorizontal=notVisionHorizontal;
    }

    public void setNotVisionVertical( float notVisionVertical){
        this.notVisionVertical=notVisionVertical;
    }

    public void setSpace( float space){
        this.space=space;
    }
    public void setDistance( float distance){
        this.distance=distance;
    }



}