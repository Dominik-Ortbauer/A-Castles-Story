import greenfoot.*;

public class Trap extends Projectiles
{
    private Vector destination = new Vector();

    public Trap(Vector destination_)
    {
        destination = destination_;
    }

    public void act() 
    {
        if(destination.dist(new Vector(getX(), getY())) > 5)
        {
            turnTowards((int)destination.x, (int)destination.y);
            move(5);
        }

        Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
        if(enemy != null)
        {
            enemy.takeDamage(3);
            getWorld().removeObject(this);
        }
    }

    public void disappear()
    {
        getWorld().removeObject(this);
    }
}