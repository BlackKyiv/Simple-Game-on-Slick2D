package Game;

import Game.interactiveObjects.Teleport;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Babka extends Rectangle {
    private float speedX = 0;
    private float speedY = 0;

    private float speed = 7f;
    private float jump = 8;

    private float deltaSeconds = 0.1666666f;
    private float timeCoeff = 1;
    private float gravity = 9.89f;
    private boolean landed = false;
    private Rectangle landTangle = null;
    private boolean blockedLeft = false;
    private boolean blockedRight = false;
    private boolean inTeleport = false;



    public Babka(float x, float y, float width, float height) throws SlickException {
        super(x, y, width, height);

    }


    public void update(float timeCoeff){
        this.timeCoeff = timeCoeff;
        if(!isLanded()) move(speedX*timeCoeff, speedY*timeCoeff);
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
            speedY += timeCoeff*gravity * Math.pow(deltaSeconds, 2);
        }

    }

    private void move(float x, float y){

        this.setCenterX(getCenterX() + x);
        this.setCenterY(getCenterY() + y);
    }


    public void controls(GameContainer gameContainer){
        if(gameContainer.getInput().isKeyPressed(Input.KEY_SPACE)&&(landed||blockedRight||blockedLeft)){
            if(blockedLeft) {
                move(3, 0);
                speedX =speed*1.5f;
            }
            if(blockedRight) {
                move(-3, 0);
                speedX =-speed*1.5f;

            }
            this.move(0,-1);
            setLanded(false);
            speedY = -jump;
        }
        if(gameContainer.getInput().isKeyDown(Input.KEY_A)&&!blockedLeft){
            if(landed) {
                this.move(-speed*timeCoeff, 0);
            }
            else {
                speedX = -speed;
            }


        }
        if(gameContainer.getInput().isKeyDown(Input.KEY_D)&&!blockedRight){
            if(landed) {
                this.move(speed*timeCoeff, 0);
            }
            else{
                if(timeCoeff < 1 )speedX = speed +  speed*timeCoeff;
                else speedX = speed;
            }
        }
    }

    public boolean isLanded(){
        return landed;
    }

    public void checkForCollision(Rectangle platform){
        Rectangle leg1 = new Rectangle(this.getCenterX(), this.getY()+this.getHeight(),1,1);
        Rectangle leg2 = new Rectangle(this.getCenterX(), this.getY()+this.getHeight(),1,1);

        if(leg1.intersects(platform)||leg2.intersects(platform) && landTangle != null && landTangle.equals(platform)){
            this.setY(platform.getY()-this.getHeight() );
            setLanded(true);
            landTangle = platform;
        }
        else if(landTangle != null && landTangle.equals(platform)){
            setLanded(false);
        }

        Rectangle head = new Rectangle(this.getCenterX()-4, this.getY(), 5, 1);
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

    public void checkForCollisionWithInterior(Rectangle platform){
        Rectangle leg1 = new Rectangle(this.getCenterX(), this.getY()+this.getHeight(),1,1);
        Rectangle leg2 = new Rectangle(this.getCenterX(), this.getY()+this.getHeight(),1,1);

        if(leg1.intersects(platform)||leg2.intersects(platform) && landTangle != null && landTangle.equals(platform)){
            this.setY(platform.getY()-this.getHeight() );
            setLanded(true);
            landTangle = platform;
        }
        else if(landTangle != null && landTangle.equals(platform)){
            setLanded(false);
        }
    }

    public void setLanded(boolean landed){
        this.landed = landed;
        if(landed) {
            speedX = 0;
            speedY = 0;
        }
        else landTangle = null;
    }


    public void getAnimation(){

    }

    public void turnLeft(){

    }

    public void turnRight(){

    }

    public boolean inTeleport(Teleport teleport){
        inTeleport=false;
        if(this.getMinX()>=teleport.getMinX() && this.getMaxX()<=teleport.getMaxX()&& this.getMinY()>=teleport.getMinY() && this.getMaxY()<=teleport.getMaxY()){
            inTeleport=true;
        }
        return inTeleport;
    }

    public void goInTeleport(GameContainer gameContainer,Teleport teleport){
        if(inTeleport(teleport)&&gameContainer.getInput().isKeyPressed(Input.KEY_ENTER)){
            this.move(teleport.getDx(),teleport.getDy());
        }
    }




}
