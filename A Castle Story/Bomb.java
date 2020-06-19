import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Actor
{
    private int timer = 60;
    private Castle castle;
    public Bomb(Castle castle_)
    {
        castle = castle_;
    }   
    
    public void act()
    {
        if(timer <= 0)
        {
            getWorld().addObject(new Effect(Effects.Colour.YELLOW, new Vector(30, 10), 20), getX(), getY());
            getWorld().removeObject(this);
            castle.takeDamage(3);
        }
        timer--;
    }
}
