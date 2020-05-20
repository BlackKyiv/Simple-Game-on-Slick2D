package Game;

import org.newdawn.slick.geom.Rectangle;

public interface Enemy {
    public void checkForCollision(Rectangle platform, boolean isBabka, boolean notice);
    public boolean isAlive();
    public void die();
}
