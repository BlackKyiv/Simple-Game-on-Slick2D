package Game.interactiveObjects;

import org.newdawn.slick.geom.Rectangle;

public interface Bullet {
    boolean right = false;


    void setRight();

    void setLeft();


    void checkForCollision(Rectangle platform);

    void setSpeed( int speed);

    boolean collided();

    public void disappear();
    public boolean isPresent();

}

