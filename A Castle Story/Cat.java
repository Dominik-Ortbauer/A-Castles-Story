import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cat extends Shopkeepers
{
    private Label label;

    public void update()
    {
        label.update("Repair Castle to Full: " + (Game.maxHealth - Game.health) * 3 + " g", 24);
    }

    boolean firstFrame = true;
    public void act()
    {
        if(firstFrame)
        {
            label = new Label("Repair Castle to Full: " + (Game.maxHealth - Game.health) * 3 + " g", 24);
            getWorld().addObject(label, getX(), getY() + 75);
            firstFrame = false;
        }

        int price = (Game.maxHealth - Game.health) * 3;
        if(Greenfoot.mouseClicked(this))
        {
            if(GoldCounter.gold >= price)
            {
                GoldCounter.gold -= price;
                Game.health = Game.maxHealth;
                Game.battlefield.castle.updateUI();
            }
            else
            {
                getWorld().addObject(new NotEnoughGold(), getX(), getY() + 50);
            }
        }
        update();
    }
}
