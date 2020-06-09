import greenfoot.*;

public class Zyklope extends Actor
{
    private int health = 1;
    boolean isDamageable = true;

    public void act() 
    {
        if(getOneIntersectingObject(Player.class) != null)
        {
            swingClub((Player)getOneIntersectingObject(Player.class));
        }
    }

    public void takeDamage(int amount)
    {
        if(isDamageable)
        {
            health -= amount;

            if(health <= 0)
            {
                defeated();
            }
        }
    }

    private void defeated()
    {
        getWorld().addObject(new Effect(Effects.Colour.PURPLE, new Vector(50, 25), 60, 15), getX(), getY());
        getWorld().removeObject(this);
    }

    private void stompAttack()
    {
        getWorld().addObject(new ShockWave(), getX(), getY());
    }

    private void throwClub()
    {
        getWorld().addObject(new Club(), getX(), getY());
    }

    private void swingClub(Player player)
    {
        Vector knockBackDir = new Vector(player.getX(), player.getY());
        knockBackDir.sub(new Vector(getX(), getY()));
        Game.player.knockBack(knockBackDir, 15.0, 12);
    }
}
