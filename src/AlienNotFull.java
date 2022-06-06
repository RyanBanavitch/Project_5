import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AlienNotFull extends Dudes{

    private final Point start;
    private int resourceCount;

    public AlienNotFull(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod, int resourceLimit, int resourceCount, Point start)
    {
        super(id, position, images, actionPeriod, animationPeriod, resourceLimit);
        this.resourceCount = resourceCount;
        this.start = start;
    }

    public void _scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                this.getActionPeriod());
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
//        Optional<Entity> target =
//                world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));
//
//        if (!target.isPresent() || !this.moveTo(world,
//                (Target)(target.get()),
//                scheduler)
//                || !this.transformNotFull(world, scheduler, imageStore))
//        {
//            scheduler.scheduleEvent(this,
//                    Factory.createActivityAction(this, world, imageStore),
//                    this.getActionPeriod());
//        }
    }

    public boolean _canPassThrough(WorldModel world, Point p) {
        return world.withinBounds(p) && (!world.isOccupied(p) || world.getOccupancyCell(p).getClass() == Stump.class);
    }

    @Override
    protected boolean _moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        return false;
    }


//    public boolean transformNotFull(
//            WorldModel world,
//            EventScheduler scheduler,
//            ImageStore imageStore)
//    {
//        if (this.resourceCount >= this.getResourceLimit()) {
//            DudeFull miner = Factory.createDudeFull(this.getId(),
//                    this.getPosition(), this.getActionPeriod(),
//                    this.getAnimationPeriod(),
//                    this.getResourceLimit(),
//                    this.getImages());
//
//            world.removeEntity(this);
//            scheduler.unscheduleAllEvents(this);
//
//            world.addEntity(miner);
//            miner.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }
}
