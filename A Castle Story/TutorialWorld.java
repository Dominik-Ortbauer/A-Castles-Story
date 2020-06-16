import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tutorial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TutorialWorld extends World
{

    /**
     * Constructor for objects of class Tutorial.
     * 
     */
    public TutorialWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(new Road(), 600, 400);
        addObject(new CastleSprite(), 1100, 400);
        ShopBow shopBow = new ShopBow();
        addObject(shopBow,900,600);
        shopBow.isBought = true;
        ShopHammer shopHammer = new ShopHammer();
        addObject(shopHammer,800,600);
        shopHammer.isBought = true;
        ShopSword shopSword = new ShopSword();
        addObject(shopSword,700,600);
        shopSword.isBought = true;
        addObject(Game.player, 600, 400);
        SpawnBomber spawnBomber = new SpawnBomber();
        addObject(spawnBomber,909,81);
        SpawnGiant spawnGiant = new SpawnGiant();
        addObject(spawnGiant,825,81);
        SpawnGoblin spawnGoblin = new SpawnGoblin();
        addObject(spawnGoblin,748,83);
        SpawnTrebuchet spawnTrebuchet = new SpawnTrebuchet();
        addObject(spawnTrebuchet,649,84);
        spawnTrebuchet.setLocation(673,87);
        spawnTrebuchet.setLocation(672,79);
    }
}
