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

        car = new Car(0, 0, this);
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
        spawnCar();


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

    public void spawnCar(Car car) {

            List<Road> roadObjects = this.getRoadObjects();
            int randomRoad = (int)(Math.random() * roadObjects.size());
            Road randomRoadObject = roadObjects.get(randomRoad);

            Car c = new Car (50,50,this);
            this.addObject(c, randomRoadObject.getX(), randomRoadObject.getY());

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
                if (neighbors.get(i).gety() == (road.gety()) && neighbors.get(i).getx() == road.getx() - 1) ;
                {
                    left = neighbors.get(i);
                }
                if (neighbors.get(i).gety() == (road.gety()) && neighbors.get(i).getx() == road.getx() + 1) {
                    right = neighbors.get(i);
                }
                if (neighbors.get(i).getx() == (road.getx()) && neighbors.get(i).gety() == road.gety() - 1) {
                    up = neighbors.get(i);
                }
                if (neighbors.get(i).getx() == (road.getx()) && neighbors.get(i).gety() == road.gety() + 1) {
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

    public List<Road> getRoadObjects()
    {
        List<Road> roadObjects = new ArrayList<Road>();
        for(int rows =0; rows<map.rows(); rows++)
        {
            for(int cols =0; cols<map.cols(); cols++)
            {
                if(map.getCell(rows,cols) instanceof Road)
                {
                    Road r = (Road) map.getCell(rows,cols);
                    roadObjects.add(r);
                }
            }
        }


        return roadObjects;
    }

    public void spawnCar()
    {
        int random=0;

            for (int i = 0; i < map.rows(); i++) {
                for (int j = 0; j < map.cols(); j++) {

                        random = (int)(Math.random()*1000);
                        if (map.getCell(i, j) instanceof Road) {
                            roadExists = true;
                            //junctionPoint = true;

                        } else {
                            roadExists = false;
                        }





                    if (random == 1 && roadExists == true) {
                        spawnCar(car);
                        car.moveTo();

                    }
                }
            }


    }

}
