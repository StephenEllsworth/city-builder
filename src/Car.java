import mayflower.*;

import java.util.List;

public class Car extends Actor
{
    private int xCord;
    private int yCord;

    public Car()
    {

    }
    public Car(int x, int y)
    {
       xCord=x;
       yCord=y;
    }


    public void act()
    {

    }


    public void carNavigation()
    {
        List<Factory> factoryList =  getWorld().getObjects(Factory.class);
        int factoryIndex = (int)(Math.random()*factoryList.size());
        Factory randomFactory = factoryList.get(factoryIndex);


      List<Road> roadList = getWorld().getObjects(Road.class);

    }
    public void moveTo(Road road)
    {


    }




}
