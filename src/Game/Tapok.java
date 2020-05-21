package Game;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Tapok extends Rectangle {

    private boolean picked = false;


    public Tapok(int x, int y) throws SlickException {
        super(x, y, 20, 20);

    }


    public void update() {

    }

    public void setPicked(){
        picked = false;
    }

    public void checkCollisions(Rectangle platform) {

        Rectangle leg = new Rectangle(this.getX(), this.getY() + this.width, width, 1);

        if (leg.intersects(platform)) {
            setPicked();
        }


        Rectangle arm1 = new Rectangle(this.getX(), this.getY() + 1, 1, height - 2);
        if ((arm1.intersects(platform))) {
            setPicked();
        }

        Rectangle arm2 = new Rectangle(this.getX() + this.getWidth(), this.getY() + 1, 1, height - 2);
        if ((arm2.intersects(platform))) {
            setPicked();
        }

        Rectangle head = new Rectangle(this.getX(), this.getY(), width, 1);
        if (head.intersects(platform)) {
            setPicked();
        }

    }

}