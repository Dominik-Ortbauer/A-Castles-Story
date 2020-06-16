;import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tree extends Enemy
{    
    private int timeBtwAttacks = 240;

    public Tree()
    {
        setHealth(15);
        setScore(500);
        setGoldAmount(10);
    }

    private boolean firstFrame = true;
    public void act() 
    {
        if(firstFrame)
        {
            move(100);
            firstFrame = false;
        }

        if(timeBtwAttacks <= 0)
        {
            attack();
            timeBtwAttacks = 120;
        }
        else
        {
            timeBtwAttacks--;
        }
    }    

    private void attack()
    {
        getWorld().addObject(new Owl(), getX(), getY());
    }
}
