package Game;

import Game.enemies.*;
import Game.interactiveObjects.Door;
import Game.interactiveObjects.Injection;
import Game.interactiveObjects.Teleport;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;


public class MapLevel1 extends BasicGameState {
    private Babka babka;
    private Image background, wall, wallpaper, window, sofa, table, wardrobe, cupboard, nightstand, rockingChair, doorDown, doorUp;
    private SpriteSheet wallSS, floorSS, wallpaper1;
    private Rectangle attackZone;

    private ArrayList<Rectangle> obstacles = new ArrayList<>();
    private ArrayList<Teleport> teleports;
    private ArrayList<Door> doors = new ArrayList<>();

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Injection> injections = new ArrayList<Injection>();

    private int wallW = 10;
    private int floorH = 225, floorW = 900;
    private int x_offset = 200;

    private String path = "/Users/dgoptsii/Game copy/pictures/";

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

        obstacles.add(new Rectangle(0, SetupGame.height - floorH * 2, wallW, floorH * 2)); //left wall
        obstacles.add(new Rectangle(SetupGame.width - x_offset, SetupGame.height - floorH * 2, wallW, floorH * 2-80)); //right wall
        obstacles.add(new Rectangle(0, SetupGame.height - wallW, floorW, wallW)); //first floor
        obstacles.add(new Rectangle(0, SetupGame.height - floorH - wallW, floorW, wallW)); //second floor
        obstacles.add(new Rectangle(0, SetupGame.height - floorH * 2 - wallW * 2, floorW + 50, wallW * 2));//roof
        obstacles.add(new Rectangle(0, SetupGame.height - 10, SetupGame.width, 10)); //terrain
        obstacles.add(new Rectangle(0, 0, 10, SetupGame.height)); //left frame
        obstacles.add(new Rectangle(1090, 0, 10, SetupGame.height-80)); //right frame
        obstacles.add(new Rectangle(0, 0, SetupGame.width, 10)); //upper frame

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
        rockingChair = new Image(path + "rocking chair.png");

        doorDown = new Image(path + "door.jpg");
        doorUp = new Image(path + "door.jpg");

        teleports = new ArrayList<>();
        teleports.add(new Teleport(100, 330, 80, 135,0,225));
        teleports.add(new Teleport(100, 555, 80, 135,0,-220));
    }

    private void initDoors() throws SlickException {
        doors.add(new Door(900,610,10,80));
    }

    private void initEnemies() throws SlickException {
        Coronavirus corona = new Coronavirus(550, 400);
        corona.setSpace(150);
        corona.setVisionHorizontal(150);
        corona.setVisionVertical(150);
        corona.setNotVisionHorizontal(200);
        corona.setNotVisionVertical(150);
        enemies.add(corona);


        CoronaSmall coronaS = new CoronaSmall(550, 700);
        coronaS.setSpeed(2);
        enemies.add(coronaS);


        Doctor doctor = new Doctor(650, 350);
        doctor.setSpace(150);
        doctor.setVisionHorizontal(150);
        doctor.setVisionVertical(50);
        doctor.setNotVisionHorizontal(150);
        doctor.setNotVisionVertical(50);

        enemies.add(doctor);

        Turrel turrel = new Turrel(650, 380);
        turrel = new Turrel(500, 380);
        turrel.setLeft();
        turrel.setRangeOfSight(500);

        enemies.add(turrel);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0, 0, 1100, 700);

        wall.startUse();
        for (int a = 0; a < SetupGame.width - x_offset+wallW; a += 10) {
            floorSS.getSubImage(0, 20, 50, 50).drawEmbedded(a, SetupGame.height - wallW, 10, 10);
            floorSS.getSubImage(0, 20, 50, 50).drawEmbedded(a, SetupGame.height - floorH - wallW, 10, 10);
        }
        for (int a = 0; a < SetupGame.width - x_offset + 50; a += 10) {
            floorSS.getSubImage(0, 20, 100, 70).drawEmbedded(a, SetupGame.height - floorH * 2 - wallW * 2, 10, 20);
        }
        for (int a = SetupGame.height; a > SetupGame.height - 2 * wallW - 2 * floorH; a -= 10) {
            wallSS.getSubImage(0, 20, 50, 50).drawEmbedded(0, a, 10, 10);
            if(a<=610)
                wallSS.getSubImage(0, 20, 50, 50).drawEmbedded(SetupGame.width - x_offset, a, 10, 10);
        }
        wall.endUse();

        wallpaper.startUse();
        for (int a = wallW; a < SetupGame.width - x_offset; a += 10) {
            wallpaper1.getSubImage(0, 0, 333, 850).drawEmbedded(a, SetupGame.height - floorH, 10, floorH - wallW);
            wallpaper1.getSubImage(0, 0, 333, 850).drawEmbedded(a, SetupGame.height - floorH * 2, 10, floorH - wallW);
        }
        wallpaper.endUse();

        window.draw(700, 300, 100, 100);
        window.draw(400, 300, 100, 100);
        window.draw(700, 550, 100, 100);
        window.draw(400, 550, 100, 100);
        doorUp.draw(100, 330, 80, 135);
        doorDown.draw(100, 555, 80, 135);
        sofa.draw(500, 365, 200, 100);
        nightstand.draw(440, 415, 60, 50);
        nightstand.draw(700, 415, 60, 50);
        table.draw(525, 630, 150, 60);
        wardrobe.draw(250, 500, 130, 193);
        cupboard.draw(220, 300, 150, 100);
        rockingChair.draw(10, 620, 70, 70);

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
                    corona.getAnimation( graphics).draw(corona.getX(), corona.getY());

                }
            } else if (enemies.get(i) instanceof CoronaSmall) {
                CoronaSmall coronaS = (CoronaSmall) enemies.get(i);
                if (coronaS.isAlive()) {
                    coronaS.update();
                    coronaS.checkForCollisionBabka(babka);
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

        //babka.goInTeleport(gameContainer, tp2);
        //babka.goInTeleport(gameContainer, tp1);
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
                }
            }
            else if(enemies.get(i) instanceof CoronaSmall){
                CoronaSmall coronaS = (CoronaSmall) enemies.get(i);
                if(coronaS.isAlive()) {
                    coronaS.update();
                    coronaS.checkForCollisionBabka(babka);
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
                Injection j = injections.get(i);
                if(j.isPresent()) {
                    j.update();
                    for (Rectangle obstacle : obstacles) {
                        j.checkForCollision(obstacle);
                    }
                }
                else {
                    injections.remove(i);
                    i--;
                }
                if(j.intersects(attackZone)) j.reflect();
            }
        }

    }

    private void checkForAttack(GameContainer container) {
        //if babka`s x > mouse x then draw attackZone on right
        //else if babka`s x < mouse x then draw attackZone on left+
        if (container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) || container.getInput().isKeyPressed(Input.KEY_F)) {
            if (container.getInput().getMouseX() > babka.getX()) {
                attackZone.setX(babka.getX() + babka.getWidth());
                attackZone.setY(babka.getY());
            } else if (container.getInput().getMouseX() < babka.getX()) {
                attackZone.setX(babka.getX() - babka.getWidth());
                attackZone.setY(babka.getY());
            }
        }
        else attackZone = new Rectangle(-50, -50, 50, 50);
        checkForAttackDoors();
        checkForAttackEnemies();

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
