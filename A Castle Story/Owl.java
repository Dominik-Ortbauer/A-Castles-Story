import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Owl here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Owl extends Enemy
{
    private String[] idleImages = {"Owl_F1.png", "Owl_F2.png", "Owl_F3.png", "Owl_F4.png", "Owl_F5.png"};
    private Animation_Controller idle = new Animation_Controller(0.1, idleImages, this);

    int timeBtwAttack = 60;

    public Owl()
    {
        setHealth(3);
        setScore(10);
        setGoldAmount(1);
    }

    public void act() 
    {
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
            Game.battlefield.castle.takeDamage(1);
            timeBtwAttack = 60;
        }
        else
        {
            timeBtwAttack--;
        }
    }
}
