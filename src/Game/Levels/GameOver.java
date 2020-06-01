package Game.Levels;

import Game.SetupGame;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameOver extends BasicGameState {

    String path = SetupGame.path, pathMusic = SetupGame.pathMusic;
    Image background, replay, levels;
    private MouseOverArea replayMOA, levelsMOA;
    private Music music;
    private static int  enterState;

    @Override
    public int getID() {
        return 8;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        music = SetupGame.entryMusic;
        background = new Image(path+"background_game_over.png");
        replay = new Image(path+"replay.png");
        replayMOA = new MouseOverArea(container, replay,800,600);

        levels = new Image(path+"levels_menu.png");
        levelsMOA = new MouseOverArea(container,levels,10,590);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        background.draw(0,0,1100,700);
        replay.draw(800,600,200,40);
        levels.draw(10,590,300,50);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(replayMOA.isMouseOver()&& Mouse.isButtonDown(0)){
            game.enterState( enterState, new FadeOutTransition(),new FadeInTransition());
            music=SetupGame.levelMusic;
            music.loop();
            music.setVolume(0.2f);
        }
        if(levelsMOA.isMouseOver()&& Mouse.isButtonDown(0)){

            game.enterState(1, new FadeOutTransition(), new FadeInTransition()); //if level5 then winner
            music = SetupGame.entryMusic;
            music.loop();

        }
    }

    public static void setReplayLevel(int i){
        enterState = i;
    }

}
