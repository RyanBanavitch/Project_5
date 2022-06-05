import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AStarPathingStrategy implements PathingStrategy{

    public List<Point> computePath(Point start, Point end, Predicate<Point> canPassThrough, BiPredicate<Point, Point> withinReach, Function<Point, Stream<Point>> potentialNeighbors)
    {
        Comparator<Node> comp1 = Comparator.comparing(Node::getF);
        PriorityQueue<Node> pq = new PriorityQueue<>(comp1);
        HashMap<Point, Node> olhm = new HashMap<Point, Node>();
        HashMap<Point, Node> clhm = new HashMap<Point, Node>();

        List<Point> path = new LinkedList<Point>();

        Node current = new Node(start, null, 0, Math.abs(start.x - end.x)+Math.abs(start.y - end.y));
        pq.add(current);
        olhm.put(current.getLocation(), current);

        boolean cond = true;
        Node oldCurr = current.getPrior();

        List<Node> valid = new LinkedList<Node>();

        while(cond)
        {

            valid.clear();
            List<Point> list = potentialNeighbors.apply(new Point(current.getX(), current.getY())).filter(canPassThrough).toList();
            for (int i = 0; i < list.size(); i++)
            {
                if(!(clhm.containsKey(list.get(i))))
                {
                    valid.add(new Node(list.get(i), current, current.getG()+1, Math.abs(list.get(i).x - end.x)+Math.abs(list.get(i).y - end.y)));
                }
            }

            //Collections.reverse(valid);

            for (int i = 0; i < valid.size(); i++)
            {
                if(olhm.containsKey(valid.get(i).getLocation()))
                {
                    int temp = valid.get(i).getG();
                    int temp2 = olhm.get(valid.get(i).getLocation()).getG();
                    if (temp < temp2)
                    {
                        olhm.get(valid.get(i).getLocation()).setG(temp);
                        pq.remove(olhm.get(valid.get(i).getLocation()));
                        pq.add(valid.get(i));
                    }

                }
                else
                {
                    pq.add(valid.get(i));
                    olhm.put(valid.get(i).getLocation(), valid.get(i));
                }

            }

            clhm.put(current.getLocation(), current);
            //oldCurr = current;
            olhm.remove(current.getLocation(), current);
            current = pq.poll();

            if (current == null || withinReach.test(current.getLocation(), end))
            {
                cond = false;
            }

        }

        if(current == null)
        {
            return path;
        }

        //oldCurr = current.getPrior();

        while(!current.getLocation().equals(start))
        {
            //oldCurr = current;
            path.add(current.getLocation());
            current = current.getPrior();
        }
        //path.add(oldCurr.getLocation());

        Collections.reverse(path);


        return path;
    }
}
