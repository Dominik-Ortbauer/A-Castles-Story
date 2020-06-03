import greenfoot.*;
import java.lang.Object;

public class GoldCounter extends Texts
{
    public static int gold = 0;
    public Color transparent = new Color(0, 0, 0, 0);
    
    public void act() 
    {
        setImage(new GreenfootImage("Gold: " + gold, 24 * 5, Color.YELLOW, transparent));
    }
    
    public static void addGold(int value)
    {
        gold += value;
    }
    
    public static void setGold(int value)
    {
        gold = value;
    }
}
