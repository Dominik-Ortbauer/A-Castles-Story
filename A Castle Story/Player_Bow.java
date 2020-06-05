import greenfoot.*;

public class Player_Bow extends Player_Weapons
{
    public Player_Bow()
    {
        timeBtwAttacks = 60;
        String[] allImages = {"Knight_Bow"};
        setImages(allImages);
    }   
    
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        if(mouse != null)
        {
            turnTowards(mouse.getX(), mouse.getY());
        }
    }
}
