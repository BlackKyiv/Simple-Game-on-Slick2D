package Game;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Level1 extends BasicGameState {
    private Babka babka;
    private Rectangle terrain;
    private Rectangle platform2;
    private Rectangle leftWall,rightWall,firstFloor,secondFloor,roof;
    private Image background,wall,wallpaper,window,door,sofa,table,wardrobe,cupboard,nightstand;
    private SpriteSheet leftW,rightW,firstF,secondF,roofF,wallpaper1;

    private static final int GROUND=80;
    private int wallW =10;
    private int floorH =225, floorW =900;
    private int x_offset=100;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = new Image("..\\Game\\Pictures\\backg.jpg");

        babka = new Babka(900, 300, 50, 50);

        terrain = new Rectangle(0, SetupGame.height-10, SetupGame.width, 10);
        platform2 = new Rectangle(0, 0, 20, SetupGame.height);

        wall = new Image("..\\Game\\Pictures\\wall.jpg");
        leftWall = new Rectangle(x_offset- wallW,SetupGame.height- floorH *2, wallW, floorH *2);
        rightWall = new Rectangle(SetupGame.width-x_offset,SetupGame.height- floorH *2-80, wallW, floorH *2);
        firstFloor = new Rectangle(x_offset,SetupGame.height- wallW, floorW, wallW);
        secondFloor = new Rectangle(x_offset,SetupGame.height- floorH - wallW, floorW, wallW);
        roof = new Rectangle(x_offset,SetupGame.height- floorH *2- wallW *2, floorW, wallW *2);

        leftW = new SpriteSheet(wall,10,10);
        rightW = new SpriteSheet(wall,10,10);
        firstF = new SpriteSheet(wall,10,10);
        secondF = new SpriteSheet(wall,10,10);
        roofF = new SpriteSheet(wall,50,50);

        wallpaper = new Image("..\\Game\\Pictures\\wallpaper.jpg");
        wallpaper1 = new SpriteSheet(wallpaper,100,100);

        window = new Image("..\\Game\\Pictures\\window.jpg");
        door = new Image("..\\Game\\Pictures\\door.jpg");
        sofa = new Image("..\\Game\\Pictures\\sofa.png");
        table = new Image("..\\Game\\Pictures\\table.png");
        wardrobe = new Image("..\\Game\\Pictures\\wardrobe.png");
        cupboard = new Image("..\\Game\\Pictures\\cupboard.png");
        nightstand = new Image("..\\Game\\Pictures\\nightstand.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.fill(platform2);
        graphics.fill(terrain);
        background.draw(0,0,1100,700);

        wall.startUse();
        for(int a=x_offset; a<SetupGame.width-x_offset; a+=10) {
            firstF.getSubImage(0, 20, 50, 50).drawEmbedded(a, SetupGame.height - wallW, 10, 10);
            secondF.getSubImage(0, 20, 50, 50).drawEmbedded(a, SetupGame.height- floorH - wallW, 10, 10);
            roofF.getSubImage(0,20,100,70).drawEmbedded(a,SetupGame.height- floorH *2- wallW *2,10,20);
        }
        for(int a = SetupGame.height; a>SetupGame.height-2* wallW -2* floorH; a-=10){
            leftW.getSubImage(0,20,50,50).drawEmbedded(x_offset- wallW,a,10,10);
            rightW.getSubImage(0,20,50,50).drawEmbedded(SetupGame.width-x_offset,a,10,10);
        }
        wall.endUse();

        wallpaper.startUse();
        for(int a=x_offset; a<SetupGame.width-x_offset; a+=10){
            wallpaper1.getSubImage(0,0,333,850).drawEmbedded(a,SetupGame.height-floorH,10,floorH-wallW);
            wallpaper1.getSubImage(0,0,333,850).drawEmbedded(a,SetupGame.height-floorH*2,10,floorH-wallW);
        }
        wallpaper.endUse();

        window.draw(800,300,100,100);
        window.draw(500,300,100,100);
        window.draw(800,550,100,100);
        window.draw(500,550,100,100);
        door.draw(200,330,80,135);
        door.draw(200,555,80,135);
        sofa.draw(600,365,200,100);
        nightstand.draw(540,415,60,50);
        nightstand.draw(800,415,60,50);
        table.draw(625,630,150,60);
        wardrobe.draw(350,500,130,193);
        cupboard.draw(320,300,150,100);

        graphics.setColor(Color.pink);
        graphics.fill(babka);
        graphics.setColor(Color.black);
        graphics.drawString("Vertical Speed:"+Math.abs(babka.getSpeedY())+" m/s", 50,50);
        graphics.drawString("Landed:"+babka.isLanded(), 50,70);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        babka.update();
        babka.checkForCollision(terrain);
        babka.checkForCollision(platform2);
        babka.checkForCollision(rightWall);
        babka.checkForCollision(leftWall);
        babka.checkForCollision(firstFloor);
        babka.checkForCollision(secondFloor);
        babka.checkForCollision(roof);
        babka.controls(gameContainer);
    }


}
