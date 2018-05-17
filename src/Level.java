import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.MouseInfo;
import mayflower.World;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import mayflower.*;

public class Level extends World {
    private CellMap map;
    private BuildingHandler bh;
    private Car car;
    boolean roadExists;
    boolean junctionPoint;
    Timer timer;

    public Level() {

        car = new Car();
        roadExists = false;
        junctionPoint = false;
        timer = new Timer(999999999);



        map = new CellMap(26,16);
        for(int i = 0; i < map.rows(); i++)
        {
            for(int j = 0; j < map.cols(); j++)
            {
                Grass g = new Grass(i*50,j*50,50,50);
                addObject(g,i*50,j*50);
                map.setCell(i,j,g);
            }
        }

        bh = new BuildingHandler(this);
        addObject(bh,0,0);
    }

    public void act()
    {
        for(int i=0; i<map.rows(); i++) {
            for (int j = 0; j < map.cols(); j++) {
                if (map.getCell(i, j) instanceof Road && isJunctionPoint((Road) (map.getCell(i, j)))) {

                    roadExists = true;
                    //junctionPoint = true;

                }
            }


            if (roadExists == true ) {
                spawnCar(car);
                car.moveTo();
            }
        }


        if(bh.updateMap() != null)
            map = bh.updateMap();

        /*MouseInfo mouse = Mayflower.getMouseInfo();
        if(mouse.getX() < 25)
        {
            List<Actor> actors = getObjects();
            for(Actor a : actors)
            {
                a.setLocation(a.getX() + 10, a.getY());
            }
        }*/

    }

    /*public void render(Graphics2D g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(50,50,500,500);
    }*/

    public CellMap getMap() {
        return map;
    }

    public void spawnCar(Car car)
    {
      Cell [][] roadMap = map.getMap();
      for(int i =0; i<roadMap.length; i++)
      {
          for(int j=0; j<roadMap[0].length; j++)
          {
              if(roadMap[i][j] instanceof Road)
              {
                  addObject(car, roadMap[i][j].getX(), roadMap[i][j].getY());
              }
          }
      }
    }






    public boolean isJunctionPoint(Road road)
    {

        Road left= null;
        Road right= null;
        Road down = null;
        Road up  = null;
        List<Road> neighbors = road.getNeighbors(this);

        if (neighbors.size() > 0) {
            for (int i = 0; i < neighbors.size(); i++) {
                if (neighbors.get(i).getY() == (road.getY()) && neighbors.get(i).getX() == road.getX() - 50) ;
                {
                    left = neighbors.get(i);
                }
                if (neighbors.get(i).getY() == (road.getY()) && neighbors.get(i).getX() == road.getX() + 50) {
                    right = neighbors.get(i);
                }
                if (neighbors.get(i).getX() == (road.getX()) && neighbors.get(i).getY() == road.getY() - 50) {
                    up = neighbors.get(i);
                }
                if (neighbors.get(i).getX() == (road.getX()) && neighbors.get(i).getY() == road.getY() + 50) {
                    down = neighbors.get(i);
                }
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

        else if(up !=null && down!=null)
        {
            if(left !=null || right!=null)
            {
                return true;
            }
        }

        else if(left!=null || right!=null)
        {
            if(up!=null || down!=null)
            {
                return true;
            }
        }
        return false;
    }



    public List<Road> getJunctionPoints(List<Road> roadCells)
    {

        ArrayList<Road> junctionPoints = new ArrayList<Road>();


        Cell[][] cellMap=map.getMap();

        for(int i =0; i<roadCells.size(); i++)
        {
            if(isJunctionPoint(roadCells.get(i)) ==true)
            {
                junctionPoints.add(roadCells.get(i));
            }
        }
        return junctionPoints;
    }

}
