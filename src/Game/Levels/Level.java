package Game.Levels;

import Game.Babka;
import Game.enemies.*;
import Game.Clock;
import Game.Timer;
import Game.SetupGame;
import Game.interactiveObjects.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.Transition;

import java.util.ArrayList;

public abstract class Level extends BasicGameState {
    private int id;

    private Babka babka;

    private boolean readyToGoNextLevel = false;
    private Rectangle exitNextLevel;

    private int nextLevelId = 0;
    private Symbol symbol;
    private Clock clock;
    private int score = 0;
    private int stars =0;
    private Image tapok;
    Music gameOverMusic;
    Timer t = new Timer (1000);

    private boolean drawObstacles = false;

    private Rectangle attackZone;
    private ArrayList<Rectangle> obstacles = new ArrayList<>();
    private ArrayList<Door> doors = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<TapokPick> tapki = new ArrayList<>();
    private ArrayList<Teleport1> teleports = new ArrayList<>();

    private GameContainer gameContainer;
    private StateBasedGame game;
    

    protected void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    protected void setSymbol(Symbol symbol){
        this.symbol = symbol;
    }

    protected boolean isSymbolPresent(){
        return symbol.isPresent();
    }

    protected void addTapok(TapokPick tapok){
        tapki.add(tapok);
    }

    protected void addTeleport(Teleport1 teleport){
        teleports.add(teleport);
    }

    protected void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

    protected void addObstacle(Rectangle obstacle){
        obstacles.add(obstacle);
    }

    protected void addDoor(Door door){
        doors.add(door);
    }

    protected void setBabka(Babka babka){
        this.babka = babka;
    }

    protected void setExitNextLevel(float x, float y, float width, float height){
        exitNextLevel = new Rectangle(x, y, width, height);
    }


    protected void setId(int id){
        this.id = id;
    }

    protected void setNextLevelId(int id){
        nextLevelId = id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        obstacles = new ArrayList<>();
        doors = new ArrayList<>();
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        tapki = new ArrayList<>();
        teleports = new ArrayList<>();
        gameContainer = container;
        this.game = game;
        initLevel(container, game);
        initAttackZone();
        initScoreTable();
        gameOverMusic = new Music(SetupGame.pathMusic+"directed by.wav");
    }

    private void initAttackZone(){
        attackZone = new Rectangle(-50, -50, 50, 50);
    }

    protected abstract void initLevel(GameContainer container, StateBasedGame game) throws SlickException;


    private void initScoreTable() throws SlickException {
        tapok = new Image(SetupGame.path + "tapok_score_table.png");
        clock = new Clock();
        clock.start();
    }
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        renderLevel(container, game, g);
        drawDoors(g);
        drawEnemies(g);
        drawBullets(g);
        drawTapki(g);
        drawBabka();
        drawScoreTable(g);
        drawSymbol(g);
        g.setColor(Color.yellow);
    }

    protected void drawObstacles(Graphics g){
        for(Rectangle obstacle: obstacles){
            g.fill(obstacle);
        }
    }

    private void drawSymbol(Graphics graphics) {
        if (symbol.isPresent()) {
            symbol.getAnimation(graphics).draw(symbol.getX(), symbol.getY());
        }
    }

    private void drawBabka() throws SlickException {
        if (babka.animationSlide()) {
            babka.getAnimation().draw(babka.getX()-25, babka.getY());
        }else{
            babka.getAnimation().draw(babka.getX(), babka.getY());
        }
    }

    private void drawScoreTable(Graphics graphics) {
        tapok.draw(900, 28);
        graphics.setColor(Color.black);
        graphics.drawString("X" + babka.tapkiLeft(), 925, 25);

        graphics.drawString("Time:"+(int)(clock.getPassedTime()/1000)+"s", 955, 25);
    }

    private void drawDoors(Graphics graphics) {
        for(Door door : doors){
            if (!door.isBroken()) {
                door.getImageDoor(graphics).draw(  door.getX(),   door.getY());
            }
        }
    }

    protected void setDrawObstacles(boolean drawObstacles){
        this.drawObstacles = drawObstacles;
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

    protected abstract void renderLevel(GameContainer container, StateBasedGame game, Graphics g);

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        t.update(delta);
        updateBabka(delta, container);
        checkForAttack(container);
        updateLevel(container,game,delta);
        updateEnemies(delta);
        updateBullets();
        updateTapki();
        updateTeleport(container);
        updateStars();

        if(container.getInput().isKeyDown(Input.KEY_ESCAPE)){
            SetupGame.entryMusic.loop();
            game.enterState(1,new FadeOutTransition(), new FadeInTransition());

        }

        if(readyToGoNextLevel && babka.intersects(exitNextLevel)){
            System.out.println("Our id"+id+" Next level id:"+nextLevelId);
            LevelScore.setLevelScore (stars);
            LevelScore.setNextLevel(id);
            restart();
            if (id==6){
                game.enterState(7);
            }else {
                game.enterState(10);
            }


        }

        clock.update(delta);

        updateSymbol();

        if(!babka.isAlive()){
            gameOverMusic.loop();
            GameOver.setReplayLevel(getID());
            if (t.isFinished()){
                game.enterState(8);

                restart();
            }


        }

        if(babka.getCenterY()>SetupGame.height){
            babka.die();
            gameOverMusic.loop();
            GameOver.setReplayLevel(this.id);
            game.enterState(8);
            restart();
        }

    }

    private void updateStars(){
        if (clock.getPassedTime()<=30000){
            stars=3;
        }
        if (clock.getPassedTime()>30000&&clock.getPassedTime()<=45000){
            stars=2;
        }
        if (clock.getPassedTime()>45000&&clock.getPassedTime()<=60000){
            stars=1;
        }
        if (clock.getPassedTime()>60000){
            stars=0;
        }
        LevelScore.setLevelTime((int)(clock.getPassedTime()/1000));

    }


    private void updateSymbol() {
        if (symbol.isPresent()) {
            symbol.checkForCollision(babka);
        }

    }

    public void restart() throws SlickException {
        t = new Timer(1000);
        readyToGoNextLevel = false;
        enemies = new ArrayList<>();
        obstacles = new ArrayList<>();
        doors = new ArrayList<>();
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        tapki = new ArrayList<>();
        teleports = new ArrayList<>();


        initLevel(this.gameContainer, this.game);

        initAttackZone();

        score=0;
        clock.restart();

    }

    protected void addEnemy(ArrayList<Enemy> enemies){
        this.enemies.addAll(enemies);
    }

    private void updateTeleport(GameContainer container){
        for(Teleport1 teleport: teleports){
            if(container.getInput().isKeyDown(Input.KEY_ENTER))babka = teleport.teleport(babka, container);
        }
    }

    private void updateBabka(int delta, GameContainer container) throws SlickException {
        babka.update(1, delta);
        for (Rectangle obstacle : obstacles) {
            babka.checkForCollision(obstacle);
        }
        for (Door door : doors){
            if(!door.isBroken())babka.checkForCollision(door);
        }
        babka.controls(container);

        if(babka.isReadyToShoot(container)){
            bullets.add(babka.shoot(container));
        }

    }

    private void updateEnemies(int delta) throws SlickException {
        ArrayList<Rectangle> all = new ArrayList<>();
        all.addAll(obstacles);
        all.addAll(doors);

        for(int i = 0; i<enemies.size(); i++){
            if(enemies.get(i) instanceof Doctor && enemies.get(i).isAlive()){
                Doctor doctor = (Doctor) enemies.get(i);
                doctor.update(delta, all);
                if (doctor.isReadyToShoot()) bullets.add(doctor.shoot(babka));

                for (Rectangle obstacle : all) {
                    doctor.checkForCollisionWall(obstacle);
                }

                for(Rectangle door: doors){
                    doctor.checkForCollisionWall(door);
                }
                doctor.checkForCollisionBabka(babka);
            }
            else if(enemies.get(i) instanceof Coronavirus && enemies.get(i).isAlive()){
                Coronavirus corona = (Coronavirus) enemies.get(i);

                if(corona.isAlive()) {
                    corona.update(all);
                    for (Rectangle obstacle : all) {
                        corona.checkForCollisionWall(obstacle);
                    }
                    corona.checkForCollisionBabka(babka);
                    if(corona.intersects(babka)&&corona.isAlive()){
                        babka.die();
                        t.start();
                    }

                }
            }
            else if(enemies.get(i) instanceof CoronaSmall && enemies.get(i).isAlive()){
                CoronaSmall coronaS = (CoronaSmall) enemies.get(i);
                if(coronaS.isAlive()) {
                    coronaS.update();
                    coronaS.checkForCollisionBabka(babka);
                    if(babka.intersects(coronaS)&&coronaS.isAlive()) {
                        babka.die();
                        t.start();
                    }
                }

            }
            else if(enemies.get(i) instanceof Turrel && enemies.get(i).isAlive()){
                Turrel turrel = (Turrel) enemies.get(i);
                turrel.update(delta);
                turrel.checkForCollisionBabka(babka);
                if(turrel.isReadyToShoot(babka)) bullets.add(turrel.shoot());
            }
           else if(enemies.get(i) instanceof Boss){

           }
           else {
                enemies.remove(i);
                i--;
           }

        }

    }

    public Rectangle getAttackZone() {
        return attackZone;
    }

    private void updateTapki(){
        if (tapki.size()!=0) {
            for (TapokPick tapok : tapki) {
                if (tapok.isPresent() && babka.isReadyToPickTapok()) {
                    if(babka.intersects(tapok)){
                        babka.pickTapok();
                        tapok.setPresent(false);
                    }
                }
            }
            for (int i = 0; i < tapki.size(); i++) {
                TapokPick tapok = tapki.get(i);
                if (!tapok.isPresent()) tapki.remove(i);
            }
        }
    }

    private void updateBullets(){
        for (int i = 0; i< bullets.size(); i++) {
            if(bullets.get(i) instanceof Injection && bullets.get(i).isPresent()){
                Injection j = (Injection) bullets.get(i);
                j.update();
                if(j.intersects(attackZone)){
                    j.reflect();
                    bullets.set(i, j);
                }
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
                if(j.intersects(babka)){
                    babka.die();
                    t.start();
                }
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
                    if(!(enemies.get(d) instanceof Turrel) &&  !(enemies.get(d) instanceof Boss) && tapok.intersects((Shape) enemies.get(d))&&tapok.isPresent()){
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

    protected abstract void  updateLevel(GameContainer container, StateBasedGame game, int delta) throws SlickException;

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
           if(enemies.get(i) instanceof Boss){
            Boss boss = (Boss)enemies.get(i);
               if(enemies.get(i).isAlive() && attackZone.intersects(boss.getZoneAttack())) {
                   boss.attacked();
               }
           }
           else if(enemies.get(i).isAlive() && attackZone.intersects( (Rectangle) enemies.get(i))){
                enemies.get(i).die();
           }

        }
    }

    protected ArrayList<Bullet> getBullets(){
        return bullets;
    }

    protected boolean isReadyToGoNextLevel(){
        return readyToGoNextLevel;
    }

    protected int getQuantityOfEnemiesAlive(){
        return enemies.size();
    }

    protected int getQuantityOfTapoksLeftOnLevel(){
        return tapki.size();
    }

    protected void giveBabkeTapok(){
        babka.pickTapok();
    }

    protected void giveBabkeTapok(int q){
        babka.pickTapok(q);
    }

    protected Babka getBabka(){
        return babka;
    }

    protected void setReadyToGoNextLevel(boolean readyToGoNextLevel){
        this.readyToGoNextLevel = true;
    }
}
