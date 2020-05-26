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

public class MapLevel5 extends Level {

    private Image background, t1,t2;

    private String path = SetupGame.path;
    private boolean winner=false;

    @Override
    public int getID() {
        return 6;
    }

    @Override
    protected void initLevel(GameContainer container, StateBasedGame game) throws SlickException {
        setBabka(new Babka(20, 600));
        setId(6);
        setNextLevelId(7);
        initDoors();
        initWalls();
        initEnemies();
        initTapki();
        setSymbol(new Symbol(1000, 50));
        setExitNextLevel(1050, 650, 50, 50);
    }

    private void initTapki() {
    }

    private void initWalls() throws SlickException {
        background = new Image(path + "background.jpg");

        addObstacle(new Rectangle(-25, 0, 25, SetupGame.height)); //left frame
        addObstacle(new Rectangle(1100, 0, 25, SetupGame.height-90)); //right frame
        addObstacle(new Rectangle(0, -25, SetupGame.width, 25)); //upper frame
        addObstacle(new Rectangle(0,680,100,20)); //left ground
        addObstacle(new Rectangle(1000,680,100,20)); //right ground
        addObstacle(new Rectangle(0,500,100,10)); //left horizontal
        addObstacle(new Rectangle(0,300,100,10));
        addObstacle(new Rectangle(0,100,100,10));
        addObstacle(new Rectangle(1000,500,100,10)); //right horizontal
        addObstacle(new Rectangle(1000,300,100,10));
        addObstacle(new Rectangle(1000,140,100,10));
        addObstacle(new Rectangle(250,440,10,160)); //left vertical
        addObstacle(new Rectangle(250,140,10,160));
        addObstacle(new Rectangle(830,440,10,160)); //right vertical
        addObstacle(new Rectangle(830,140,10,160));

        t1 = new Image(path+"teleport.png");
        t2 = new Image(path+"teleport.png");

        addTeleport(new Teleport1(10,15,80,85,1010,595));
    }

    private void initDoors() throws SlickException {
    }

    private void initEnemies() throws SlickException {
    }

    @Override
    protected void renderLevel(GameContainer container, StateBasedGame game, Graphics g) {
        background.draw(0, 0, 1100, 700);
        t1.draw(10,15,80,85);
        t2.draw(1010,595,80,85);

        g.fill(new Rectangle(-25, 0, 25, SetupGame.height));
        g.fill(new Rectangle(1100, 0, 25, SetupGame.height-90));
        g.fill(new Rectangle(0, -25, SetupGame.width, 25));
        g.fill(new Rectangle(0,680,100,20));
        g.fill(new Rectangle(1000,680,100,20));
        g.fill(new Rectangle(0,500,100,10));
        g.fill(new Rectangle(0,300,100,10));
        g.fill(new Rectangle(0,100,100,10));
        g.fill(new Rectangle(1000,500,100,10));
        g.fill(new Rectangle(1000,300,100,10));
        g.fill(new Rectangle(1000,140,100,10));
        g.fill(new Rectangle(250,440,10,160));
        g.fill(new Rectangle(250,140,10,160));
        g.fill(new Rectangle(830,440,10,160));
        g.fill(new Rectangle(830,140,10,160));
    }

    @Override
    protected void updateLevel(GameContainer container, StateBasedGame game, int delta) {
        if (!isSymbolPresent()) {
            setReadyToGoNextLevel(true);
        }
    }

}
