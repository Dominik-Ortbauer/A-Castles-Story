import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Giant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Giant extends Enemy
{
    private String[] images = {"Ogre_F1.png", "Ogre_F2.png", "Ogre_F3.png", "Ogre_F4.png", "Ogre_F5.png", "Ogre_F6.png", "Ogre_F7.png", "Ogre_F8.png"};
    private Animation_Controller animation = new Animation_Controller(0.2, images, this);    
    
    private int timer = 120;
    private int startMovementTimer = 1;
    private int movementTimer = 1;

    public Giant()
    {
        setHealth(20);
        setSpeed(1);
        setGoldAmount(5);
        setScore(1000);
    }

    public void act() 
    {
        if(update())
        {
            return;
        }
        
        animation.update();

        Castle castle = (Castle)getOneIntersectingObject(Castle.class);
        if(castle == null)
        {
            if(movementTimer <= 0)
            {
                move(speed);  
                movementTimer = startMovementTimer;
            }
            else
            {
                movementTimer--;
            }
            
        }
        else if(timer <= 0)
        {
            castle.takeDamage(2);
            timer = 120;
        }
        else
        {
            timer --;
        }
    }    
}
