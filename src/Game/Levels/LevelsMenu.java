package Game.Levels;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class LevelsMenu  extends BasicGameState {
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if( container.getInput().isKeyDown(Input.KEY_1)){
            game.enterState(2, new FadeOutTransition(),new FadeInTransition());
        }
        if( container.getInput().isKeyDown(Input.KEY_2)){
            game.enterState(3, new FadeOutTransition(),new FadeInTransition());
        }
        if( container.getInput().isKeyDown(Input.KEY_3)){
            game.enterState(4, new FadeOutTransition(),new FadeInTransition());
        }
        if( container.getInput().isKeyDown(Input.KEY_4)){
            game.enterState(5, new FadeOutTransition(),new FadeInTransition());
        }
        if( container.getInput().isKeyDown(Input.KEY_5)){
            game.enterState(6, new FadeOutTransition(),new FadeInTransition());
        }
    }
}
