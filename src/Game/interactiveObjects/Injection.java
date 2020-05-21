package Game.interactiveObjects;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Injection extends Rectangle implements Bullet {

    private boolean blockedLeft = false;

    private boolean collided = false;

    private float initialX;
    private float initialY;
    private float speed=3;


    private boolean right = false;
    private boolean present = true;


    public Injection(int x, int y) throws SlickException {
        super(x, y, 20, 10);
        initialX = x;
        initialY = y;
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
    public void checkCollisions() {

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

    public void reflect(){
        if (right){
            right=false;
        }else{
            right=true;
        }
    }

    @Override
    public  boolean collided(){
        return collided;
    }



}