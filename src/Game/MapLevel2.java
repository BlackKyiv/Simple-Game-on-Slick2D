package Game;

import Game.enemies.Coronavirus;
import Game.enemies.Doctor;
import Game.interactiveObjects.Door;
import Game.interactiveObjects.Teleport;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MapLevel2 extends BasicGameState {
    private Babka babka;
    private Rectangle terrain;
    private Rectangle platform;
    private Rectangle leftWallUp,leftWallDown,rightWall,firstFloor,secondFloor,thirdFloor,roof;
    private Image background,wall,wallpaper,cellarwallpaper,workshopwallpaper,window,helicopter,turret1,turret2;
    private Image lift21, lift22, lift1,lift31, lift32, lift4;
    private SpriteSheet wallSS,floorSS,wallpaper1,platform1,platform2;
    private Rectangle attackZone;
    private Coronavirus corona;
    private Doctor doctor;
    private Teleport tp1,tp21,tp22,tp31,tp32,tp4;

    private Door door1,door2;

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

        initWalls();
        initDoors();
        initEnemies();
        initAttackZone();
    }

    private void initAttackZone(){
        attackZone = new Rectangle(-50, -50, 50, 50);
    }

    private void initWalls()throws SlickException {
        background = new Image(path+"backg.jpg");

        terrain = new Rectangle(0, SetupGame.height-10, SetupGame.width, 10);
        platform = new Rectangle(0, 0, 20, SetupGame.height);

        wall = new Image(path+"wall.jpg");
        leftWallUp = new Rectangle(x_offset- wallW,110, wallW, 310);
        leftWallDown = new Rectangle(x_offset- wallW,SetupGame.height- floorH - wallW,wallW,110);
        rightWall = new Rectangle(SetupGame.width-x_offset,110, wallW, 590);
        firstFloor = new Rectangle(x_offset,SetupGame.height- wallW, floorW, wallW);
        secondFloor = new Rectangle(x_offset,SetupGame.height- floorH - wallW, floorW, wallW);
        thirdFloor = new Rectangle(x_offset,400, floorW, wallW);
        roof = new Rectangle(x_offset,100, floorW, wallW *2);

        wallSS = new SpriteSheet(wall,10,10);
        floorSS = new SpriteSheet(wall,10,10);

        cellarwallpaper = new Image(path+"cellarwallpaper.png");
        workshopwallpaper = new Image(path+"workshopwallpaer.jpg");
        wallpaper = new Image(path+"wallpaper.jpg");
        wallpaper1 = new SpriteSheet(wallpaper,10,10);

        lift22 = new Image(path+"lift.png");
        lift1 = new Image(path+"lift.png");
        lift32 = new Image(path+"lift.png");
        lift31 = new Image(path+"lift.png");
        lift4 = new Image(path+"lift.png");
        lift21 = new Image(path+"lift.png");
        tp1 = new Teleport(900,600,80,90);
        tp21 = new Teleport(120,410,80,90);
        tp22 = new Teleport(900,410,80,90);
        tp31 = new Teleport(120,310,80,90);
        tp32 = new Teleport(900,310,80,90);
        tp4 = new Teleport(900,10,80,90);

        helicopter = new Image(path+"helicopter.png");
        window = new Image(path+"window.jpg");
        turret1 = new Image(path+"turret.png");
        turret2 = new Image(path+"turret.png");
    }

    private void initDoors()throws SlickException{
        door1 = new Door(90,610,10,80);
        door2 = new Door(90,490,10,80);
    }
    private void initEnemies() throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        // drawEnenmies(graphics);//if dummy is alive then draw it

        graphics.fill(platform);
        graphics.fill(terrain);
        background.draw(0,0,1100,700);

        wall.startUse();
        for(int a=x_offset; a<SetupGame.width-x_offset; a+=10) {
            floorSS.getSubImage(0, 20, 50, 50).drawEmbedded(a, SetupGame.height - wallW, 10, 10);
            floorSS.getSubImage(0, 20, 50, 50).drawEmbedded(a, SetupGame.height- floorH - wallW, 10, 10);
            floorSS.getSubImage(0,20,50,50).drawEmbedded(a,SetupGame.height- floorH -90- wallW *2,10,10);
            floorSS.getSubImage(0,20,100,70).drawEmbedded(a,SetupGame.height- floorH *3- wallW *3,10,20);
        }
        for(int a = SetupGame.height; a>SetupGame.height-3* wallW -3* floorH; a-=10){
            if(!(a<=690&&a>=610 || a<=490&&a>=410))
                wallSS.getSubImage(0,20,50,50).drawEmbedded(x_offset- wallW,a,10,10);
            wallSS.getSubImage(0,20,50,50).drawEmbedded(SetupGame.width-x_offset,a,10,10);
        }
        wall.endUse();

        workshopwallpaper.startUse();
        for(int a=x_offset; a<SetupGame.width-x_offset; a+=10){
            wallpaper1.getSubImage(0,0,333,850).drawEmbedded(a,SetupGame.height-floorH*3-wallW*2,10,290);
            wallpaper1.getSubImage(0,0,333,850).drawEmbedded(a,SetupGame.height-floorH-90-wallW,10,90);
        }
        workshopwallpaper.endUse();
        cellarwallpaper.draw(100,510,900,180);

        window.draw(700,420,140,60);
        window.draw(300,420,140,60);
        window.draw(700,180,140,180);
        window.draw(300,180,140,180);

        lift1.draw(900,600,80,90);
        lift21.draw(120,410,80,90);
        lift22.draw(900,410,80,90);
        lift32.draw(120,310,80,90);
        lift31.draw(900,310,80,90);
        lift4.draw(900,10,80,90);
        drawDoors(graphics);

        turret1.draw(800,600,100,70);
        turret2.draw(800,320,100,70);
        helicopter.draw(150,20,250,80);

        graphics.setColor(Color.pink);
        graphics.fill(babka);
    }

    private void drawDoors(Graphics graphics) {
        if(!door1.isBroken()) {
            graphics.setColor(Color.blue);
            graphics.fill(door1);
        }
        if(!door2.isBroken()) {
            graphics.setColor(Color.blue);
            graphics.fill(door2);
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
        babka.checkForCollision(platform);
        babka.checkForCollision(rightWall);
        babka.checkForCollision(leftWallUp);
        babka.checkForCollision(leftWallDown);
        babka.checkForCollision(firstFloor);
        babka.checkForCollision(secondFloor);
        babka.checkForCollision(thirdFloor);
        babka.checkForCollision(roof);
        /*
        lift1.draw(900,600,80,90);
        lift21.draw(900,410,80,90);
        lift22.draw(120,410,80,90);
        lift32.draw(900,310,80,90);
        lift31.draw(120,310,80,90);
        lift4.draw(900,10,80,90);
        */

        babka.goInTeleport(gameContainer,tp1,0,-190);
        babka.goInTeleport(gameContainer,tp21,0,-90);
        babka.goInTeleport(gameContainer,tp22,0,190);
        babka.goInTeleport(gameContainer,tp31,0,90);
        babka.goInTeleport(gameContainer,tp32,0,-300);
        babka.goInTeleport(gameContainer,tp4,0,300);

        babka.controls(gameContainer);
        checkForAttack(gameContainer);
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
