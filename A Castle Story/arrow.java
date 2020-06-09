import greenfoot.*;

public class arrow extends Projectiles
{
    private Enemy enemy;
    int speed = 5;

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
                turnTowards(enemy.getX(), enemy.getY());  
            }
            catch(Exception ex)
            {
                getWorld().removeObject(this);
                return;
            }
            firstFrame = false;
        }

        if(update())
        {
            return;
        }        
        move(speed);
        
        Enemy hit = (Enemy)getOneIntersectingObject(Enemy.class);
        if(hit != null)
        {
            hit.takeDamage(1);
            getWorld().addObject(new Effect(Effects.Colour.GREEN, new Vector(15, 5), 20), getX() + (int)(vel.x * 10), getY() + (int)(vel.y * 10));
            getWorld().removeObject(this);
        }
    }    
}
