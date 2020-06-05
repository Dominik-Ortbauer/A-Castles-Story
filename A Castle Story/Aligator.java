import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Aligator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Aligator extends Enemy
{    
    public Aligator()
    {
        setGoldAmount(1);
        setHealth(5);
        setSpeed(2);
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

    public void movement()
    {
        Environment environment = (Environment)getOneIntersectingObject(Environment.class);

        if(environment != null)
        {
            if(environment instanceof Castle && timeBtwAttacks <= 0)
            {
                ((Castle)environment).takeDamage(1);
                timeBtwAttacks = startTimeBtwAttacks;
            }

            if(environment instanceof Barrier && timeBtwAttacks <= 0)
            {
                ((Barrier)environment).takeDamge(1);
                timeBtwAttacks = startTimeBtwAttacks;
            }
        }
        else
        {
            setLocation((int)pos.x + speed, (int)pos.y);
        }

        timeBtwAttacks -= 0.017;
    }    
}
