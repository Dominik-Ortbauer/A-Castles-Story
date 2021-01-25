import greenfoot.*;

public class Granade extends Projectiles
{
    private Enemy enemy;
    private int explosionTimer = 0;
    private Enemy enemyGettingDamage;
    
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
            return;
        }
        
        enemyGettingDamage = (Enemy)getOneIntersectingObject(Enemy.class);
        if(enemyGettingDamage != null || explosionTimer > 0)
        {
            setImage("FireBall.png");
            if(!enemyGettingDamage.gotAlreadyDamaged)
            {
                enemyGettingDamage.takeDamage(3);
                enemyGettingDamage.gotAlreadyDamaged = true;
            }
        }
    }    
}
