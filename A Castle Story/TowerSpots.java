import greenfoot.*;

public class TowerSpots extends Buttons
{
    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {
            if(ShopTower1.isBought)
            {
                if(getY() == 200)
                {
                    Battlefield.towerSpots[0] = 1;
                }
                else
                {
                    Battlefield.towerSpots[1] = 1;
                }
                ShopTower1.isBought = false;
                getWorld().addObject(new PurchasedInformation(), 600, 600);
                GoldCounter.gold -= ((Shop)getWorld()).getObjects(ShopTower1.class).get(0).price;
                setImage("Tower1_F2.png");
                getImage().setTransparency(255);
            }
            else if(ShopTrapPlacer.isBought)
            {
                if(getY() == 200)
                {
                    Battlefield.towerSpots[0] = 2;
                }
                else
                {
                    Battlefield.towerSpots[1] = 2;
                }
                ShopTrapPlacer.isBought = false;
                getWorld().addObject(new PurchasedInformation(), 600, 600);
                GoldCounter.gold -= ((Shop)getWorld()).getObjects(ShopTrapPlacer.class).get(0).price;
                setImage("TrapPlacer_F1.png");
                getImage().setTransparency(255);
            }
            ((Shop)getWorld()).removeTowerSpots();
        }
    }

}