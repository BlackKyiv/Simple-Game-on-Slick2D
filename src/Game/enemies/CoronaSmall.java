
package Game.enemies;

import Game.SetupGame;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class CoronaSmall extends Rectangle implements Enemy {

    private boolean blockedLeft = false;
    private boolean blockedRight = false;
    private boolean blockedUp = false;
    private boolean blockedDown = false;

    private float babkaX;
    private float babkaY;
    private float speed;


    private boolean alive = true;

    private boolean goRight;

    private SpriteSheet imageLeftNoticed;
    private SpriteSheet imageRightNoticed;
    private Animation animationLeftNoticed;
    private Animation animationRightNoticed;


    public CoronaSmall (float x, float y) throws SlickException {
        super(x, y, 25, 25);
        setUpAnimation();
    }

    private void setUpAnimation() throws SlickException{


        imageLeftNoticed = new SpriteSheet(SetupGame.path + "corona_left_noticed.PNG", 50, 50);;
        imageRightNoticed = new SpriteSheet(SetupGame.path + "corona_right_noticed.PNG", 50, 50);

        animationLeftNoticed = new Animation(imageLeftNoticed,100);
        animationLeftNoticed.setPingPong(true);

        animationRightNoticed = new Animation(imageRightNoticed,100);
        animationRightNoticed.setPingPong(true);

    }

    public  Animation  getAnimation(Graphics graphics){
        if (goRight) {
            return animationRightNoticed;
        } else {
            return animationLeftNoticed;
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

        if (this.getX() < babkaX ) {
            this.setCenterX(getCenterX() + speed);
            goRight=true;

        } if (this.getX() > babkaX ) {
            this.setCenterX(getCenterX() - speed);
            goRight=false;
        }
        if (this.getY() > babkaY  ) {
            this.setCenterY(getCenterY() - speed);
        } if (this.getY() < babkaY ) {
            this.setCenterY(getCenterY() + speed);

        }

    }

    public void die() {

        SetupGame.coronaSound.play();
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void checkForCollisionWall(Rectangle platform) {}
    public void checkForCollisionBabka(Rectangle platform) {
        babkaX = platform.getCenterX();
        babkaY = platform.getCenterY();

        Rectangle headDie = new Rectangle(this.getX() + 8, this.getY(), width - 16, 1);
        if (headDie.intersects(platform)) {
           // die();
        }

    }



    public void setSpeed(float speed) {
        this.speed=speed;
    }


}
