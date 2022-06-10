import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Tree extends Target{



    public Tree(
            String id,
            Point position,
            List<PImage> images,
            int actionPeriod,
            int animationPeriod,
            int health)
    {
        super(id, position, images, 0, actionPeriod, animationPeriod, health);
    }

















//    public boolean transformPlant(WorldModel world,
//                                  EventScheduler scheduler,
//                                  ImageStore imageStore)
//    {
//            return this.transformTree(world, scheduler, imageStore);
//    }

    public boolean transformTree(
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

        return false;
    }

    public boolean transformDeadTree(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {

        if(this.getHealth() == -99)
        {
            DeadTree dt = Factory.createDeadTree(this.getId(),
                    this.getPosition(),
                    imageStore.getImageList(WorldInfo.DEADTREE_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(dt);

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
        if (!this.transformDeadTree(world, scheduler, imageStore) && !this.transformTree(world, scheduler, imageStore))
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
