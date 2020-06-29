import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{    
    private int damage = 1;
    final double startTimeBtwAttacks = 1;
    double timeBtwAttacks = 1;

    public Enemy[] enemies;

    public Vector pos = new Vector();
    public int speed;

    public int health;
    public int maxHealth;

    public int goldToDrop;
    public int scoreToDrop;

    int dazedTime = 0;
    
    public boolean gotAlreadyDamaged = false;

    public boolean update()
    {
        pos.set(getX(), getY());

        if(dazedTime > 0)
        {
            dazedTime--;
            return true;
        }        

        return false;
    }
    
    public GreenfootImage[] makeImages(String firstPart, int length)
    {
        GreenfootImage[] images = new GreenfootImage[length];
        
        for(int i = 0; i < length; i++)
        {
            images[i] = new GreenfootImage(firstPart + (i+1) + ".png");
        }
        
        return images;
    }

    public void checkHealth()
    {
        if(health <= 0)
        {
            if(this instanceof Tree)
            {
                getWorld().addObject(new Leaf(makeImages("Leaf/Run/Leaf_F", 5)), getX(), getY());
                getWorld().addObject(new Leaf(makeImages("Leaf/Run/Leaf_F", 5)), getX() - 50, getY() - 50);
                getWorld().addObject(new Leaf(makeImages("Leaf/Run/Leaf_F", 5)), getX() - 50, getY() + 50);
            }
            else
            {
                getWorld().addObject(new Effect(Effects.Colour.YELLOW, new Vector(30, 10), 20), getX(), getY());
            }
            GoldCounter.addGold(goldToDrop);
            ScoreCounter.addScore(scoreToDrop);
            getWorld().removeObject(this);
        }
    }

    public void setScore(int value)
    {
        scoreToDrop = value;
    }

    public void setHealth(int amount)
    {
        health = amount;
        maxHealth = amount;
    }

    public void setSpeed(int value)
    {
        speed = value;
    }

    public void setGoldAmount(int value)
    {
        goldToDrop = value;
    }

    public void takeDamage(int value)
    {
        health -= value;
        checkHealth();
    }    

    public void stun(int time)
    {
        dazedTime = time;
    }
    
    public void pushBack(Vector direction, int speed)
    {
        direction.setMag(speed);
        setLocation(getX() + (int)direction.x, getY() + (int)direction.y);
    }
    
    public Enemy getSecondClosestEnemy()
    {
        setEnemies();

        Enemy closestEnemy = null;
        if(enemies != null && enemies.length != 0)
        {
            closestEnemy = enemies[0];
            for(int i = 1; i < enemies.length; i++)
            {
                if(pos.dist(closestEnemy.pos) > pos.dist(enemies[i].pos) && !(enemies[i] instanceof Magician))
                {
                    closestEnemy = enemies[i];
                }
            }
        }

        if(closestEnemy instanceof Magician)
        {
            return null;
        }
        else
        {
            return closestEnemy;
        }
    }

    public void setEnemies()
    {
        enemies = getEnemies();
    }
    
    public Enemy[] getEnemies()
    {
        Object[] objects = getWorld().getObjects(Enemy.class).toArray();
        Enemy[] enemies = new Enemy[objects.length];

        for(int i = 0; i < objects.length; i ++)
        {
            enemies[i] = (Enemy)objects[i];
        }

        return enemies;
    }
}
