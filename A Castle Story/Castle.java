import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;

public class Castle extends Environment
{    
    Heart[] hearts = new Heart[Game.maxHealth];
    private double startSpawnTime = 4;
    private double spawnTime = 4;
    private int spawnedEnemyCount = 0;
    private boolean cleared = false;
    public Wave wave;

    public Castle(Wave wave_)
    {        
        wave = wave_;
    }

    public void setWave(Wave wave_)
    {
        wave = wave_;
        cleared = false;
    }

    public void act() 
    {
        checkHealth();

        if(!wave.enemyAvailable() && getWorld().getObjects(Enemy.class).size() == 0 && !cleared)
        {
            ((Door)getOneIntersectingObject(Door.class)).openDoor();
            Game.levelCount++;
            if(startSpawnTime >= 0.5)
            {
                startSpawnTime *= 0.90;
            }
            Object[] traps = (getWorld().getObjects(Projectiles.class).toArray());

            for(Object trap : traps)
            {
                getWorld().removeObject((Actor)trap);
            }
            cleared = true;
        }

        if(wave.enemyAvailable())
        {
            if(spawnTime <= 0)
            {
                getWorld().addObject(wave.getNextEnemy(), 0, Greenfoot.getRandomNumber(getWorld().getHeight() - 100)+50);
                spawnTime = startSpawnTime;
            }
            else
            {
                spawnTime -= 0.017;
            }
        }

    } 

    private void checkHealth()
    {
        if(Game.health <= 0)
        {
            boolean highscorePlace = ScoreCounter.score > Highscore.highscore[4];

            if(highscorePlace)
            {
                Highscore.highscore[4] = ScoreCounter.score;
                Arrays.sort(Highscore.highscore);
                reverse(Highscore.highscore);
            }

            Greenfoot.setWorld(new MainMenu());         
        }
    }

    public static void reverse(int[] array)
    {
        for(int i = 0; i < array.length/2; i++){
            int copy = array[i];
            array[i] = array[array.length -i -1];
            array[array.length -i -1] = copy;
        }
    }

    public void updateUI()
    {
        for(int i = 0; i < hearts.length; i++)
        {
            if(i + 1 <= Game.health)
            {
                hearts[i].changeToFullHeart();
            }
            else
            {
                hearts[i].changeToEmptyHeart();
            }
        }
    }

    public void addUI()
    {
        for(int i = 0; i < hearts.length; i++)
        {
            hearts[i] = new Heart();
            getWorld().addObject(hearts[i],i * 50 + 50 , 50);
        }
    }

    public void takeDamage(int value)
    {
        Game.health -= value;
        updateUI();
    }

    public void heal(int value)
    {
        Game.health += value;
        updateUI();
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
