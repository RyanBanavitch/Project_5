public class Node {

    private int g;
    private int h;
    private int f;
    private Node prior;
    private Point location;
    //private boolean visited;

    public Node(Point loc, Node prior, int g, int h)
    {
        this.location = loc;
        this.prior = prior;
        this.g = g;
        this.h = h;
        this.f = g + h;
        //this.visited = false;
    }

    public int getF()
    {
        return this.f;
    }

    public Point getLocation()
    {
        return this.location;
    }

    public int getG()
    {
        return this.g;
    }

    public void setG(int num)
    {
        this.g = num;
    }

    public int getX()
    {
        return this.location.x;
    }

    public int getY()
    {
        return this.location.y;
    }

    public Node getPrior()
    {
        return this.prior;
    }
}
