package Game.Levels;

import Game.*;
import Game.enemies.*;
import Game.interactiveObjects.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class MapLevel2 extends Level {

    private Image background,wall,wallpaper,cellarwallpaper,workshopwallpaper,window,helicopter;
    private Image lift21, lift22, lift1,lift31, lift32, lift4;
    private SpriteSheet wallSS,floorSS,wallpaper1, platformSS;

    private int wallWidth = 25, floorHeight = 15;
    private int floorH = 190, floorW = 900;
    private int x_offset = 200;

    private String path = SetupGame.path;

    @Override
    public int getID() {
        return 3;
    }

    @Override
    protected void initLevel(GameContainer container, StateBasedGame game) throws SlickException {
        setBabka(new Babka(40, 600));
        setId(3);
        setNextLevelId(4);
        initDoors();
        initWalls();
        initEnemies();
        initTapki();
        setSymbol(new Symbol(400, 150));
        setExitNextLevel(0,0,50,50);
    }

    private void initWalls()throws SlickException {
        background = new Image(path+"background.jpg");

        addObstacle(new Rectangle(0, SetupGame.height, SetupGame.width, floorHeight)); //terrain
        addObstacle(new Rectangle(-25, 0, 25, SetupGame.height)); //left frame
        addObstacle(new Rectangle(1100, 0, 25, SetupGame.height-90)); //right frame
        addObstacle(new Rectangle(0, -25, SetupGame.width, 25)); //upper frame
        addObstacle(new Rectangle(x_offset- wallWidth,110, wallWidth, 490)); //left wall
        addObstacle(new Rectangle(SetupGame.width- wallWidth,110, wallWidth, 590)); //right wall
        addObstacle(new Rectangle(0, SetupGame.height- floorHeight, SetupGame.width, floorHeight)); //first floor
        addObstacle(new Rectangle(x_offset, SetupGame.height- floorH - floorHeight, floorW, floorHeight)); //second floor
        addObstacle(new Rectangle(x_offset,390, floorW, floorHeight)); //third floor
        addObstacle(new Rectangle(0,85, 1100, floorHeight *2)); //roof
        addObstacle(new Rectangle(440,250,150,40)); //platform 1
        addObstacle(new Rectangle(740,250,150,40)); //platform 2
        addObstacle(new Rectangle(495,400,wallWidth,10));
        addObstacle(new Rectangle(795,400,wallWidth,10));
        for(int i=400; i<=800; i+=200){
            addObstacle(new Rectangle(i,510,wallWidth,90)); //on the first floor
        }

        wall = new Image(path+"wall.jpg");
        wallSS = new SpriteSheet(wall,10,10);
        floorSS = new SpriteSheet(wall,10,10);
        platformSS = new SpriteSheet(wall,10,10);

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

        addTeleport(new Teleport1(980,410,80,85,980,600)); //1-2 floor
        addTeleport(new Teleport1(220,305,80,85,220,410)); //2-3 floor
        addTeleport(new Teleport1(980,0,80,85,980,90)); //3-4 floor

        helicopter = new Image(path+"helicopter.png");
        window = new Image(path+"window.jpg");
    }

    private void initTapki() throws SlickException {
        addTapok(new TapokPick(500,535));
        addTapok(new TapokPick(770,530));
        addTapok(new TapokPick(350,120));
    }

    private void initDoors()throws SlickException{
        addDoor(new Door(x_offset-wallWidth,600,wallWidth,85, false)); //entry
        addDoor(new Door(400, SetupGame.height-floorHeight-85,wallWidth,85, false)); //1st floor
        addDoor(new Door(495,410,wallWidth,85, false)); //2nd floor
        addDoor(new Door(795,410,wallWidth,85, false)); //2nd floor
    }

    private void initEnemies() throws SlickException {
        Turrel t = new Turrel(900,600);
        t.setRangeOfSight(500);
        addEnemy(t);

        Turrel t1 = new Turrel(900,310);
        t1.setTimeBeforeShoot(400);
        t1.setRangeOfSight(750);
        addEnemy(t1);

        Coronavirus corona1 = new Coronavirus(250,580);
        corona1.setSpace(150);
        corona1.setVisionVertical(20,0,20,0);
        addEnemy(corona1);

        Coronavirus corona2 = new Coronavirus(300,600);
        corona2.setSpace(150);
        corona2.setVisionVertical(20,0,20,0);
        addEnemy(corona2);

        Coronavirus corona3 = new Coronavirus(500,530);
        corona3.setSpace(150);
        corona3.setVisionVertical(20,0,20,0);
        addEnemy(corona3);

        Coronavirus corona4 = new Coronavirus(600,530);
        corona4.setSpace(150);
        corona4.setVisionVertical(20,0,20,0);
        addEnemy(corona4);

        Coronavirus corona5 = new Coronavirus(920,430);
        corona5.setSpace(100);
        corona5.setVisionVertical(20,0,20,0);
        addEnemy(corona5);

        Coronavirus corona6 = new Coronavirus(350,430);
        corona6.setSpace(100);
        corona6.setVisionVertical(20,0,20,0);
        addEnemy(corona6);

        Coronavirus corona7 = new Coronavirus(800,200);
        corona7.setSpace(100);
        corona7.setVisionVertical(20,0,20,0);
        addEnemy(corona7);

        Doctor doctor1 = new Doctor(600,415);
        doctor1.setSpace(150);
        doctor1.setVisionVertical(50,0,50,0);
        addEnemy(doctor1);

        Doctor doctor2 = new Doctor(500,170);
        doctor2.setSpace(60);
        doctor2.setVisionVertical(50,0,50,0);
        addEnemy(doctor2);
    }

    @Override
    protected void renderLevel(GameContainer container, StateBasedGame game, Graphics g) {
        background.draw(0,0,1100,700);

        workshopwallpaper.startUse();
        for(int a = x_offset; a< SetupGame.width- wallWidth; a+=wallWidth){
            wallpaper1.getSubImage(0,0,333,850).drawEmbedded(a, SetupGame.height-floorH*3- wallWidth,wallWidth,285);
            wallpaper1.getSubImage(0,0,333,850).drawEmbedded(a, SetupGame.height-floorH-90- wallWidth,wallWidth,100);
        }
        workshopwallpaper.endUse();
        cellarwallpaper.draw(x_offset,510,floorW-wallWidth,180);
        wall.startUse();
        for(int a = x_offset-wallWidth; a< SetupGame.width; a+=floorHeight) {
            floorSS.getSubImage(0, 0, 55, 55).drawEmbedded(a, SetupGame.height - floorHeight, floorHeight, floorHeight); //floors
            floorSS.getSubImage(0, 0, 55, 55).drawEmbedded(a, SetupGame.height- floorH - floorHeight, floorHeight, floorHeight);
            floorSS.getSubImage(0,0,55,55).drawEmbedded(a, SetupGame.height- floorH -90- floorHeight *2,floorHeight,floorHeight);
        }
        for(int a = x_offset-60; a< SetupGame.width; a+=floorHeight*2)
            floorSS.getSubImage(0,0,110,110).drawEmbedded(a, SetupGame.height- floorH *3- floorHeight *3,floorHeight*2,floorHeight*2);

        for(int a = SetupGame.height; a> SetupGame.height-3*floorHeight -3*floorH; a-=wallWidth){
            if(!(a<=685&&a>=600))
                wallSS.getSubImage(0,0,85,85).drawEmbedded(x_offset- wallWidth,a,wallWidth,wallWidth); //walls
            wallSS.getSubImage(0,0,85,85).drawEmbedded(SetupGame.width- wallWidth,a,wallWidth,wallWidth);
        }
        for(int a=505; a<=570; a+=wallWidth){
            wallSS.getSubImage(0,0,85,85).drawEmbedded(400,a,wallWidth,wallWidth); //above doors wall
            wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(600, a, wallWidth, wallWidth); //1-st floor балки
            wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(800, a, wallWidth, wallWidth);
        }
        wallSS.getSubImage(0,0,85,60).drawEmbedded(400,580,wallWidth,20); //above doors separate end of wall
        wallSS.getSubImage(0, 0, 85, 17).drawEmbedded(495, 405, wallWidth, 5);
        wallSS.getSubImage(0, 0, 85, 17).drawEmbedded(795, 405, wallWidth, 5);
        wall.endUse();

        window.draw(850,420,120,60);
        window.draw(575,420,120,60);
        window.draw(340,420,120,60);

        lift1.draw(980,600,80,85);
        lift21.draw(220,410,80,85);
        lift22.draw(980,410,80,85);
        lift32.draw(220,305,80,85);
        lift31.draw(980,305,80,85);
        lift4.draw(980,0,80,85);

        wall.startUse();
        for(int a=440; a<590; a+=wallWidth){
            platformSS.getSubImage(0,0,85,170).drawEmbedded(a,250,wallWidth,40);
            platformSS.getSubImage(0,0,85,170).drawEmbedded(a+300,250,wallWidth,40);
        }
        wall.endUse();
        helicopter.draw(250,5,250,80);
    }

    @Override
    protected void updateLevel(GameContainer container, StateBasedGame game, int delta) {
        if(!isSymbolPresent()){
            setReadyToGoNextLevel(true);
        }
    }

}
