import greenfoot.*;

public class BombTower extends Tower
{
    private int startTimer = 96;
    private int timer = 96;
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
                timer--;
            }
      }
    }    
    
    private void shoot()
    {
        getWorld().addObject(new Granade(getClosestEnemy()), getX(), getY());
    }
}
