import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Owl here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Owl extends Enemy
{
    private String[] idleImages = {"Owl/Run/Owl_F1.png", "Owl/Run/Owl_F2.png", "Owl/Run/Owl_F3.png", "Owl/Run/Owl_F4.png", "Owl/Run/Owl_F5.png"};
    private Animation_Controller idle;

    int timeBtwAttack = 60;

    public Owl()
    {
        setHealth(2);
        setScore(10);
        setGoldAmount(1);
        idle = new Animation_Controller(0.1, idleImages, this);
    }

    public Owl(GreenfootImage[] images)
    {
        setHealth(2);
        setScore(10);
        setGoldAmount(1);
        idle = new Animation_Controller(0.1, images, this);
    }

    public void act() 
    {
        if(update())
        {
            return;
        }

        idle.update();

        if(getOneIntersectingObject(Castle.class) == null)
        {
            move(5);
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
            ((Castle)getOneIntersectingObject(Castle.class)).takeDamage(1);
            timeBtwAttack = 60;
        }
        else
        {
            timeBtwAttack--;
        }
    }
}
