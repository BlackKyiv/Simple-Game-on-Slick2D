package Game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MapLevel2 extends BasicGameState {
    private Babka babka;
    private Rectangle terrain;
    private Rectangle platform2;
    private Rectangle leftWall,rightWall,firstFloor,secondFloor,thirdFloor,roof;
    private Image background,wall,wallpaper,cellarwallpaper,workshopwallpaper,window,helicopter,turret1,turret2;
    private Image lift21, lift22, lift1,lift31, lift32, lift4;
    private SpriteSheet leftW,rightW,firstF,secondF,thirdF, roofF, wallpaper1;
    private Rectangle attackZone;
    private Coronavirus corona;
    private Doctor doctor;
    private Teleport stairsL0,liftL1,liftL2,stairsL2,stairsL3;

    private Door door;

    private static final int GROUND=80;
    private int wallW =10;
    private int floorH =190, floorW =900;
    private int x_offset=100;

    private String path = "C:\\Users\\atcat\\Documents\\Goptsii game 2\\Game\\pictures\\";

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        babka = new Babka(120, 600, 50, 50);

        initDoors();
        initEnemies();
        initAttackZone();
        initWalls();
    }

    private void initAttackZone(){
        attackZone = new Rectangle(-50, -50, 50, 50);
    }

    private void initWalls()throws SlickException {
        background = new Image(path+"backg.jpg");

        terrain = new Rectangle(0, SetupGame.height-10, SetupGame.width, 10);
        platform2 = new Rectangle(0, 0, 20, SetupGame.height);

        wall = new Image(path+"wall.jpg");
        leftWall = new Rectangle(x_offset- wallW,SetupGame.height- floorH *2-80, wallW, floorH *2);
        rightWall = new Rectangle(SetupGame.width-x_offset,SetupGame.height- floorH *2, wallW, floorH *2);
        firstFloor = new Rectangle(x_offset,SetupGame.height- wallW, floorW, wallW);
        secondFloor = new Rectangle(x_offset,SetupGame.height- floorH - wallW, floorW, wallW);
        thirdFloor = new Rectangle(x_offset,SetupGame.height- floorH *2- wallW *2, floorW, wallW);
        roof = new Rectangle(x_offset,SetupGame.height- floorH *3- wallW *3, floorW, wallW *2);

        leftW = new SpriteSheet(wall,10,10);
        rightW = new SpriteSheet(wall,10,10);
        firstF = new SpriteSheet(wall,10,10);
        secondF = new SpriteSheet(wall,10,10);
        thirdF = new SpriteSheet(wall,10,10);
        roofF = new SpriteSheet(wall,50,50);

        cellarwallpaper = new Image(path+"cellarwallpaper.png");
        workshopwallpaper = new Image(path+"workshopwallpaer.jpg");
        wallpaper = new Image(path+"wallpaper.jpg");
        wallpaper1 = new SpriteSheet(wallpaper,100,100);

        helicopter = new Image(path+"helicopter.png");
        window = new Image(path+"window.jpg");
        turret1 = new Image(path+"turret.png");
        turret2 = new Image(path+"turret.png");
    }

    private void initDoors()throws SlickException{
        lift22 = new Image(path+"lift.png");
        lift1 = new Image(path+"stairsLeft.png");
        lift32 = new Image(path+"stairsLeft.png");
        lift31 = new Image(path+"lift.png");
        lift4 = new Image(path+"stairsLeft.png");
    }
    private void initEnemies() throws SlickException {


    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        // drawEnenmies(graphics);//if dummy is alive then draw it
        //drawDoors(graphics);

        graphics.fill(platform2);
        graphics.fill(terrain);
        background.draw(0,0,1100,700);

        wall.startUse();
        for(int a=x_offset; a<SetupGame.width-x_offset; a+=10) {
            firstF.getSubImage(0, 20, 50, 50).drawEmbedded(a, SetupGame.height - wallW, 10, 10);
            firstF.getSubImage(0, 20, 50, 50).drawEmbedded(a, SetupGame.height- floorH - wallW, 10, 10);
            firstF.getSubImage(0,20,50,50).drawEmbedded(a,SetupGame.height- floorH *2- wallW *2,10,10);
            firstF.getSubImage(0,20,100,70).drawEmbedded(a,SetupGame.height- floorH *3- wallW *3,10,20);
        }
        for(int a = SetupGame.height; a>SetupGame.height-3* wallW -3* floorH; a-=10){
            leftW.getSubImage(0,20,50,50).drawEmbedded(x_offset- wallW,a,10,10);
            rightW.getSubImage(0,20,50,50).drawEmbedded(SetupGame.width-x_offset,a,10,10);
        }
        wall.endUse();

        workshopwallpaper.startUse();
        for(int a=x_offset; a<SetupGame.width-x_offset; a+=10){
            wallpaper1.getSubImage(0,0,333,850).drawEmbedded(a,SetupGame.height-floorH*3-wallW*2,10,floorH);
            wallpaper1.getSubImage(0,0,333,850).drawEmbedded(a,SetupGame.height-floorH*2-wallW,10,floorH);
        }
        workshopwallpaper.endUse();
        cellarwallpaper.draw(100,510,900,180);

        window.draw(700,350,150,70);
        window.draw(300,350,150,70);
        window.draw(700,150,150,70);
        window.draw(300,150,150,70);

        lift22.draw(140,365,80,135);
        lift1.draw(1000,530,-80,160);
        lift32.draw(1000,140,-80,160);
        lift31.draw(140,165,80,135);
        lift4.draw(920,45,80,60);

        turret1.draw(800,600,100,70);

        helicopter.draw(150,20,250,80);

        graphics.setColor(Color.pink);
        graphics.fill(babka);
    }

    private void drawDoors(Graphics graphics) {
        if(!door.isBroken()) {
            graphics.setColor(Color.blue);
            graphics.fill(door);
        }
    }

    private void drawEnenmies(Graphics graphics){
        if(corona.isAlive()) {
            graphics.setColor(Color.green);
            graphics.fill(corona);
        }
        if(doctor.isAlive()) {
            graphics.setColor(Color.cyan);
            graphics.fill(doctor);
        }

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {


        babka.update(1);
        babka.checkForCollision(terrain);
        babka.checkForCollision(platform2);
        babka.checkForCollision(rightWall);
        babka.checkForCollision(leftWall);
        babka.checkForCollision(firstFloor);
        babka.checkForCollision(secondFloor);
        babka.checkForCollision(thirdFloor);
        babka.checkForCollision(roof);
        babka.controls(gameContainer);
        checkForAttack(gameContainer);
        babka.teleport(gameContainer,200,330,80,135,0,100); //upper door
        babka.teleport(gameContainer,200,555,80,135,0,-300); //lower door
    }

    private void checkForAttack(GameContainer container){
        //if babka`s x > mouse x then draw attackZone on right
        //else if babka`s x < mouse x then draw attackZone on left+
        if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)||container.getInput().isKeyPressed(Input.KEY_F)){
            if(container.getInput().getMouseX()>babka.getX()){
                attackZone.setX(babka.getX()+babka.getWidth());
                attackZone.setY(babka.getY());
            }
            else if(container.getInput().getMouseX()<babka.getX()){
                attackZone.setX(babka.getX()-babka.getWidth());
                attackZone.setY(babka.getY());
            }
        }
        else {
            attackZone = new Rectangle(-50, -50, 50, 50);
        }


       /* if(attackZone.intersects(corona)) corona.die();
        if(attackZone.intersects(door)) door.broke();*/

    }


}
