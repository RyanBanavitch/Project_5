import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DeadTree extends Entity{

    public DeadTree(String id, Point position, List<PImage> images)
    {
        super(id, position, images);
    }

    public PImage getCurrentImage() {
        return this.getImages().get(0);
    }

}

