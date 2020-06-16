import greenfoot.*;

public class Game
{
    public static final int maxHealth = 10;
    public static int health = 10;
    public static Player player = new Player();
    public static Battlefield battlefield = new Battlefield();
    public static Shop shop = new Shop();
    public static int levelCount = 1;
    public static GreenfootSound backgroundMusic = new GreenfootSound("Ausseerland7.mp3");

    public static void reset()
    {
        health = maxHealth;
        player = new Player();
        battlefield = new Battlefield();
        shop = new Shop();
        levelCount = 1;
        GoldCounter.gold = 0;
        ScoreCounter.score = 0;
        Battlefield.towerSpots = new int[7];
    }
    
    public static int indexOf(int[] array, int value)
    {
        for(int i = 0; i < array.length; i++)
        {
            if(array[i] == value)
            {
                return i;
            }
        }
        return -1;
    }
}
