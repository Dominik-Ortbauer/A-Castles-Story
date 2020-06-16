import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shop extends World
{
    /**
     * Constructor for objects of class Shop.
     * 
     */
    public Shop()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        prepare();
    }

    private Cat cat = new Cat();
    public Door door;
    private ShopBow shopBow = new ShopBow();
    private ShopHammer shopHammer = new ShopHammer();
    private ShopSword shopSword = new ShopSword();    

    private void prepare()
    {
        CastleSprite castle = new CastleSprite();
        addObject(castle, 100, 400);
        castle.getImage().mirrorHorizontally();

        door = new Door(Game.battlefield);
        door.openDoor();
        door.getImage().mirrorHorizontally();
        addObject(door, 136, 400);

        addTowerSpots();
        addObject(new GoldCounter(), 400, 740);
        addObject(new ScoreCounter(), 900, 790);
        addObject(cat, 400, 200);
        addObject(new SpeechBubbleCat(), 500, 120);
        addObject(new Dog(), 1000, 200);
        addObject(new Label("Click on a Tower to Buy it", 24), 1000, 275);
        addObject(new ShopTrapPlacer(), 1000, 350);
        addObject(new ShopTower1(), 1000, 500);
        addObject(new SpeechBubbleDog(), 1100, 120);
        addObject(shopSword, 300, 600);
        addObject(shopHammer, 400, 600);
        addObject(shopBow, 500, 600);
        addObject(Game.player, 250, 400);

        shopBow.updateImage();
        shopSword.updateImage();
        shopHammer.updateImage();
    }

    public void addTowerSpots()
    { 
        for(int i = 1; i <= 7; i++)
        {
            if(Battlefield.towerSpots[i - 1] < 1)
            {
                TowerSpots towerSpot = new TowerSpots();
                towerSpot.getImage().setTransparency(0);
                addObject(towerSpot, 100, i*100);
            }
        }
    }

    public void showTowerSpots()
    {
        for(int i = 0; i < getObjects(TowerSpots.class).size(); i++)
        {
            Actor towerSpot = (Actor)getObjects(TowerSpots.class).get(i);

            if(towerSpot.getY() != 400 || Battlefield.towerSpots[i] != 0)
            {
                towerSpot.getImage().setTransparency(255);
            }
        }
    }
    
    public void removeTowerSpots()
    {
        for(int i = 0; i < getObjects(TowerSpots.class).size(); i++)
        {
            Actor towerSpot = (Actor)getObjects(TowerSpots.class).get(i);
            
            if(towerSpot.getY() == 400 || Battlefield.towerSpots[i] == 0)
            {
                towerSpot.getImage().setTransparency(0);
            }
        }
    }

    public void start()
    {
        addObject(Game.player, 250, 400);
        Game.player.setLocation(250, 400);
        shopBow.updateImage();
        shopSword.updateImage();
        shopHammer.updateImage();
        if(Game.levelCount % 10 == 0)
        {
            addObject(new BossSpeechBubble(), 600, 400);
        }
    }
}
