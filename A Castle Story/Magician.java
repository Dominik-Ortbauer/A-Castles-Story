import greenfoot.*;

public class Magician extends Enemy
{
    private String[] allImages = {"Magician_F1.png", "Magician_F2.png", "Magician_F3.png", "Magician_F4.png", "Magician_F5.png", "Magician_F6.png", "Magician_F7.png"};
    private Animation_Controller movement = new Animation_Controller(0.1, allImages, this);
    
    private int timeBtwPortals = 300;
    private int portalTimer;
    
    public Magician()
    {
        setGoldAmount(2);
        setHealth(5);
        setSpeed(1);
        setScore(500);
    }
    
    public void act() 
    {
        if(update())
        {
            return;
        }
        
        movement.update();
        
        movement();
        makePortal();
        
        portalTimer++;
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
    
    private void makePortal()
    {
        Enemy enemy = getSecondClosestEnemy();
        
        if(portalTimer >= timeBtwPortals && enemy != null && enemy.getX() < 850)
        {
            getWorld().addObject(new Portal(), enemy.getX() + 100, enemy.getY());
            getWorld().addObject(new Portal(), 900, enemy.getY());
            
            portalTimer = 0;
        }
    }
}
