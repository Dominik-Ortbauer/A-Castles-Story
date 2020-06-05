import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Heart extends Actor
{
    GreenfootImage fullHeart = new GreenfootImage("herz.png"); 
    GreenfootImage emptyHeart = new GreenfootImage("leeresHerz.png"); 
    private int scale = 3;
    
    public Heart()
    {
        fullHeart.scale(15 * scale, 13 * scale);
        emptyHeart.scale(15 * scale, 13 * scale);
        setImage(fullHeart);
    }
    
    public void changeToFullHeart()
    {
        setImage(fullHeart);
    }
    
    public void changeToEmptyHeart()
    {
        setImage(emptyHeart);
    }
    
    public void setHeartLocation(Vector pos)
    {
        setLocation((int)pos.x, (int)pos.y);
    }
}
