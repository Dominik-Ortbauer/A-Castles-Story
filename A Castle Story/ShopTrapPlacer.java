import greenfoot.*;

public class ShopTrapPlacer extends ShopItems
{
    public static boolean isBought;

    public ShopTrapPlacer()
    {
        setPrice(15);
    }

    public void act() 
    {
        if(firstFrame)
        {
            addUI();
            firstFrame = false;
        }

        if(Greenfoot.mouseClicked(this) && GoldCounter.gold >= price)
        {
            
            isBought = true;
            ((Shop)getWorld()).addTowerSpots();
        }

        else if(Greenfoot.mouseClicked(this) && GoldCounter.gold < price)
        {
            getWorld().addObject(new NotEnoughGold(), 600, 600);
        }
    }
}