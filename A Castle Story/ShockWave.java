import greenfoot.*;

public class ShockWave extends BossAttacks
{
    public void act() 
    {
        turnTowards(1200, 400);
        move(10);
        if(getX() >= 1000)
        {
            getWorld().removeObject(this);
            return;
        }

        if(getOneIntersectingObject(Player.class) != null)
        {
            Game.player.stun(60);
        }
    }    
}
