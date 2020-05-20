package Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



    public class MapLevel1 extends BasicGameState {
        private Babka babka;
        private Coronavirus corona;
        private Rectangle terrain;
        private Rectangle platform;
        private Rectangle platform1;
        private Rectangle platform2;
        private Rectangle platform3;

        @Override
        public int getID() {
            return 0;
        }

        @Override
        public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
            babka = new Babka(50, 50, 50, 50);
            corona = new Coronavirus(350, 550, 50, 50);
            terrain = new Rectangle(0, SetupGame.height-100, SetupGame.width, 100);
            platform = new Rectangle(0, SetupGame.height-100, SetupGame.width,20);
            platform1 = new Rectangle(SetupGame.width/2, SetupGame.height - 500, 100, 300);
            platform2 = new Rectangle(0, 0, 20, SetupGame.height);
            platform3 = new Rectangle(0, platform.getY()-100, 100, 100);
            platform2 = new Rectangle(0, 0, 20, SetupGame.height);
        }

        @Override
        public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
            graphics.setColor(Color.darkGray);
            graphics.fill(terrain);
           // graphics.setColor(Color.green);
           // graphics.fill(platform);
            graphics.setColor(Color.red);
            graphics.fill(platform1);
            graphics.fill(platform3);
            graphics.setColor(Color.white);
            graphics.fill(babka);
            graphics.setColor(Color.green);
            graphics.fill(corona);
            graphics.drawString("Vertical Speed:"+Math.abs(babka.getSpeedY())+" m/s", 50,50);
            graphics.drawString("Landed:"+babka.isLanded(), 50,70);
        }

        @Override
        public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
            babka.update(1);
            babka.checkForCollision(platform);
            babka.checkForCollision(platform1);
            babka.checkForCollision(platform2);
            babka.checkForCollision(platform3);
            babka.checkForCollision(corona);
            babka.controls(gameContainer);
           corona.update();
            corona.checkForCollision(platform,false, true);
            corona.checkForCollision(platform1,false,true);
            corona.checkForCollision(platform2,false,true);
            corona.checkForCollision(platform3,false,false);
            corona.checkForCollision(babka, true,true);
        }


}
