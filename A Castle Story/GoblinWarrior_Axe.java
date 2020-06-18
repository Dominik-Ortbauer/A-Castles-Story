import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoblinWarrior_Axe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GoblinWarrior_Axe extends Enemy
{
    private String[] images_ = {"GoblinWarrior/Axe/Idle/GoblinAxe_F1.png", "GoblinWarrior/Axe/Idle/GoblinAxe_F2.png"};
    private Animation_Controller idle;
    
    public GoblinWarrior_Axe()
    {
        setHealth(1);
        setScore(0);
        setGoldAmount(0);
        idle = new Animation_Controller(0.2, images_, this);
    }
    
    public GoblinWarrior_Axe(GreenfootImage[] images)
    {
        setHealth(1);
        setScore(0);
        setGoldAmount(0);
        idle = new Animation_Controller(0.2, images, this);
    }
    
    public void act() 
    {
        if(update())
        {
            return;
        }
        
        idle.update();
        move(6);
        
        if(getOneIntersectingObject(Castle.class) != null)
        {
            ((Castle)getOneIntersectingObject(Castle.class)).takeDamage(1);
            getWorld().removeObject(this);
        }
    }    
}
