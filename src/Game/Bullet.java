package Game;

public interface Bullet {
    boolean right = false;

    void setRight();

    void setLeft();

    void move();

    void checkCollisions();

    void setSpeed();


}

