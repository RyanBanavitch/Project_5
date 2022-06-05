import processing.core.PImage;

import java.util.List;

public abstract class Dudes extends Movable{

    private int resourceLimit;

    public Dudes(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod, int resourceLimit)
    {
        super(id, position, images, 0, animationPeriod, actionPeriod);
        this.resourceLimit = resourceLimit;
    }

    public int getResourceLimit()
    {
        return this.resourceLimit;
    }

}
