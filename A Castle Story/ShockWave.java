import greenfoot.*;

public class ShockWave extends BossAttacks
{
    boolean alreadyStunned = false;

    public void act()
    {
        turnTowards(1200, 400);
        move(10);
        if(getX() >= 1000)
        {
            getWorld().removeObject(this);
            return;
        }

        if(getOneIntersectingObject(Player.class) != null && !Game.player.isDashing && !alreadyStunned)
        {
            Game.player.stun(60);
            ((Zyklope)getWorld().getObjects(Zyklope.class).get(0)).throwClub();
            alreadyStunned = true;
        }
    }
}
