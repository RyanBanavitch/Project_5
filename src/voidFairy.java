import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class voidFairy extends Movable{

    public voidFairy(String id, Point position, List<PImage> images, int imageIndex, int animationPeriod, int actionPeriod) {
        super(id, position, images, imageIndex, animationPeriod, actionPeriod);
    }

    public void _scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                this.getActionPeriod());
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> fairyTarget =
                //change this to the dead tree class instead of stump
                world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Stump.class)));

        if (fairyTarget.isPresent()) {
            Point tgtPos = fairyTarget.get().getPosition();

            if (this.moveTo(world, fairyTarget.get(), scheduler)) {
                // create a live tree, not sapling
                Sapling sapling = Factory.createSapling("sapling_" + this.getId(), tgtPos,
                        imageStore.getImageList(WorldInfo.SAPLING_KEY));
                //add the tree to world
                world.addEntity(sapling);
                //deadTree.scedule.....
                sapling.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                this.getActionPeriod());
    }

    public boolean _canPassThrough(WorldModel world, Point p) {
        return world.withinBounds(p) && !world.isOccupied(p);
    }

    public boolean _moveTo(WorldModel world, Entity target, EventScheduler scheduler)
    {
        world.removeEntity(target);
        scheduler.unscheduleAllEvents(target);
        return true;
    }


}
