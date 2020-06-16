import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpawnGoblin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpawnGoblin extends Buttons
{    
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {
            getWorld().addObject(new Aligator(), 40, Greenfoot.getRandomNumber(600)+100);
        }
    }    
}
