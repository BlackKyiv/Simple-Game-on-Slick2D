package Game;

import Game.Levels.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class SetupGame extends StateBasedGame {

    public final static int width = 1100;
    public final static int height = 700;
    public final static int fps = 60;
    public final static String path =  "C:\\Users\\atcat\\Documents\\Goptsii game 2\\Game\\pictures\\";
    public final static String pathMusic ="C:\\Users\\atcat\\Documents\\Goptsii game 2\\Game\\music\\";
    public static Music entryMusic;
    public static Music levelMusic;
    public static Music level1Music;
    public static Music level2Music;
    public static Music level3Music;
    public static Music level4Music;
    public static Music level5Music;
    public static Music gameOverMusic;
    public static Music winMusic;
    public static Sound hitSound;
    public static Sound throwSound;
    public static Sound coronaSound;
    public static Sound doctorSound;
    public static Sound windowSound;
    public static Sound doorSound;
    public static Sound pickSound;
    public static Sound teleportSound;


    public static Sound pickedSymbol;

    public void initMusic(){

    }


    static {
        try {
            entryMusic = new Music(pathMusic + "entryMusic.wav");
            levelMusic = new Music(pathMusic+"Intro Theme.wav");
            level1Music = new Music(pathMusic+"level1Music.wav");
            level2Music = new Music(pathMusic+"level2Music.wav");
            level3Music = new Music(pathMusic+"level3Music.wav");
            level4Music = new Music(pathMusic+"level4Music.wav");
            level5Music = new Music(pathMusic+"corona time.wav");

            gameOverMusic = new Music(pathMusic+"directed by.wav");
            winMusic = new Music(pathMusic+"winningMusic.wav");
            pickedSymbol = new Sound(pathMusic+"ta da.wav");

            pickSound = new Sound(pathMusic + "pick.wav");
            hitSound = new Sound(pathMusic + "hit.wav");
            doorSound = new Sound(pathMusic + "door.wav");
            doctorSound = new Sound(pathMusic + "doctor.wav");
            windowSound = new Sound(pathMusic + " window.wav");
            coronaSound = new Sound(pathMusic + "corona.wav");
            throwSound = new Sound(pathMusic + "throw.wav");
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }



    static {
        try {
            entryMusic = new Music(pathMusic + "entryMusic.wav");
            levelMusic = new Music(pathMusic+"Intro Theme.wav");
            gameOverMusic = new Music(pathMusic+"directed by.wav");
            hitSound = new Sound(pathMusic+"hit.wav");
            throwSound = new Sound(pathMusic+"throw.wav");
            doctorSound = new Sound(pathMusic+"doctor.wav");
            coronaSound = new Sound(pathMusic+"corona.wav");
            doorSound = new Sound(pathMusic+"door.wav");
            windowSound = new Sound(pathMusic+"window.wav");
            teleportSound = new Sound(pathMusic+"teleport.wav");

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    public SetupGame(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {

        this.addState(new MainMenu());
        this.addState(new LevelsMenu());
        this.addState(new MapLevel1());
        this.addState(new MapLevel4());
        this.addState(new MapLevel3());
        this.addState(new MapLevel2());
        this.addState(new MapLevel5());
        this.addState(new GameOver());
        this.addState(new Winner());
        this.addState(new LevelScore());

    }

    public static void main(String[] args) throws SlickException {
        System.out.println(path);
        AppGameContainer app = new AppGameContainer(new SetupGame("Grandma vs Corona"));
        app.setDisplayMode(width, height, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(fps);
        app.setAlwaysRender(true);
        app.start();
    }
}
