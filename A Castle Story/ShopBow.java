import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShopBow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShopBow extends ShopItems
{
    public boolean isBought;

    public ShopBow()
    {
        setPrice(20);
    }
    
    public ShopBow(boolean isBought_)
    {
        setPrice(20);
        isBought = isBought_;
    }

    public void act() 
    {
        if(firstFrame && !isBought)
        {
            addUI();
            firstFrame = false;
        }
        
        updateImage();
                
        if(Greenfoot.mouseClicked(this) && (GoldCounter.gold >= price || isBought))
        {
            if(!isBought)
            {
                GoldCounter.gold -= price;
                removeUI();
                isBought = true;                
            }
            getWorld().removeObject(Game.player.currentWeapon);
            Game.player.currentWeapon = new Player_Bow();
            Game.player.playerBody.images.resetAnimation();
            Game.player.forceUpdateImages();
        }
        else if(Greenfoot.mouseClicked(this) && GoldCounter.gold < price)
        {
            getWorld().addObject(new NotEnoughGold(), 600, 600);
        }
    }    
    
    public void updateImage()
    {
        if(Game.player.currentWeapon instanceof Player_Bow)
        {
            getImage().setTransparency(0);
        }
        else
        {
            getImage().setTransparency(255);
        }
    }
}
