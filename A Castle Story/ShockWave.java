import greenfoot.*;

public class ShockWave extends BossAttacks
{
    
    public void act() 
    {
        turnTowards(1200, 400);
        move(10);

        Player.isStunned = getOneIntersectingObject(Player.class) != null && !Player.isDashing;
    }    
}
