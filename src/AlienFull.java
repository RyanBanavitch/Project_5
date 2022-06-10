import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AlienFull extends Dudes{

    private final Point start;

    public AlienFull(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod, int resourceLimit, Point start)
    {
        super(id, position, images, actionPeriod, animationPeriod, resourceLimit);
        this.start = start;
    }

    @Override
    protected void _scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                this.getActionPeriod());
    }

    public Point getStart() {
        return start;
    }

    @Override
    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Point fullTarget = this.getStart();

        if (this.moveTo(world,
                fullTarget, scheduler))
        {
            this.transformAlienFull(world, scheduler, imageStore);
        }
        else {
            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore),
                    this.getActionPeriod());
        }
    }

    protected boolean _canPassThrough(WorldModel world, Point p)
    {
        return world.withinBounds(p) && (!world.isOccupied(p));
    }

    @Override
    protected boolean _moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        return false;
    }


    public void transformAlienFull(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {
        AlienNotFull alien = Factory.createAlienNotFull(this.getId(),
                this.getPosition(), this.getActionPeriod(),
                this.getAnimationPeriod(),
                this.getResourceLimit(),
                this.getImages(), this.getStart());

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(alien);
        alien.scheduleActions(scheduler, world, imageStore);
    }


    public boolean moveTo(
            WorldModel world,
            Point target,
            EventScheduler scheduler)
    {
        if (WorldInfo.adjacent(this.getPosition(), target)) {
            return true;
        }
        else {
            Point nextPos = this.nextPosition(world, target);

            if (!this.getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }


}
