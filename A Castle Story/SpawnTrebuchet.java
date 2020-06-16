import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpawnTrebuchet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpawnTrebuchet extends Buttons
{
    /**
     * Act - do whatever the SpawnTrebuchet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {
            getWorld().addObject(new Trebuchet(), 40, Greenfoot.getRandomNumber(600)+100);
        }
    }    
}
