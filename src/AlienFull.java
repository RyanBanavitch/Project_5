import processing.core.PImage;

import java.util.List;

public class AlienFull extends Dudes{


    public AlienFull(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod, int resourceLimit)
    {
        super(id, position, images, actionPeriod, animationPeriod, resourceLimit);
    }

    @Override
    protected void _scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {

    }

    @Override
    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

    }

    @Override
    protected boolean _canPassThrough(WorldModel world, Point p) {
        return false;
    }

    @Override
    protected boolean _moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        return false;
    }
}
