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
        if (Mayflower.mouseHovered(this)) {
            getWorld().showText("Road", 15, 1325, 550, Color.BLACK);
            getWorld().showText("" + getX(), 15, 1325, 575, Color.BLACK);
            getWorld().showText("" + getY(), 15, 1325, 600, Color.BLACK);
        }
    }




    public List<Road> getNeighbors(Level level)
    {
        List<Road> neighbors = new ArrayList<Road>();
        if(level.getMap().getCell(this.getx() - 1, this.gety()) instanceof Road)
        {
            Road r = (Road) level.getMap().getCell(this.getx() - 1, this.gety());
            neighbors.add(r);
        }
        if(level.getMap().getCell((this.getx()+1), (this.gety()) )instanceof Road)
        {
            Road r = (Road) level.getMap().getCell((this.getx()+1), (this.gety()));
            neighbors.add(r);
        }
        if(level.getMap().getCell((this.getx()), (this.gety()-1) )instanceof Road)
        {
            Road r = (Road) level.getMap().getCell((this.getX()), (this.gety()-1));
            neighbors.add(r);
        }
        if(level.getMap().getCell((this.getx()), (this.gety()+1) )instanceof Road)
        {
            Road r = (Road) level.getMap().getCell((this.getx()), (this.gety()+1));
            neighbors.add(r);
        }
        return neighbors;
    }
}
