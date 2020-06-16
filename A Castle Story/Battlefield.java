import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.*;

public class Battlefield extends World
{    
    private Wave wave;
    
    public static int[] towerSpots = new int[7];

    public Castle castle;
    private Door door = new Door(Game.shop);

    private WaveIndicator waveIndicator = new WaveIndicator();

    private List<Tower> towers = new ArrayList<Tower>();
    public Battlefield()
    {       
        super(1200, 800, 1);   

        addObject(new Road(), 600, 400);
        castle = new Castle(wave);
        addObject(castle, 1100, 400);

        castle.addUI();

        addObject(new GoldCounter(), 250, 740);
        addObject(new ScoreCounter(), 900, 790);

        addObject(door, 1064, 400);
        enemyAmounts[0] = 3;

        addObject(waveIndicator, 600, 780);

        prepare();
    }

    public void prepare()
    {
        door.setDestination(Game.shop);
        door.closeDoor();
        waveIndicator.update();
        
        wave = createWave();
        castle.setWave(wave);
        
        addObject(Game.player, 950, getHeight()/2);
        castle.firstFrame = true;

        removeObjects(towers);

        for(int i = 0; i < towerSpots.length; i++)
        {
            if(towerSpots[i] == 1)
            {
                Tower tower = new Tower1();
                towers.add(tower);
                addObject(tower, 1100, i*100 + 100);
            }
            if(towerSpots[i] == 2)
            {
                Tower tower = new TrapPlacer();
                towers.add(tower);
                addObject(tower, 1100, i*100 + 100);
            }
        }
    }

    Enemy[][] enemiesToSpawn = new Enemy[4][0];
    int[] enemyAmounts = new int[enemiesToSpawn.length];
    
    
    public Wave createWave()
    {      
        if(Game.levelCount < 10)
        {
            enemiesToSpawn = new Enemy[4][0];
            enemyAmounts = new int[enemiesToSpawn.length];
        }
        else if(Game.levelCount < 20)
        {
            enemiesToSpawn = new Enemy[2][0];
            enemyAmounts = new int[enemiesToSpawn.length];
        }

        int amount = Game.levelCount % 10;
        for(int i = 0; i < amount; i++)
        {
            int spawnProbability = Greenfoot.getRandomNumber(100);

            if(Game.levelCount < 10)
            {
                if(spawnProbability <= 30)
                {
                    enemyAmounts[0]++;
                }
                else if(spawnProbability <= 60)
                {
                    enemyAmounts[1]++;
                }
                else if(spawnProbability <= 90)
                {
                    enemyAmounts[2]++;
                }
                else if(spawnProbability <= 100)
                {
                    enemyAmounts[3]++;
                }
            }
            else if(Game.levelCount < 20)
            {
                if(spawnProbability <= 50)
                {
                    enemyAmounts[0]++;
                }
                else if(spawnProbability <= 100)
                {
                    enemyAmounts[1]++;
                }
            }
            
            if(i <= Game.levelCount % 10)
            {
                amount += Greenfoot.getRandomNumber(3);
            }
        }

        for(int i = 0; i < enemyAmounts.length; i++)
        {
            enemiesToSpawn[i] = new Enemy[enemyAmounts[i]];
        }

        if(Game.levelCount < 10)
        {            
            for(int i = 0; i < enemiesToSpawn[0].length; i++)
            {
                enemiesToSpawn[0][i] = new Aligator();
            }
            for(int i = 0; i < enemiesToSpawn[1].length; i++)
            {
                enemiesToSpawn[1][i] = new Bomber();
            }
            for(int i = 0; i < enemiesToSpawn[2].length; i++)
            {
                enemiesToSpawn[2][i] = new Trebuchet();
            }
            for(int i = 0; i < enemiesToSpawn[3].length; i++)
            {
                enemiesToSpawn[3][i] = new Giant();
            }
        }
        else if(Game.levelCount < 20)
        {
            for(int i = 0; i < enemiesToSpawn[0].length; i++)
            {
                enemiesToSpawn[0][i] = new Tree();
            }
            for(int i = 0; i < enemiesToSpawn[1].length; i++)
            {
                enemiesToSpawn[1][i] = new Magician();
            }
        }

        return new Wave(enemiesToSpawn);
    }
}
