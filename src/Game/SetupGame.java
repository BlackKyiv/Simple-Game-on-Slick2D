package Game;

import Game.Levels.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SetupGame extends StateBasedGame {

    public final static int width = 1100;
    public final static int height = 700;
    public final static int fps = 60;
    public final static String path = "C:\\Users\\atcat\\Documents\\Goptsii game 2\\Game\\pictures\\";

    public SetupGame(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new MainMenu());
        this.addState(new LevelsMenu());
        this.addState(new MapLevel1());
        this.addState(new MapLevel2());
        this.addState(new MapLevel3());

    }

    public static void main(String[] args) throws SlickException {
        System.out.println(path);
        AppGameContainer app = new AppGameContainer(new SetupGame("Corona vs Granny"));
        app.setDisplayMode(width, height, false);
        app.setTargetFrameRate(fps);
        app.setAlwaysRender(true);
        app.start();
    }
}
