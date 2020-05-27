package Game.enemies;

import Game.SetupGame;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;

public class Lava extends Rectangle implements Enemy {
    private boolean readyToGoUp = false;
    private float maxY = 0;
    private float minY = 0;

    private boolean alive = true;

    private float speed = 5;

    public Lava(float x, float y, float height) {
        super(x, y, SetupGame.width, height);
    }

    @Override
    public void checkForCollisionWall(Rectangle platform) {

    }

    @Override
    public void checkForCollisionBabka(Rectangle platform) {

    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void die() {
        alive = false;
    }
}
