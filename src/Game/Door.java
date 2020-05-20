package Game;

import org.newdawn.slick.geom.Rectangle;

public class Door extends Rectangle {

    private boolean broken = false;

    public Door(float x, float y, float width, float height) {
        super(x, y, width, height);
    }


    public boolean isBroken(){
        return broken;
    }

    public void broke(){
        broken = true;
    }

}
