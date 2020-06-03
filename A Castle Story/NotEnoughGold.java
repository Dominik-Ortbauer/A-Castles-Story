import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NotEnoughGold here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NotEnoughGold extends Texts
{
    int transparency = 255;
    
    public void act() 
    {
        setImage(new GreenfootImage("Not Enough Gold", 24, new Color(255, 255, 255, transparency), new Color(0, 0, 0, 0)));
        transparency--;
        if(transparency <= 0)
        {
            getWorld().removeObject(this);
        }
    }    
}
