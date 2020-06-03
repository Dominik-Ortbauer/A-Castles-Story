import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Label here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Label extends Texts
{
    public Label(String text, int fontSize)
    {
        setImage(new GreenfootImage(text, fontSize, Color.WHITE, new Color(0, 0, 0, 0)));
    }    
    
    public void update(String text, int fontSize)
    {
        setImage(new GreenfootImage(text, fontSize, Color.WHITE, new Color(0, 0, 0, 0)));
    } 
}
