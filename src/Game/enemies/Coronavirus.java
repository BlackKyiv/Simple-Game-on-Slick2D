package Game.enemies;

import Game.SetupGame;
import Game.interactiveObjects.Door;
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


    private float babkaX;
    private float babkaY;
    private float babkaWidth;
    private float babkaHeight;
    private float space;
    private boolean defaultVision = true;


    private float visionHorizontalLeft;
    private float visionHorizontalRight;
    private float visionVerticalUp;
    private float visionVerticalDown;


    private float notVisionHorizontalLeft;
    private float notVisionHorizontalRight;
    private float notVisionVerticalUp;
    private float notVisionVerticalDown;

    private boolean babkaNoticed = false;
    private boolean babkaVisible = true;
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

    private ArrayList<Rectangle> obstacles = new ArrayList<Rectangle>();


    public Coronavirus(int x, int y) throws SlickException {
        super(x, y, 40, 40);
        initialX = x;
        initialY = y;
        vision1 = new Rectangle(getX(), getY(), getWidth(), getHeight());
        vision2 = new Rectangle(getX(), getY(), getWidth(), getHeight());

        setUpAnimation();
    }

    private void setUpAnimation() throws SlickException {
        Image image = new Image(SetupGame.path + "corona_left.PNG");

        imageLeft = new SpriteSheet(SetupGame.path + "corona_left.PNG", 50, 50);
        ;
        imageRight = new SpriteSheet(SetupGame.path + "corona_right.PNG", 50, 50);
        ;

        imageLeftNoticed = new SpriteSheet(SetupGame.path + "corona_left_noticed.PNG", 50, 50);
        ;
        imageRightNoticed = new SpriteSheet(SetupGame.path + "corona_right_noticed.PNG", 50, 50);


        animationLeft = new Animation(imageLeft, 100);
        animationLeft.setPingPong(true);

        animationRight = new Animation(imageRight, 100);
        animationRight.setPingPong(true);

        animationLeftNoticed = new Animation(imageLeftNoticed, 100);
        animationLeftNoticed.setPingPong(true);

        animationRightNoticed = new Animation(imageRightNoticed, 100);
        animationRightNoticed.setPingPong(true);

    }

    public Animation getAnimation(Graphics graphics) {
        if (babkaNoticed == false) {
            if (goRight) {
                return animationRight;
            } else {
                return animationLeft;
            }
        } else {
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


    public void createVision(ArrayList<Rectangle> obstacles) {
        vision1.setX(getX());
        vision1.setY(getY());
        vision1.setWidth(getWidth() / 2);
        vision1.setHeight(getHeight());

        boolean visionCollided = false;
        for (int i = 0; i < getCenterX(); i++) {
            vision1.setX(vision1.getX() - 1);
            vision1.setWidth(vision1.getWidth() + 1);
            for (int a = 0; a < obstacles.size(); a++) {
                if (vision1.intersects(obstacles.get(a))) {
                    visionCollided = true;
                    break;
                }
            }
            if (visionCollided) break;
        }

        vision2.setX(getCenterX());
        vision2.setY(getY());
        vision2.setWidth(getWidth() / 2);
        vision2.setHeight(getHeight());

        visionCollided = false;
        for (int i = 0; i < SetupGame.width - getCenterX(); i++) {
            vision2.setWidth(vision2.getWidth() + 1);
            for (int a = 0; a < obstacles.size(); a++) {
                if (vision2.intersects(obstacles.get(a))) {
                    visionCollided = true;
                    break;
                }
            }
            if (visionCollided) break;
        }
        if (defaultVision) {
            visionHorizontalLeft = vision1.getWidth();
            visionHorizontalRight = vision2.getWidth();
            notVisionHorizontalLeft = vision1.getWidth();
            notVisionHorizontalRight = vision2.getWidth();
        }

    }

    public Rectangle getVision1() {
        return vision1;
    }

    public Rectangle getVision2() {
        return vision2;
    }

    private void move() {
        if (babkaNoticed == false) {

            if (goRight) {
                if (this.getX() >= initialX + space || blockedRight) {
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


            if (this.getX() < babkaX && blockedRight == false) {
                this.setCenterX(getCenterX() + 2);
                goRight = true;
            } else if (this.getX() > babkaX && blockedLeft == false) {
                this.setCenterX(getCenterX() - 2);
                goRight = false;
            }


        }
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }


    public void checkForCollisionWall(Rectangle platform) {
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

    public void checkForCollisionBabka(Rectangle platform) {

        Rectangle leg = new Rectangle(this.getX(), this.getY() + this.width, width, 1);

        if (leg.intersects(platform)) {
            blockedDown = true;
        }


        Rectangle arm1 = new Rectangle(this.getX(), this.getY() + 1, 1, height - 2);
        if ((arm1.intersects(platform))) {
            blockedLeft = true;
        }

        Rectangle arm2 = new Rectangle(this.getX() + this.getWidth()-1, this.getY() + 1, 1, height - 2);
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

       Rectangle arm1B = new Rectangle(this.getX() - visionHorizontalLeft, this.getY() - visionVerticalUp, visionHorizontalLeft, visionVerticalUp + this.getHeight() + visionVerticalDown);
        Rectangle arm2B = new Rectangle(this.getX() + this.getWidth(), this.getY() - visionVerticalUp, visionHorizontalRight, visionVerticalUp + this.getHeight() + visionVerticalDown);

        if (arm1B.intersects(platform) && goRight == false || arm2B.intersects(platform) && goRight) {
            babkaNoticed = true;


        } else {
            babkaNoticed = false;

        }

        Rectangle headDie = new Rectangle(this.getX() + 8, this.getY(), width - 16, 1);
        if (headDie.intersects(platform)) {
            die();
        }

    }


    public void setVisionHorizontal(float visionHorizontalLeft, float visionHorizontalRight, float notVisionHorizontalLeft, float notVisionHorizontalRight) {
        this.visionHorizontalLeft = visionHorizontalLeft;
        this.visionHorizontalRight = visionHorizontalRight;
        this.notVisionHorizontalLeft = notVisionHorizontalLeft;
        this.notVisionHorizontalRight = notVisionHorizontalRight;
        defaultVision = false;

    }

    public void setVisionVertical(float visionVerticalUp, float visionVerticalDown, float notVisionVerticalUp, float notVisionVerticalDown) {
        this.visionVerticalUp = visionVerticalUp;
        this.visionVerticalDown = visionVerticalDown;
        this.notVisionVerticalUp = notVisionVerticalUp;
        this.notVisionVerticalDown = notVisionVerticalDown;
    }


    public void setSpace(float space) {
        this.space = space;
    }


}
