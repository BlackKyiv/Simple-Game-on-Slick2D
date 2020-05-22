package Game.enemies;

import Game.SetupGame;
import org.newdawn.slick.*;
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
    float visionHorizontal;
    float visionVertical;
    float notVisionHorizontal;
    float notVisionVertical;
    float space;

    private boolean babkaNoticed = false;
    private boolean alive = true;
    private String path = "/Users/dgoptsii/Game copy/pictures/";

   // private Image image  = new Image(path + "corona_left.PNG");;

    private SpriteSheet image1  = new SpriteSheet( "/Users/dgoptsii/Game/pictures/corona_left.PNG",50,50);;
    private Animation animation;

    public Coronavirus(int x, int y) throws SlickException {
        super(x, y, 50, 50);
        initialX = x;
        initialY = y;

        animation = new Animation(image1,100);
        animation.setPingPong(true);
    }
    public  Animation  getAnimation(Graphics graphics){
        return animation;
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


    public void checkForCollisionWall(Rectangle platform){

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
    }
    public void checkForCollisionBabka(Rectangle platform){
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
            babkaX = platform.getX();
            babkaY = platform.getY()+platform.getHeight();
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



}