import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Void extends Animationable{



    public Void(String id, Point position, List<PImage> images, int animationPeriod)
    {
        super(id, position, images, 0, animationPeriod);
    }







    public void _scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {

    }

}
