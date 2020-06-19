import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Minotaur here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Minotaur extends Bosses
{
    private String[] spinImages = {"Minotaur/SpinLoop/Minotaur_SpinLoop_F1.png", "Minotaur/SpinLoop/Minotaur_SpinLoop_F2.png", "Minotaur/SpinLoop/Minotaur_SpinLoop_F3.png"};
    private Animation_Controller spinAnim = new Animation_Controller(0.1, spinImages, this);
    
    private String[] meeleImages = {"Minotaur/Meele/Minotaur_Meele_F1.png", "Minotaur/Meele/Minotaur_Meele_F2.png", "Minotaur/Meele/Minotaur_Meele_F3.png", "Minotaur/Meele/Minotaur_Meele_F4.png", "Minotaur/Meele/Minotaur_Meele_F5.png", "Minotaur/Meele/Minotaur_Meele_F6.png", "Minotaur/Meele/Minotaur_Meele_F7.png", "Minotaur/Meele/Minotaur_Meele_F8.png"};
    private Animation_Controller meeleAnim = new Animation_Controller(0.1, meeleImages, this);
    
    private boolean isSpinning = false;
    private boolean isMeeleing = false;
        
    public void act() 
    {
        spinAnim.update();
    }    
}
