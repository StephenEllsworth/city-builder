import mayflower.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Car extends Actor
{
    private int xCord;
    private int yCord;
    private CellMap map;
    private Level level;

    public Car()
    {
        super();
     setImage("img/Car.png");
    }
    public Car(int x, int y, Level l)
    {
       xCord=x;
       yCord=y;
       level=l;
    }


    public void act()
    {

    }



    public void moveTo()
    {

      List<Road> roadList = getWorld().getObjects(Road.class);
      List<Road> junctionPoints = level.getJunctionPoints(roadList);
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












}
