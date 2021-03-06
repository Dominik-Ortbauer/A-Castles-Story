import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ShopTower1 extends ShopItems
{
    public static boolean isBought;
    
    public ShopTower1()
    {
        setPrice(50);
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
            ((Shop)getWorld()).showTowerSpots();
            isBought = true;
        }

        else if(Greenfoot.mouseClicked(this) && GoldCounter.gold < price)
        {
            getWorld().addObject(new NotEnoughGold(), 600, 600);
        }
    }
}