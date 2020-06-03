import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Giant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Giant extends Enemy
{
    private int timer = 120;
    private int startMovementTimer = 1;
    private int movementTimer = 1;

    public Giant()
    {
        setHealth(20);
        setSpeed(1);
        setGoldAmount(5);
        setScore(1000);
    }

    public void act() 
    {
        if(update())
        {
            return;
        }

        Castle castle = (Castle)getOneIntersectingObject(Castle.class);
        if(castle == null)
        {
            if(movementTimer <= 0)
            {
                move(speed);  
                movementTimer = startMovementTimer;
            }
            else
            {
                movementTimer--;
            }
            
        }
        else if(timer <= 0)
        {
            castle.takeDamage(2);
            timer = 120;
        }
        else
        {
            timer --;
        }
    }    
}
