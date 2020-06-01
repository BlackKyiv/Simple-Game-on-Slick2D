package Game.interactiveObjects;

import Game.SetupGame;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class TapokPick extends Rectangle{
    private boolean present = true;

    private SpriteSheet image;
    private Animation animation;



    public TapokPick(float x, float y) throws SlickException {
        super(x, y, 20, 10);

    setUpAnimation();
}

    private void setUpAnimation() throws SlickException{


        image = new SpriteSheet(SetupGame.path + "tapok.PNG", 20, 13);;
        animation = new Animation(image,100);
        animation.setPingPong(true);


    }

    public  Animation  getAnimation(Graphics graphics){
            return animation;
    }




    public void update() {

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
            present=false;
        }


        Rectangle arm1 = new Rectangle(this.getX(), this.getY() + 1, 1, height - 2);
        if ((arm1.intersects(platform))) {
            present=false;
        }

        Rectangle arm2 = new Rectangle(this.getX() + this.getWidth(), this.getY() + 1, 1, height - 2);
        if ((arm2.intersects(platform))) {
            present=false;
        }

        Rectangle head = new Rectangle(this.getX(), this.getY(), width, 1);
        if (head.intersects(platform)) {
            present=false;
        }


    }



}