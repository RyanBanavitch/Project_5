import processing.core.PImage;

import java.util.List;

public abstract class Entity {

    private String id;
    private Point position;
    private List<PImage> images;

    public Entity(String id, Point position, List<PImage> images)
    {
        this.id = id;
        this.position = position;
        this.images = images;
    }

    public Point getPosition() {
        return position;
    }
    public void setPosition(Point pos)
    {
        this.position = pos;
    }
    public String getId() {
        return id;
    }
    public List<PImage> getImages() {
        return images;
    }
    protected abstract PImage getCurrentImage();
}
