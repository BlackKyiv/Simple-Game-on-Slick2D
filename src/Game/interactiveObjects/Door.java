package Game.interactiveObjects;

import Game.SetupGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Door extends Rectangle {
    private Image imageWindow;
    private Image imageDoor;

    private boolean broken = false;
    private boolean window=false;

    public Door(float x, float y, float width, float height, boolean window) throws SlickException {
        super(x, y, width, height);
        this.window=window;
        setUpImage();
    }

    private void setUpImage() throws SlickException {
       imageWindow = new Image(SetupGame.path + "window_interactive.PNG");;
       imageDoor = new Image (SetupGame.path + "door_interactive.PNG");;

    }
    public Image getImageDoor(Graphics graphics) {
        if (window) {
                return imageWindow;
            } else {
                return imageDoor;

            }
        }


    public boolean isBroken(){
        return broken;
    }

    public void broke(){
        if (window==false) {
            SetupGame.doorSound.play(1, 0.3f);
        }
            else{
            SetupGame.windowSound.play(1, 0.3f);
            }

        broken = true;
    }

    public void setWindow(){
        this.window=true;
    }


}
