package Game.Levels;

import Game.*;
import Game.enemies.*;
import Game.interactiveObjects.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;

public class MapLevel4 extends Level {

    private Image background, wall, wallpaper, window, doorDown, doorUp,arrow;
    private SpriteSheet wallSS, floorSS, wallpaper1;
    private  Teleport winnerT;

    private ArrayList<Teleport> teleports;
    private  ArrayList<Coronavirus> coronas = new ArrayList<>();

    private int wallWidth = 25, floorHeight = 15;
    private int floorH = 225, floorW = 900;
    private int x_offset = 200;

    private String path = SetupGame.path;

    private boolean nextLevel=true;

    @Override
    public int getID() {
        return 5;
    }

    private void initTapki() throws SlickException {
        addTapok(new TapokPick(100,250));
    }

    @Override
    protected void initLevel(GameContainer container, StateBasedGame game) throws SlickException {
        setBabka(new Babka(800, 350));
        setId(5);
        setNextLevelId(6);
        initDoors();
        initWalls();
        initEnemies();
        initTapki();
        setSymbol(new Symbol(50, 150));
        setExitNextLevel(1050, 650, 50, 50);
    }

    private void initWalls() throws SlickException {
        background = new Image(path + "background.jpg");
        wall = new Image(path + "wall.jpg");

        addObstacle(new Rectangle(0, SetupGame.height - floorH * 2, wallWidth, floorH * 2)); //left wall
        addObstacle(new Rectangle(SetupGame.width - x_offset, SetupGame.height - floorH * 2, wallWidth, floorH * 2-85-floorHeight)); //right wall
        addObstacle(new Rectangle(0, SetupGame.height - floorHeight, floorW, floorHeight)); //first floor
        addObstacle(new Rectangle(0, SetupGame.height - floorH - floorHeight, floorW, floorHeight)); //second floor
        addObstacle(new Rectangle(0, SetupGame.height - floorH * 2 - floorHeight * 2, floorW + 60, floorHeight * 2));//roof
        addObstacle(new Rectangle(0, SetupGame.height, SetupGame.width, floorHeight)); //terrain
        addObstacle(new Rectangle(-25, 0, 25, SetupGame.height)); //left frame
        addObstacle(new Rectangle(1100, 0, 25, SetupGame.height)); //right frame
        addObstacle(new Rectangle(0, -25, SetupGame.width, 25)); //upper frame

        wallSS = new SpriteSheet(wall, 10, 10);
        floorSS = new SpriteSheet(wall, 10, 10);

        wallpaper = new Image(path + "wallpaper.jpg");
        wallpaper1 = new SpriteSheet(wallpaper, 100, 100);

        window = new Image(path + "window.jpg");

        doorDown = new Image(path + "door1.png");
        doorUp = new Image(path + "door1.png");
        arrow = new Image(path+"arrow.png");

        teleports = new ArrayList<>();
        winnerT = new Teleport(1030, 630, 70, 70, 0, 0);
        teleports.add(winnerT);

        addTeleport(new Teleport1(110,600,80,85,110,375));
    }

    private void initDoors() throws SlickException {
        addDoor(new Door(900, SetupGame.height-floorHeight-85,wallWidth,85, false));
    }


    private void initEnemies() throws SlickException {
        ArrayList<Coronavirus> coronas = new ArrayList<>();
        Coronavirus corona1 = new Coronavirus(950, 400);
        coronas.add(corona1);
        Coronavirus corona2 = new Coronavirus(50, 390);
        coronas.add(corona2);
        Coronavirus corona3 = new Coronavirus(200, 600);
        coronas.add(corona3);
        Coronavirus corona4 = new Coronavirus(700, 620);
        coronas.add(corona4);
        Coronavirus corona5 = new Coronavirus(400, 150);
        coronas.add(corona5);
        for(Coronavirus c: coronas){
            if(c instanceof Coronavirus){
                addEnemy(c);
                c.setSpace(150);
                c.setVisionVertical(20,0,20,0);
            }
        }
        Doctor doctor = new Doctor(900, 605);
        doctor.setSpace(150);
        doctor.setVisionVertical(50,0,50,0);
        addEnemy(doctor);
    }

    @Override
    protected void renderLevel(GameContainer container, StateBasedGame game, Graphics g) {
        background.draw(0, 0, 1100, 700);

        wallpaper.startUse();
        for (int a = wallWidth; a <= SetupGame.width - x_offset; a += 10) {
            wallpaper1.getSubImage(0, 0, 333, 850).drawEmbedded(a, SetupGame.height - floorH, 10, floorH - floorHeight);
            wallpaper1.getSubImage(0, 0, 333, 850).drawEmbedded(a, SetupGame.height - floorH * 2, 10, floorH - floorHeight);
        }
        wallpaper.endUse();
        wall.startUse();
        for (int a = wallWidth; a < SetupGame.width - x_offset; a += floorHeight) {
            floorSS.getSubImage(0, 0, 55, 55).drawEmbedded(a, SetupGame.height - floorHeight, floorHeight*2, floorHeight);
            floorSS.getSubImage(0, 0, 55, 55).drawEmbedded(a, SetupGame.height - floorH - floorHeight, floorHeight*2, floorHeight);
        }
        for (int a = 0; a < SetupGame.width - x_offset+50; a += floorHeight*2) {
            floorSS.getSubImage(0, 0, 110, 110).drawEmbedded(a, SetupGame.height - floorH * 2 - floorHeight * 2, floorHeight*2, floorHeight*2);
        }
        for (int a = SetupGame.height-floorHeight; a > SetupGame.height-2*floorH-2*floorHeight; a -= wallWidth) {
            wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(0, a, wallWidth, wallWidth);
            if(a<=580)
                wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(SetupGame.width-x_offset, a, wallWidth, wallWidth);
        }
        wallSS.getSubImage(0,0,85,60).drawEmbedded(SetupGame.width-x_offset,585,wallWidth,15); //above doors
        wall.endUse();

        window.draw(700, 300, 100, 100);
        window.draw(400, 300, 100, 100);
        window.draw(700, 550, 100, 100);
        window.draw(400, 550, 100, 100);
        doorUp.draw(110, 375, 80, 85);
        doorDown.draw(110, 600, 80, 85);
    }

    @Override
    protected void updateLevel(GameContainer container, StateBasedGame game, int delta) {
        if (!isSymbolPresent()) {
            setReadyToGoNextLevel(true);
        }
    }

}




