import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossSpeechBubble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossSpeechBubble extends Texts
{
    private int transparency = 255;
    private int timer = 180;
    
    public void act() 
    {
        if(Game.levelCount % 10 != 0)
        {
            getWorld().removeObject(this);
            return;
        }
        if(timer <= 0)
        {
            transparency--;
            getImage().setTransparency(transparency);
            
            if(transparency <= 0)
            {
                getWorld().removeObject(this);
                return;
            }
        }
        else
        {
            timer--;
        }
    }    
}
