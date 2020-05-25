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

public class MapLevel5 extends BasicGameState {
    private Babka babka;
    private Image background, t1,t2;
    private Rectangle attackZone;

    private ArrayList<Rectangle> obstacles = new ArrayList<>();
    private ArrayList<Teleport> teleports;
    private ArrayList<Door> doors = new ArrayList<>();

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();

    private String path = SetupGame.path;
    private boolean winner=false;

    @Override
    public int getID() {
        return 6;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        babka = new Babka(50, 10);

        initDoors();
        initEnemies();
        initAttackZone();
        initWalls();
    }

    private void initAttackZone() {
        attackZone = new Rectangle(-50, -50, 50, 50);
    }

    private void initWalls() throws SlickException {
        background = new Image(path + "background.jpg");

        obstacles.add(new Rectangle(-25, 0, 25, SetupGame.height)); //left frame
        obstacles.add(new Rectangle(1100, 0, 25, SetupGame.height-90)); //right frame
        obstacles.add(new Rectangle(0, -25, SetupGame.width, 25)); //upper frame
        obstacles.add(new Rectangle(0,680,100,20)); //left ground
        obstacles.add(new Rectangle(1000,680,100,20)); //right ground
        obstacles.add(new Rectangle(0,500,100,10)); //left horizontal
        obstacles.add(new Rectangle(0,300,100,10));
        obstacles.add(new Rectangle(0,100,100,10));
        obstacles.add(new Rectangle(1000,500,100,10)); //right horizontal
        obstacles.add(new Rectangle(1000,300,100,10));
        obstacles.add(new Rectangle(1000,140,100,10));
        obstacles.add(new Rectangle(250,440,10,160)); //left vertical
        obstacles.add(new Rectangle(250,140,10,160));
        obstacles.add(new Rectangle(830,440,10,160)); //right vertical
        obstacles.add(new Rectangle(830,140,10,160));

        t1 = new Image(path+"teleport.png");
        t2 = new Image(path+"teleport.png");

        teleports = new ArrayList<>();
        teleports.add(new Teleport(10,15,80,85,1000,580)); //left
        teleports.add(new Teleport(1010,595,80,85,-1000,-580)); //right
    }

    private void initDoors() throws SlickException {
    }

    private void initEnemies() throws SlickException {
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0, 0, 1100, 700);
        t1.draw(10,15,80,85);
        t2.draw(1010,595,80,85);

        if ( babka.animationSlide()) {
            babka.getAnimation().draw(babka.getX()-25, babka.getY());
        }else {
            babka.getAnimation().draw(babka.getX(), babka.getY());
        }

        graphics.setColor(Color.yellow);
        for(Rectangle r: obstacles)
            graphics.fill(r);

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
