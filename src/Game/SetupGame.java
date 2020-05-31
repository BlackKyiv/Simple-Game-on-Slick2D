package Game;

import Game.Levels.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SetupGame extends StateBasedGame {

    public final static int width = 1100;
    public final static int height = 700;
    public final static int fps = 60;
    public final static String path =  "..\\Game\\pictures\\";
    public final static String pathMusic ="..\\Game\\music\\";
    public static Music entryMusic;
    public static Music levelMusic;
    public static Music gameOverMusic;

    static {
        try {
            entryMusic = new Music(pathMusic + "entryMusic.wav");
            levelMusic = new Music(pathMusic+"Intro Theme.wav");
            gameOverMusic = new Music(pathMusic+"directed by.wav");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public SetupGame(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {

        /*this.addState(new MainMenu());
        this.addState(new MapLevel1());
        this.addState(new MapLevel2());

         */
        this.addState(new LevelsMenu());
        this.addState(new MapLevel3());
        this.addState(new GameOver());
        /*this.addState(new LevelsMenu());
        this.addState(new MapLevel4());
        this.addState(new MapLevel5());
        this.addState(new LevelScore());
        this.addState(new GameOver());
        this.addState(new Winner());

         */
    }

    public static void main(String[] args) throws SlickException {
        System.out.println(path);
        AppGameContainer app = new AppGameContainer(new SetupGame("Grandma vs Corona"));
        app.setDisplayMode(width, height, false);
        app.setTargetFrameRate(fps);
        app.setAlwaysRender(true);
        app.start();
    }
}
