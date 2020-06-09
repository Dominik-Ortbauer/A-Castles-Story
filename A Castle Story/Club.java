import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class Club here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Club extends BossAttacks
{
    boolean firstFrame = true;
    Vector target;
    private int speed = 5;
    
    public void act() 
    {
        if(firstFrame)
        {
            Random dice = new Random();
            firstFrame = false;
            target = new Vector(1200, dice.nextInt(800));
            // target.setMag(speed);
        }
            
        turnTowards((int)target.x, (int)target.y);
        move(speed);
        //setLocation(getX() + (int)target.x, getY() + (int)target.y);
    } 
    
    public void hitByPlayer()
    {
        target.set(200, 400);
    }
}
