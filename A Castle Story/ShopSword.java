import greenfoot.*;

public class ShopSword extends ShopItems
{   
    public boolean isBought = true;

    public ShopSword()
    {
        setPrice(0);
    }

    public void act() 
    {
        if(firstFrame && !isBought)
        {
            addUI();
            firstFrame = false;
        }
        
        updateImage();
                
        if(Greenfoot.mouseClicked(this) && (GoldCounter.gold >= price || !isBought))
        {
            if(!isBought)
            {
                GoldCounter.gold -= price;
                removeUI();
                isBought = true;
            }
            getWorld().removeObject(Game.player.currentWeapon);
            Game.player.currentWeapon = new Player_Sword();
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
        if(Game.player.currentWeapon instanceof Player_Sword)
        {
            getImage().setTransparency(0);
        }
        else
        {
            getImage().setTransparency(255);
        }
    }
}
