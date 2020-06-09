import greenfoot.*;

public class Zyklope extends Actor
{
    private int health = 100;
    boolean isDamageable = false;

    private int chooseAttack;
    private int chooseTimeBtwAttack;
    private int timeBtwAttack;
    private int attackTimer;

    public void act()
    {
        if(getOneIntersectingObject(Player.class) != null)
        {
            swingClub((Player)getOneIntersectingObject(Player.class));
        }
        else if(Game.player.isStunned)
        {
            throwClub();
        }
        else
        {
            attackRandomly();
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

    private void attackRandomly()
    {
        chooseAttack = Greenfoot.getRandomNumber(100);
        chooseTimeBtwAttack = Greenfoot.getRandomNumber(2);
        attackTimer++;

        if(chooseTimeBtwAttack == 0)
        {
            timeBtwAttack = 20;
        }
        else if(chooseTimeBtwAttack == 1)
        {
            timeBtwAttack = 60;
        }
        else if(chooseTimeBtwAttack == 2)
        {
            timeBtwAttack = 120;
        }

        if(chooseAttack <= 25 && attackTimer >= timeBtwAttack)
        {
            throwClub();
            attackTimer = 0;
        }
        else if(chooseAttack > 25 && attackTimer >= timeBtwAttack)
        {
            stompAttack();
            attackTimer = 0;
        }
    }
}
