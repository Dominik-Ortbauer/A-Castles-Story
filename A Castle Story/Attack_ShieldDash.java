import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Attack_ShieldDash extends Attacks
{
    private Vector direction;
    private int damage = 2;

    private int dashTimer = 0;

    private Enemy enemy;
    private int pushTimer = 0;

    public Attack_ShieldDash(Vector direction_)
    {
        direction = direction_;
        Game.player.turnTowards((int)direction.x, (int)direction.y);
        Game.player.isShieldDashing = true;
    }

    public void act() 
    {
        Game.player.move(10);

        enemy = Game.player.getIntersectingEnemy();
        if(enemy != null)
        {
            enemy.stun(60);
            enemy.takeDamage(damage);
            if(pushTimer <= 6)
            {
                enemy.pushBack(direction, 10);
                pushTimer++;
            }
        }
        
        if(dashTimer == 6)
        {
            getWorld().removeObject(this);
            Game.player.isShieldDashing = false;
            return;
        }
    }    
}
