import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainMenu here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainMenu extends World
{
    public MainMenu()
    {
        super(1200, 800, 1);
        
        Class[] classes = {StunnedEffect.class, PlayerImages.class};
        setPaintOrder(classes);
        
        prepare();
    }

    private void prepare()
    {
        Game.reset();
        addObject(new Road(), 600, 400);
        addObject(new CastleSprite(), 1100, 400);
        addObject(new Door(Game.shop), 1064, 400);
        Play play = new Play();
        addObject(play,600,200);
        addObject(new HighscoresButton(), 600, 500);
        addObject(new TutorialButton(), 600, 700);
    }

    private boolean firstFrame = true;

    public void act()
    {
        if(firstFrame)
        {
            Game.backgroundMusic.playLoop();
            Game.backgroundMusic.setVolume(20);
            firstFrame = false;
        }
    }
}
