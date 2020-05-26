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
    private Rectangle attackZone;

    private ArrayList<Rectangle> obstacles = new ArrayList<>();
    private ArrayList<Teleport> teleports;
    private ArrayList<Door> doors = new ArrayList<>();

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();

    private int wallWidth = 25, floorHeight = 15;
    private int floorH = 300, floorW = 700;
    private int x_offset = 200;


    private String path = SetupGame.path;

    public MapLevel3(){
    }

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        babka = new Babka(50, 300);

        initDoors();
        initEnemies();
        initAttackZone();
        initWalls();
    }

    private void initAttackZone() {
        attackZone = new Rectangle(-50, -50, 50, 50);
    }

    @Override
    protected void initLevel(GameContainer container, StateBasedGame game) throws SlickException {

    }

    private void initWalls() throws SlickException {
        background = new Image(path + "background.jpg");

        wall = new Image(path + "wall.jpg");

        obstacles.add(new Rectangle(x_offset-wallWidth, SetupGame.height - floorH * 2, wallWidth, floorH -floorHeight-85)); //left upper wall
        obstacles.add(new Rectangle(x_offset-wallWidth, SetupGame.height - floorH , wallWidth, floorH -floorHeight-85)); //left lower wall
        obstacles.add(new Rectangle(SetupGame.width - x_offset, SetupGame.height - floorH * 2, wallWidth, floorH -floorHeight-85)); //right upper wall
        obstacles.add(new Rectangle(SetupGame.width - x_offset, SetupGame.height - floorH , wallWidth, floorH -floorHeight-85)); //right lower wall
        obstacles.add(new Rectangle(x_offset-wallWidth, SetupGame.height - floorHeight, floorW+wallWidth*2, floorHeight)); //first floor
        obstacles.add(new Rectangle(x_offset-wallWidth, SetupGame.height - floorH - floorHeight, floorW+wallWidth*2, floorHeight)); //second floor
        obstacles.add(new Rectangle(x_offset-55, SetupGame.height - floorH * 2 - floorHeight * 2, floorW+110, floorHeight * 2));//roof
        obstacles.add(new Rectangle(0, SetupGame.height, SetupGame.width, floorHeight)); //terrain
        obstacles.add(new Rectangle(-25, 0, 25, SetupGame.height)); //left frame
        obstacles.add(new Rectangle(1100, 0, 25, SetupGame.height-90)); //right frame
        obstacles.add(new Rectangle(0, -25, SetupGame.width, 25)); //upper frame

        wallSS = new SpriteSheet(wall, 10, 10);
        floorSS = new SpriteSheet(wall, 10, 10);

        wallpaper = new Image(path + "wallpaper.jpg");
        wallpaper1 = new SpriteSheet(wallpaper, 100, 100);

        window = new Image(path + "window.jpg");
        door = new Image(path+"door1.png");

        teleports = new ArrayList<>();
        teleports.add(new Teleport(800, SetupGame.height-floorHeight-85,80,85,0,-floorH)); //1-st floor
        teleports.add(new Teleport(800, SetupGame.height-floorHeight-floorH-85,80,85,0,floorH)); //2-nd floor
    }

    private void initDoors() throws SlickException {
        doors.add(new Door(x_offset-wallWidth, SetupGame.height-floorHeight-85,wallWidth,85, false)); //1-st left
        doors.add(new Door(SetupGame.width-x_offset, SetupGame.height-floorHeight-85,wallWidth,85, false)); //1-st right
        doors.add(new Door(x_offset-wallWidth, SetupGame.height-floorHeight-floorH-85,wallWidth,85, true)); //2-nd left
        doors.add(new Door(SetupGame.width-x_offset, SetupGame.height-floorHeight-floorH-85,wallWidth,85, true)); //2-nd right
    }

    private void initEnemies() throws SlickException {
        Coronavirus coronavirus = new Coronavirus(300,304);
        coronavirus.setSpace(200);
        //coronavirus.setNotVisionHorizontal(500,500);
       // coronavirus.setVisionHorizontal(500,500);
        enemies.add(coronavirus);
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
        for (int a = x_offset-wallWidth; a < SetupGame.width - x_offset; a += floorHeight) {
            floorSS.getSubImage(0, 0, 55, 55).drawEmbedded(a, SetupGame.height - floorHeight, floorHeight*2, floorHeight);
            floorSS.getSubImage(0, 0, 55, 55).drawEmbedded(a, SetupGame.height - floorH - floorHeight, floorHeight*2, floorHeight);
        }
        for (int a = x_offset-55; a < SetupGame.width - x_offset+55; a += floorHeight*2) {
            floorSS.getSubImage(0, 0, 110, 110).drawEmbedded(a, SetupGame.height - floorH * 2 - floorHeight * 2, floorHeight*2, floorHeight*2);
        }
        for (int a = SetupGame.height-floorHeight-85-wallWidth; a > SetupGame.height-2*floorH-floorHeight; a -= wallWidth) {
            if(!(a>= SetupGame.height-floorHeight-floorH-100&&a<= SetupGame.height-floorHeight-floorH)) {
                wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(x_offset - wallWidth, a, wallWidth, wallWidth);
                wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(SetupGame.width - x_offset, a, wallWidth, wallWidth);
            }
        }
        wall.endUse();

        window.draw(700, 150, 100, 100);
        window.draw(400, 150, 100, 100);
        window.draw(700, 450, 100, 100);
        window.draw(400, 450, 100, 100);
        door.draw(800, SetupGame.height-floorHeight-85,80,85);
        door.draw(800, SetupGame.height-floorHeight-floorH-85,80,85);

        drawDoors(graphics);
        /*
        for(Rectangle r: obstacles)
            graphics.fill(r);


         */
        graphics.setColor(Color.pink);
        if ( babka.animationSlide()) {
            babka.getAnimation().draw(babka.getX()-25, babka.getY());
        }else {
            babka.getAnimation().draw(babka.getX(), babka.getY());
        }
        graphics.setColor(Color.black);
        graphics.setColor(Color.yellow);
        drawEnemies(graphics);
        drawBullets(graphics);
    }

    private void drawDoors(Graphics graphics) {
        for(Door door : doors){
            if (!door.isBroken()) {
                door.getImageDoor(graphics).draw(  door.getX(),   door.getY());
            }
        }
    }

    private void drawBullets(Graphics graphics){
        if (!bullets.isEmpty()) {
            for (int i = 0; i<bullets.size(); i++ ) {
                if(bullets.get(i) instanceof Injection){
                    Injection bullet = (Injection) bullets.get(i);;
                    if (bullet.isPresent()) {
                        bullet.getImageInjection(graphics).draw(bullet.getX(), bullet.getY());
                    }
                }
                else if(bullets.get(i) instanceof TapokThrow){
                    TapokThrow bullet = (TapokThrow) bullets.get(i);;
                    if (bullet.isPresent()) {
                        bullet.getImageInjection(graphics).draw(bullet.getX(), bullet.getY());
                    }
                }

            }
        }
    }

    @Override
    protected void renderLevel(GameContainer container, StateBasedGame game, Graphics g) {

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
                    graphics.draw(corona.getVision1());
                    graphics.draw(corona.getVision2());
                }
            } else if (enemies.get(i) instanceof CoronaSmall) {
                CoronaSmall coronaS = (CoronaSmall) enemies.get(i);
                if (coronaS.isAlive()) {
                    coronaS.getAnimation(graphics).draw(coronaS.getX(), coronaS.getY(),25,25);
                }
            } else if (enemies.get(i) instanceof Turrel) {
                Turrel turrel = (Turrel) enemies.get(i);
                if (turrel.isAlive()) {
                    turrel.getImageTurrel(graphics).draw(turrel.getX(), turrel.getY());
                }
            }
        }



    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame game, int delta) throws SlickException {
        babka.update(1, delta);
        for (Rectangle obstacle : obstacles) {
            babka.checkForCollision(obstacle);
        }
        for (Door door : doors){
            if(!door.isBroken())babka.checkForCollision(door);
        }
        for(Teleport teleport: teleports){
            if(gameContainer.getInput().isKeyDown(Input.KEY_ENTER))
                babka.goInTeleport(gameContainer,teleport);
        }
        if( gameContainer.getInput().isKeyDown(Input.KEY_ESCAPE)){
            game.enterState(1, new FadeOutTransition(),new FadeInTransition());
        }
        babka.controls(gameContainer);

        updateEnemies(delta);
        updateBullets();
        checkForAttack(gameContainer);

        if( gameContainer.getInput().isKeyDown(Input.KEY_ESCAPE)){
            game.enterState(1, new FadeOutTransition(),new FadeInTransition());
        }
        if(gameContainer.getInput().isKeyDown(Input.KEY_R)){
            enemies = new ArrayList<>();
            bullets = new ArrayList<>();
            obstacles = new ArrayList<>();

            babka = new Babka(800, 300);

            initDoors();
            initEnemies();
            initAttackZone();
            initWalls();
        }
    }

    private void updateTeleports(){

    }

    private void updateEnemies(int delta) throws SlickException {
        ArrayList<Rectangle> all = new ArrayList<>();
        all.addAll(obstacles);
        all.addAll(doors);

        for(int i = 0; i<enemies.size(); i++){
            if(enemies.get(i) instanceof Doctor){
                Doctor doctor = (Doctor) enemies.get(i);
                doctor.update(delta, all);
                if (doctor.isReadyToShoot()) bullets.add(doctor.shoot(babka));

                for (Rectangle obstacle : obstacles) {
                    doctor.checkForCollisionWall(obstacle);
                }

                for(Rectangle door: doors){
                    doctor.checkForCollisionWall(door);
                }
                doctor.checkForCollisionBabka(babka);
            }
            else if(enemies.get(i) instanceof Coronavirus){
                Coronavirus corona = (Coronavirus) enemies.get(i);
                if(corona.isAlive()) {
                    corona.update(all);
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
                if(turrel.isReadyToShoot(babka)) bullets.add(turrel.shoot());
            }

        }


    }

    private void updateBullets(){
        for (int i = 0; i< bullets.size(); i++) {
            if(bullets.get(i) instanceof Injection && bullets.get(i).isPresent()){
                Injection j = (Injection) bullets.get(i);
                j.update();
                for (Rectangle obstacle : obstacles) {
                    j.checkForCollision(obstacle);
                }
                for (Rectangle obstacle : doors) {
                    j.checkForCollision(obstacle);
                }
                if(j.isReflected()) {
                    for (int d = 0; d < enemies.size(); d++) {
                        if (enemies.get(d) instanceof Doctor) {
                            Doctor doctor = (Doctor) enemies.get(d);
                            if (j.intersects(doctor)) {
                                doctor.die();
                                j.disappear();
                            }
                        }
                    }
                }
                if(j.intersects(babka)) babka.die();
            }
            else if((bullets.get(i) instanceof TapokThrow) && bullets.get(i).isPresent()){
                TapokThrow tapok = (TapokThrow) bullets.get(i);
                tapok.update();
                for (Rectangle obstacle : obstacles) {
                    tapok.checkForCollision(obstacle);
                }
                for (Rectangle obstacle : doors) {
                    tapok.checkForCollision(obstacle);
                }
                for (int d = 0; d < enemies.size(); d++) {
                    if(!(enemies.get(d) instanceof Turrel) && tapok.intersects((Shape) enemies.get(d))&&tapok.isPresent()){
                        enemies.get(d).die();
                        tapok.disappear();
                    }
                }
            }
            else {
                bullets.remove(i);
                i--;
            }
        }

    }

    @Override
    protected void updateLevel(GameContainer container, StateBasedGame game, int delta) {

    }

    private void checkForAttack(GameContainer container) {
        attackZone = babka.getHitZone(container);
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
