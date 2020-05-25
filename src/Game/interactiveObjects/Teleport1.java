package Game.interactiveObjects;


import Game.Babka;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class Teleport1 extends Rectangle {
    private float exitX = 0;
    private float exitY = 0;

    public Teleport1(float x, float y, float width, float height, float exitX, float exitY){
        super(x, y, width, height);
        this.exitX = exitX;
        this.exitY = exitY;
    }


    public Babka teleport(Babka babka, GameContainer container){
        if(babka.intersects(this) && container.getInput().isKeyPressed(Input.KEY_ENTER)) {
            babka.setX(exitX);
            babka.setY(exitY);
        }
        else if(babka.intersects(new Rectangle(exitX, exitY, getWidth(), getHeight())) && container.getInput().isKeyPressed(Input.KEY_ENTER)) {
            babka.setX(getX());
            babka.setY(getY());
        }
        return babka;
    }
}
