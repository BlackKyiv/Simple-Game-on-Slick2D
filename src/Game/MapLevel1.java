package Game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class MapLevel1 extends BasicGameState {
    private Babka babka;
    private Rectangle terrain;
    private Rectangle platform;
    private Rectangle platform1;
    private Rectangle platform2;
    private Rectangle platform3;
    private Rectangle attackZone;
    private Coronavirus corona;

    private Door door;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        babka = new Babka(50, 50, 50, 50);

        initDoors();
        initEnemies();
        initAttackZone();
        initWals();
    }

    private void initAttackZone(){
        attackZone = new Rectangle(-50, -50, 50, 50);
    }

    private void initWals(){
        terrain = new Rectangle(0, SetupGame.height-100, SetupGame.width, 100);
        platform = new Rectangle(0, SetupGame.height-100, SetupGame.width,20);
        platform1 = new Rectangle(SetupGame.width/2, SetupGame.height - 500, 100, 300);
        platform2 = new Rectangle(0, 0, 20, SetupGame.height);
        platform3 = new Rectangle(0, platform.getY()-100, 100, 100);
        platform2 = new Rectangle(0, 0, 20, SetupGame.height);
    }

    private void initDoors(){
        door = new Door(550,SetupGame.height-200, 20,100);
    }
    private void initEnemies() throws SlickException {

        corona = new Coronavirus(300,SetupGame.height-150, 50,50);

    }




    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.darkGray);
        graphics.fill(terrain);
        graphics.setColor(Color.yellow);
        graphics.fill(attackZone);

        drawDummies(graphics);//if dummy is alive then draw it
        drawDoors(graphics);


        graphics.setColor(Color.red);
        graphics.fill(platform1);
        graphics.fill(platform3);
        graphics.setColor(Color.white);
        graphics.fill(babka);
        graphics.drawString("Vertical Speed:"+Math.abs(babka.getSpeedY())+" m/s", 50,50);
        graphics.drawString("Landed:"+babka.isLanded(), 50,70);
    }

    private void drawDoors(Graphics graphics) {
        if(!door.isBroken()) {
            graphics.setColor(Color.blue);
            graphics.fill(door);
        }
    }

    private void drawDummies(Graphics graphics){
        if(corona.isAlive()) {
            graphics.setColor(Color.green);
            graphics.fill(corona);
        }



    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(gameContainer.getInput().isKeyDown(Input.KEY_R)){
            babka = new Babka(50, 50, 50, 50);

            initDoors();
            initEnemies();
            initAttackZone();
            initWals();
        }



        corona.update();
        corona.checkForCollision(platform, false, true);
        corona.checkForCollision(platform1, false, true);
        corona.checkForCollision(platform2, false, true);
        corona.checkForCollision(platform3, false, true);
        corona.checkForCollision(babka, true, true);


        babka.update(1);
        babka.checkForCollision(platform);
        babka.checkForCollision(platform1);
        babka.checkForCollision(platform2);
        babka.checkForCollision(platform3);
        if(!door.isBroken())babka.checkForCollision(door);
        babka.controls(gameContainer);

        checkForAttack(gameContainer);//if dummy intersects attackZone, then dummy die

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


        if(attackZone.intersects(corona)) corona.die();
        if(attackZone.intersects(door)) door.broke();

    }


}
