import greenfoot.*;
public class Trebuchet extends Enemy
{
    private GreenfootImage fire = new GreenfootImage("trebuchet_F2.png");
    private GreenfootImage wait = new GreenfootImage("trebuchet_F1.png");
    private int timer = 0;

    public Trebuchet()
    {
        setGoldAmount(2);
        setHealth(5);
        setScore(500);
    }

    public void act() 
    {
        if(update())
        {
            return;
        }

        if(getX() < 400)
        {    
            setLocation((int)pos.x + 1, (int)pos.y);
        }
        else
        {
            fire();
            timer++;
        }
    }

    public void fire()
    {
        if (timer == 1)
        {
            setImage(wait);
        }
        if (timer == 30)
        {
            getWorld().addObject(new StoneProjectile(), getX(), getY());
        }
        if (timer == 31)
        {
            setImage(fire);
        }
        if (timer == 91)
        {
            timer = 0;
        }
    }
}
