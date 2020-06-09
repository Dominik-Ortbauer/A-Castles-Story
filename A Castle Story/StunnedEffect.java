import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StunnedEffect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StunnedEffect extends Actor
{
    String[] allImages = {"StunnedEffect_F1.png", "StunnedEffect_F2.png"};
    private Animation_Controller controller = new Animation_Controller(0.1, allImages, this);
    public void act() 
    {
        controller.update();
    }    
}
