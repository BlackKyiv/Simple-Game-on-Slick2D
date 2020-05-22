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

    private int wallW = 10;
    private int floorH = 190, floorW = 900;
    private int x_offset = 200;

    private String path = SetupGame.path;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        babka = new Babka(120, 600, 50, 50);

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

        obstacles.add(new Rectangle(0, SetupGame.height-10, SetupGame.width, 10)); //terrain
        obstacles.add(new Rectangle(0, 0, 10, SetupGame.height)); //left frame
        obstacles.add(new Rectangle(1090,0,10,700)); //right frame
        obstacles.add(new Rectangle(0,0,1100,10)); //upper frame
        obstacles.add(new Rectangle(x_offset- wallW,110, wallW, 500)); //left wall
        obstacles.add(new Rectangle(SetupGame.width-wallW,110, wallW, 590)); //right wall
        obstacles.add(new Rectangle(x_offset,SetupGame.height- wallW, floorW, wallW)); //first floor
        obstacles.add(new Rectangle(x_offset,SetupGame.height- floorH - wallW, floorW, wallW)); //second floor
        obstacles.add(new Rectangle(x_offset,400, floorW, wallW)); //third floor
        obstacles.add(new Rectangle(0,100, 1100, wallW *2)); //roof
        obstacles.add(new Rectangle(440,250,150,40)); //platform 1
        obstacles.add(new Rectangle(740,250,150,40)); //platform 2

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
        teleports.add(new Teleport(1000,600,80,90,0,-190)); //1
        teleports.add(new Teleport(220,410,80,90,0,-90)); //21
        teleports.add(new Teleport(1000,410,80,90,0,190)); //22
        teleports.add(new Teleport(220,310,80,90,0,90)); //31
        teleports.add(new Teleport(1000,310,80,90,0,-300)); //32
        teleports.add(new Teleport(1000,10,80,90,0,300)); //4

        helicopter = new Image(path+"pictures\\helicopter.png");
        window = new Image(path+"window.jpg");
        turret1 = new Image(path+"turret.png");
        turret2 = new Image(path+"turret.png");
        table = new Image(path+"officetable.png");
    }

    private void initDoors()throws SlickException{
        doors.add(new Door(190,610,10,80));
    }
    private void initEnemies() throws SlickException {
        Turrel t = new Turrel(900,600);
        t.setRangeOfSight(500);
        enemies.add(t);

        Turrel t1 = new Turrel(900,320);
        t1.setRangeOfSight(800);
        enemies.add(t1);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0,0,1100,700);

        wall.startUse();
        for(int a=x_offset; a<SetupGame.width; a+=10) {
            floorSS.getSubImage(0, 20, 50, 50).drawEmbedded(a, SetupGame.height - wallW, 10, 10);
            floorSS.getSubImage(0, 20, 50, 50).drawEmbedded(a, SetupGame.height- floorH - wallW, 10, 10);
            floorSS.getSubImage(0,20,50,50).drawEmbedded(a,SetupGame.height- floorH -90- wallW *2,10,10);
        }

        for(int a=x_offset-50; a<SetupGame.width; a+=10)
            floorSS.getSubImage(0,20,100,70).drawEmbedded(a,SetupGame.height- floorH *3- wallW *3,10,20);
        for(int a = SetupGame.height; a>SetupGame.height-3* wallW -3* floorH; a-=10){
            if(!(a<=690&&a>=610))
                wallSS.getSubImage(0,20,50,50).drawEmbedded(x_offset- wallW,a,10,10);
            wallSS.getSubImage(0,20,50,50).drawEmbedded(SetupGame.width-wallW,a,10,10);
        }
        wall.endUse();

        workshopwallpaper.startUse();
        for(int a=x_offset; a<SetupGame.width-wallW; a+=10){
            wallpaper1.getSubImage(0,0,333,850).drawEmbedded(a,SetupGame.height-floorH*3-wallW,10,280);
            wallpaper1.getSubImage(0,0,333,850).drawEmbedded(a,SetupGame.height-floorH-90-wallW,10,90);
        }
        workshopwallpaper.endUse();
        cellarwallpaper.draw(x_offset,510,980,180);

        window.draw(800,420,140,60);
        window.draw(400,420,140,60);
        window.draw(800,180,140,180);
        window.draw(400,180,140,180);

        lift1.draw(1000,600,80,90);
        lift21.draw(220,410,80,90);
        lift22.draw(1000,410,80,90);
        lift32.draw(220,310,80,90);
        lift31.draw(1000,310,80,90);
        lift4.draw(1000,10,80,90);
        drawDoors(graphics);

        wall.startUse();
        for(int a=440; a<590; a+=10){
            platformSS.getSubImage(0,20,50,200).drawEmbedded(a,250,10,40);
            platformSS.getSubImage(0,20,50,200).drawEmbedded(a+300,250,10,40);
        }
        wall.endUse();

        turret1.draw(900,600,100,70);
        turret2.draw(900,320,100,70);
        helicopter.draw(250,20,250,80);
        table.draw(400,400,100,70);

        graphics.setColor(Color.pink);
        graphics.fill(babka);
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
                    graphics.setColor(Color.cyan);
                    graphics.fill(doctor);
                }
            } else if (enemies.get(i) instanceof Coronavirus) {
                Coronavirus corona = (Coronavirus) enemies.get(i);
                if (corona.isAlive()) {
                    graphics.setColor(Color.green);
                    graphics.fill(corona);
                }
            } else if (enemies.get(i) instanceof CoronaSmall) {
                CoronaSmall coronaS = (CoronaSmall) enemies.get(i);
                if (coronaS.isAlive()) {
                    coronaS.update();
                    graphics.setColor(Color.green);
                    graphics.fill(coronaS);
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

        updateEnemies(delta);
        updateBullets();
        checkForAttack(gameContainer);
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
