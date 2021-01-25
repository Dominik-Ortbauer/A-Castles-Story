import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tower1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tower1 extends Tower
{    
    private int startTimer = 96;
    private int timer = 96;

    public void act() 
    {
        if(update())
        {
            turnTowards(getClosestEnemy().getX(), getClosestEnemy().getY());
            turn(180);
            if(timer == 48)
            {
                setImage("Tower1.png");
            }
            
            if(timer <= 0)
            {
                shoot();
                setImage("Tower1_F2.png");
                timer = startTimer;
            }
            
            else
            {
                timer--;
            }
        }
    }  

    private void shoot()
    {
        getWorld().addObject(new arrow(getClosestEnemy()), (int)pos.x, (int)pos.y);
    }
}
