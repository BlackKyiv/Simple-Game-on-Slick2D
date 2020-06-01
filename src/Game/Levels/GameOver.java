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
    
    Image background, replay, levels;
    private MouseOverArea replayMOA, levelsMOA;
    private Music music;
    private static int enterState;

    @Override
    public int getID() {
        return 8;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        music = SetupGame.entryMusic;
        background = new Image(SetupGame.path+"background_game_over.png");
        replay = new Image(SetupGame.path+"replay.png");
        replayMOA = new MouseOverArea(container, replay,800,600);

        levels = new Image(SetupGame.path+"levels_menu.png");
        levelsMOA = new MouseOverArea(container,levels,50,600);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0,0,1100,700);
        replay.draw(800,600,200,40);
        levels.draw(50,600,300,35);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(replayMOA.isMouseOver()&& Mouse.isButtonDown(0)){
            game.enterState( enterState, new FadeOutTransition(),new FadeInTransition());
            switch (enterState){
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
