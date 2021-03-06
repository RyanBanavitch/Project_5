import processing.core.PImage;

import java.util.List;

public class Factory {


    public static Action createAnimationAction(Animationable entity, int repeatCount)
    {
        return new Animation(entity, repeatCount);
    }

    public static Action createActivityAction(Executable entity, WorldModel world, ImageStore imageStore)
    {
        return new Activity(entity, world, imageStore);
    }

    public static Entity createHouse(String id, Point position, List<PImage> images)
    {
        return new House(id, position, images);
    }

    public static Entity createObstacle(String id, Point position, int animationPeriod, List<PImage> images)
    {
        return new Obstacle(id, position, images, animationPeriod);
    }

    public static Tree createTree(String id, Point position, int actionPeriod, int animationPeriod, int health, List<PImage> images)
    {
        return new Tree(id, position, images, actionPeriod, animationPeriod, health);
    }

    public static Stump createStump(String id, Point position, List<PImage> images)
    {
        return new Stump(id, position, images);
    }

    // health starts at 0 and builds up until ready to convert to Tree
    public static Sapling createSapling(String id, Point position, List<PImage> images)
    {
        return new Sapling(id, position, images, WorldInfo.SAPLING_ACTION_ANIMATION_PERIOD, WorldInfo.SAPLING_ACTION_ANIMATION_PERIOD, 0, WorldInfo.SAPLING_HEALTH_LIMIT);
    }

    public static Fairy createFairy(String id, Point position, int actionPeriod, int animationPeriod, List<PImage> images)
    {
        return new Fairy(id, position, images, actionPeriod, animationPeriod);
    }

    // need resource count, though it always starts at 0
    public static DudeNotFull createDudeNotFull(String id, Point position, int actionPeriod, int animationPeriod, int resourceLimit, List<PImage> images)
    {
        return new DudeNotFull(id, position, images, actionPeriod, animationPeriod, resourceLimit, 0);
    }

    // don't technically need resource count ... full
    public static DudeFull createDudeFull(String id, Point position, int actionPeriod, int animationPeriod, int resourceLimit, List<PImage> images)
    {
        return new DudeFull(id, position, images, actionPeriod, animationPeriod, resourceLimit);
    }

    public static AlienFull createAlienFull(String id, Point position, int actionPeriod, int animationPeriod, int resourceLimit, List<PImage> images, Point start)
    {
        return new AlienFull(id, position, images, actionPeriod, animationPeriod, resourceLimit, start);
    }

    public static AlienNotFull createAlienNotFull(String id, Point position, int actionPeriod, int animationPeriod, int resourceLimit, List<PImage> images, Point start)
    {
        return new AlienNotFull(id, position, images, actionPeriod, animationPeriod, resourceLimit, 0, start);
    }

    public static DeadTree createDeadTree(String id, Point position, List<PImage> images)
    {
        return new DeadTree(id, position, images);
    }

    public static VoidFairy createVoidFairy(String id, Point position, int actionPeriod, int animationPeriod, List<PImage> images){
        return new VoidFairy(id, position, images, actionPeriod, animationPeriod);
    }

}
