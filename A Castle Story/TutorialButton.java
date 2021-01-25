import greenfoot.*;

public class TutorialButton extends Buttons
{   
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(new Tutorial());
        }
    }    
}
