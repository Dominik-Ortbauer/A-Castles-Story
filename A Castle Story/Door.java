import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Door extends Environment
{
    public boolean open = false;
    public World destination;
    private boolean justForShow = false;

    public Door(World destination_)
    {
        destination = destination_;
    }

    public Door()
    {
        justForShow = true;
    }

    public void setDestination(World world)
    {
        destination = world;
    }

    public void openDoor()
    {
        setImage("Door_Open.png");
        open = true;
    }

    public void closeDoor()
    {
        setImage("Door.png");
        open = false;
    }

    public void act()
    {
        if(!justForShow)
        {
            if(getObjectsInRange(90, Player.class).size() > 0 && open)
            {
                if(destination instanceof Shop)
                {
                    Game.shop.start();
                }
                if(destination instanceof Battlefield)
                {
                    ((Battlefield)destination).prepare();
                }
                Greenfoot.setWorld(destination);
            }
        }
    }
}
