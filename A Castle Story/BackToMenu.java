import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BackToMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BackToMenu extends Buttons
{
    public BackToMenu()
    {
        setImage(new GreenfootImage("Back", 24, Color.GREEN, new Color(0, 0, 0, 0))); 
    }
    
    public void act()
    {
        if(Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(new MainMenu());
        }
    }
}
