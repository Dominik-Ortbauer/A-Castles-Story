import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FireBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FireBall extends Enemy
{
    public FireBall()
    {
        setHealth(1);
        setScore(0);
        setGoldAmount(0);
    }
    
    public void act() 
    {
        Castle castle = (Castle)getOneIntersectingObject(Castle.class);
        if(castle != null)
        {
            castle.takeDamage(1);
            getWorld().removeObject(this);
        }
        else
        {
            move(4);
        }
    }    
}
