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

    private Image background, wall, subWall, t1,t2, arrow;
    private int wallThickness=20;

    private Boss boss;

    private Lava lava;
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
        lava = new Lava(SetupGame.height-5, SetupGame.height-110);
        setSymbol(new Symbol(1000, 50));
        setExitNextLevel(1050, 650, 50, 50);
        boss=new Boss(300, 150);
        addEnemy(boss);
        setExitNextLevel(1050,50,50,50);

    }

    private void initTapki() {
    }

    private void initWalls() throws SlickException {
        background = new Image(path + "background.jpg");
        wall = new Image(path+"wall.jpg");
        subWall = wall.getSubImage(0,0,85,85);

        addObstacle(new Rectangle(-25, 0, 25, SetupGame.height)); //left frame
        addObstacle(new Rectangle(1100, 0, 25, SetupGame.height)); //right frame
        addObstacle(new Rectangle(0, -25, SetupGame.width, 25)); //upper frame
        addObstacle(new Rectangle(0,680,100,25)); //left ground
        addObstacle(new Rectangle(1000,680,100,25)); //right ground
        addObstacle(new Rectangle(0,500,100,25)); //left horizontal
        addObstacle(new Rectangle(0,300,100,25));
        addObstacle(new Rectangle(0,100,100,25));
        addObstacle(new Rectangle(1000,500,100,25)); //right horizontal
        addObstacle(new Rectangle(1000,300,100,25));
        addObstacle(new Rectangle(1000,140,100,25 ));
        addObstacle(new Rectangle(250,440,20,160)); //left vertical
        addObstacle(new Rectangle(250,200,20,160));
        addObstacle(new Rectangle(830,440,20,160)); //right vertical
        addObstacle(new Rectangle(830,200,20,160));
        addObstacle(new Rectangle(300,680, 500,20));

        t1 = new Image(path+"teleport.png");
        t2 = new Image(path+"teleport.png");

        addTeleport(new Teleport1(10,15,80,85,1010,595));
        arrow = new Image(path+"arrow.png");
    }

    private void initDoors() throws SlickException {
    }

    private void initEnemies() throws SlickException {
    }

    @Override
    protected void renderLevel(GameContainer container, StateBasedGame game, Graphics g) {
        background.draw(0, 0, 1100, 700);

        subWall.startUse();
        for(int a=0; a<SetupGame.width; a+=wallThickness){
            if(a<100){
                subWall.drawEmbedded(a,680,wallThickness,wallThickness); //left horizontal
                subWall.drawEmbedded(a,500,wallThickness,wallThickness);
                subWall.drawEmbedded(a,300,wallThickness,wallThickness);
                subWall.drawEmbedded(a,100,wallThickness,wallThickness);
            }
            if(a>=1000){
                subWall.drawEmbedded(a,680,wallThickness,wallThickness); //right horizontal
                subWall.drawEmbedded(a,500,wallThickness,wallThickness);
                subWall.drawEmbedded(a,300,wallThickness,wallThickness);
                subWall.drawEmbedded(a,140,wallThickness,wallThickness);
            }
            if(a>=300&&a<800){
                subWall.drawEmbedded(a,680,wallThickness,wallThickness);
            }
        }
        for(int a=0; a<SetupGame.height; a+=wallThickness){
            if(a>=200&&a<360){
                subWall.drawEmbedded(250,a,wallThickness,wallThickness); //upper vertical
                subWall.drawEmbedded(830,a,wallThickness,wallThickness);
            }
            if(a>=440&&a<600){
                subWall.drawEmbedded(250,a,wallThickness,wallThickness); //lower vertical
                subWall.drawEmbedded(830,a,wallThickness,wallThickness);
            }
        }
        subWall.endUse();

        t1.draw(10,15,80,85);
        t2.draw(1010,595,80,85);
        g.setColor(Color.black);
        //drawObstacles(g);
        g.setColor(Color.orange);
        g.fill(lava);

        if (boss.isAlive()) {
            boss.getImageBoss().draw(boss.getX()-50,boss.getY()-50);;
        }
        if(!isSymbolPresent()){
            arrow.draw(1050,50,50,50);
        }
        g.setColor(Color.black);
        g.drawString ("Enemy`s lives:"+ boss.livesLeft(),750,25);
    }

    @Override
    protected void updateLevel(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (!isSymbolPresent()) {
            setReadyToGoNextLevel(true);
        }
        boss.update(delta);
        lava.update(delta);
        if(getBabka().intersects(lava)) {
            t.start();
            getBabka().die();
        }

        if(boss.zonePresent()&&boss.spawnActive()) {
            ArrayList<CoronaSmall> c = boss.spawnCorona();
            for(int i = 0; i<c.size(); i++){
                addEnemy(c.get(i));
            }
        }


    }

}
