import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Highscores here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Highscores extends World
{

    /**
     * Constructor for objects of class Highscores.
     * 
     */
    public Highscores()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Highscore highscore = new Highscore();
        addObject(highscore,600,400);
        BackToMenu backToMenu = new BackToMenu();
        addObject(backToMenu,100,100);
    }
}
