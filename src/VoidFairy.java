import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class VoidFairy extends Movable{

    public VoidFairy(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod) {
        super(id, position, images, 0, animationPeriod, actionPeriod);
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
                world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(DeadTree.class)));

        if (fairyTarget.isPresent()) {
            Point tgtPos = fairyTarget.get().getPosition();

            if (this.moveTo(world, fairyTarget.get(), scheduler)) {
                // create a live tree, not sapling
                Tree tree = Factory.createTree("tree_" + this.getId(),
                        tgtPos,
                        WorldInfo.getNumFromRange(WorldInfo.TREE_ACTION_MAX, WorldInfo.TREE_ACTION_MIN),
                        WorldInfo.getNumFromRange(WorldInfo.TREE_ANIMATION_MAX, WorldInfo.TREE_ANIMATION_MIN),
                        WorldInfo.getNumFromRange(WorldInfo.TREE_HEALTH_MAX, WorldInfo.TREE_HEALTH_MIN),
                        imageStore.getImageList(WorldInfo.TREE_KEY));
                //add the tree to world
                world.addEntity(tree);
                //deadTree.scedule.....
                tree.scheduleActions(scheduler, world, imageStore);
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
