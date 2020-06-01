package Game.Levels;

import Game.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;

import javax.management.monitor.CounterMonitor;
import java.lang.management.MemoryUsage;

public class MainMenu extends BasicGameState {


    private  Image studios, presents, background, play;
    private MouseOverArea playMOA;
    private int timePassed=0;
    private Music entryMusic, music;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        music = SetupGame.entryMusic;
        music.loop();
        music.setVolume(0.4f);
        studios = new Image(SetupGame.path+"studios.png");
        presents = new Image(SetupGame.path+"presents.png");
        background = new Image(SetupGame.path+"main_menu_background.png");
        play = new Image(SetupGame.path+"play.png");
        playMOA = new MouseOverArea(container,play,850,600);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if(timePassed<3000)
            studios.draw(0,0,1100,700);
        if(timePassed>3000)
            presents.draw(0,0,1100,700);
        if(timePassed>7000) {
            music.setVolume(0.3f);
            background.draw(0, 0, 1100, 700);
            play.draw(850, 600);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(timePassed<7100)
            timePassed += delta;
        if(playMOA.isMouseOver() && Mouse.isButtonDown(0)){
            game.enterState(1, new FadeOutTransition(),new FadeInTransition());
        }


    }
}
