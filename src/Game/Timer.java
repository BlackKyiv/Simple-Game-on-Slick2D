package Game;

public class Timer {
    private boolean finished = false;
    private boolean running = false;

    private float timePassed = 0;
    private float goalTime = 0;

    public Timer(float goalTime){
        this.goalTime = goalTime;
    }

    public void update(float delta){
        if(running) {
            timePassed += delta;
            if (timePassed >= goalTime){
                finished = true;
                running = false;
            }
        }
    }

    public float getTimePassed(){
        return timePassed;
    }

    public void start(){
        running = true;
    }

    public void stop(){
        running = false;
    }

    public boolean isRunning(){
        return running;
    }

    public void restart(){
        timePassed = 0;
        running = false;
        finished = false;
    }

    public boolean isFinished(){
        return finished;
    }




}
