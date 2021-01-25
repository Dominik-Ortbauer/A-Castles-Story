import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player_Sword here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player_Sword extends Player_Weapons
{
    public Player_Sword() 
    {
        String[] allImages = {"Knight/Arms/Sword/Knight_Sword_F1", "Knight/Arms/Sword/Knight_Sword_F2", "Knight/Arms/Sword/Knight_Sword_F3", "Knight/Arms/Sword/Knight_Sword_F4", "Knight/Arms/Sword/Knight_Sword_F5", "Knight/Arms/Sword/Knight_Sword_F6", "Knight/Arms/Sword/Knight_Sword_F7", "Knight/Arms/Sword/Knight_Sword_F8"};
        setImages(allImages);
        timeBtwAttacks = 30;
    }  
        
}
