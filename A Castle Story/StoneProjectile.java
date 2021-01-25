import greenfoot.*;

public class StoneProjectile extends Enemy
{
    public StoneProjectile()
    {
        setHealth(1);
        setGoldAmount(0);
        setScore(0);
    }

    public void act() 
    {
        if(update())
        {
            return;
        }

        move();

        if(getOneIntersectingObject(Castle.class) != null)
        {
            ((Castle)getOneIntersectingObject(Castle.class)).takeDamage(1);
            getWorld().removeObject(this);
        }
    }

    public void move()
    {
        setLocation(getX() + 10, getY());
    }
}
