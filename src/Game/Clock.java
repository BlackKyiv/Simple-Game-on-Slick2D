package Game;

public class Clock {
    private boolean running = false;
    private float passedTime = 0;


    public Clock(){

    }

    public void start(){
        running = true;
    }

    public void update(float delta){
        if(running) passedTime+=delta;
    }

    public float getPassedTime(){
         return passedTime;
    }

    public void stop(){
        running = false;
    }
    public void restart(){
        passedTime = 0;
    }

}
