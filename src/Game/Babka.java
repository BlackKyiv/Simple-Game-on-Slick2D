package Game;

import Game.interactiveObjects.TapokThrow;
import Game.interactiveObjects.Teleport;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import javax.print.DocFlavor;

public class Babka extends Rectangle {
    private float speedX = 0;
    private float speedY = 0;

    private float speed = 5f;
    private float jump = 8;

    private boolean alive = true;
    private boolean mortal = true;


    private float deltaSeconds = 0.1666666f;
    private float timeCoeff = 1;
    private float gravity = 9.89f;
    private boolean landed = false;
    private Rectangle landTangle = null;
    private boolean blockedLeft = false;
    private boolean blockedRight = false;
    private boolean inTeleport = false;

    private boolean walkingLeft = false;
    private boolean walkingRight = false;
    private boolean standingLeft = false;
    private boolean standingRight = true;
    private boolean fightingLeft = false;
    private boolean fightingRight = false;

    private Animation animationStandLeft;
    private Animation animationStandRight;

    private Animation animationWalkingLeft;
    private Animation animationWalkingRight;

    private Animation animationSlidingLeft;
    private Animation animationSlidingRight;

    private Animation animationJumpingLeft;
    private Animation animationJumpingRight;

    private Animation dead;

    private Timer attackTimerLeft;
    private Timer attackTimerRight;

    private Animation animationFightingLeft;
    private Animation animationFightingRight;


    private float attackZoneSizeX = 50;
    private float attackZoneSizeY = 80;

    private int tapokQ = 0;


    public Babka(float x, float y) throws SlickException {
        super(x, y,50,50);
        setupAnimation();
        attackTimerLeft = new Timer(200);
        attackTimerRight = new Timer(200);
    }


    public void update(float timeCoeff, int delta){
        this.timeCoeff = timeCoeff;
        this.attackTimerLeft.update(delta*timeCoeff);
        this.attackTimerRight.update(delta*timeCoeff);
        if(!isLanded()) move(speedX*timeCoeff, speedY*timeCoeff);
        gravityPull();


        if(walkingLeft||standingLeft){
            standingLeft =true;
            standingRight = false;
        }
        else if(walkingRight||standingRight){
            standingLeft =false;
            standingRight = true;
        }
        blockedLeft = false;
        blockedRight = false;

        walkingRight = false;
        walkingLeft = false;

        fightingLeft = false;
        fightingRight = false;

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
        if(isAlive()) {
            if ((gameContainer.getInput().isKeyPressed(Input.KEY_SPACE) || gameContainer.getInput().isKeyPressed(Input.KEY_W)) && (landed || blockedRight || blockedLeft)) {
                if (blockedLeft) {
                    move(3, 0);
                    speedX = speed * 1.5f;
                }
                if (blockedRight) {
                    move(-3, 0);
                    speedX = -speed * 1.5f;
                }
                this.move(0, -1);
                setLanded(false);
                speedY = -jump;
            }
            if (gameContainer.getInput().isKeyDown(Input.KEY_A) && !blockedLeft) {
                if (landed) {
                    this.move(-speed * timeCoeff, 0);
                    walkingLeft = true;
                    standingLeft = false;
                    standingRight = false;
                } else {
                    speedX = -speed;
                }


            }
            if (gameContainer.getInput().isKeyDown(Input.KEY_D) && !blockedRight) {
                if (landed) {
                    this.move(speed * timeCoeff, 0);
                    walkingRight = true;
                    standingLeft = false;
                    standingRight = false;
                } else {
                    if (timeCoeff < 1) speedX = speed + speed * timeCoeff;
                    else speedX = speed;
                }
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

        Rectangle head = new Rectangle(this.getCenterX()-1, this.getY(), 2, 1);
        if(head.intersects(platform) && speedY<0){
            speedY = 0;
            this.setY(platform.getY()+platform.getHeight());
        }

        Rectangle arm1 = new Rectangle(this.getX()-1, this.getY()+1,1,2);
        Rectangle arm2 = new Rectangle(this.getX()-1, this.getY()+this.getHeight()-3,1,2);

        if((arm1.intersects(platform)||arm2.intersects(platform))&&!head.intersects(platform)){
            blockedLeft = true;
            this.setX(platform.getWidth()+platform.getX());
            if(speedX < 0) speedX = 0;
            else speedY *= 0.9;
        }

        Rectangle arm3 = new Rectangle(this.getX()+getWidth()+1, this.getY()+1,1,2);
        Rectangle arm4 = new Rectangle(this.getX()+getWidth()+1, this.getY()+this.getHeight()-3,1,2);

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

    private void setupAnimation() throws SlickException {

        dead = new Animation(new SpriteSheet(SetupGame.path+"babka_dead_right.png",50,50),100);

        animationSlidingLeft = new Animation(new SpriteSheet(SetupGame.path+"babka_climb_right.PNG",50,50), 100);
        animationSlidingRight = new Animation(new SpriteSheet(SetupGame.path+"babka_climb_left.PNG",50,50), 100);


        animationStandLeft = new Animation(new SpriteSheet(SetupGame.path+"babka_stand_right.PNG",50,50), 100);
        animationStandRight = new Animation(new SpriteSheet(SetupGame.path+"babka_stand_left.PNG",50,50), 100);

        animationWalkingLeft = new Animation(new SpriteSheet(SetupGame.path+"babka_go_left.PNG", 50,50), 100);
        animationWalkingLeft.setPingPong(true);

        animationWalkingRight = new Animation(new SpriteSheet(SetupGame.path+"babka_go_right.PNG", 50,50), 100);
        animationWalkingRight.setPingPong(true);

        animationJumpingLeft = new Animation(new SpriteSheet(SetupGame.path+"babka_jump_left.PNG", 50,50), 100);
        animationJumpingRight = new Animation(new SpriteSheet(SetupGame.path+"babka_jump_right.PNG", 50,50), 100);

        animationFightingLeft = new Animation(new SpriteSheet(SetupGame.path+"babka_hit_left.PNG", 75,50), 200);
        animationFightingRight = new Animation(new SpriteSheet(SetupGame.path+"babka_hit_right.PNG", 75,50), 100);
    }

    public Animation getAnimation() throws SlickException {

        if(!isAlive()){
            return dead;
        }


        if(attackTimerLeft.isRunning()){
            return animationFightingLeft;
        }
        else if(attackTimerRight.isRunning()){
            return animationFightingRight;
        }
        if(blockedLeft){
            return animationSlidingLeft;
        }
        else if(blockedRight){
            return animationSlidingRight;
        }
        if(landed) {
            if(standingRight){
                return animationStandLeft;
            }
            else if(standingLeft){
                return animationStandRight;
            }
            else if (walkingLeft) {
                return animationWalkingRight;
            }
            else if(walkingRight){
                return animationWalkingLeft;
            }

        }
        else {
            if(speedX==0){
                if (standingRight){
                    return animationJumpingRight;
                }else{
                    return animationJumpingLeft;
                }
            }
            if(speedX>0){
                return animationJumpingRight;
            }
            else {
                return animationJumpingLeft;
            }
        }
        return animationSlidingLeft;
    }

    public boolean isAlive(){
        return alive && mortal;
    }

    public void die(){
        alive = false;
    }

    public void setFightingLeft(boolean fighting){
        if(fighting) {
            this.fightingLeft = fighting;
            attackTimerLeft.restart();
            standingRight = false;
            standingLeft = true;
        }
        attackTimerLeft.start();
    }

    public void setFightingRight(boolean fighting){
        if(fighting) {
            attackTimerRight.restart();
            attackTimerRight.start();
            standingRight = true;
            standingLeft = false;
        }
        this.fightingRight = fighting;
    }

    public Rectangle getHitZone(GameContainer container) {
        if (isAlive()) {
            if (container.getInput().isKeyDown(Input.KEY_F)&&container.getInput().isKeyPressed(Input.KEY_F)) {
                SetupGame.hitSound.play();
                if (speedX == 0) {
                    if (standingRight||walkingRight) {
                        this.setFightingRight(true);
                        return new Rectangle(getX(), getY() - 15, attackZoneSizeX + getWidth(), attackZoneSizeY);
                    } else {
                        this.setFightingLeft(true);
                        return new Rectangle(getX() - attackZoneSizeX, getY() - 15, attackZoneSizeX + getWidth(), attackZoneSizeY);
                    }
                }
                if (speedX > 0) {
                    this.setFightingRight(true);
                    return new Rectangle(getX(), getY() - 15, attackZoneSizeX + getWidth(), attackZoneSizeY);
                } else  {
                    this.setFightingLeft(true);
                    return new Rectangle(getX() - attackZoneSizeX, getY() - 15, attackZoneSizeX + getWidth(), attackZoneSizeY);
                }
            }
            if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
                if (container.getInput().getMouseX() > this.getX()) {
                    this.setFightingRight(true);
                    return new Rectangle(getX(), getY()-15, attackZoneSizeX+getWidth(), attackZoneSizeY);

                } else if (container.getInput().getMouseX() < this.getX()) {
                    this.setFightingLeft(true);
                    return new Rectangle(getX() - attackZoneSizeX, getY()-15, attackZoneSizeX+getWidth(), attackZoneSizeY);
                }
            }
        }
        return new Rectangle(-attackZoneSizeX, -attackZoneSizeY, attackZoneSizeX, attackZoneSizeY);
    }

    public boolean isReadyToShoot(GameContainer container){
        return isAlive()&&(tapokQ>=1)&&(container.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)||
                (container.getInput().isKeyPressed(Input.KEY_E) && container.getInput().isKeyDown(Input.KEY_E)));
    }

    public boolean isReadyToPickTapok(){
        if(tapokQ < 2 && isAlive()) return true;
        return false;
    }

    public TapokThrow shoot(GameContainer container) throws SlickException {
        tapokQ--;
        SetupGame.throwSound.play();
        if(container.getInput().isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)){
            if(container.getInput().getMouseX()<getCenterX()){
                TapokThrow tapokThrow = new TapokThrow(getCenterX(), getCenterY()-20);
                tapokThrow.setLeft();
                return tapokThrow;
            }
            else {
                TapokThrow tapokThrow = new TapokThrow(getCenterX(), getCenterY()-20);
                tapokThrow.setRight();
                return tapokThrow;
            }
        }
        else {
            if(blockedRight){
                TapokThrow tapokThrow = new TapokThrow(getCenterX(), getCenterY()-20);
                tapokThrow.setLeft();
                return tapokThrow;
            }
            if(blockedLeft){
                TapokThrow tapokThrow = new TapokThrow(getCenterX(), getCenterY()-20);
                tapokThrow.setRight();
                return tapokThrow;
            }
            if(blockedRight || standingLeft || walkingLeft || speedX <0){
                TapokThrow tapokThrow = new TapokThrow(getCenterX(),getCenterY()-20);
                tapokThrow.setLeft();
                return tapokThrow;
            }
            else{
                TapokThrow tapokThrow = new TapokThrow(getCenterX(), getCenterY()-20);
                tapokThrow.setRight();
                return tapokThrow;
            }
        }

    }

    public void pickTapok(){
        SetupGame.pickSound.play(1, 0.3f);
        tapokQ++;
    }
    public void pickTapok(int q){
        tapokQ+=q;
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

    public boolean animationSlide(){
        if(attackTimerLeft.isRunning()) {
            return true;
        }else{
            return false;
        }
    }


    public int tapkiLeft(){
        return  tapokQ;
    }

    public void setStandingLeft(boolean standingLeft) {
        this.standingLeft = standingLeft;
    }
}
