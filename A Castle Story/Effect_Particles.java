import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Effect_Particles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Effect_Particles extends Actor
{
    double speed = (Math.random() * 3) + 8.5;
    double decrease = 0;
    double increase = (Math.random()/5) + 0.1;
    private double timer = 20;
            
    public void act() 
    {
        if(isAtEdge())
        {
            getWorld().removeObject(this);
            return;
        }
        
        if(speed >= 0)
        {
            move((int)speed);
            speed -= decrease;
            decrease += 0.02;
        }
        else
        {
            if(timer <= 0)
            {
                getWorld().removeObject(this);
            }
            else
            {
                timer -= 1;
            }
        }
    }  
    
    public void setSpeed(double Value, double variability)
    {
        speed = (Math.random() * variability) + Value;
    }
    public void setIncrease(double Value, double variability)
    {
        increase = (Math.random() * variability) + Value;
    }
    public void setTimer(int value)
    {
        timer = value;
    }
}
