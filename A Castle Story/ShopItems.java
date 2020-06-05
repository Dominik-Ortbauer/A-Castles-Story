import greenfoot.*;

public class ShopItems extends Actor
{
    public int price = 0;
    public boolean firstFrame = true;

    public void setPrice(int value)
    {
        price = value;
    }

    private Prices prices;
    public void addUI()
    {
        prices = new Prices(price + " g");
        getWorld().addObject(prices, getX(), getY() + 60);
    }

    public void removeUI()
    {
        getWorld().removeObject(prices);
    }
}
