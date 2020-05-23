package Game;

import Game.enemies.*;
import Game.interactiveObjects.Bullet;
import Game.interactiveObjects.Door;
import Game.interactiveObjects.Injection;
import Game.interactiveObjects.Teleport;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;


public class MapLevel3 extends BasicGameState {
    private Babka babka;
    private Image background, wall, wallpaper, window;
    private SpriteSheet wallSS, floorSS, wallpaper1;
    private Rectangle attackZone;

    private ArrayList<Rectangle> obstacles = new ArrayList<>();
    private ArrayList<Teleport> teleports;
    private ArrayList<Door> doors = new ArrayList<>();

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Injection> injections = new ArrayList<>();

    private int wallWidth = 25, floorHeight = 15;
    private int floorH = 300, floorW = 900;
    private int x_offset = 200;

    private String path = SetupGame.path;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        babka = new Babka(800, 300, 50, 50);

        initDoors();
        initEnemies();
        initAttackZone();
        initWalls();
    }

    private void initAttackZone() {
        attackZone = new Rectangle(-50, -50, 50, 50);
    }

    private void initWalls() throws SlickException {
        background = new Image(path + "backg.jpg");

        wall = new Image(path + "wall.jpg");

        obstacles.add(new Rectangle(0, SetupGame.height - floorH * 2, wallWidth, floorH * 2)); //left wall
        obstacles.add(new Rectangle(SetupGame.width - x_offset, SetupGame.height - floorH * 2, wallWidth, floorH * 2-85-floorHeight)); //right wall
        obstacles.add(new Rectangle(0, SetupGame.height - floorHeight, floorW, floorHeight)); //first floor
        obstacles.add(new Rectangle(0, SetupGame.height - floorH - floorHeight, floorW, floorHeight)); //second floor
        obstacles.add(new Rectangle(0, SetupGame.height - floorH * 2 - floorHeight * 2, 1100, floorHeight * 2));//roof
        obstacles.add(new Rectangle(0, SetupGame.height - floorHeight, SetupGame.width, floorHeight)); //terrain
        obstacles.add(new Rectangle(0, 0, 10, SetupGame.height)); //left frame
        obstacles.add(new Rectangle(1090, 0, 10, SetupGame.height-90)); //right frame
        obstacles.add(new Rectangle(0, 0, SetupGame.width, 10)); //upper frame

        wallSS = new SpriteSheet(wall, 10, 10);
        floorSS = new SpriteSheet(wall, 10, 10);

        wallpaper = new Image(path + "wallpaper.jpg");
        wallpaper1 = new SpriteSheet(wallpaper, 100, 100);

        window = new Image(path + "window.jpg");

        teleports = new ArrayList<>();
        teleports.add(new Teleport(110, 325, 80, 135,0,225));
        teleports.add(new Teleport(110, 550, 80, 135,0,-220));
    }

    private void initDoors() throws SlickException {
        doors.add(new Door(x_offset-wallWidth,SetupGame.height-floorHeight-85,wallWidth,85)); //1-st left
        doors.add(new Door(SetupGame.width-x_offset,SetupGame.height-floorHeight-85,wallWidth,85)); //1-st right
        doors.add(new Door(x_offset-wallWidth,SetupGame.height-floorHeight-floorH-85,wallWidth,85)); //2-nd left
        doors.add(new Door(SetupGame.width-x_offset,SetupGame.height-floorHeight-floorH-85,wallWidth,85)); //2-nd right
    }

    private void initEnemies() throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0, 0, 1100, 700);

        wallpaper.startUse();
        for (int a = x_offset; a <= SetupGame.width - x_offset; a += 10) {
            wallpaper1.getSubImage(0, 0, 333, 850).drawEmbedded(a, SetupGame.height - floorH, 10, floorH - floorHeight);
            wallpaper1.getSubImage(0, 0, 333, 850).drawEmbedded(a, SetupGame.height - floorH * 2, 10, floorH - floorHeight);
        }
        wallpaper.endUse();
        wall.startUse();
        for (int a = x_offset; a < SetupGame.width - x_offset; a += floorHeight) {
            floorSS.getSubImage(0, 0, 55, 55).drawEmbedded(a, SetupGame.height - floorHeight, floorHeight*2, floorHeight);
            floorSS.getSubImage(0, 0, 55, 55).drawEmbedded(a, SetupGame.height - floorH - floorHeight, floorHeight*2, floorHeight);
        }
        for (int a = x_offset-50; a < SetupGame.width - x_offset+50; a += floorHeight*2) {
            floorSS.getSubImage(0, 0, 110, 110).drawEmbedded(a, SetupGame.height - floorH * 2 - floorHeight * 2, floorHeight*2, floorHeight*2);
        }
        for (int a = SetupGame.height-floorHeight-85; a > SetupGame.height-2*floorH-floorHeight; a -= wallWidth) {
            if(!(a>=SetupGame.height-floorHeight-floorH-85&&a<=SetupGame.height-floorHeight-floorH)) {
                wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(x_offset - wallWidth, a, wallWidth, wallWidth);
                wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(SetupGame.width - x_offset, a, wallWidth, wallWidth);
            }
        }
        wall.endUse();



        window.draw(700, 150, 100, 100);
        window.draw(400, 150, 100, 100);
        window.draw(700, 450, 100, 100);
        window.draw(400, 450, 100, 100);

        drawDoors(graphics);

        graphics.setColor(Color.pink);
        graphics.fill(babka);
        graphics.setColor(Color.black);
        graphics.setColor(Color.yellow);
        graphics.fill(attackZone);
        drawEnemies(graphics);

    }

    private void drawDoors(Graphics graphics) {
        for(Door door : doors){
            if (!door.isBroken()) {
                graphics.setColor(Color.blue);
                graphics.fill(door);
            }
        }
    }

    private void drawEnemies(Graphics graphics) {
        for(int i = 0; i<enemies.size(); i++) {
            if (enemies.get(i) instanceof Doctor) {
                Doctor doctor = (Doctor) enemies.get(i);
                if (doctor.isAlive()) {
                    doctor.getAnimation(graphics).draw(  doctor.getX(),  doctor.getY());
                }
            } else if (enemies.get(i) instanceof Coronavirus) {
                Coronavirus corona = (Coronavirus) enemies.get(i);
                if (corona.isAlive()) {
                    corona.getAnimation(graphics).draw(corona.getX(), corona.getY());
                }
            } else if (enemies.get(i) instanceof CoronaSmall) {
                CoronaSmall coronaS = (CoronaSmall) enemies.get(i);
                if (coronaS.isAlive()) {
                    coronaS.getAnimation(graphics).draw(coronaS.getX(), coronaS.getY(),25,25);
                }
            } else if (enemies.get(i) instanceof Turrel) {
                Turrel turrel = (Turrel) enemies.get(i);
                if (turrel.isAlive()) {
                    graphics.setColor(Color.gray);
                    graphics.fill(turrel);
                }
            }
        }

        if (!injections.isEmpty()) {
            for (Injection i : injections) {
                if (i.isPresent()) {
                    i.getImage (graphics).draw(i.getX(), i.getY());
                }
            }
        }


    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        babka.update(1);
        for (Rectangle obstacle : obstacles) {
            babka.checkForCollision(obstacle);
        }
        for (Door door : doors){
            if(!door.isBroken())babka.checkForCollision(door);
        }
        for(Teleport teleport: teleports){
            babka.goInTeleport(gameContainer,teleport);
        }

        babka.controls(gameContainer);

        updateEnemies(delta);
        updateBullets();
        checkForAttack(gameContainer);


        if(gameContainer.getInput().isKeyDown(Input.KEY_R)){
            enemies = new ArrayList<>();
            injections = new ArrayList<>();
            obstacles = new ArrayList<>();

            babka = new Babka(800, 300, 50, 50);

            initDoors();
            initEnemies();
            initAttackZone();
            initWalls();
        }
    }

    private void updateTeleports(){

    }

    private void updateEnemies(int delta) throws SlickException {

        for(int i = 0; i<enemies.size(); i++){
            if(enemies.get(i) instanceof Doctor){
                Doctor doctor = (Doctor) enemies.get(i);
                doctor.update(delta);
                if (doctor.isReadyToShoot()) injections.add(doctor.shoot(babka));

                for (Rectangle obstacle : obstacles) {
                    doctor.checkForCollisionWall(obstacle);
                }
                doctor.checkForCollisionBabka(babka);
            }
            else if(enemies.get(i) instanceof Coronavirus){
                Coronavirus corona = (Coronavirus) enemies.get(i);
                if(corona.isAlive()) {
                    corona.update();
                    for (Rectangle obstacle : obstacles) {
                        corona.checkForCollisionWall(obstacle);
                    }
                    corona.checkForCollisionBabka(babka);
                    if(babka.intersects(corona)&&corona.isAlive()) babka.die();
                }
            }
            else if(enemies.get(i) instanceof CoronaSmall){
                CoronaSmall coronaS = (CoronaSmall) enemies.get(i);
                if(coronaS.isAlive()) {
                    coronaS.update();
                    coronaS.checkForCollisionBabka(babka);
                    if(babka.intersects(coronaS)&&coronaS.isAlive()) babka.die();
                }
            }
            else if(enemies.get(i) instanceof Turrel){
                Turrel turrel = (Turrel) enemies.get(i);
                turrel.update(delta);
                turrel.checkForCollisionBabka(babka);
                if(turrel.isReadyToShoot(babka))injections.add(turrel.shoot());
            }

        }

    }

    private void updateBullets(){
        //Injections update
        if (!injections.isEmpty()) {
            for (int i =0; i<injections.size(); i++) {
                Injection j = (Injection) injections.get(i);
                if(j.isPresent()) {
                    j.update();
                    for (Rectangle obstacle : obstacles) {
                        j.checkForCollision(obstacle);
                    }
                    if(j.intersects(babka))babka.die();
                }
                else {
                    injections.remove(i);
                    i--;
                }
                if(j.intersects(attackZone)) j.reflect();
                if(j.intersects(babka)) babka.die();
            }
        }

    }

    private void checkForAttack(GameContainer container) {
        //if babka`s x > mouse x then draw attackZone on right
        //else if babka`s x < mouse x then draw attackZone on left+
        if(babka.isAlive()) {
            if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) || container.getInput().isKeyPressed(Input.KEY_F)) {
                if (container.getInput().getMouseX() > babka.getX()) {
                    attackZone.setX(babka.getX() + babka.getWidth());
                    attackZone.setY(babka.getY());
                } else if (container.getInput().getMouseX() < babka.getX()) {
                    attackZone.setX(babka.getX() - babka.getWidth());
                    attackZone.setY(babka.getY());
                }
            } else attackZone = new Rectangle(-50, -50, 50, 50);
            checkForAttackDoors();
            checkForAttackEnemies();
        }
    }

    private void checkForAttackDoors(){
        for(int i = 0; i<doors.size(); i++){
            Door door = doors.get(i);
            if(attackZone.intersects(door)) door.broke();
            if(door.isBroken()) doors.remove(i);
        }
    }

    private void checkForAttackEnemies(){
        for(int i = 0; i<enemies.size(); i++){
            if(enemies.get(i).isAlive() && attackZone.intersects( (Rectangle) enemies.get(i))){
                enemies.get(i).die();
            }
        }

    }

}
