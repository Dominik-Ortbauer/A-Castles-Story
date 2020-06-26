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
        
        if(getOneIntersectingObject(Club.class) != null)
        {
            ((Club)getOneIntersectingObject(Club.class)).hitByPlayer();
        }
        
        Object hitBoss1 = getOneIntersectingObject(Zyklope.class);
        if(hitBoss1 != null)
        {
            ((Zyklope)hitBoss1).takeDamage(damage);
            getWorld().removeObject(this);
            return;
        }
        
        Object hitBoss2 = getOneIntersectingObject(Minotaur.class);
        if(hitBoss2 != null)
        {
            ((Minotaur)hitBoss2).takeDamage(damage);
            getWorld().removeObject(this);
            return;
        }
        
        Object hit = getOneIntersectingObject(Enemy.class);
        if(hit != null)
        {
            ((Enemy)hit).takeDamage(damage);
            getWorld().removeObject(this);
            return;
        }
    }    
}
