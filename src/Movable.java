import processing.core.PImage;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class Movable extends Executable{

    public Movable(String id, Point position, List<PImage> images, int imageIndex, int animationPeriod, int actionPeriod)
    {
        super(id, position, images, imageIndex, animationPeriod, actionPeriod);
    }

    public Point nextPosition(
            WorldModel world, Point destPos)
    {
        PathingStrategy strat = new AStarPathingStrategy();
        Predicate<Point> canPassThrough = (p) -> _canPassThrough(world, p);
        BiPredicate<Point, Point> withinReach = (p, p2) -> WorldInfo.adjacent(p, p2);
        Function<Point, Stream<Point>> potentialNeighbors = PathingStrategy.CARDINAL_NEIGHBORS;
        List<Point> path = strat.computePath(this.getPosition(), destPos, canPassThrough, withinReach, potentialNeighbors);
        if (path.size() == 0)
            return this.getPosition();
        else
            return path.get(0);
    }

    protected abstract boolean _canPassThrough(WorldModel world, Point p);


    public boolean moveTo(
            WorldModel world,
            Entity target,
            EventScheduler scheduler)
    {
        if (WorldInfo.adjacent(this.getPosition(), target.getPosition())) {
            return _moveTo(world, target, scheduler);
        }
        else {
            Point nextPos = this.nextPosition(world, target.getPosition());

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

    protected abstract boolean _moveTo(WorldModel world, Entity target, EventScheduler scheduler);

}
