import greenfoot.*;

public class Zyklope extends Actor
{
    private boolean firstFrame;
    
    private int health = 30;
    boolean isDamageable = false;
    
    private int goldToDrop = 50;
    private int scoreToDrop = 10000;

    private int chooseAttack = 100;
    private int chooseTimeBtwAttack = 0;
    private int timeBtwAttack = 0;
    private int attackTimer;

    private int stunTimer = 0;
    
    public Zyklope()
    {
        firstFrame = true;
    }
    
    public void act()
    {
        if(firstFrame)
        {
            addUI();
            firstFrame = false;
        }
        
        if(stunTimer <= 0)
        {
            isDamageable = false;
            getWorld().removeObject(stunnedEffect);
        }
        else
        {
            stunTimer--;
        }

        if(!isDamageable)
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
    }

    private void addUI()
    {
        getWorld().addObject(new Label("HP: " + health, 48), getX(), getY()-80);
    }
    
    private void removeUI()
    {
        getWorld().removeObjects(getWorld().getObjects(Label.class));
    }
    
    public void takeDamage(int amount)
    {
        if(isDamageable)
        {
            health -= amount;
            
            removeUI();
            addUI();

            if(health <= 0)
            {
                defeated();
            }
        }
    }

    private void defeated()
    {
        getWorld().addObject(new Effect(Effects.Colour.PURPLE, new Vector(50, 25), 60, 15), getX(), getY());
        removeUI();
        getWorld().removeObject(stunnedEffect);
        GoldCounter.gold += goldToDrop;
        ScoreCounter.score += scoreToDrop;
        
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

    StunnedEffect stunnedEffect = new StunnedEffect();
    public void stun()
    {
        isDamageable = true;
        stunTimer = 240;
        attackTimer = 0;
        getWorld().addObject(stunnedEffect, getX(), getY() - 50);
    }

    private void attackRandomly()
    {
        attackTimer++;

        if(chooseAttack <= 25 && attackTimer >= timeBtwAttack)
        {
            throwClub();
            attackTimer = 0;

            chooseAttack = Greenfoot.getRandomNumber(100);
            chooseTimeBtwAttack = Greenfoot.getRandomNumber(2) + 1;
            timeBtwAttack = chooseTimeBtwAttack * 120;
        }
        else if(chooseAttack > 25 && attackTimer >= timeBtwAttack)
        {
            stompAttack();
            attackTimer = 0;

            chooseAttack = Greenfoot.getRandomNumber(100);
            chooseTimeBtwAttack = Greenfoot.getRandomNumber(2) + 1;
            timeBtwAttack = chooseTimeBtwAttack * 120;
        }
    }
}
