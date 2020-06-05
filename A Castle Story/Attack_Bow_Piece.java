import greenfoot.*;

public class Attack_Bow_Piece extends Attacks
{
    Vector dir;
    private int damage = 1;
    
    public Attack_Bow_Piece(Vector dir_)
    {
        dir = dir_;
    }
    
    public void act() 
    {
        if(isAtEdge())
        {
            getWorld().removeObject(this);
            return;
        }
        
        turnTowards((int)dir.x + getX(), (int)dir.y + getY());
        move(10);
        
        Object hit = getOneIntersectingObject(Enemy.class);
        if(hit != null)
        {
            ((Enemy)hit).takeDamage(damage);
            getWorld().removeObject(this);
            return;
        }
    }    
}
