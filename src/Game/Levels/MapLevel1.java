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



public class MapLevel1 extends BasicGameState {
    private Babka babka;
    private Image background, wall, wallpaper, window, sofa, table, wardrobe, cupboard, nightstand, rockingChair, doorDown, doorUp,arrow;
    private SpriteSheet wallSS, floorSS, wallpaper1;
    private Rectangle attackZone;
    private  Teleport nextLevelT;

    private ArrayList<Rectangle> obstacles = new ArrayList<>();
    private ArrayList<Teleport> teleports;
    private ArrayList<Door> doors = new ArrayList<>();
    private  ArrayList<Coronavirus> coronas = new ArrayList<>();

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<TapokPick> tapki = new ArrayList<>();

    private int wallWidth = 25, floorHeight = 15;
    private int floorH = 225, floorW = 900;
    private int x_offset = 200;

    private TapokPick tapok1, tapok2, tapok3;

    private String path = SetupGame.path;

    private boolean nextLevel=true;

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        babka = new Babka(800, 350);

        initDoors();
        initEnemies();
        initAttackZone();
        initWalls();
        initTapki();
    }

    private void initTapki() throws SlickException {
        tapok1 = new TapokPick(250,285);
        tapki.add(tapok1);
        tapok2 = new TapokPick(50,150);
        tapki.add(tapok2);
        tapok3 = new TapokPick(30,670);
        tapki.add(tapok3);
    }
    private void initAttackZone() {
        attackZone = new Rectangle(-50, -50, 50, 50);
    }

    private void initWalls() throws SlickException {
        background = new Image(path + "background.jpg");

        wall = new Image(path + "wall.jpg");

        obstacles.add(new Rectangle(0, SetupGame.height - floorH * 2, wallWidth, floorH * 2)); //left wall
        obstacles.add(new Rectangle(SetupGame.width - x_offset, SetupGame.height - floorH * 2, wallWidth, floorH * 2-85-floorHeight)); //right wall
        obstacles.add(new Rectangle(0, SetupGame.height - floorHeight, floorW, floorHeight)); //first floor
        obstacles.add(new Rectangle(0, SetupGame.height - floorH - floorHeight, floorW, floorHeight)); //second floor
        obstacles.add(new Rectangle(0, SetupGame.height - floorH * 2 - floorHeight * 2, floorW + 60, floorHeight * 2));//roof
        obstacles.add(new Rectangle(0, SetupGame.height, SetupGame.width, floorHeight)); //terrain
        obstacles.add(new Rectangle(-25, 0, 25, SetupGame.height)); //left frame
        obstacles.add(new Rectangle(1100, 0, 25, SetupGame.height)); //right frame
        obstacles.add(new Rectangle(0, -25, SetupGame.width, 25)); //upper frame

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
        arrow = new Image(path+"arrow.png");

        teleports = new ArrayList<>();
        teleports.add(new Teleport(110, 375, 80, 85,0,225));
        teleports.add(new Teleport(110, 600, 80, 85,0,-220));
        nextLevelT = new Teleport(1030, 630, 70, 70, 0, 0);
        teleports.add(nextLevelT);

    }

    private void initDoors() throws SlickException {
        doors.add(new Door(900, SetupGame.height-floorHeight-85,wallWidth,85, false));
    }


    private void initEnemies() throws SlickException {
        Coronavirus corona1 = new Coronavirus(950, 400);
        coronas.add(corona1);
        Coronavirus corona2 = new Coronavirus(50, 390);
        coronas.add(corona2);
        Coronavirus corona3 = new Coronavirus(200, 600);
        coronas.add(corona3);
        Coronavirus corona4 = new Coronavirus(700, 620);
        coronas.add(corona4);
        Coronavirus corona5 = new Coronavirus(400, 150);
        coronas.add(corona5);
        for(Coronavirus c: coronas){
            if(c instanceof Coronavirus){
                enemies.add(c);
                c.setSpace(150);
                c.setVisionHorizontal(150,150);
                c.setVisionVertical(20,0);
                c.setNotVisionHorizontal(150,150);
                c.setNotVisionVertical(20,0);
            }
        }

        Doctor doctor = new Doctor(900, 605);
        doctor.setSpace(150);
        doctor.setVisionHorizontal(100,100);
        doctor.setVisionVertical(50,0);
        doctor.setNotVisionHorizontal(50,100);
        doctor.setNotVisionVertical(50,0);

        enemies.add(doctor);
    }
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0, 0, 1100, 700);

        wallpaper.startUse();
        for (int a = wallWidth; a <= SetupGame.width - x_offset; a += 10) {
            wallpaper1.getSubImage(0, 0, 333, 850).drawEmbedded(a, SetupGame.height - floorH, 10, floorH - floorHeight);
            wallpaper1.getSubImage(0, 0, 333, 850).drawEmbedded(a, SetupGame.height - floorH * 2, 10, floorH - floorHeight);
        }
        wallpaper.endUse();
        wall.startUse();
        for (int a = wallWidth; a < SetupGame.width - x_offset; a += floorHeight) {
            floorSS.getSubImage(0, 0, 55, 55).drawEmbedded(a, SetupGame.height - floorHeight, floorHeight*2, floorHeight);
            floorSS.getSubImage(0, 0, 55, 55).drawEmbedded(a, SetupGame.height - floorH - floorHeight, floorHeight*2, floorHeight);
        }
        for (int a = 0; a < SetupGame.width - x_offset+50; a += floorHeight*2) {
            floorSS.getSubImage(0, 0, 110, 110).drawEmbedded(a, SetupGame.height - floorH * 2 - floorHeight * 2, floorHeight*2, floorHeight*2);
        }
        for (int a = SetupGame.height-floorHeight; a > SetupGame.height-2*floorH-2*floorHeight; a -= wallWidth) {
            wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(0, a, wallWidth, wallWidth);
            if(a<=580)
                wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(SetupGame.width-x_offset, a, wallWidth, wallWidth);
        }
        wallSS.getSubImage(0,0,85,60).drawEmbedded(SetupGame.width-x_offset,585,wallWidth,15); //above doors
        wall.endUse();

        window.draw(700, 300, 100, 100);
        window.draw(400, 300, 100, 100);
        window.draw(700, 550, 100, 100);
        window.draw(400, 550, 100, 100);
        doorUp.draw(110, 375, 80, 85);
        doorDown.draw(110, 600, 80, 85);
        sofa.draw(500, 360, 200, 100);
        nightstand.draw(440, 410, 60, 50);
        nightstand.draw(700, 410, 60, 50);
        table.draw(525, 625, 150, 60);
        wardrobe.draw(250, 495, 130, 193);
        cupboard.draw(220, 300, 150, 100);
        rockingChair.draw(30, 625, 60, 60);

        drawDoors(graphics);
        if(nextLevel)
            arrow.draw(1050,650,50,50);

        graphics.setColor(Color.pink);
       if ( babka.animationSlide()) {
           babka.getAnimation().draw(babka.getX()-25, babka.getY());
       }else{
           babka.getAnimation().draw(babka.getX(), babka.getY());
       }

        drawEnemies(graphics);
        drawTapki( graphics);

    }

    private void drawDoors(Graphics graphics) {
        for(Door door : doors){
            if (!door.isBroken()) {
                door.getImageDoor(graphics).draw(  door.getX(),   door.getY());
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
                    graphics.setColor(Color.blue);
                    corona.getAnimation(graphics).draw(corona.getX()-5, corona.getY()-5);
                }
            } else if (enemies.get(i) instanceof CoronaSmall) {
                CoronaSmall coronaS = (CoronaSmall) enemies.get(i);
                if (coronaS.isAlive()) {
                    coronaS.getAnimation(graphics).draw(coronaS.getX(), coronaS.getY(),25,25);
                }
            } else if (enemies.get(i) instanceof Turrel) {
                Turrel turrel = (Turrel) enemies.get(i);
                if (turrel.isAlive()) {
                    turrel.getImageTurrel(graphics).draw(turrel.getX(), turrel.getY(),75,80);

                }
            }
        }

        drawBullets(graphics);


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

    private void drawTapki(Graphics graphics){
        if (!tapki.isEmpty()) {
            for (TapokPick i : tapki) {
                if (i.isPresent()) {
                    i.getAnimation(graphics).draw(i.getX(), i.getY());
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
        if(nextLevel&&babka.inTeleport(nextLevelT)&&gameContainer.getInput().isKeyDown(Input.KEY_ENTER)){
            game.enterState(3, new FadeOutTransition(), new FadeInTransition());
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            game.enterState(0,new FadeOutTransition(), new FadeInTransition());
        }

        babka.controls(gameContainer);
        updateTapki();
        updateEnemies(delta);
        updateBullets();
        checkForAttack(gameContainer);

        if( gameContainer.getInput().isKeyDown(Input.KEY_ESCAPE)){
            game.enterState(1, new FadeOutTransition(),new FadeInTransition());
        }

        if(babka.isReadyToShoot(gameContainer)) bullets.add(babka.shoot(gameContainer));
        if(gameContainer.getInput().isKeyDown(Input.KEY_R)){
            enemies = new ArrayList<>();
            bullets = new ArrayList<>();
            obstacles = new ArrayList<>();
            doors = new ArrayList<>();
            coronas = new ArrayList<>();
            tapki= new ArrayList<>();
            babka = new Babka(800, 350);


            initDoors();
            initEnemies();
            initAttackZone();
            initWalls();
            initTapki();
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

   private void updateTapki(){
        if (tapki.size()!=0) {
            for (TapokPick tapok : tapki) {
                if (tapok.isPresent()) {
                    tapok.checkForCollision(babka);
                }
            }
            for (int i = 0; i < tapki.size(); i++) {
                TapokPick tapok = tapki.get(i);
                if (tapok.isPresent() == false) tapki.remove(i);
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
            else if(bullets.get(i) instanceof TapokThrow && bullets.get(i).isPresent()){
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

    private void checkForAttack(GameContainer container) {
        attackZone = babka.getHitZone(container);
        checkForAttackDoors();
        checkForAttackEnemies();

    }

    private void checkForAttackDoors(){
        for(int i = 0; i<doors.size(); i++){
            Door door = doors.get(i);
            if(attackZone.intersects(door)) door.broke() ;
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
