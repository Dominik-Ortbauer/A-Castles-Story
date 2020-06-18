import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Leaf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Leaf extends Enemy
{
    private String[] movementImages = {"Leaf/Run/Leaf_F1.png", "Leaf/Run/Leaf_F2.png", "Leaf/Run/Leaf_F3.png", "Leaf/Run/Leaf_F4.png", "Leaf/Run/Leaf_F5.png"};
    private Animation_Controller movement = new Animation_Controller(0.1, movementImages, this);
    private int timeBtwAttack = 60;
    
    public Leaf()
    {
        setHealth(1);
        setScore(50);
        setGoldAmount(0);
    }
    
    public void act() 
    {
        if(update())
        {
            return;
        }
        
        movement.update();
        
        if(getOneIntersectingObject(Castle.class) == null)
        {
            move(3);
        }
        else
        {
            attack();
        }
    }   
    
    private void attack()
    {
        if(timeBtwAttack <= 0)
        {
            Game.battlefield.castle.takeDamage(1);
            timeBtwAttack = 60;
        }
        else
        {
            timeBtwAttack--;
        }
    }
}
