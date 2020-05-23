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


public class MapLevel2 extends BasicGameState {
    private Babka babka;
    private Image background,wall,wallpaper,cellarwallpaper,workshopwallpaper,window,helicopter,turret1,turret2,table;
    private Image lift21, lift22, lift1,lift31, lift32, lift4;
    private SpriteSheet wallSS,floorSS,wallpaper1, platformSS;
    private Rectangle attackZone;
    private Coronavirus corona;
    private Doctor doctor;

    private ArrayList<Rectangle> obstacles = new ArrayList<>();
    private ArrayList<Door> doors = new ArrayList<>();
    private ArrayList<Teleport> teleports;

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Injection> injections = new ArrayList<Injection>();

    private int wallWidth = 25, floorHeight = 15;
    private int floorH = 190, floorW = 900;
    private int x_offset = 200;

    private String path = SetupGame.path;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        babka = new Babka(40, 600);

        initWalls();
        initDoors();
        initEnemies();
        initAttackZone();
    }

    private void initAttackZone(){
        attackZone = new Rectangle(-50, -50, 50, 50);
    }

    private void initWalls()throws SlickException {

        background = new Image(path+"backg.jpg");

        obstacles.add(new Rectangle(0, SetupGame.height, SetupGame.width, floorHeight)); //terrain
        obstacles.add(new Rectangle(-25, 0, 25, SetupGame.height)); //left frame
        obstacles.add(new Rectangle(1100, 0, 25, SetupGame.height-90)); //right frame
        obstacles.add(new Rectangle(0, -25, SetupGame.width, 25)); //upper frame
        obstacles.add(new Rectangle(x_offset- wallWidth,110, wallWidth, 490)); //left wall
        obstacles.add(new Rectangle(SetupGame.width- wallWidth,110, wallWidth, 590)); //right wall
        obstacles.add(new Rectangle(x_offset-wallWidth,SetupGame.height- floorHeight, floorW, floorHeight)); //first floor
        obstacles.add(new Rectangle(x_offset,SetupGame.height- floorH - floorHeight, floorW, floorHeight)); //second floor
        obstacles.add(new Rectangle(x_offset,390, floorW, floorHeight)); //third floor
        obstacles.add(new Rectangle(0,85, 1100, floorHeight *2)); //roof
        obstacles.add(new Rectangle(440,250,150,40)); //platform 1
        obstacles.add(new Rectangle(740,250,150,40)); //platform 2
        obstacles.add(new Rectangle(495,400,wallWidth,10));
        obstacles.add(new Rectangle(795,400,wallWidth,10));
        for(int i=400; i<=800; i+=200){
            obstacles.add(new Rectangle(i,510,wallWidth,90)); //on the first floor
        }


        wall = new Image(path+"wall.jpg");
        wallSS = new SpriteSheet(wall,10,10);
        floorSS = new SpriteSheet(wall,10,10);
        platformSS = new SpriteSheet(wall,10,10);

        cellarwallpaper = new Image(path+"cellarwallpaper.png");
        workshopwallpaper = new Image(path+"workshopwallpaer.jpg");
        wallpaper = new Image(path+"wallpaper.jpg");
        wallpaper1 = new SpriteSheet(wallpaper,10,10);

        lift22 = new Image(path+"lift.png");
        lift1 = new Image(path+"lift.png");
        lift32 = new Image(path+"lift.png");
        lift31 = new Image(path+"lift.png");
        lift4 = new Image(path+"lift.png");
        lift21 = new Image(path+"lift.png");

        teleports = new ArrayList<>();
        teleports.add(new Teleport(980,600,80,85,0,-190)); //1
        teleports.add(new Teleport(220,410,80,85,0,-90)); //21
        teleports.add(new Teleport(980,410,80,85,0,190)); //22
        teleports.add(new Teleport(220,305,80,85,0,90)); //31
        teleports.add(new Teleport(980,305,80,85,0,-300)); //32
        teleports.add(new Teleport(980,0,80,85,0,300)); //4

        helicopter = new Image(path+"helicopter.png");
        window = new Image(path+"window.jpg");
        turret1 = new Image(path+"turret.png");
        turret2 = new Image(path+"turret.png");
        table = new Image(path+"officetable.png");
    }

    private void initDoors()throws SlickException{
        doors.add(new Door(x_offset-wallWidth,600,wallWidth,85)); //entry
        doors.add(new Door(400,SetupGame.height-floorHeight-85,wallWidth,85)); //1st floor
        doors.add(new Door(495,410,wallWidth,85)); //2nd floor
        doors.add(new Door(795,410,wallWidth,85)); //2nd floor
    }
    private void initEnemies() throws SlickException {
        Turrel t = new Turrel(900,600);
        t.setRangeOfSight(500);
        enemies.add(t);

        Turrel t1 = new Turrel(900,310);
        t1.setTimeBeforeShoot(200);
        t1.setRangeOfSight(750);
        enemies.add(t1);

        Coronavirus corona1 = new Coronavirus(250,550);
        corona1.setSpace(150);
        corona1.setVisionHorizontal(150,150);
        corona1.setVisionVertical(20,0);
        corona1.setNotVisionHorizontal(150,150);
        corona1.setNotVisionVertical(20,0);
        enemies.add(corona1);
        Coronavirus corona2 = new Coronavirus(300,570);
        corona2.setSpace(150);
        corona2.setVisionHorizontal(150,150);
        corona2.setVisionVertical(20,0);
        corona2.setNotVisionHorizontal(150,150);
        corona2.setNotVisionVertical(20,0);
        enemies.add(corona2);
        Coronavirus corona3 = new Coronavirus(500,530);
        corona3.setSpace(150);
        corona3.setVisionHorizontal(150,150);
        corona3.setVisionVertical(20,0);
        corona3.setNotVisionHorizontal(150,150);
        corona3.setNotVisionVertical(20,0);
        enemies.add(corona3);
        Coronavirus corona4 = new Coronavirus(600,530);
        corona4.setSpace(150);
        corona4.setVisionHorizontal(150,150);
        corona4.setVisionVertical(20,0);
        corona4.setNotVisionHorizontal(150,150);
        corona4.setNotVisionVertical(20,0);
        enemies.add(corona4);
        Coronavirus corona5 = new Coronavirus(920,430);
        corona5.setSpace(100);
        corona5.setVisionHorizontal(150,150);
        corona5.setVisionVertical(20,0);
        corona5.setNotVisionHorizontal(150,150);
        corona5.setNotVisionVertical(20,0);
        enemies.add(corona5);
        Coronavirus corona6 = new Coronavirus(350,430);
        corona6.setSpace(100);
        corona6.setVisionHorizontal(150,150);
        corona6.setVisionVertical(20,0);
        corona6.setNotVisionHorizontal(150,150);
        corona6.setNotVisionVertical(20,0);
        enemies.add(corona6);
        Coronavirus corona7 = new Coronavirus(800,200);
        corona7.setSpace(100);
        corona7.setVisionHorizontal(150,150);
        corona7.setVisionVertical(20,0);
        corona7.setNotVisionHorizontal(150,150);
        corona7.setNotVisionVertical(20,0);
        enemies.add(corona7);

        Doctor doctor1 = new Doctor(600,415);
        doctor1.setSpace(150);
        doctor1.setVisionHorizontal(100,100);
        doctor1.setVisionVertical(50,0);
        doctor1.setNotVisionHorizontal(50,100);
        doctor1.setNotVisionVertical(50,0);
        enemies.add(doctor1);
        Doctor doctor2 = new Doctor(500,170);
        doctor2.setSpace(60);
        doctor2.setVisionHorizontal(100,100);
        doctor2.setVisionVertical(50,0);
        doctor2.setNotVisionHorizontal(50,100);
        doctor2.setNotVisionVertical(50,0);
        enemies.add(doctor2);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0,0,1100,700);

        workshopwallpaper.startUse();
        for(int a = x_offset; a<SetupGame.width- wallWidth; a+=wallWidth){
            wallpaper1.getSubImage(0,0,333,850).drawEmbedded(a,SetupGame.height-floorH*3- wallWidth,wallWidth,285);
            wallpaper1.getSubImage(0,0,333,850).drawEmbedded(a,SetupGame.height-floorH-90- wallWidth,wallWidth,100);
        }
        workshopwallpaper.endUse();
        cellarwallpaper.draw(x_offset,510,floorW-wallWidth,180);
        wall.startUse();
        for(int a=x_offset-wallWidth; a<SetupGame.width; a+=floorHeight) {
            floorSS.getSubImage(0, 0, 55, 55).drawEmbedded(a, SetupGame.height - floorHeight, floorHeight, floorHeight); //floors
            floorSS.getSubImage(0, 0, 55, 55).drawEmbedded(a, SetupGame.height- floorH - floorHeight, floorHeight, floorHeight);
            floorSS.getSubImage(0,0,55,55).drawEmbedded(a,SetupGame.height- floorH -90- floorHeight *2,floorHeight,floorHeight);
        }
        for(int a=x_offset-60; a<SetupGame.width; a+=floorHeight*2)
            floorSS.getSubImage(0,0,110,110).drawEmbedded(a,SetupGame.height- floorH *3- floorHeight *3,floorHeight*2,floorHeight*2);

        for(int a = SetupGame.height; a>SetupGame.height-3*floorHeight -3*floorH; a-=wallWidth){
            if(!(a<=685&&a>=600))
                wallSS.getSubImage(0,0,85,85).drawEmbedded(x_offset- wallWidth,a,wallWidth,wallWidth); //walls
            wallSS.getSubImage(0,0,85,85).drawEmbedded(SetupGame.width- wallWidth,a,wallWidth,wallWidth);
        }
        for(int a=505; a<=570; a+=wallWidth){
            wallSS.getSubImage(0,0,85,85).drawEmbedded(400,a,wallWidth,wallWidth); //above doors wall
            wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(600, a, wallWidth, wallWidth); //1-st floor балки
            wallSS.getSubImage(0, 0, 85, 85).drawEmbedded(800, a, wallWidth, wallWidth);
        }
        wallSS.getSubImage(0,0,85,60).drawEmbedded(400,580,wallWidth,20); //above doors separate end of wall
        wallSS.getSubImage(0, 0, 85, 17).drawEmbedded(495, 405, wallWidth, 5);
        wallSS.getSubImage(0, 0, 85, 17).drawEmbedded(795, 405, wallWidth, 5);
        wall.endUse();

        window.draw(850,420,120,60);
        window.draw(575,420,120,60);
        window.draw(340,420,120,60);
        //window.draw(800,180,140,60);
        //window.draw(400,180,140,60);

        lift1.draw(980,600,80,85);
        lift21.draw(220,410,80,85);
        lift22.draw(980,410,80,85);
        lift32.draw(220,305,80,85);
        lift31.draw(980,305,80,85);
        lift4.draw(980,0,80,85);
        drawDoors(graphics);

        wall.startUse();
        for(int a=440; a<590; a+=wallWidth){
            platformSS.getSubImage(0,0,85,170).drawEmbedded(a,250,wallWidth,40);
            platformSS.getSubImage(0,0,85,170).drawEmbedded(a+300,250,wallWidth,40);
        }
        wall.endUse();

        helicopter.draw(250,5,250,80);

        graphics.setColor(Color.pink);
        babka.getAnimation().draw(babka.getX(), babka.getY());
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
        if (!injections.isEmpty()) {
            for (Injection i : injections) {
                if (i.isPresent()) {
                    i.getImageInjection(graphics).draw(i.getX(), i.getY());
                }
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
                    coronaS.update();
                    coronaS.getAnimation(graphics).draw(coronaS.getX(), coronaS.getY(),25,25);
                    coronaS.checkForCollisionBabka(babka);
                }
            } else if (enemies.get(i) instanceof Turrel) {
                Turrel turrel = (Turrel) enemies.get(i);
                if (turrel.isAlive()) {
                    turrel.getImageTurrel(graphics).draw(turrel.getX(), turrel.getY());
                }
            }
        }

        if (!injections.isEmpty()) {
            for (Bullet i : injections) {
                if (i.isPresent()) {
                    graphics.setColor(Color.red);
                    graphics.fill((Rectangle)i);
                }
            }
        }


    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        babka.update(1, delta);

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

        updateEnemies(delta);
        updateBullets();
        checkForAttack(gameContainer);

        if(gameContainer.getInput().isKeyDown(Input.KEY_R)){
            enemies = new ArrayList<>();
            injections = new ArrayList<>();
            obstacles = new ArrayList<>();

            babka = new Babka(120, 600);

            initWalls();
            initDoors();
            initEnemies();
            initAttackZone();
        }

    }

    private void updateEnemies(int delta) throws SlickException {
        ArrayList<Rectangle> all = new ArrayList<>();
        all.addAll(obstacles);
        all.addAll(doors);
        for(int i = 0; i<enemies.size(); i++){
            if(enemies.get(i) instanceof Doctor){
                Doctor doctor = (Doctor) enemies.get(i);
                doctor.update(delta, all);
                if (doctor.isReadyToShoot()) injections.add(doctor.shoot(babka));

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
                    corona.update(obstacles);
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
                Injection j = (Injection) injections.get(i);
                if(j.isPresent()) {
                    j.update();
                    for (Rectangle obstacle : obstacles) {
                        j.checkForCollision(obstacle);
                    }
                    for (Rectangle obstacle : doors) {
                        j.checkForCollision(obstacle);
                    }
                    for(int d = 0;d<enemies.size(); d++){
                        if(enemies.get(d) instanceof Doctor) {
                            Doctor doctor = (Doctor) enemies.get(d);
                            if(j.intersects(doctor)&&j.isReflected()){
                                doctor.die();
                                j.disappear();
                            }
                        }
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
