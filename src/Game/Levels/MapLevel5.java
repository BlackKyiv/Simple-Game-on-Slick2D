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

    private Boss boss;

    private String path = SetupGame.path;

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
        addObstacle(new Rectangle(0,500,100,20)); //left horizontal
        addObstacle(new Rectangle(0,300,100,20));
        addObstacle(new Rectangle(0,100,100,20));
        addObstacle(new Rectangle(1000,500,100,20)); //right horizontal
        addObstacle(new Rectangle(1000,300,100,20));
        addObstacle(new Rectangle(1000,140,100,20 ));
        addObstacle(new Rectangle(250,440,20,160)); //left vertical
        addObstacle(new Rectangle(250,200,20,160));
        addObstacle(new Rectangle(830,440,20,160)); //right vertical
        addObstacle(new Rectangle(830,200,20,160));

        t1 = new Image(path+"teleport.png");
        t2 = new Image(path+"teleport.png");

        addTeleport(new Teleport1(10,15,80,85,1010,595));
    }

    private void initDoors() throws SlickException {
    }

    private void initEnemies() throws SlickException {
        boss=new Boss(300, 150);
        addEnemy(boss);


    }

    @Override
    protected void renderLevel(GameContainer container, StateBasedGame game, Graphics g) {
        background.draw(0, 0, 1100, 700);
        t1.draw(10,15,80,85);
        t2.draw(1010,595,80,85);
        drawObstacles(g);
        g.setColor(Color.green);
        g.draw(boss);
     boss.getImageBoss().draw(boss.getX()-50, boss.getY()-50);
       // g.fill(boss);
        g.setColor(Color.red);
        if (boss.zonePresent()) {
            g.draw(boss.getZoneAttack());
           // g.fill(boss.getZoneAttack());
        }

    }

    @Override
    protected void updateLevel(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (!isSymbolPresent()) {
            setReadyToGoNextLevel(true);
        }
        boss.update(delta);

        if(boss.zonePresent()&&boss.spawnActive()) {
            ArrayList<CoronaSmall> c = boss.spawnCorona();
            for(int i = 0; i<c.size(); i++){
                addEnemy(c.get(i));
            }
        }






    }

}
