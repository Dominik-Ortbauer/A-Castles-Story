import greenfoot.*;  

public class SpeechBubbleCat extends Texts
{
   public SpeechBubbleCat()
   {
        getImage().setTransparency(0);
   }
    
   public void act()
   {
       MouseInfo mouse = Greenfoot.getMouseInfo();

        if(mouse != null && getWorld().getObjectsAt(mouse.getX(), mouse.getY(), Cat.class).size() != 0 && Game.health < Game.maxHealth)
        {
            getImage().setTransparency(255);
        }
        else
        {
            getImage().setTransparency(0);
        }
   }
}
