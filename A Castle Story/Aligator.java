import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Aligator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Aligator extends Enemy
{    
    private String[] images = {"Goblin_F1.png", "Goblin_F2.png", "Goblin_F3.png", "Goblin_F4.png", "Goblin_F5.png", "Goblin_F6.png", "Goblin_F7.png", "Goblin_F8.png"}; 
    private Animation_Controller movement = new Animation_Controller(0.1, images, this);
    public Aligator()
    {
        setGoldAmount(1);
        setHealth(5);
        setSpeed(2);
        setScore(500);
    }

    public void act() 
    {     
        movement.update();
        if(update())
        {
            return;
        }

        movement();
    }   

    public void movement()
    {
        Environment environment = (Environment)getOneIntersectingObject(Environment.class);

        if(environment != null)
        {
            if(environment instanceof Castle && timeBtwAttacks <= 0)
            {
                ((Castle)environment).takeDamage(1);
                timeBtwAttacks = startTimeBtwAttacks;
            }

            if(environment instanceof Barrier && timeBtwAttacks <= 0)
            {
                ((Barrier)environment).takeDamge(1);
                timeBtwAttacks = startTimeBtwAttacks;
            }
        }
        else
        {
            setLocation((int)pos.x + speed, (int)pos.y);
        }

        timeBtwAttacks -= 0.017;
    }    
}
