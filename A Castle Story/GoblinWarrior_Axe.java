import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoblinWarrior_Axe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GoblinWarrior_Axe extends Enemy
{
    private String[] images = {"GoblinAxe_F1.png", "GoblinAxe_F2.png"};
    private Animation_Controller idle = new Animation_Controller(0.2, images, this);
    
    public GoblinWarrior_Axe()
    {
        setHealth(1);
        setScore(0);
        setGoldAmount(0);
    }
    
    public void act() 
    {
        idle.update();
        move(6);
        
        if(getOneIntersectingObject(Castle.class) != null)
        {
            Game.battlefield.castle.takeDamage(1);
            getWorld().removeObject(this);
        }
    }    
}
