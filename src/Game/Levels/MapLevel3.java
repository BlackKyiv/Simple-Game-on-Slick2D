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

public class MapLevel3 extends Level {
    private Image background, wall, wallpaper, window, door, table, bed, gurney, picture, logo, arrow;
    private SpriteSheet wallSS, floorSS, wallpaper1;

    private int wallWidth = 25, floorHeight = 15;
    private int floorH = 275, floorW = 700;
    private int x_offset = 200;
    

    public MapLevel3() {
    }

    @Override
    public int getID() {
        return 4;
    }

    @Override
    protected void initLevel(GameContainer container, StateBasedGame game) throws SlickException {
        setBabka(new Babka(30, 500));
        setId(4);
        setNextLevelId(5);
        initDoors();
        initWalls();
        initEnemies();
        initTapki();
        setSymbol(new Symbol(650,605));
        setExitNextLevel(1050, 650, 50, 50);
    }

    private void initTapki() throws SlickException {
        addTapok(new TapokPick(800, 250));
        addTapok(new TapokPick(300, 600));
    }

    private void initWalls() throws SlickException {
        background = new Image(SetupGame.path + "background.jpg");

        wall = new Image(SetupGame.path + "wall.jpg");

        addObstacle(new Rectangle(x_offset - wallWidth, SetupGame.height - floorH * 2, wallWidth, floorH - floorHeight - 85)); //left upper wall
        addObstacle(new Rectangle(x_offset - wallWidth, SetupGame.height - floorH, wallWidth, floorH - floorHeight - 85)); //left lower wall
        addObstacle(new Rectangle(SetupGame.width - x_offset, SetupGame.height - floorH * 2, wallWidth, floorH - floorHeight - 85)); //right upper wall
        addObstacle(new Rectangle(SetupGame.width - x_offset, SetupGame.height - floorH, wallWidth, floorH - floorHeight - 85)); //right lower wall
        addObstacle(new Rectangle(x_offset - wallWidth, SetupGame.height - floorHeight, floorW + wallWidth * 2, floorHeight)); //first floor
        addObstacle(new Rectangle(x_offset - wallWidth, SetupGame.height - floorH - floorHeight, floorW + wallWidth * 2, floorHeight)); //second floor
        addObstacle(new Rectangle(x_offset - 55, SetupGame.height - floorH * 2 - floorHeight * 2, floorW + 110, floorHeight * 2));//roof
        addObstacle(new Rectangle(0, SetupGame.height, SetupGame.width, floorHeight)); //terrain
        addObstacle(new Rectangle(-25, 0, 25, SetupGame.height)); //left frame
        addObstacle(new Rectangle(1100, 0, 25, SetupGame.height - 90)); //right frame
        addObstacle(new Rectangle(0, -25, SetupGame.width, 25)); //upper frame

        wallSS = new SpriteSheet(wall, 10, 10);
        floorSS = new SpriteSheet(wall, 10, 10);

        wallpaper = new Image(SetupGame.path + "wallpaper.jpg");
        wallpaper1 = new SpriteSheet(wallpaper, 100, 100);

        window = new Image(SetupGame.path + "window.jpg");
        door = new Image(SetupGame.path + "door1.png");

        table = new Image(SetupGame.path+"hospital_table.png");
        bed = new Image(SetupGame.path+"bed.png");
        gurney = new Image(SetupGame.path+"gurney.png");
        picture = new Image(SetupGame.path+"shelf.png");
        logo = new Image(SetupGame.path+"hospital_logo.png");

        addTeleport(new Teleport1(800, SetupGame.height - floorHeight - floorH - 85, 80, 85, 800, SetupGame.height - floorHeight - 85));
        arrow = new Image(SetupGame.path + "arrow.png");
    }

    private void initDoors() throws SlickException {
        addDoor(new Door(x_offset - wallWidth, SetupGame.height - floorHeight - 85, wallWidth, 85, false)); //1-st left
        addDoor(new Door(SetupGame.width - x_offset, SetupGame.height - floorHeight - 85, wallWidth, 85, false)); //1-st right
        addDoor(new Door(x_offset - wallWidth, SetupGame.height - floorHeight - floorH - 85, wallWidth, 85, true)); //2-nd left
        addDoor(new Door(SetupGame.width - x_offset, SetupGame.height - floorHeight - floorH - 85, wallWidth, 85, true)); //2-nd right
    }

    private void initEnemies() throws SlickException {
        Coronavirus corona1 = new Coronavirus(500, 304);
        corona1.setSpace(250);
        corona1.setVisionVertical(20,0,20,0);
        addEnemy(corona1);

        Coronavirus corona2 = new Coronavirus(600, 610);
        corona2.setSpace(300);
        corona2.setVisionVertical(20,0,20,0);
        addEnemy(corona2);

        Coronavirus corona3 = new Coronavirus(800, 590);
        corona3.setSpace(150);
        corona3.setVisionVertical(20,0,20,0);
        addEnemy(corona3);

        Coronavirus corona4 = new Coronavirus(400, 600);
        corona4.setSpace(200);
        corona4.setVisionVertical(20,0,20,0);
        addEnemy(corona4);

        Coronavirus corona5 = new Coronavirus(200, 70);
        corona5.setSpace(200);
        corona5.setVisionVertical(20,0,20,0);
        addEnemy(corona5);

        Coronavirus corona6 = new Coronavirus(100, 150);
        corona6.setSpace(100);
        corona6.setVisionVertical(20,0,20,0);
        addEnemy(corona6);

        Coronavirus corona7 = new Coronavirus(900, 70);
        corona7.setSpace(200);
        corona7.setVisionVertical(20,0,20,0);
        addEnemy(corona7);

        Coronavirus corona8 = new Coronavirus(1000, 150);
        corona8.setSpace(100);
        corona8.setVisionVertical(20,0,20,0);
        addEnemy(corona8);

        Doctor doctor = new Doctor(350, 330);
        doctor.setSpace(150);
        doctor.setVisionVertical(20,0,50,0);
        addEnemy(doctor);

        Doctor doctor1 = new Doctor(550, 330);
        doctor1.setSpace(150);
        doctor1.setVisionVertical(20,0,50,0);
        addEnemy(doctor1);

        Doctor doctor2 = new Doctor(450, 40);
        doctor2.setSpace(300);
        doctor2.setVisionVertical(20,0,50,0);
        addEnemy(doctor2);

        Doctor doctor3 = new Doctor(620, 40);
        doctor3.setSpace(300);
        doctor3.setVisionVertical(20,0,50,0);
        addEnemy(doctor3);

        Turrel t = new Turrel(700,605);
        t.setTimeBeforeShoot(100);
        t.setRangeOfSight(500);
        addEnemy(t);
    }

    @Override
    protected void renderLevel(GameContainer container, StateBasedGame game, Graphics g) {
        background.draw(0, 0, 1100, 700);

        wallpaper.startUse();
        for (int a = x_offset; a <= SetupGame.width - x_offset; a += 10) {
            wallpaper1.getSubImage(0, 0, 333, 850).drawEmbedded(a, SetupGame.height - floorH, 10, floorH - floorHeight);
            wallpaper1.getSubImage(0, 0, 333, 850).drawEmbedded(a, SetupGame.height - floorH * 2, 10, floorH - floorHeight);
        }
        wallpaper.endUse();
        wall.startUse();
        for (int a = x_offset - wallWidth; a < SetupGame.width - x_offset; a += floorHeight) {
            floorSS.getSubImage(0, 0, 55, 55).drawEmbedded(a, SetupGame.height - floorHeight, floorHeight * 2, floorHeight);
            floorSS.getSubImage(0, 0, 55, 55).drawEmbedded(a, SetupGame.height - floorH - floorHeight, floorHeight * 2, floorHeight);
        }
        for (int a = x_offset - 55; a < SetupGame.width - x_offset + 55; a += floorHeight * 2) {
            floorSS.getSubImage(0, 0, 110, 110).drawEmbedded(a, SetupGame.height - floorH * 2 - floorHeight * 2, floorHeight * 2, floorHeight * 2);
        }
        for (int a = SetupGame.height - floorHeight - 85 - wallWidth; a > SetupGame.height - 2 * floorH - floorHeight; a -= wallWidth) {
            if (!(a >= SetupGame.height - floorHeight - floorH - 100 && a <= SetupGame.height - floorHeight - floorH)) {
                wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(x_offset - wallWidth, a, wallWidth, wallWidth);
                wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(SetupGame.width - x_offset, a, wallWidth, wallWidth);
            }
        }
        wall.endUse();

        window.draw(700, 180, 100, 100);
        window.draw(400, 180, 100, 100);
        window.draw(700, 480, 100, 100);
        window.draw(400, 480, 100, 100);
        door.draw(800, SetupGame.height - floorHeight - 85, 80, 85);
        door.draw(800, SetupGame.height - floorHeight - floorH - 85, 80, 85);

        logo.draw(535,120,30,30);
        bed.draw(310,350,120,60);

        table.draw(530,350,140,60);
        gurney.draw(300,625,120,60);
        gurney.draw(530,625,120,60);
        picture.draw(530,550,140,24);

        if (!isSymbolPresent()) {
            arrow.draw(1050,650,50,50);
        }
    }

    @Override
    protected void updateLevel(GameContainer container, StateBasedGame game, int delta) {
        if (!isSymbolPresent()) {
            setReadyToGoNextLevel(true);
        }
    }
}
