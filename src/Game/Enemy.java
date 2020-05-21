package Game;

import org.newdawn.slick.geom.Rectangle;

public interface Enemy {
    public void checkForCollisionWall(Rectangle platform);
    public void checkForCollisionBabka(Rectangle platform);
    public boolean isAlive();
    public void die();

}
