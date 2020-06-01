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

public class MapLevel2 extends Level {

    private Image background, wall,subWall,arrow;
    private SpriteSheet wallSS;

    private  ArrayList<Coronavirus> coronas = new ArrayList<>();

    private int wallThickness=25;

    private String path = SetupGame.path;


    @Override
    public int getID() {
        return 3;
    }

    private void initTapki() throws SlickException {
        addTapok(new TapokPick(100,250));
        addTapok(new TapokPick(600,50));
        addTapok(new TapokPick(1050,150));
    }

    @Override
    protected void initLevel(GameContainer container, StateBasedGame game) throws SlickException {
        setBabka(new Babka(1000, 0));
        getBabka().setStandingLeft(true);
        setId(3);
        setNextLevelId(4);
        initDoors();
        initWalls();
        initEnemies();
        initTapki();
        setSymbol(new Symbol(95, 440));
        setExitNextLevel(1050, 620, 50, 50);
    }

    private void initWalls() throws SlickException {
        background = new Image(path + "background.jpg");
        wall = new Image(path + "wall.jpg");
        subWall = wall.getSubImage(0,0,85,85);

        addObstacle(new Rectangle(-25, 0, 25, SetupGame.height)); //left frame
        addObstacle(new Rectangle(1100, 0, 25, SetupGame.height)); //right frame
        addObstacle(new Rectangle(0, -25, SetupGame.width, 25)); //upper frame
        addObstacle(new Rectangle(0,SetupGame.height-wallThickness,200,wallThickness)); //left terrain
        addObstacle(new Rectangle(900,SetupGame.height-wallThickness,200,wallThickness)); //right terrain

        addObstacle(new Rectangle(200,90,900,wallThickness)); //1-st row
        addObstacle(new Rectangle(0,190,800,wallThickness)); //2-nd row left
        addObstacle(new Rectangle(900,190,200,wallThickness)); //2-nd row right
        addObstacle(new Rectangle(0,290,300,wallThickness)); //3-rd row left
        addObstacle(new Rectangle(400,290,700,wallThickness)); //3-rd row centre
        addObstacle(new Rectangle(0,410,900,wallThickness)); //4-th row

        addObstacle(new Rectangle(100,450,25,wallThickness)); //below symbol
        addObstacle(new Rectangle(0,590,75,wallThickness)); //stair to symbol

        addObstacle(new Rectangle(225,600,150,wallThickness)); //left stair
        addObstacle(new Rectangle(475,520,150,wallThickness)); //center stair
        addObstacle(new Rectangle(725,600,150,wallThickness)); //right stair

        wallSS = new SpriteSheet(wall, 10, 10);
        arrow = new Image(path+"arrow.png");
    }

    private void initDoors() throws SlickException {
        //addDoor(new Door(900, SetupGame.height-floorHeight-85,wallWidth,85, false));
    }


    private void initEnemies() throws SlickException {
        coronas = new ArrayList<>();
        Coronavirus corona1 = new Coronavirus(500,20);
        coronas.add(corona1);
        Coronavirus corona2 = new Coronavirus(900,125);
        coronas.add(corona2);
        Coronavirus corona3 = new Coronavirus(100,225);
        coronas.add(corona3);
        Coronavirus corona4 = new Coronavirus(100,325);
        coronas.add(corona4);
        Coronavirus corona5 = new Coronavirus(900,325);
        coronas.add(corona5);
        Coronavirus corona6 = new Coronavirus(525,450);
        coronas.add(corona6);
        Coronavirus corona7 = new Coronavirus(50,630);
        coronas.add(corona7);
        for(Coronavirus c: coronas){
            if(c instanceof Coronavirus){
                addEnemy(c);
                c.setSpace(150);
                c.setVisionVertical(20,0,20,0);
            }
        }
        corona6.setSpace(50);

        Doctor doctor = new Doctor(500, 330);
        doctor.setSpace(200);
        doctor.setVisionVertical(20,0,50,0);
        addEnemy(doctor);

        Turrel t = new Turrel(0,510);
        t.setRight();
        t.setTimeBeforeShoot(400);
        t.setRangeOfSight(400);
        addEnemy(t);
    }

    @Override
    protected void renderLevel(GameContainer container, StateBasedGame game, Graphics g) {
        background.draw(0, 0, 1100, 700);
        wall.startUse();
        for(int a=0; a<SetupGame.width; a+=wallThickness){ //horizontal
            if(a>=200) {
                subWall.drawEmbedded(a, 90, wallThickness, wallThickness); //1-st row
                if(a>=400){
                    subWall.drawEmbedded(a,290,wallThickness,wallThickness); //3-rd right
                }
            }
            if(a<900) {
                subWall.drawEmbedded(a, 410, wallThickness, wallThickness); //4-th row
                if(a<70){
                    subWall.drawEmbedded(a,590,wallThickness,wallThickness); //stair to symbol
                }
                if(a>=100 && a<125){
                    subWall.drawEmbedded(a,490,wallThickness,wallThickness); //below symbol
                }
                if(a<200){
                    subWall.drawEmbedded(a,SetupGame.height-wallThickness,wallThickness,wallThickness); //left terrain
                }
                if(a<300){
                    subWall.drawEmbedded(a,290,wallThickness,wallThickness); //3-rd left
                }
                if(a<800) {
                    subWall.drawEmbedded(a, 190, wallThickness, wallThickness); //2-nd left
                }
                if(a>=225 && a<375){
                    subWall.drawEmbedded(a,600,wallThickness,wallThickness); //left stair
                }
                if(a>=475 && a<625){
                    subWall.drawEmbedded(a,520,wallThickness,wallThickness); //center stair
                }
                if(a>=725 && a<875){
                    subWall.drawEmbedded(a,600,wallThickness,wallThickness); //right stair
                }
            }
            if(a>=900) {
                wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(a, 190, wallThickness, wallThickness); //2-nd right
                wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(a, SetupGame.height - wallThickness, wallThickness, wallThickness); //right terrain
            }
        }
        wall.endUse();

        if(!isSymbolPresent())
            arrow.draw(1050,620,50,50);
    }

    @Override
    protected void updateLevel(GameContainer container, StateBasedGame game, int delta) {
        if (!isSymbolPresent()) {
            setReadyToGoNextLevel(true);
        }
    }

}




