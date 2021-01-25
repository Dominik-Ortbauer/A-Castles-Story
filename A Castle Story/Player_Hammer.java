import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player_Hammer extends Player_Weapons
{
    public Player_Hammer()
    {
        timeBtwAttacks = 120;
        String[] allImages = {"Knight/Arms/Hammer/Knight_Hammer_F1", "Knight/Arms/Hammer/Knight_Hammer_F2", "Knight/Arms/Hammer/Knight_Hammer_F3", "Knight/Arms/Hammer/Knight_Hammer_F4", "Knight/Arms/Hammer/Knight_Hammer_F5", "Knight/Arms/Hammer/Knight_Hammer_F6", "Knight/Arms/Hammer/Knight_Hammer_F7", "Knight/Arms/Hammer/Knight_Hammer_F8"};
        setImages(allImages);
    }
}
