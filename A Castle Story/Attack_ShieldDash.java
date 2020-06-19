import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Attack_ShieldDash extends Attacks
{
    private Vector direction;

    private int dashTimer = 0;
    
    private Enemy enemy;
    private boolean pushEnemy = false;
    private int pushTimer = 0;

    public Attack_ShieldDash(Vector direction_)
    {
        direction = direction_;
        Game.player.turnTowards((int)direction.x, (int)direction.y);
        Game.player.isShieldDashing = true;
    }

    public void act() 
    {
        if(dashTimer <= 6)
        {
            Game.player.move(10);
        }
        
        enemy = Game.player.getIntersectingEnemy();
        if(enemy != null)
        {
            enemy.stun(60);
            pushEnemy = true;
        }
        if(pushEnemy && pushTimer <= 30)
        {
            enemy.pushBack(direction, 2);
            pushTimer++;
        }
        
        if(dashTimer == 6)
        {
            getWorld().removeObject(this);
            Game.player.isShieldDashing = false;
            return;
        }
    }    
}
