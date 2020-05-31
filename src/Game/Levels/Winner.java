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

    private Image background, main,levels, babkaWon,symbol;
    private static int levelScore=1;
    private int levelID=2;
    private static int levelTime=0;
    private String path= SetupGame.path;
    private MouseOverArea mainMOA, levelsMOA;
    private Music music;

    @Override
    public int getID() {
        return 7;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        background = new Image(path+"menu_background_game_won.png");


        main = new Image(path+"main_menu.png");
        mainMOA = new MouseOverArea(container, main,800,590);

        levels = new Image(path+"levels_menu.png");
        levelsMOA = new MouseOverArea(container,levels,10,590);

        babkaWon = new Image(path+"babka_won.png");
        symbol = new Image(path+"symbol_5.png");

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0,0,1100,700);

        main.draw(780,590,300,50);
        levels.draw(10,590,300,50);
        babkaWon.draw(350,10,645,655);
        symbol.draw(250,100,200,150);

        g.setColor(Color.black);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(mainMOA.isMouseOver()&& Mouse.isButtonDown(0)){
            game.enterState(0, new FadeOutTransition(), new FadeInTransition()); //if level5 then winner
            music = SetupGame.entryMusic;
            music.loop();
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



}
