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
    private MouseOverArea mainMOA, levelsMOA;
    private Music music;

    @Override
    public int getID() {
        return 7;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        background = new Image(SetupGame.path+"menu_background_game_won.png");


        main = new Image(SetupGame.path+"main_menu.png");
        mainMOA = new MouseOverArea(container, main,780,600);

        levels = new Image(SetupGame.path+"levels_menu.png");
        levelsMOA = new MouseOverArea(container,levels,50,600);

        babkaWon = new Image(SetupGame.path+"babka_won.png");
        symbol = new Image(SetupGame.path+"symbol_5.png");

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0,0,1100,700);

        main.draw(780,600,300,40);
        levels.draw(50,600,300,35);
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
