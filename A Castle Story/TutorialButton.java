import greenfoot.*;

public class TutorialButton extends Buttons
{
    public TutorialButton()
    {
        setImage(new GreenfootImage("Tutorial", 96, Color.GREEN, new Color(0, 0, 0, 0)));
    }
    
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(new Tutorial());
        }
    }    
}
