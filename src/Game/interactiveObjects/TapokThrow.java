package Game.interactiveObjects;

import Game.SetupGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class TapokThrow extends Rectangle implements Bullet {

    private boolean collided = false;

    private float speed=3;


    private boolean right = false;
    private boolean present = true;
    private Image imageLeft;
    private Image imageRight;





    public TapokThrow(int x, int y) throws SlickException {
        super(x, y, 30, 10);
        setUpImage();
    }

    private void setUpImage() throws SlickException {
        imageLeft = new Image (SetupGame.path + "injection_tapok_left.PNG");;
        imageRight  = new Image (SetupGame.path + "injection_tapok_right.PNG");;

    }
    public Image getImageInjection(Graphics graphics) {

            if (right) {
                return imageRight;
            } else {
                return imageLeft;
            }


    }




    public void update() {
        move();
    }


    @Override
    public void setRight() {
        right=true;
    }

    @Override
    public void setLeft() {
        right=false;
    }

    private void move() {
        if (present) {
            if (right) {
                this.setCenterX(getCenterX() + speed);
            } else {
                this.setCenterX(getCenterX() - speed);
            }
        }
    }


    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void disappear(){
        present = false;
    }
    public boolean isPresent(){return present;}
    public void setPresent(boolean present){
        this.present=present;
    }
    @Override
    public void checkForCollision(Rectangle platform) {

        Rectangle leg = new Rectangle(this.getX(), this.getY() + this.width, width, 1);

        if (leg.intersects(platform)) {
            collided= true;
            present=false;
        }


        Rectangle arm1 = new Rectangle(this.getX(), this.getY() + 1, 1, height - 2);
        if ((arm1.intersects(platform))) {
            collided = true;
            present=false;
        }

        Rectangle arm2 = new Rectangle(this.getX() + this.getWidth(), this.getY() + 1, 1, height - 2);
        if ((arm2.intersects(platform))) {
            collided = true;
            present=false;
        }

        Rectangle head = new Rectangle(this.getX(), this.getY(), width, 1);
        if (head.intersects(platform)) {
            collided = true;
            present=false;
        }


    }


    @Override
    public  boolean collided(){
        return collided;
    }


}