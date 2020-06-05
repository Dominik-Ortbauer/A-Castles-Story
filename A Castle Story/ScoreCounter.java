import greenfoot.*;

public class ScoreCounter extends Texts
{
    public static int score = 0;
    public Color transparent = new Color(0, 0, 0, 0);
    
    public void act() 
    {
        setImage(new GreenfootImage("Score: " + score, 24, Color.YELLOW, transparent));
    }
    
    public static void addScore(int value)
    {
        score += value;
    } 
    
     public static void setScore(int value)
    {
        score = value;
    }
}
