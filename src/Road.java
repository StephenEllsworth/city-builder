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


    public List<Road> junctionPoints()
    {
        if(getWorld() instanceof Level)
        {
            Level l= (Level) getWorld();
            map=l.getMap();
        }
        ArrayList<Road> junctionPoints = new ArrayList<Road>();
        ArrayList<Road> roadCells = new ArrayList<Road>();

        Cell[][] cellMap=map.getMap();
        for(int r =0; r < cellMap.length; r++)
        {
            for(int c =0; c< cellMap[0].length; c++ )
            {
               if(cellMap[r][c] instanceof Road)
               {
                   roadCells.add((Road)(cellMap[r][c]));
               }
            }
        }



        return junctionPoints;
    }

    public boolean isJunctionPoint(Road road)
    {

        Road left= null;
        Road right= null;
        Road down = null;
        Road up  = null;
        List<Road> neighbors = road.getNeighbors(50, false, Road.class);

        for(int i =0; i<neighbors.size(); i++)
        {
            if(neighbors.get(i).getY()==(road.getY()) && neighbors.get(i).getX() == road.getX()-50);
            {
                left = neighbors.get(i);
            }
            if(neighbors.get(i).getY()==(road.getY()) && neighbors.get(i).getX() == road.getX() +50)
            {
               right=  neighbors.get(i);
            }
            if(neighbors.get(i).getX()==(road.getX()) && neighbors.get(i).getY() == road.getY()-50)
            {
                up = neighbors.get(i);
            }
            if(neighbors.get(i).getX()==(road.getX()) && neighbors.get(i).getY() == road.getY()+50)
            {
                down = neighbors.get(i);
            }
        }

        if(left!=null && right !=null)
        {
            if(up != null && down !=null)
            {
                return true;
            }
        }
        else if (left!=null && right != null)
        {
            if(up !=null || down !=null)
            {
                return true;
            }
        }




        return true;
    }
}
