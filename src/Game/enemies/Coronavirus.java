package Game.enemies;

import Game.SetupGame;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
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


    private SpriteSheet imageLeft;
    private SpriteSheet imageRight;
    private Animation animationLeft;
    private Animation animationRight;

    private SpriteSheet imageLeftNoticed;
    private SpriteSheet imageRightNoticed;
    private Animation animationLeftNoticed;
    private Animation animationRightNoticed;

    public Coronavirus(int x, int y) throws SlickException {
        super(x, y, 50, 50);
        initialX = x;
        initialY = y;

        setUpAnimation();
    }

    private void setUpAnimation() throws SlickException{
        Image image = new Image(SetupGame.path + "corona_left.PNG");

        imageLeft = new SpriteSheet(SetupGame.path + "corona_left.PNG", 50, 50);;
        imageRight = new SpriteSheet(SetupGame.path + "corona_right.PNG", 50, 50);;

        imageLeftNoticed = new SpriteSheet(SetupGame.path + "corona_left_noticed.PNG", 50, 50);;
        imageRightNoticed = new SpriteSheet(SetupGame.path + "corona_right_noticed.PNG", 50, 50);


        animationLeft = new Animation(imageLeft,100);
        animationLeft.setPingPong(true);

        animationRight = new Animation(imageRight,100);
        animationRight.setPingPong(true);

        animationLeftNoticed = new Animation(imageLeftNoticed,100);
        animationLeftNoticed.setPingPong(true);

        animationRightNoticed = new Animation(imageRightNoticed,100);
        animationRightNoticed.setPingPong(true);

    }

    public  Animation  getAnimation(Graphics graphics){
       if (babkaNoticed==false) {
           if (goRight) {
               return animationRight;
           } else {
               return animationLeft;
           }
       }else {
           if (goRight) {
               return animationRightNoticed;
           } else {
               return animationLeftNoticed;
           }
       }

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
                    goRight=true;
                } else if (this.getX() > babkaX && blockedLeft == false) {
                    this.setCenterX(getCenterX() - 2);
                    goRight=false;
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