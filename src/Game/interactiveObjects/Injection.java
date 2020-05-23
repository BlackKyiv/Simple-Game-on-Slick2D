package Game.interactiveObjects;

import Game.SetupGame;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;



public class Injection extends Rectangle implements Bullet {

    private boolean collided = false;

    private float speed=3;
    private boolean doctor;
    boolean reflected=false;


    private boolean right = false;
    private boolean present = true;
    private Image imageBlueLeft;
    private Image imageBlueRight;
    private Image imageRedLeft;
    private Image imageRedRight;



    public Injection(int x, int y) throws SlickException {
        super(x, y, 30, 10);
        setUpImage();
    }

    private void setUpImage() throws SlickException {
        imageBlueLeft = new Image (SetupGame.path + "injection_blue_left.PNG");;
        imageBlueRight  = new Image (SetupGame.path + "injection_blue_right.PNG");;

        imageRedLeft = new Image (SetupGame.path + "injection_red_left.PNG");;
        imageRedRight = new Image (SetupGame.path + "injection_red_right.PNG");;
    }
    public Image getImageInjection(Graphics graphics) {
    if (doctor) {
    if (right) {
        return imageBlueRight;
    } else {
        return imageBlueLeft;
    }
}
else{
    if (right) {
        return imageRedRight;
    } else {
        return imageRedLeft;
    }
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
        reflected=true;
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
    public  boolean isReflected(){
        return reflected;
    }

    public void setDoctor(boolean doctor){
        this.doctor=doctor;
    }


}