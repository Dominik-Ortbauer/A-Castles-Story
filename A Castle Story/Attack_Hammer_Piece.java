import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Attack_Hammer_Piece here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Attack_Hammer_Piece extends Attacks
{     
    int damage = 1;

    public void doDamage(Vector dir, int dist) 
    {
        if(getOneIntersectingObject(Club.class) != null)
        {
            ((Club)getOneIntersectingObject(Club.class)).hitByPlayer();
        }

        Object hitBoss = getOneIntersectingObject(Zyklope.class);
        if(hitBoss != null)
        {
            ((Zyklope)hitBoss).takeDamage(damage);
        }

        Object[] hits = getIntersectingObjects(Enemy.class).toArray();

        for(int i = 0; i < hits.length; i++)
        {            
            if(!(hits[i] instanceof Tree))
            {
                ((Enemy)hits[i]).turnTowards((int)dir.x + ((Enemy)hits[i]).getX(), (int)dir.y + ((Enemy)hits[i]).getY());
                ((Enemy)hits[i]).move(dist);
                ((Enemy)hits[i]).setRotation(0);
                ((Enemy)hits[i]).takeDamage(damage);
            }
        }
    } 

    public void stunEnemies()
    {
        Object[] hits = getIntersectingObjects(Enemy.class).toArray();

        for(int i = 0; i < hits.length; i++)
        {
            ((Enemy)hits[i]).stun(30);
        }
    }
}
