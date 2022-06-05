import processing.core.PImage;

import java.util.List;

public abstract class Executable extends Animationable{

    private int actionPeriod;
    public Executable(String id, Point position, List<PImage> images, int imageIndex, int animationPeriod, int actionPeriod)
    {
        super(id, position, images, imageIndex, animationPeriod);
        this.actionPeriod = actionPeriod;
    }

    public int getActionPeriod()
    {
        return this.actionPeriod;
    }

    protected abstract void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler);
}
