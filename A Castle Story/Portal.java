import greenfoot.*;

public class Portal extends Actor
{
    public void act() 
    {
        Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
        if(enemy != null)
        {
            enemy.setLocation(enemy.getX() + 500, enemy.getY());
            getWorld().removeObject(this);
            return;
        }
    }    
}
