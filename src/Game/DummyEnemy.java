package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class DummyEnemy extends Rectangle {

    private boolean alive = true;

    public DummyEnemy(float x, float y, float width, float height) {
        super(x, y, width, height);
    }


    public boolean isAlive(){
        return alive;
    }

    public void die(){
        alive = false;
    }

}
