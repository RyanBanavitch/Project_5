import processing.core.PImage;

import java.util.List;

public abstract class Animationable extends Entity {

    private int imageIndex;
    private int animationPeriod;

    public Animationable(String id, Point position, List<PImage> images, int imageIndex, int animationPeriod)
    {
        super(id, position, images);
        this.imageIndex = imageIndex;
        this.animationPeriod = animationPeriod;
    }

    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.getImages().size();
    }
    public int getAnimationPeriod() {
        return this.animationPeriod;
    }
    public int getImageIndex() {return this.imageIndex;}
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                Factory.createAnimationAction(this, 0),
                this.getAnimationPeriod());
        _scheduleActions(scheduler, world, imageStore);
    }
    public PImage getCurrentImage() {
        return this.getImages().get(this.getImageIndex());
    }
    protected abstract void _scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);
}
