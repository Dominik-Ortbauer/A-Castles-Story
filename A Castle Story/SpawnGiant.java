import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpawnGiant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpawnGiant extends Buttons
{
    /**
     * Act - do whatever the SpawnGiant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {
            getWorld().addObject(new Giant(), 40, Greenfoot.getRandomNumber(600)+100);
        }
    }    
}
