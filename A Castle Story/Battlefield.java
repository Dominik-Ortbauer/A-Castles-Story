import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.*;

public class Battlefield extends World
{    
    private Wave wave;

    public static int[] towerSpots = new int[2];

    public Castle castle;
    private Door door = new Door(Game.shop);

    private WaveIndicator waveIndicator = new WaveIndicator();

    private List<Tower> towers = new ArrayList<Tower>();
    public Battlefield()
    {       
        super(1200, 800, 1);   
        
        Class[] classes = {StunnedEffect.class, PlayerImages.class};
        setPaintOrder(classes);

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

        if(towerSpots[0] == 1)
        {
            Tower tower = new Tower1();
            towers.add(tower);
            addObject(tower, 1100, 200);
        }
        else if(towerSpots[0] == 2)
        {
            Tower tower = new TrapPlacer();
            towers.add(tower);
            addObject(tower, 1100, 200);
        }

        if(towerSpots[1] == 1)
        {
            Tower tower = new Tower1();
            towers.add(tower);
            addObject(tower, 1100, 600);
        }
        else if(towerSpots[1] == 2)
        {
            Tower tower = new TrapPlacer();
            towers.add(tower);
            addObject(tower, 1100, 600);
        }
    }

    Enemy[][] enemiesToSpawn = new Enemy[4][0];
    int[] enemyAmounts = new int[enemiesToSpawn.length];

    public Wave createWave()
    {      
        if(Game.levelCount < 10)
        {
            enemiesToSpawn = new Enemy[5][0];
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
                if(spawnProbability <= 23)
                {
                    enemyAmounts[0]++;
                }
                else if(spawnProbability <= 46)
                {
                    enemyAmounts[1]++;
                }
                else if(spawnProbability <= 69)
                {
                    enemyAmounts[2]++;
                }
                else if(spawnProbability <= 77)
                {
                    enemyAmounts[3]++;
                }
                else if(spawnProbability <= 100)
                {
                    enemyAmounts[4]++;
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
                enemiesToSpawn[0][i] = new Goblin();
            }
            for(int i = 0; i < enemiesToSpawn[1].length; i++)
            {
                enemiesToSpawn[1][i] = new Rider();
            }
            for(int i = 0; i < enemiesToSpawn[2].length; i++)
            {
                enemiesToSpawn[2][i] = new Trebuchet();
            }
            for(int i = 0; i < enemiesToSpawn[3].length; i++)
            {
                enemiesToSpawn[3][i] = new Giant();
            }
            for(int i = 0; i < enemiesToSpawn[4].length; i++)
            {
                enemiesToSpawn[4][i] = new GoblinWarrior();
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
