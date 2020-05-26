package Game.Levels;

import Game.*;
import Game.enemies.Coronavirus;
import Game.enemies.Doctor;
import Game.enemies.Enemy;
import Game.interactiveObjects.Door;
import Game.interactiveObjects.TapokPick;
import Game.interactiveObjects.Teleport;
import Game.interactiveObjects.Teleport1;
import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class TestLevel extends Level{
    private Image background, wall, wallpaper, window, sofa, table, wardrobe, cupboard, nightstand, rockingChair, doorDown, doorUp,arrow;
    private SpriteSheet wallSS, floorSS, wallpaper1;


    private int wallWidth = 25, floorHeight = 15;
    private int floorH = 225, floorW = 900;
    private int x_offset = 200;

    private String path = SetupGame.path;

    @Override
    protected void initLevel(GameContainer container, StateBasedGame game) throws SlickException {
        setBabka(new Babka(800, 350));
        setId(2);
        setNextLevelId(3);
        initDoors();
        initWalls();
        initEnemies();
        initTapki();
        setExitNextLevel(1050,650,50,50);
        addTeleport(new Teleport1(110, 375, 80, 85,110, 600));

    }

    private void initTapki() throws SlickException {
        TapokPick tapok1 = new TapokPick(250,285);
        addTapok(tapok1);
        TapokPick tapok2 = new TapokPick(50,150);
        addTapok(tapok2);
        TapokPick tapok3 = new TapokPick(30,670);
        addTapok(tapok3);
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
        sofa = new Image(path + "sofa.png");
        table = new Image(path + "table.png");
        wardrobe = new Image(path + "wardrobe.png");
        cupboard = new Image(path + "cupboard.png");
        nightstand = new Image(path + "nightstand.png");

        doorDown = new Image(path + "door1.png");
        doorUp = new Image(path + "door1.png");
        arrow = new Image(path+"arrow.png");


    }

    private void initDoors() throws SlickException {
        addDoor(new Door(900, SetupGame.height-floorHeight-85,wallWidth,85, false));
    }

    private void initEnemies() throws SlickException {
        ArrayList<Enemy> coronas = new ArrayList<>();
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
        for (Enemy c : coronas) {
            Coronavirus cor = (Coronavirus) c;
            if (cor instanceof Coronavirus) {
                cor.setSpace(150);
                cor.setVisionVertical(70, 0, 70, 0);

            }
        }
        addEnemy(coronas);
        Doctor doctor = new Doctor(900, 605);
        doctor.setSpace(150);
        doctor.setVisionVertical(50, 0, 50, 0);


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
        sofa.draw(500, 360, 200, 100);
        nightstand.draw(440, 410, 60, 50);
        nightstand.draw(700, 410, 60, 50);
        table.draw(525, 625, 150, 60);
        wardrobe.draw(250, 495, 130, 193);
        cupboard.draw(220, 300, 150, 100);
        rockingChair.draw(30, 625, 60, 60);

        arrow.draw(1050,650,50,50);
    }

    @Override
    protected void updateLevel(GameContainer container, StateBasedGame game, int delta) {
        if(getQuantityOfEnemiesAlive()<=0){
            setReadyToGoNextLevel(true);
            System.out.println(getID());
        }
    }


}
