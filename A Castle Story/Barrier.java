import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Barrier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Barrier extends Environment
{
    private int health = 10;
    
    public Barrier()
    {
        getImage().scale(30, 120);
    }
    
    public void act() 
    {               
        if(health <= 0)
        {
            getWorld().removeObject(this);
        }
    }   
    
    public void takeDamge(int amount)
    {
        health -= amount;
    }
}
