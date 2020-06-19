import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bomber here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rider extends Enemy
{    
    private String[] images = {"Rider/Run/Rider_F1.png", "Rider/Run/Rider_F2.png", "Rider/Run/Rider_F3.png", "Rider/Run/Rider_F4.png", "Rider/Run/Rider_F5.png", "Rider/Run/Rider_F6.png", "Rider/Run/Rider_F7.png", "Rider/Run/Rider_F8.png"};
    private Animation_Controller animation = new Animation_Controller(0.1, images, this);
    public Rider()
    {
        setHealth(2);
        setGoldAmount(2);
        setSpeed(5);
        setScore(500);
    }

    public void act() 
    {
        if(update())
        {
            return;
        }
        animation.update();

        movement();
    }  

    boolean firstTime = true;
    public void movement()
    {
        Environment environment = (Environment)getOneIntersectingObject(Environment.class);

        if(environment != null && firstTime)
        {
            if(environment instanceof Castle && firstTime)
            {
                getWorld().addObject(new Bomb((Castle)environment), getX(), getY());
                setSpeed(-5);
                for(int i = 0; i < animation.images.length; i++)
                {
                    animation.images[i].mirrorHorizontally();
                }
                firstTime = false;
            }
        }
        else
        {
            setLocation((int)pos.x + speed, (int)pos.y);
            if(isAtEdge())
            {
                getWorld().removeObject(this);
            }
        }
    }
}
