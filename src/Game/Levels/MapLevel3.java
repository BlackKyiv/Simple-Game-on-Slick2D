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
    private Babka babka;
    private Image background, wall, wallpaper, window, door;
    private SpriteSheet wallSS, floorSS, wallpaper1;

    private int wallWidth = 25, floorHeight = 15;
    private int floorH = 300, floorW = 700;
    private int x_offset = 200;

    private String path = SetupGame.path;

    public MapLevel3() {
    }

    @Override
    public int getID() {
        return 4;
    }

    @Override
    protected void initLevel(GameContainer container, StateBasedGame game) throws SlickException {
        setBabka(new Babka(800, 350));
        setId(2);
        setNextLevelId(3);
        initDoors();
        initWalls();
        initEnemies();
        initTapki();
        setSymbol(new Symbol(50, 150));
        setExitNextLevel(1050, 650, 50, 50);
    }

    private void initTapki() throws SlickException {
        addTapok(new TapokPick(250, 285));
        addTapok(new TapokPick(30, 670));
        addTapok(new TapokPick(50, 670));
    }

    private void initWalls() throws SlickException {
        background = new Image(path + "background.jpg");

        wall = new Image(path + "wall.jpg");

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

        wallpaper = new Image(path + "wallpaper.jpg");
        wallpaper1 = new SpriteSheet(wallpaper, 100, 100);

        window = new Image(path + "window.jpg");
        door = new Image(path + "door1.png");

        addTeleport(new Teleport1(800, SetupGame.height - floorHeight - floorH - 85, 80, 85, 800, SetupGame.height - floorHeight - 85));
    }

    private void initDoors() throws SlickException {
        addDoor(new Door(x_offset - wallWidth, SetupGame.height - floorHeight - 85, wallWidth, 85, false)); //1-st left
        addDoor(new Door(SetupGame.width - x_offset, SetupGame.height - floorHeight - 85, wallWidth, 85, false)); //1-st right
        addDoor(new Door(x_offset - wallWidth, SetupGame.height - floorHeight - floorH - 85, wallWidth, 85, true)); //2-nd left
        addDoor(new Door(SetupGame.width - x_offset, SetupGame.height - floorHeight - floorH - 85, wallWidth, 85, true)); //2-nd right
    }

    private void initEnemies() throws SlickException {
        Coronavirus coronavirus = new Coronavirus(300, 304);
        coronavirus.setSpace(200);
        //coronavirus.setNotVisionHorizontal(500,500);
        // coronavirus.setVisionHorizontal(500,500);
        addEnemy(coronavirus);
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

        window.draw(700, 150, 100, 100);
        window.draw(400, 150, 100, 100);
        window.draw(700, 450, 100, 100);
        window.draw(400, 450, 100, 100);
        door.draw(800, SetupGame.height - floorHeight - 85, 80, 85);
        door.draw(800, SetupGame.height - floorHeight - floorH - 85, 80, 85);
    }

    @Override
    protected void updateLevel(GameContainer container, StateBasedGame game, int delta) {
        if (!isSymbolPresent()) {
            setReadyToGoNextLevel(true);
        }
    }
}
