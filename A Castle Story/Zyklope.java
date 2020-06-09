import greenfoot.*;

public class Zyklope extends Actor
{
    private int health = 100;
    boolean isDamageable = false;

    private int chooseAttack = Greenfoot.getRandomNumber(100);
    private int chooseTimeBtwAttack = Greenfoot.getRandomNumber(2);
    private int timeBtwAttack;
    private int attackTimer;

    public void act()
    {
        if(getOneIntersectingObject(Player.class) != null)
        {
            swingClub((Player)getOneIntersectingObject(Player.class));
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

    public void stompAttack()
    {
        getWorld().addObject(new ShockWave(), getX(), getY());
    }

    public void throwClub()
    {
        getWorld().addObject(new Club(), getX(), getY());
    }

    public void swingClub(Player player)
    {
        Vector knockBackDir = new Vector(player.getX(), player.getY());
        knockBackDir.sub(new Vector(getX(), getY()));
        Game.player.knockBack(knockBackDir, 15.0, 12);
    }

    private void attackRandomly()
    {

        attackTimer++;

        if(chooseTimeBtwAttack == 0)
        {
            timeBtwAttack = 60;
        }
        else if(chooseTimeBtwAttack == 1)
        {
            timeBtwAttack = 120;
        }
        else if(chooseTimeBtwAttack == 2)
        {
            timeBtwAttack = 360;
        }

        if(chooseAttack <= 25 && attackTimer >= timeBtwAttack)
        {
            throwClub();
            attackTimer = 0;
            chooseAttack = Greenfoot.getRandomNumber(100);
            chooseTimeBtwAttack = Greenfoot.getRandomNumber(2);
        }
        else if(chooseAttack > 25 && attackTimer >= timeBtwAttack)
        {
            stompAttack();
            attackTimer = 0;
            chooseAttack = Greenfoot.getRandomNumber(100);
            chooseTimeBtwAttack = Greenfoot.getRandomNumber(2);
        }
    }
}
