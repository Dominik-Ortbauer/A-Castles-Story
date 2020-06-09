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
        }

        Game.player.stun(60);
    }    
}
