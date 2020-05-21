
package Game;

import org.newdawn.slick.SlickException;
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


    public CoronaSmall (int x, int y) throws SlickException {
        super(x, y, 25, 25);
    }


    public void update() {
        move();
        blockedLeft = false;
        blockedRight = false;
        blockedDown = false;
        blockedLeft = false;
    }


    private void move() {
        if (this.getX() < babkaX && blockedRight == false) {
            this.setCenterX(getCenterX() + speed);

        } if (this.getX() > babkaX && blockedLeft == false) {
            this.setCenterX(getCenterX() - speed);
        }
        if (this.getY() > babkaY  && blockedUp == false) {
            this.setCenterY(getCenterY() - speed);
        } if (this.getY() < babkaY && blockedDown == false) {
            this.setCenterY(getCenterY() + speed);

        }

    }

    public void die() {
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
            die();
        }

    }



    public void setSpeed(float speed) {
        this.speed=speed;
    }


}
