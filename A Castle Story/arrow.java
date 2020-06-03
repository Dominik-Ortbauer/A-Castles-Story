import greenfoot.*;

public class arrow extends Projectiles
{
    private Enemy enemy;
    int speed = 10;

    public arrow(Enemy enemy_)
    {
        enemy = enemy_;        
    }

    boolean firstFrame = true;
    public void act() 
    {     
        if(firstFrame)
        {
            try
            {
                vel = new Vector(enemy.getX() - getX(), enemy.getY() - getY());
                vel.setMag(speed);
                turnTowards(enemy.getX(), enemy.getY());  
            }
            catch(Exception ex)
            {

            }
            firstFrame = false;
        }

        if(update())
        {
            return;
        }        
        setLocation(getX() + (int)vel.x, getY() + (int)vel.y);

        if(vel.mag() == 0)
        {
            getWorld().removeObject(this);
            return;
        }   

        Enemy hit = (Enemy)getOneIntersectingObject(Enemy.class);
        if(hit != null)
        {
            hit.takeDamage(1);
            getWorld().addObject(new Effect(Effects.Colour.GREEN, new Vector(15, 5), 20), getX() + (int)(vel.x * 10), getY() + (int)(vel.y * 10));
            getWorld().removeObject(this);
        }
    }    
}
