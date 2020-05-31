package Game.Levels;

import Game.SetupGame;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Winner extends BasicGameState {

    private Image background, stars0,stars1,stars2,stars3, next,levels, score, time, symbol;
    private static int levelScore=1;
    private int levelID=2;
    private static int levelTime=0;
    private String path= SetupGame.path;
    private MouseOverArea nextMOA, levelsMOA;
    private Music music;

    @Override
    public int getID() {
        return 7;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        background = new Image(path+"menu_background_game_won.png");

        stars0 = new Image(path+"stars_0.png");
        stars1 = new Image(path+"stars_1.png");
        stars2 = new Image(path+"stars_2.png");
        stars3 = new Image(path+"stars_3.png");

        /*switch(levelID){
            case 2: symbol = new Image(path+"symbol_1.png");
                break;
            case 3: symbol = new Image(path+"symbol_2.png");
                break;
            case 4: symbol = new Image(path+"symbol_3.png");
                break;
            case 5: symbol = new Image(path+"symbol_4.png");
                break;
            case 6: symbol = new Image(path+"symbol_5.png");
                break;
        }*/
        next = new Image(path+"next.png");
        nextMOA = new MouseOverArea(container,next,900,600);

        levels = new Image(path+"levels_menu.png");
        levelsMOA = new MouseOverArea(container,levels,10,590);
        // score = new Image(path+"score.png");
        //  time = new Image(path+"time.png");

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0,0,1100,700);
        // symbol.draw(950,20,130,100);
        next.draw(900,600,180,55);
        levels.draw(10,590,300,50);
        switch(levelScore){
            case 0:
                stars0.draw(335,100,430,150);
                break;

            case 1:
                stars1.draw(335,100,430,150);
                break;
            case 2:
                stars2.draw(335,100,430,150);
                break;
            case 3:
                stars3.draw(335,100,430,150);
                break;
        }

        //  score.draw(420,270,226,50);
        g.setColor(Color.black);
        //  g.drawString(String.valueOf(levelScore),660,270);
        //  time.draw(450,400,176,50);
        //   g.drawString(String.valueOf(levelTime),660,400);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(nextMOA.isMouseOver()&& Mouse.isButtonDown(0)){
            if(levelID<6) {
                game.enterState(levelID + 1, new FadeOutTransition(), new FadeInTransition()); //next level
                music = SetupGame.levelMusic;
                music.loop();
            }
            if(levelID==6) {
                game.enterState(9, new FadeOutTransition(), new FadeInTransition()); //if level5 then winner
                //other music.loop
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

    public static void setLevelTime(int t) {
        levelTime = t;
    }

}
