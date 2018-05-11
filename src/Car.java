import mayflower.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Car extends Actor
{
    private int xCord;
    private int yCord;
    private CellMap map;

    public Car()
    {
        super();
     setImage("img/Car.png");
    }
    public Car(int x, int y)
    {
       xCord=x;
       yCord=y;
    }


    public void act()
    {

    }



    public void moveTo()
    {

      List<Road> roadList = getWorld().getObjects(Road.class);
      List<Road> junctionPoints = getJunctionPoints(roadList);
      List<Road> randomJunctionPoints = null;

      //car coordinates
      int x = this.getX();
      int y = this.getY();

      //int factoryX= factory.getX();
    //  int factoryY = factory.getY();

      //gets a list of random junction points.
      for(int i =0; i<junctionPoints.size(); i++)
      {
          int roadIndex = (int)(Math.random()*junctionPoints.size());
           Road randomJunction=junctionPoints.get(roadIndex);
           randomJunctionPoints.add(randomJunction);
      }

       //Checks locations.
      if(this.isTouching(Road.class))
      {
          for(int j=0; j<randomJunctionPoints.size(); j++)
          {
              if(x==(randomJunctionPoints.get(j).getX()))
              {
                  this.turnTowards(randomJunctionPoints.get(j));
                  this.move(1);
              }
              else if(y==randomJunctionPoints.get(j).getY())
              {
                  this.turnTowards(randomJunctionPoints.get(j));
                  this.move(1);
              }
          }
      }



    }






    public List<Road> getJunctionPoints(List<Road> roadCells)
    {
        if(getWorld() instanceof Level)
        {
            Level l= (Level) getWorld();
            map=l.getMap();
        }
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

    public boolean isJunctionPoint(Road road)
    {

        Road left= null;
        Road right= null;
        Road down = null;
        Road up  = null;
        List<Road> neighbors = road.returnNeighbors();

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




}
