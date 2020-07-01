import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Play here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Play extends Buttons
{
    public Play()
    {

    }

    public void act()
    {
        if(Greenfoot.mouseClicked(this))
        {
            getWorld().getObjects(Door.class).get(0).openDoor();
            getWorld().addObject(new Label("press space to dash", 48), 500, 20);
            getWorld().addObject(Game.player, getWorld().getWidth()/2, getWorld().getHeight()/2);
            HighscoresButton highscoreButton = getWorld().getObjects(HighscoresButton.class).get(0);
            TutorialButton tutorialButton = getWorld().getObjects(TutorialButton.class).get(0);
            getWorld().removeObject(highscoreButton);
            getWorld().removeObject(tutorialButton);
            getWorld().removeObject(this);
        }
    }
}