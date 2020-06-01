package Game.enemies;

import Game.SetupGame;
import Game.interactiveObjects.Door;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import Game.Timer;
import java.util.ArrayList;
import java.util.Random;


public class Boss extends Rectangle implements Enemy {
    private float initialY;
    private boolean alive = true;
    private boolean goDown = false;
    private Timer zoneGap;
    private Timer zoneCreate;
    private boolean zonePresent;
    private  Rectangle zoneAttack = new Rectangle(0,0,0,0);

    private boolean zoneActive1=false;
    private boolean zoneActive2=false;
    private boolean zoneActive3=false;
    private boolean zoneActive4=false;
    private boolean zoneActive5=false;
    private boolean zoneActive6=false;
    private boolean zoneActive7=false;

    private boolean zoneAlive1=true;
    private boolean zoneAlive2=true;
    private boolean zoneAlive3=true;
    private boolean zoneAlive4=true;
    private boolean zoneAlive5=true;
    private boolean zoneAlive6=true;
    private boolean zoneAlive7=true;

    private boolean spawnActive=true;

    int zonesActive=5;
    int spawn =2;

    private Image boss0 = new Image (SetupGame.path+"boss0.png");
    private Image boss1 = new Image (SetupGame.path+"boss1.png");
    private Image boss2 = new Image (SetupGame.path+"boss2.png");
    private Image boss3 = new Image (SetupGame.path+"boss3.png");
    private Image boss4 = new Image (SetupGame.path+"boss4.png");
    private Image boss5 = new Image (SetupGame.path+"boss5.png");
    private Image boss6 = new Image (SetupGame.path+"boss6.png");
    private Image boss7 = new Image (SetupGame.path+"boss7.png");







    public Boss (int x, int y) throws SlickException {
        super(x, y, 500, 500);
        initialY=y;
        zoneGap = new Timer(2500);
        zoneGap.start();
        zoneCreate = new Timer(17000);
        zoneCreate.start();
        getHitZone();
    }

    public Image getImageBoss() {
        if (zoneActive1&&zonePresent) {
            return boss1;
        }else if (zoneActive2&&zonePresent){
            return boss2;
        }
        else if (zoneActive2&&zonePresent){
            return boss2;
        }
        else if (zoneActive3&&zonePresent){
            return boss3;
        }
        else if (zoneActive4&&zonePresent){
            return boss4;
        }
        else if (zoneActive5&&zonePresent){
            return boss5;
        }
        else if (zoneActive6&&zonePresent){
            return boss6;
        }
        else if (zoneActive7&&zonePresent){
            return boss7;
        }

        else{
            return boss0;
        }

    }





    public void update(int delta) {
        //System.out.println(zonesActive);
        if (zonesActive==0){
            die();
        }
        move();
        zoneGap.update(delta);
        zoneCreate.update(delta);
        if (zoneGap.isFinished()){
            zonePresent=true;
            if (zoneCreate.isFinished()){
                zoneActive1=false;
                zoneActive2=false;
                zoneActive3=false;
                zoneActive4=false;
                zoneActive5=false;
                zoneActive6=false;
                zoneActive7=false;

                getHitZone();
                zonePresent=false;
                zoneGap = new Timer(2500);
                zoneGap.start();
                zoneCreate = new Timer(17000);
                zoneCreate.start();
            }
        }


    }



    private void move() {
        if (this.getY()+this.getHeight()>=650){
            goDown=false;
        }
        if (this.getY()<=50){
            goDown=true;
        }
        if (goDown) {
            this.setCenterY(getCenterY() + 1);
            zoneAttack.setCenterY( zoneAttack.getCenterY() + 1);
        }else{
            this.setCenterY(getCenterY() - 1);
            zoneAttack.setCenterY( zoneAttack.getCenterY() - 1);
     }

    }

    public void die() {

        alive = false;
    }


    public void getHitZone() {

        Random rand = new Random();
        int zone = rand.nextInt(7);

        if (zone==0&&zoneAlive1){
            zoneAttack = new Rectangle(this.getX(), this.getY()+100, 100, 100);
            zoneActive1=true;
            spawnActive=true;
        }else if (zone==1&&zoneAlive2){
          zoneAttack =new  Rectangle(this.getX(), this.getY()+300, 100, 100);
            zoneActive2=true;
            spawnActive=true;
        }else if (zone==2&&zoneAlive3){
            zoneAttack =new Rectangle(this.getX()+this.getWidth()-100, this.getY()+100, 100, 100);
            zoneActive3=true;
            spawnActive=true;
        }else if (zone==3&&zoneAlive4){
            zoneAttack =new Rectangle(this.getX()+this.getWidth()-100, this.getY()+300, 100, 100);
            zoneActive4=true;
            spawnActive=true;
        }else if (zone==4&&zoneAlive5){
            zoneAttack =new Rectangle(this.getX()+this.getWidth()/2-50, this.getY(), 100, 100);
            zoneActive5=true;
            spawnActive=true;
        }else if (zone==5&&zoneAlive6){
            zoneAttack =new Rectangle(this.getX()+this.getWidth()/2-50, this.getY()+200, 100, 100);
            zoneActive6=true;
            spawnActive=true;
        }else if (zone==6&&zoneAlive7){
            zoneAttack =new Rectangle(this.getX()+this.getWidth()/2-50, this.getY()+400, 100, 100);
            zoneActive7=true;
            spawnActive=true;
        }else if (isAlive()){
            getHitZone();
        }
    }

    public Rectangle getZoneAttack(){
        return zoneAttack;

    }


    @Override
    public void checkForCollisionWall(Rectangle platform) {}

    @Override
    public void checkForCollisionBabka(Rectangle platform) {}

    public boolean isAlive() {
        return alive;
    }

    public boolean zonePresent() {
        return zonePresent;
    }

    public int livesLeft(){
        return zonesActive;
    }
    public void attacked(){
        if (zoneActive1){
            zonesActive--;
            zoneAlive1=false;
            zoneActive1=false;
            spawn+=1;
            return;
        }else if(zoneActive2){
            zonesActive--;
            zoneActive2=false;
            zoneAlive2=false;
            spawn+=2;
            return;
        }
        else if(zoneActive3){
            zonesActive--;
            zoneActive3=false;
            zoneAlive3=false;
            spawn+=1;
            return;
        }
        else if(zoneActive4){
            zonesActive--;
            zoneActive4=false;
            zoneAlive4=false;
            spawn+=2;
            return;
        }
        else if(zoneActive5){
            zonesActive--;
            zoneActive5=false;
            zoneAlive5=false;
            spawn+=1;
            return;
        }
        else if(zoneActive6){
            zonesActive--;
            zoneActive6=false;
            zoneAlive6=false;
            spawn+=2;
            return;
        }
        else if(zoneActive7){
            zonesActive--;
            zoneActive7=false;
            zoneAlive7=false;
            return;
        }
    }

    public ArrayList<CoronaSmall> spawnCorona() throws SlickException{
        ArrayList<CoronaSmall> coronas= new ArrayList<CoronaSmall>();
        int shift=0;
        Random r = new Random();
        if(isAlive()) {
            for (int i = 0; i < spawn; i++) {
                CoronaSmall c = new CoronaSmall(this.getCenterX() + r.nextInt(25)+shift, this.getCenterY() + shift + r.nextInt(50));
                c.setSpeed(4);
                coronas.add(c);
                shift += 25;
            }

        }
        spawnActive = false;
        return coronas;

    }
    public boolean spawnActive(){
        return spawnActive;
}



}
