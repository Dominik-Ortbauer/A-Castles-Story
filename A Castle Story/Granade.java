import greenfoot.*;

public class Granade extends Projectiles
{
    private Enemy enemy;
    
    public Granade(Enemy enemy_)
    {
        enemy = enemy_;
    }
    
    public void act() 
    {
        if(enemy != null)
        {
            turnTowards(enemy.getX(), enemy.getY());
            move(10);
        }
        else
        {
            getWorld().removeObject(this);
        }
    }    
}
