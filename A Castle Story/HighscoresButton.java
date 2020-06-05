import greenfoot.*;

public class HighscoresButton extends Buttons
{
    public HighscoresButton()
    {
    }
    
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(new Highscores());
        }
    }    
}
