package Game.Levels;

import Game.SetupGame;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class LevelScore extends BasicGameState {

    private Image background, stars0,stars1,stars2,stars3, next,levels, score, time, symbol, result1,result2,result3,result0;
    private static int levelScore=0;
    private static int levelID;
    private static int levelTime=0;
    private String path= SetupGame.path;
    private MouseOverArea nextMOA, levelsMOA;
    private Music music;

    @Override
    public int getID() {
        return 10;
    }

    public static void setLevelID(int id){
        levelID = id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        background = new Image(path+"menu_background_game_won.png");

          stars0 = new Image(path+"stars_0.png");
            stars1 = new Image(path+"stars_1.png");
            stars2 = new Image(path+"stars_2.png");
           stars3 = new Image(path+"stars_3.png");


        result0 = new Image(path+"passed.png");
        result1 = new Image(path+"good.png");
        result2 = new Image(path+"well.png");
        result3 = new Image(path+"excellent.png");

        next = new Image(path+"next.png");
        nextMOA = new MouseOverArea(container,next,900,600);

        levels = new Image(path+"levels_menu.png");
       levelsMOA = new MouseOverArea(container,levels,50,600);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0,0,1100,700);
        next.draw(900,600,180,55);
        levels.draw(50,600,300,35);
        switch(levelScore){
            case 0:
                stars0.draw(335,100,430,150);
                result0.draw(130,300,826,82);
                break;
                case 1:
                    stars1.draw(335,100,430,150);
                    result1.draw(150,300,826,82);
                    break;
                case 2:
                    stars2.draw(335,100,430,150);
                    result2.draw(150,300,826,82);
                    break;
                case 3:
                    stars3.draw(335,100,430,150);
                    result3.draw(130,300,826,82);
                    break;
            }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(nextMOA.isMouseOver()&& Mouse.isButtonDown(0)){
            if(levelID<6) {
                game.enterState(levelID + 1, new FadeOutTransition(), new FadeInTransition()); //next level
                switch (levelID+1){
                    case 2:
                        SetupGame.level1Music.loop();
                        SetupGame.level1Music.setVolume(0.2f);
                        break;
                    case 3:
                        SetupGame.level2Music.loop();
                        SetupGame.level2Music.setVolume(0.2f);
                        break;
                    case 4:
                        SetupGame.level3Music.loop();
                        SetupGame.level3Music.setVolume(0.2f);
                        break;
                    case 5:
                        SetupGame.level4Music.loop();
                        SetupGame.level4Music.setVolume(0.2f);
                        break;
                    case 6:
                        SetupGame.level5Music.loop();
                        SetupGame.level5Music.setVolume(0.2f);
                        break;

                }
            }
            if(levelID==6) {
                game.enterState(9, new FadeOutTransition(), new FadeInTransition()); //if level5 then winner
            }
        }
        if(levelsMOA.isMouseOver()&& Mouse.isButtonDown(0)){
            game.enterState(1, new FadeOutTransition(), new FadeInTransition()); //if level5 then winner
            music = SetupGame.entryMusic;
            music.loop();
        }
        if(container.getInput().isKeyDown(Input.KEY_ESCAPE)){
            game.enterState(1, new FadeOutTransition(),new FadeInTransition()); //level menu
        }
    }


    public static void setLevelScore(int l){
        levelScore=l;
    }
    public static void setNextLevel(int l){
        levelID=l;
    }

    public static void setLevelTime(int t) {
        levelTime = t;
    }

}
