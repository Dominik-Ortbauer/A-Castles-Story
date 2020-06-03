import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bomber here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomber extends Enemy
{       
    public Bomber()
    {
        setHealth(2);
        setGoldAmount(2);
        setSpeed(5);
        setScore(500);
    }

    public void act() 
    {
        if(update())
        {
            return;
        }

        movement();
    }  
    
    boolean firstTime = true;
    public void movement()
    {
        Environment environment = (Environment)getOneIntersectingObject(Environment.class);

        if(environment != null && firstTime)
        {
            if(environment instanceof Castle && firstTime)
            {
                getWorld().addObject(new Bomb(), getX(), getY());
                setSpeed(-5);
                getImage().mirrorHorizontally();
                firstTime = false;
            }
        }
        else
        {
            setLocation((int)pos.x + speed, (int)pos.y);
            if(isAtEdge())
            {
                getWorld().removeObject(this);
            }
        }
    }
}
