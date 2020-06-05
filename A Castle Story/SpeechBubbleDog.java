import greenfoot.*;

public class SpeechBubbleDog extends Texts
{
    public SpeechBubbleDog()
    {
        getImage().setTransparency(0);
    }

    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();

        if(mouse != null && getWorld().getObjectsAt(mouse.getX(), mouse.getY(), Dog.class).size() != 0)
        {
            getImage().setTransparency(255);
        }
        else
        {
            getImage().setTransparency(0);
        }
    }
}
