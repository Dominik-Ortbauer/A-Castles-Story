import greenfoot.*;
import java.lang.Object;

public class PurchasedInformation extends Texts
{
    int transparency = 255;
    
    public void act() 
    {
        setImage(new GreenfootImage("The tower will be placed on the battlefield", 24, new Color(255, 255, 255, transparency), new Color(0, 0, 0, 0)));
        transparency--;
        if(transparency <= 0)
        {
            getWorld().removeObject(this);
        }
    }    
}
