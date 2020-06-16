import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpawnRider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpawnBomber extends Buttons
{
    /**
     * Act - do whatever the SpawnRider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {
            getWorld().addObject(new Bomber(), 40, Greenfoot.getRandomNumber(600)+100);
        }
    }    
}
