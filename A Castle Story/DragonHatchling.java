import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DragonHatchling here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DragonHatchling extends Enemy
{
    private String[] runImages = {"DragonHatchling/Run/DragonHatchling_Run_F1.png", "DragonHatchling/Run/DragonHatchling_Run_F2.png", "DragonHatchling/Run/DragonHatchling_Run_F3.png", "DragonHatchling/Run/DragonHatchling_Run_F4.png", "DragonHatchling/Run/DragonHatchling_Run_F5.png"};
    private Animation_Controller runAnim = new Animation_Controller(0.1, runImages, this);
    
    private int timer = 180;
    
    public DragonHatchling()
    {
        setHealth(3);
        setScore(1000);
        setGoldAmount(3);
    }
    
    public void act() 
    {
        runAnim.update();
        if(getX() <= 200)
        {
            move(2);
        }
        else if(timer <= 0)        
        {
            getWorld().addObject(new FireBall(), getX() + 20, getY());
            timer = 180;
        }
        else
        {
            timer--;
        }
    }    
}
