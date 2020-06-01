package Game.enemies;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class RecImage extends Rectangle {
    /**
     * Create a new bounding box
     *
     * @param x      The x position of the box
     * @param y      The y position of the box
     * @param width  The width of the box
     * @param height The hieght of the box
     */


    private String path = "/Users/dgoptsii/Game copy/pictures/";
    private Image image  = new Image(path + "door.jpg");;

    public RecImage(float x, float y, float width, float height) throws SlickException {
        super(x, y, width, height);
    }

    public void setImage(Image image){
        this.image = image;
    }
    public void draw(Graphics graphics){

    }


}
