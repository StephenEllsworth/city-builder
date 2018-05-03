import mayflower.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Road extends Cell {
    private CellMap map;
    public Road(int x, int y, int w, int h) {
        super(x,y,w,h);
        setImage("img/road.png");
    }

    public void act()
    {

    }




    public List<Road> returnNeighbors()
    {
        return getNeighbors(50, false, Road.class);
    }
}
