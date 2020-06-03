import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Projectiles extends Actor
{
    public Vector vel = new Vector();

    public boolean update() 
    {        
        if(isAtEdge() || getOneIntersectingObject(Barrier.class) != null)
        {
            getWorld().removeObject(this);
            return true;
        }
        return false;
    }
}