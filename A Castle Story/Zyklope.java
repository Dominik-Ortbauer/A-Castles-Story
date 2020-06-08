import greenfoot.*;

public class Zyklope extends Enemy
{
    public void act() 
    {
        
    }
    
    private void stompAttack()
    {
        getWorld().addObject(new ShockWave(), getX(), getY());
    }
    
    private void throwClub()
    {
        getWorld().addObject(new Club(), getX(), getY());
    }
}
