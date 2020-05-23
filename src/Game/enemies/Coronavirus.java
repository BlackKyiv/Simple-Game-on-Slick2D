package Game.enemies;

import Game.SetupGame;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;


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
    float babkaWidth;
    float babkaHeight;
    float space;


    float visionHorizontalLeft;
    float visionHorizontalRight;
    float visionVerticalUp;
    float visionVerticalDown;

    float notVisionHorizontalLeft;
    float notVisionHorizontalRight;
    float notVisionVerticalUp;
    float notVisionVerticalDown;

    private boolean babkaNoticed = false;
    private boolean alive = true;

    private Rectangle vision1;
    private Rectangle vision2;


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
        vision1 = new Rectangle(getX(), getY(), getWidth(), getHeight());
        vision2 = new Rectangle(getX(), getY(), getWidth(), getHeight());
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
    public void update(ArrayList<Rectangle> obstacles) {

        move();

        blockedLeft = false;
        blockedRight = false;
        blockedDown = false;
        blockedLeft = false;

        createVision(obstacles);
    }


    public void createVision(ArrayList<Rectangle> obstacles){
        vision1.setX(getX());
        vision1.setY(getY());
        vision1.setWidth(getWidth()/2);
        vision1.setHeight(getHeight());

        boolean visionCollided = false;
        for(int i = 0; i<getCenterX(); i++){
            vision1.setX(vision1.getX()-1);
            vision1.setWidth(vision1.getWidth()+1);
            for(int a =0; a<obstacles.size();a++){
                if(vision1.intersects(obstacles.get(a))){
                    visionCollided = true;
                    break;
                }
            }
            if(visionCollided) break;
        }

        vision2.setX(getCenterX());
        vision2.setY(getY());
        vision2.setWidth(getWidth()/2);
        vision2.setHeight(getHeight());

        visionCollided = false;
        for(int i = 0; i<SetupGame.width-getCenterX(); i++){
            vision2.setWidth(vision2.getWidth()+1);
            for(int a =0; a<obstacles.size();a++){
                if(vision2.intersects(obstacles.get(a))){
                    visionCollided = true;
                    break;
                }
            }
            if(visionCollided) break;
        }
    }

    public Rectangle getVision1(){
        return vision1;
    }
    public Rectangle getVision2(){
        return vision2;
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

            if ((babkaX>(this.getX()+this.getWidth())&&babkaX - (this.getX()+this.getWidth()) > notVisionHorizontalRight) ||(babkaX+babkaWidth<this.getX()&&this.getX() - (babkaX+babkaWidth) > notVisionHorizontalLeft)||(babkaY>(this.getY()+this.getHeight())&&babkaY - (this.getY()+this.getHeight()) > notVisionVerticalUp) ||(babkaY+babkaHeight<this.getY()&&this.getY() - (babkaY+babkaHeight) > notVisionVerticalDown)) {
                babkaNoticed = false;
            }
            else {


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
        babkaY = platform.getY();
        babkaWidth = +platform.getWidth();
        babkaHeight = platform.getHeight();

        Rectangle legB = new Rectangle(this.getX(), this.getY() + this.height, width, visionVerticalDown);
        Rectangle arm1B = new Rectangle(this.getX() - visionHorizontalLeft, this.getY() + 1, visionHorizontalLeft, height - 2);
        Rectangle arm2B = new Rectangle(this.getX() + this.getWidth(), this.getY() + 1, visionHorizontalRight, height - 2);
        Rectangle headB = new Rectangle(this.getX(), this.getY() - visionVerticalUp, width, visionVerticalUp);

        if (legB.intersects(platform) || headB.intersects(platform) || arm1B.intersects(platform) || arm2B.intersects(platform)) {
                babkaNoticed = true;
            }

            Rectangle headDie = new Rectangle(this.getX()+8, this.getY(), width-16, 1);
            if (headDie.intersects(platform)) {
                die();
            }

    }


    public void setVisionHorizontal(float visionHorizontalLeft, float visionHorizontalRight) {
        this.visionHorizontalLeft = visionHorizontalLeft;
        this.visionHorizontalRight = visionHorizontalRight;
    }

    public void setVisionVertical(float visionVerticalUp,float visionVerticalDown ) {
        this.visionVerticalUp = visionVerticalUp;
        this.visionVerticalDown = visionVerticalDown;
    }

    public void setNotVisionHorizontal(float notVisionHorizontalLeft, float notVisionHorizontalRight) {
        this.notVisionHorizontalLeft = notVisionHorizontalLeft;
        this.notVisionHorizontalRight = notVisionHorizontalRight;
    }

    public void setNotVisionVertical(float notVisionVerticalUp,float notVisionVerticalDown ) {
        this.notVisionVerticalUp = notVisionVerticalUp;
        this.notVisionVerticalDown = notVisionVerticalDown;
    }
    public void setSpace( float space){
        this.space=space;
    }



}