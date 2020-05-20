package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Coronavirus extends Rectangle {
    private float speedX = 0;
    private float speedY = 0;

    private float speed = 7f;

    private float time = 0.1666666f;
    private float gravity = 9.89f;
    private boolean landed = false;
    private Rectangle landTangel = null;
    private boolean blockedLeft = false;
    private boolean blockedRight = false;



    public Coronavirus (float x, float y, float width, float height) throws SlickException {
        super(x, y, width, height);
    }



    public void update(){
        if(!isLanded()) move(speedX, speedY);
        gravityPull();

        blockedLeft = false;
        blockedRight = false;
    }

    public int getSpeedY(){
        return (int) speedY;
    }
    public int getSpeedX(){return  (int) speedX;}

    private void gravityPull(){
        if(!landed){
            speedY += gravity * Math.pow(time, 2);
        }

    }

    private void move(float x, float y){

        this.setCenterX(getCenterX() + x);
        this.setCenterY(getCenterY() + y);
    }


    public void controls(GameContainer gameContainer){
        if(gameContainer.getInput().isKeyPressed(Input.KEY_SPACE)&&(landed||blockedRight||blockedLeft)){
            if(blockedLeft) {
                move(1, 0);
            }
            if(blockedRight) {
                move(-1, 0);

            }
            this.move(0,-1);
            setLanded(false);
            speedY = -5;
            speedY = -7;
        }
        if(gameContainer.getInput().isKeyDown(Input.KEY_A)&&!blockedLeft){
            if(landed) {
                this.move(-speed, 0);
            }
            else speedX = -speed;

        }
        if(gameContainer.getInput().isKeyDown(Input.KEY_D)&&!blockedRight){
            if(landed) {
                this.move(speed, 0);
            }
            else speedX = speed;
        }
    }

    public boolean isLanded(){
        return landed;
    }

    public void checkForCollision(Rectangle platform){
        Rectangle leg1 = new Rectangle(this.getCenterX(), this.getY()+this.getHeight(),1,1);
        Rectangle leg2 = new Rectangle(this.getCenterX(), this.getY()+this.getHeight(),1,1);

        if(leg1.intersects(platform)||leg2.intersects(platform) && landTangel != null && landTangel.equals(platform)){
            this.setY(platform.getY()-this.getHeight() );
            setLanded(true);
            landTangel = platform;
        }
        else if(landTangel != null && landTangel.equals(platform)){
            setLanded(false);
        }

        Rectangle head = new Rectangle(this.getCenterX()-1, this.getY(), 1, 1);
        if(head.intersects(platform) && speedY<0){
            speedY = 0;
            this.setY(platform.getY()+platform.getHeight());
        }




        Rectangle arm1 = new Rectangle(this.getX()-1, this.getY()+2,1,1);
        Rectangle arm2 = new Rectangle(this.getX()-1, this.getY()+this.getHeight()-2,1,1);

        if((arm1.intersects(platform)||arm2.intersects(platform))&&!head.intersects(platform)){
            blockedLeft = true;
            this.setX(platform.getWidth()+platform.getX());
            if(speedX < 0) speedX = 0;
            else speedY *= 0.9;
        }


        Rectangle arm3 = new Rectangle(this.getX()+getWidth()+1, this.getY()+2,1,1);
        Rectangle arm4 = new Rectangle(this.getX()+getWidth()+1, this.getY()+this.getHeight()-2,1,1);

        if(arm3.intersects(platform)||arm4.intersects(platform)&&!head.intersects(platform)){
            blockedRight = true;
            this.setX(platform.getX()-this.getWidth());
            if(speedX > 0) speedX = 0;
            else speedY *= 0.9;

        }

    }

    public void setLanded(boolean landed){
        this.landed = landed;
        if(landed) {
            speedX = 0;
            speedY = 0;
        }
        else landTangel = null;
    }

}