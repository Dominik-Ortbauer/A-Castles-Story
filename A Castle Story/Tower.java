import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Tower extends Actor
{
    public Enemy[] enemies;
    public Vector pos = new Vector();

    public boolean update()
    {
        pos.set(getX(), getY());
        if(getClosestEnemy() == null)
        {
            return false;
        }
        return true;
    }
    
    public Enemy getClosestEnemy()
    {
        setEnemies();
        
        Enemy closestEnemy = null;
        if(enemies != null && enemies.length != 0)
        {
            closestEnemy = enemies[0];
            for(int i = 1; i < enemies.length; i++)
            {
                if(pos.dist(closestEnemy.pos) > pos.dist(enemies[i].pos))
                {
                    closestEnemy = enemies[i];
                }
            }
        }  
        return closestEnemy;
    }

    public void setEnemies()
    {
        enemies = ((Castle)getWorld().getObjects(Castle.class).get(0)).getEnemies();
    }
}
