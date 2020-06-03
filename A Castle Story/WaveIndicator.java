import greenfoot.*;

public class WaveIndicator extends Texts
{
    public WaveIndicator()
    {
        int levelCount = Game.levelCount;
        setImage(new GreenfootImage("Wave " + levelCount, 24, Color.BLACK, new Color(0, 0, 0, 0)));
    }

    public void update()
    {
        int levelCount = Game.levelCount;
        setImage(new GreenfootImage("Wave " + levelCount, 24, Color.BLACK, new Color(0, 0, 0, 0)));
    }
}