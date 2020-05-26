package Game.interactiveObjects;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Teleport extends Rectangle {

    int dx,dy;
    /**
     * Create a new bounding box
     *
     * @param x      The x position of the box
     * @param y      The y position of the box
     * @param width  The width of the box
     * @param height The height of the box
     */
    public Teleport(int x, int y, int width, int height, int dx, int dy) throws SlickException {
        super(x, y, width, height);
        this.dx=dx;
        this.dy=dy;
    }

    public int getDx(){
        return dx;
    }

    public int getDy(){
        return dy;
    }
    
}
