import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Optional;

import processing.core.*;

public final class VirtualWorld extends PApplet
{
    public static final int TIMER_ACTION_PERIOD = 100;

    public static final int VIEW_WIDTH = 640;
    public static final int VIEW_HEIGHT = 480;
    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;
    public static final int WORLD_WIDTH_SCALE = 2;
    public static final int WORLD_HEIGHT_SCALE = 2;

    public static final int VIEW_COLS = VIEW_WIDTH / TILE_WIDTH;
    public static final int VIEW_ROWS = VIEW_HEIGHT / TILE_HEIGHT;
    public static final int WORLD_COLS = VIEW_COLS * WORLD_WIDTH_SCALE;
    public static final int WORLD_ROWS = VIEW_ROWS * WORLD_HEIGHT_SCALE;

    public static final String IMAGE_LIST_FILE_NAME = "imagelist";
    public static final String DEFAULT_IMAGE_NAME = "background_default";
    public static final int DEFAULT_IMAGE_COLOR = 0x808080;

    public static String LOAD_FILE_NAME = "world.sav";

    public static final String FAST_FLAG = "-fast";
    public static final String FASTER_FLAG = "-faster";
    public static final String FASTEST_FLAG = "-fastest";
    public static final double FAST_SCALE = 0.5;
    public static final double FASTER_SCALE = 0.25;
    public static final double FASTEST_SCALE = 0.10;

    public static double timeScale = 1.0;

    private ImageStore imageStore;
    private WorldModel world;
    private WorldView view;
    private EventScheduler scheduler;

    private long nextTime;

    public void settings() {
        size(VIEW_WIDTH, VIEW_HEIGHT);
    }

    /*
       Processing entry point for "sketch" setup.
    */
    public void setup() {
        this.imageStore = new ImageStore(
                createImageColored(TILE_WIDTH, TILE_HEIGHT,
                                   DEFAULT_IMAGE_COLOR));
        this.world = new WorldModel(WORLD_ROWS, WORLD_COLS,
                                    createDefaultBackground(imageStore));
        this.view = new WorldView(VIEW_ROWS, VIEW_COLS, this, world, TILE_WIDTH,
                                  TILE_HEIGHT);
        this.scheduler = new EventScheduler(timeScale);

        loadImages(IMAGE_LIST_FILE_NAME, imageStore, this);
        loadWorld(world, LOAD_FILE_NAME, imageStore);

        scheduleActions(world, scheduler, imageStore);

        nextTime = System.currentTimeMillis() + TIMER_ACTION_PERIOD;
    }

    public void draw() {
        long time = System.currentTimeMillis();
        if (time >= nextTime) {
            this.scheduler.updateOnTime(time);
            nextTime = time + TIMER_ACTION_PERIOD;
        }

        view.drawViewport();
    }

    // Just for debugging and for P5
    public void mousePressed() {
        Point pressed = mouseToPoint(mouseX, mouseY);
        Point right = new Point(pressed.x + 1, pressed.y);
        Point left = new Point(pressed.x - 1, pressed.y);
        Point top = new Point(pressed.x, pressed.y - 1);
        Point bottom = new Point(pressed.x, pressed.y + 1);
        Point topR = new Point(pressed.x + 1, pressed.y - 1);
        Point topL = new Point(pressed.x - 1, pressed.y - 1);
        Point bottomR = new Point(pressed.x + 1, pressed.y + 1);
        Point bottomL = new Point(pressed.x - 1, pressed.y + 1);
        this.world.setBackgroundCell(pressed, new Background("void", imageStore.getImageList("void")));
        if(this.world.isOccupied(pressed) && this.world.getOccupancyCell(pressed).getClass() != Fairy.class && this.world.getOccupancyCell(pressed).getClass() != DudeNotFull.class && this.world.getOccupancyCell(pressed).getClass() != DudeFull.class)
        {
            this.scheduler.unscheduleAllEvents(this.world.getOccupancyCell(pressed));
            this.world.removeEntityAt(pressed);
        }
        if(this.world.withinBounds(right))
        {
            this.world.setBackgroundCell(right, new Background("void", imageStore.getImageList("void")));
            if(this.world.isOccupied(right) && this.world.getOccupancyCell(right).getClass() != Fairy.class && this.world.getOccupancyCell(right).getClass() != DudeNotFull.class && this.world.getOccupancyCell(right).getClass() != DudeFull.class)
            {
                this.scheduler.unscheduleAllEvents(this.world.getOccupancyCell(right));
                this.world.removeEntityAt(right);
            }
        }
        if(this.world.withinBounds(left))
        {
            this.world.setBackgroundCell(left, new Background("void", imageStore.getImageList("void")));
            if(this.world.isOccupied(left) && this.world.getOccupancyCell(left).getClass() != Fairy.class && this.world.getOccupancyCell(left).getClass() != DudeNotFull.class && this.world.getOccupancyCell(left).getClass() != DudeFull.class)
            {
                this.scheduler.unscheduleAllEvents(this.world.getOccupancyCell(left));
                this.world.removeEntityAt(left);
            }
        }
        if(this.world.withinBounds(top))
        {
            this.world.setBackgroundCell(top, new Background("void", imageStore.getImageList("void")));
            if(this.world.isOccupied(top) && this.world.getOccupancyCell(top).getClass() != Fairy.class && this.world.getOccupancyCell(top).getClass() != DudeNotFull.class && this.world.getOccupancyCell(top).getClass() != DudeFull.class)
            {
                this.scheduler.unscheduleAllEvents(this.world.getOccupancyCell(top));
                this.world.removeEntityAt(top);
            }
        }
        if(this.world.withinBounds(bottom))
        {
            this.world.setBackgroundCell(bottom, new Background("void", imageStore.getImageList("void")));
            if(this.world.isOccupied(bottom) && this.world.getOccupancyCell(bottom).getClass() != Fairy.class && this.world.getOccupancyCell(bottom).getClass() != DudeNotFull.class && this.world.getOccupancyCell(bottom).getClass() != DudeFull.class)
            {
                this.scheduler.unscheduleAllEvents(this.world.getOccupancyCell(bottom));
                this.world.removeEntityAt(bottom);
            }
        }
        if(this.world.withinBounds(topR))
        {
            this.world.setBackgroundCell(topR, new Background("void", imageStore.getImageList("void")));
            if(this.world.isOccupied(topR) && this.world.getOccupancyCell(topR).getClass() != Fairy.class && this.world.getOccupancyCell(topR).getClass() != DudeNotFull.class && this.world.getOccupancyCell(topR).getClass() != DudeFull.class)
            {
                this.scheduler.unscheduleAllEvents(this.world.getOccupancyCell(topR));
                this.world.removeEntityAt(topR);
            }
        }
        if(this.world.withinBounds(topL))
        {
            this.world.setBackgroundCell(topL, new Background("void", imageStore.getImageList("void")));
            if(this.world.isOccupied(topL) && this.world.getOccupancyCell(topL).getClass() != Fairy.class && this.world.getOccupancyCell(topL).getClass() != DudeNotFull.class && this.world.getOccupancyCell(topL).getClass() != DudeFull.class)
            {
                this.scheduler.unscheduleAllEvents(this.world.getOccupancyCell(topL));
                this.world.removeEntityAt(topL);
            }
        }
        if(this.world.withinBounds(bottomR))
        {
            this.world.setBackgroundCell(bottomR, new Background("void", imageStore.getImageList("void")));
            if(this.world.isOccupied(bottomR) && this.world.getOccupancyCell(bottomR).getClass() != Fairy.class && this.world.getOccupancyCell(bottomR).getClass() != DudeNotFull.class && this.world.getOccupancyCell(bottomR).getClass() != DudeFull.class)
            {
                this.scheduler.unscheduleAllEvents(this.world.getOccupancyCell(bottomR));
                this.world.removeEntityAt(bottomR);
            }
        }
        if(this.world.withinBounds(bottomL))
        {
            this.world.setBackgroundCell(bottomL, new Background("void", imageStore.getImageList("void")));
            if(this.world.isOccupied(bottomL) && this.world.getOccupancyCell(bottomL).getClass() != Fairy.class && this.world.getOccupancyCell(bottomL).getClass() != DudeNotFull.class && this.world.getOccupancyCell(bottomL).getClass() != DudeFull.class)
            {
                this.scheduler.unscheduleAllEvents(this.world.getOccupancyCell(bottomL));
                this.world.removeEntityAt(bottomL);
            }
        }

        AlienNotFull alien = Factory.createAlienNotFull("alien", pressed, WorldInfo.ALIEN_ACTION_PERIOD, WorldInfo.ALIEN_ANIMATION_PERIOD, WorldInfo.ALIEN_LIMIT, this.imageStore.getImageList(WorldInfo.ALIEN_KEY), pressed);
        this.world.addEntity(alien);
        alien.scheduleActions(this.scheduler, this.world, this.imageStore);

        System.out.println("CLICK! " + pressed.getX() + ", " + pressed.getY());

        Optional<Entity> entityOptional = world.getOccupant(pressed);
        if (entityOptional.isPresent())
        {
            Entity entity = entityOptional.get();
            //System.out.println(entity.getKind() + ": " + entity.getKind() + " : " + entity.getHealth());
        }

    }

    private Point mouseToPoint(int x, int y)
    {
        return view.getViewport().viewportToWorld(mouseX/TILE_WIDTH, mouseY/TILE_HEIGHT);
    }
    public void keyPressed() {
        if (key == CODED) {
            int dx = 0;
            int dy = 0;

            switch (keyCode) {
                case UP:
                    dy = -1;
                    break;
                case DOWN:
                    dy = 1;
                    break;
                case LEFT:
                    dx = -1;
                    break;
                case RIGHT:
                    dx = 1;
                    break;
            }
            view.shiftView(dx, dy);
        }
    }

    public static Background createDefaultBackground(ImageStore imageStore) {
        return new Background(DEFAULT_IMAGE_NAME,
                imageStore.getImageList(DEFAULT_IMAGE_NAME));
    }

    public static PImage createImageColored(int width, int height, int color) {
        PImage img = new PImage(width, height, RGB);
        img.loadPixels();
        for (int i = 0; i < img.pixels.length; i++) {
            img.pixels[i] = color;
        }
        img.updatePixels();
        return img;
    }

    static void loadImages(
            String filename, ImageStore imageStore, PApplet screen)
    {
        try {
            Scanner in = new Scanner(new File(filename));
            WorldInfo.loadImages(in, imageStore, screen);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void loadWorld(
            WorldModel world, String filename, ImageStore imageStore)
    {
        try {
            Scanner in = new Scanner(new File(filename));
            WorldInfo.load(in, world, imageStore);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void scheduleActions(
            WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        for (Entity entity : world.getEntities()) {
            if(entity instanceof Animationable) {
                ((Animationable)entity).scheduleActions(scheduler, world, imageStore);
            }
        }
    }

    public static void parseCommandLine(String[] args) {
        if (args.length > 1)
        {
            if (args[0].equals("file"))
            {

            }
        }
        for (String arg : args) {
            switch (arg) {
                case FAST_FLAG:
                    timeScale = Math.min(FAST_SCALE, timeScale);
                    break;
                case FASTER_FLAG:
                    timeScale = Math.min(FASTER_SCALE, timeScale);
                    break;
                case FASTEST_FLAG:
                    timeScale = Math.min(FASTEST_SCALE, timeScale);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        parseCommandLine(args);
        PApplet.main(VirtualWorld.class);
    }
}
