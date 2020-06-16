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

    private String[] attackImages = {"Goblin_Attack_F1.png", "Goblin_Attack_F2.png", "Goblin_Attack_F3.png", "Goblin_Attack_F4.png"}; 
    private Animation_Controller attack = new Animation_Controller(0.2, attackImages, this);

    public Aligator()
    {
        setGoldAmount(1);
        setHealth(5);
        setSpeed(2);
        setScore(500);
    }

    public void act() 
    {     
        if(update())
        {
            return;
        }

        movement();
    }   

    public void movement()
    {
        Castle castle = (Castle)getOneIntersectingObject(Environment.class);

        if(castle != null)
        {
            if(attack.update(2))
            {
                Game.battlefield.castle.takeDamage(1);
            }
        }
        else
        {
            setLocation((int)pos.x + speed, (int)pos.y);
            movement.update();
        }
    }    
}
