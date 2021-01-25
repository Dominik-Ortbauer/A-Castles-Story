import greenfoot.*;

public class TrapPlacer extends Tower
{
    private double startTimer = 2.0;
    private double timer = 2.0;

    public void act() 
    {
        if(update())
        {
            if(timer <= 0)
            {
                shoot();
                timer = startTimer;
            }
            else
            {
                timer -= 0.017;
            }
        }
    }  

    private void shoot()
    {
        getWorld().addObject(new Trap(new Vector(850, Greenfoot.getRandomNumber(800))), getX(), getY());
    }  
}
