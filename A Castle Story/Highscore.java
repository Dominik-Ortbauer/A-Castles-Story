import greenfoot.*;

public class Highscore extends Actor
{
    public static int[] highscore = new int[5];
    public Color transparent = new Color(0, 0, 0, 0);

    public Highscore()
    {
        setImage(new GreenfootImage(
        highscore[0] + "\n" +
        highscore[1] + "\n" +
        highscore[2] + "\n" +
        highscore[3] + "\n" +
        highscore[4] + "\n" , 48, Color.GREEN, transparent));
    }
}