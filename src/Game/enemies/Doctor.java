package Game.enemies;


import Game.SetupGame;
import Game.interactiveObjects.Injection;
import Game.Timer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;



public class Doctor extends Rectangle implements Enemy {

    private boolean blockedLeft = false;
    private boolean blockedRight = false;
    private boolean blockedUp = false;
    private boolean blockedDown = false;

    private Timer shootGap;


    private float initialX;
    private float initialY;
    private boolean goRight = true;
    private boolean babkaToRight;


    float babkaX;
    float babkaXPrevious=0;
    float babkaY;
    float babkaWidth;
    float babkaHeight;

    float visionHorizontalLeft;
    float visionHorizontalRight;
    float visionVerticalUp;
    float visionVerticalDown;

    float notVisionHorizontalLeft;
    float notVisionHorizontalRight;
    float notVisionVerticalUp;
    float notVisionVerticalDown;


    float space;
    float distance = 100;

    private boolean babkaNoticed = false;
    private boolean alive = true;
    private boolean notMoves;


    private SpriteSheet imageLeft;
    private SpriteSheet imageRight;
    private Animation animationLeft;
    private Animation animationRight;

    private SpriteSheet imageStandLeft;
    private SpriteSheet imageStandRight;
    private Animation animationStandLeft;
    private Animation animationStandRight;

    private SpriteSheet imageLeftNoticed;
    private SpriteSheet imageRightNoticed;
    private Animation animationLeftNoticed;
    private Animation animationRightNoticed;


    public Doctor(int x, int y) throws SlickException {
        super(x, y, 50, 80);
        initialX = x;
        initialY = y;

        shootGap = new Timer(1000);
        shootGap.start();

        setUpAnimation();
    }

    private void setUpAnimation() throws SlickException {

        imageLeft = new SpriteSheet(SetupGame.path + "doctor_left.PNG", 50, 80);;
        imageRight = new SpriteSheet(SetupGame.path + "doctor_right.PNG", 49, 80);;

        imageLeftNoticed = new SpriteSheet(SetupGame.path + "doctor_left_noticed.PNG", 50, 80);;
        imageRightNoticed = new SpriteSheet(SetupGame.path + "doctor_right_noticed.PNG", 50, 80);;

        imageStandLeft = new SpriteSheet(SetupGame.path + "doctor_stand_left.PNG", 50, 80);;
        imageStandRight = new SpriteSheet(SetupGame.path + "doctor_stand_right.PNG", 49, 80);;


        animationLeft = new Animation(imageLeft, 100);
        animationLeft.setPingPong(true);

        animationRight = new Animation(imageRight, 100);
        animationRight.setPingPong(true);

        animationLeftNoticed = new Animation(imageLeftNoticed, 100);
        animationLeftNoticed.setPingPong(true);

        animationRightNoticed = new Animation(imageRightNoticed, 100);
        animationRightNoticed.setPingPong(true);


        animationStandLeft = new Animation(imageStandLeft, 100);
        animationStandRight = new Animation(imageStandRight, 100);


    }

    public Animation getAnimation(Graphics graphics) {

       if (notMoves==false) {
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
       }else{

           if (goRight) {
               return animationStandRight;
           } else {
               return animationStandLeft;
           }
       }
       // return animationLeft;
    }

    public void update(int delta) {
        shootGap.update(delta);
        move();

        blockedLeft = false;
        blockedRight = false;
        blockedDown = false;
        blockedLeft = false;
        if (babkaX <= this.getCenterX()) {
            babkaToRight = false;
        } else {
            babkaToRight = true;
        }
    }


    private void move() {
       if (babkaXPrevious==babkaX-this.getX()&&babkaNoticed){
           notMoves = true;
           babkaXPrevious=babkaX-this.getX();
           if (this.getX() >= babkaX+babkaWidth) {
               goRight = false;
           } else {
               goRight = true;

           }
       }
        else {
           babkaXPrevious=babkaX;
           notMoves=false;
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
               babkaXPrevious=babkaX-this.getX();
               if ((babkaX>(this.getX()+this.getWidth())&&babkaX - (this.getX()+this.getWidth()) > notVisionHorizontalRight) ||(babkaX+babkaWidth<this.getX()&&this.getX() - (babkaX+babkaWidth) > notVisionHorizontalLeft)||(babkaY>(this.getY()+this.getHeight())&&babkaY - (this.getY()+this.getHeight()) > notVisionVerticalUp) ||(babkaY+babkaHeight<this.getY()&&this.getY() - (babkaY+babkaHeight) > notVisionVerticalDown)) {
                   babkaNoticed = false;
               }


               if (this.getX() + this.getWidth() + distance < babkaX || (this.getX() < babkaX + babkaWidth + distance && this.getX() > babkaX + babkaWidth) && blockedRight == false) {
                   this.setCenterX(getCenterX() + 2);
                   goRight = true;
               }
               if (this.getX() > babkaX + this.getWidth() + distance || (this.getX() + this.getWidth() + distance > babkaX && this.getX() + this.getWidth() < babkaX) && blockedLeft == false) {
                   this.setCenterX(getCenterX() - 2);
                   goRight = false;
               }


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

        Rectangle headDie = new Rectangle(this.getX() + 8, this.getY(), width - 16, 1);
        if (headDie.intersects(platform)) {
            die();
        }


    }


    public boolean isBabkaNoticed() {
        return babkaNoticed;
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

    public void setSpace(float space) {
        this.space = space;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Injection shoot(Rectangle target) throws SlickException {
        Injection injection;
        if (goRight) {
           injection = new Injection((int) (this.getX()+this.getWidth()-  30), (int) this.getCenterY());
        }else{
           injection = new Injection((int) this.getX(), (int) this.getCenterY());
        }
        if (target.getX() < this.getX()) injection.setLeft();
        else injection.setRight();
        injection.setPresent(true);
        injection.setDoctor(true);
        shootGap.restart();
        shootGap.start();
        return injection;
    }

    public boolean isReadyToShoot() {
        return isAlive() && isBabkaNoticed() && shootGap.isFinished() && notMoves;
    }

    public boolean babkaIsToRight() {
        return babkaToRight;
    }

}