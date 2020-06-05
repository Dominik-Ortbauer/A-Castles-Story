import greenfoot.*;

public class Wave  
{
    int[] counts;
    public Enemy[][] allEnemies;
    int enemyCounter = 0;

    public Wave(Enemy[][] enemiesToSpawn)
    {
        allEnemies = enemiesToSpawn;
        
        counts = new int[allEnemies.length];
    }

    public Enemy getNextEnemy()
    {
        boolean decided = false;
        int dice = 0;
        Enemy enemy = null;
        
        do
        {
            dice = Greenfoot.getRandomNumber(allEnemies.length);
            
            if(counts[dice] < allEnemies[dice].length)
            {
                enemy = allEnemies[dice][counts[dice]];
                counts[dice]++;
                decided = true;
                enemyCounter++;
            }
        }while(!decided);
        
        return enemy;
    }
    
    public void reset()
    {
        enemyCounter = 0;
        
        for(int i = 0; i < counts.length; i++)
        {
            counts[i] = 0;
        }
    }
    
    public boolean enemyAvailable()
    {
        return enemyCounter < wholeLength();   
    }
    
    public int wholeLength()
    {
        int sum = 0;
        
        for(int i = 0; i < allEnemies.length; i++)
        {
            sum += allEnemies[i].length;
        }
        
        return sum;
    }
}
