import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.MouseInfo;
import mayflower.World;

import java.util.List;
import java.awt.*;


public class Level extends World {
    private CellMap map;
    private BuildingHandler bh;
    private Car car;
    boolean roadExists;

    public Level() {

        car = new Car();
        roadExists = false;


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
        for(int i=0; i<map.rows(); i++)
        {
            for(int j=0; j<map.cols(); j++)
            {
                if(map.getCell(i,j) instanceof Road)
                {

                    roadExists=true;

                }
            }
        }
        if(roadExists==true) {
            spawnCar(car);
            car.moveTo();
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
}
