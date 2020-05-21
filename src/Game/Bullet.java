package Game;

public interface Bullet {
    boolean right = false;


    void setRight();

    void setLeft();


    void checkCollisions();

    void setSpeed( int speed);

    void reflect();

    boolean collided();

    public void disappear();
    public boolean isPresent();

}

