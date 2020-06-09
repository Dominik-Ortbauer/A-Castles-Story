import greenfoot.*;
import java.util.Random;

public class Club extends BossAttacks
{
    boolean firstFrame = true;
    Vector target = new Vector();
    private int speed = 5;
    boolean hitByPlayer = false;
    
    public void act() 
    {
        if(getOneIntersectingObject(Zyklope.class) != null && hitByPlayer)
        {
            ((Zyklope)getOneIntersectingObject(Zyklope.class)).stun();
            getWorld().removeObject(this);
            return;
        }
        
        if(getOneIntersectingObject(Castle.class) != null)
        {
            ((Castle)getOneIntersectingObject(Castle.class)).takeDamage(2);
            getWorld().removeObject(this);
            return;
        }
        
        if(firstFrame)
        {
            Random dice = new Random();
            firstFrame = false;
            target = new Vector(1200, dice.nextInt(800));
            // target.setMag(speed);
        }
            
        turnTowards((int)target.x, (int)target.y);
        move(speed);
        //setLocation(getX() + (int)target.x, getY() + (int)target.y);
    } 
    
    public void hitByPlayer()
    {
        target.set(150, 400);
        speed = 10;
        hitByPlayer = true;
    }
}
