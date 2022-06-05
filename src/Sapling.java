import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Sapling extends Target{

    private int healthLimit;

    public Sapling(
            String id,
            Point position,
            List<PImage> images,
            int actionPeriod,
            int animationPeriod,
            int health,
            int healthLimit)
    {
        super(id, position, images, 0, actionPeriod, animationPeriod, health);
        this.healthLimit = healthLimit;
    }




//    public boolean transformPlant(WorldModel world,
//                                  EventScheduler scheduler,
//                                  ImageStore imageStore)
//    {
//            return this.transformSapling(world, scheduler, imageStore);
//    }

    public boolean transformSapling(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {
        if (this.getHealth() <= 0) {
            Stump stump = Factory.createStump(this.getId(),
                    this.getPosition(),
                    imageStore.getImageList(WorldInfo.STUMP_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(stump);

            return true;
        }
        else if (this.getHealth() >= this.healthLimit)
        {
            Tree tree = Factory.createTree("tree_" + this.getId(),
                    this.getPosition(),
                    WorldInfo.getNumFromRange(WorldInfo.TREE_ACTION_MAX, WorldInfo.TREE_ACTION_MIN),
                    WorldInfo.getNumFromRange(WorldInfo.TREE_ANIMATION_MAX, WorldInfo.TREE_ANIMATION_MIN),
                    WorldInfo.getNumFromRange(WorldInfo.TREE_HEALTH_MAX, WorldInfo.TREE_HEALTH_MIN),
                    imageStore.getImageList(WorldInfo.TREE_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(tree);
            tree.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
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
        this.setHealth(this.getHealth()+1);
        if (!this.transformSapling(world, scheduler, imageStore))
        {
            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore),
                    this.getActionPeriod());
        }
    }

    public PImage getCurrentImage() {
        return this.getImages().get(this.getImageIndex());
    }
}
