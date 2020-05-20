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

    private float initialX;
    private float initialY;
    private boolean goRight=true;
    private boolean babkaNoticed=false;
    float babkaX;
    float babkaY;





    public Coronavirus (float x, float y, float width, float height) throws SlickException {
        super(x, y, width, height);
        initialX=x;
        initialY=x;
    }



    public void update(){

            move( speedY);

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

    private void move(float y) {
        if (babkaNoticed = false) {
            if (landed) {
                if (goRight) {
                    if (this.getX() >= initialX + 150 || blockedRight) {
                        goRight = false;
                    } else {
                        this.setCenterX(getCenterX() + 1);
                    }
                } else {
                    if (this.getX() <= initialX - 150 || blockedLeft) {
                        goRight = true;
                    } else {
                        this.setCenterX(getCenterX() - 1);
                    }
                }

            } else {
                initialX = this.getX();
                this.setCenterX(getCenterX() + 0);
            }
            this.setCenterY(getCenterY() + y);

        }

        ///// babka noticed
        else {
            if (this.getX() < babkaX&&blockedRight==false) {
                this.setCenterX(getCenterX() + 1);
            } else if (this.getX() > babkaX&&blockedLeft==false){
                this.setCenterX(getCenterX() - 1);
            }
            if (this.getY() < babkaY) {
                this.setCenterY(getCenterY() + 1);
            } else {
                this.setCenterY(getCenterY() - 1);
            }
        }
    }





    public void checkForCollision(Rectangle platform, boolean isBabka){
        if (isBabka){

            babkaX=platform.getCenterX();
          babkaY=platform.getCenterY();
            Rectangle arm1 = new Rectangle(this.getX()-50, this.getY()+1, 50, 1);
            Rectangle arm2 = new Rectangle(this.getX()-50, this.getY() + this.getHeight() - 2, 50, 1);

            if ((arm1.intersects(platform) || arm2.intersects(platform)) && landed) {
                blockedLeft = true;
                babkaNoticed=true;
            }


            Rectangle arm3 = new Rectangle(this.getX() + getWidth() +50, this.getY()+1 , 50, 1);
            Rectangle arm4 = new Rectangle(this.getX() + getWidth() +50, this.getY() + this.getHeight() - 2, 50, 1);

            if (arm3.intersects(platform) || arm4.intersects(platform) && landed) {
                blockedRight = true;
                babkaNoticed=true;
            }




        }


            Rectangle leg1 = new Rectangle(this.getX(), this.getY() + this.getHeight(), 1, 1);
            Rectangle leg2 = new Rectangle(this.getX() + this.width - 1, this.getY() + this.getHeight(), 1, 1);

            if (leg1.intersects(platform) || leg2.intersects(platform) && landTangel != null && landTangel.equals(platform)) {
                this.setY(platform.getY() - this.getHeight());
                setLanded(true);
                landTangel = platform;
            } else if (landTangel != null && landTangel.equals(platform)) {
                setLanded(false);
            }


            Rectangle arm1 = new Rectangle(this.getX()-1, this.getY()+1, 1, 1);
            Rectangle arm2 = new Rectangle(this.getX()-1, this.getY() + this.getHeight() - 2, 1, 1);

            if ((arm1.intersects(platform) || arm2.intersects(platform)) && landed) {
                blockedLeft = true;
            }


            Rectangle arm3 = new Rectangle(this.getX() + getWidth() +1, this.getY()+1 , 1, 1);
            Rectangle arm4 = new Rectangle(this.getX() + getWidth() +1, this.getY() + this.getHeight() - 2, 1, 1);

            if (arm3.intersects(platform) || arm4.intersects(platform) && landed) {
                blockedRight = true;
            }


    }

    public void setLanded(boolean landed){
        this.landed = landed;
        if(landed) {
             speedY = 0;
        }
        else landTangel = null;
    }

}