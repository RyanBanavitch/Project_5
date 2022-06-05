import processing.core.PImage;

import java.util.List;

public abstract class Target extends Executable{

    private int health;

    public Target(String id, Point position, List<PImage> images, int imageIndex, int animationPeriod, int actionPeriod, int health)
    {
        super(id, position, images, imageIndex, animationPeriod, actionPeriod);
        this.health = health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }
//   protected abstract boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore);
}
