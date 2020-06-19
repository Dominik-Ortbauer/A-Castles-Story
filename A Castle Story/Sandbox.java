import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tutorial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sandbox extends World
{

    /**
     * Constructor for objects of class Tutorial.
     * 
     */
    public Sandbox()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        
        Class[] classes = {PlayerImages.class};
        setPaintOrder(classes);
        
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(new Road(), 600, 400);
        Castle castle = new Castle();
        addObject(castle, 1100, 400);
        castle.addUI();
        addObject(new Door(new MainMenu(), true), 1064, 400);        
        ShopBow shopBow = new ShopBow();
        addObject(shopBow,900,750);
        shopBow.isBought = true;
        ShopHammer shopHammer = new ShopHammer();
        addObject(shopHammer,800,750);
        shopHammer.isBought = true;
        ShopSword shopSword = new ShopSword();
        addObject(shopSword,700,750);
        shopSword.isBought = true;
        addObject(Game.player, 600, 400);
        SpawnButton[] spawnButtons = new SpawnButton[7];
        spawnButtons[0] = new SpawnButton(Goblin.class);
        spawnButtons[1] = new SpawnButton(Rider.class);
        spawnButtons[2] = new SpawnButton(Giant.class);
        spawnButtons[3] = new SpawnButton(Trebuchet.class);
        spawnButtons[4] = new SpawnButton(GoblinWarrior.class);
        spawnButtons[5] = new SpawnButton(Magician.class);
        spawnButtons[6] = new SpawnButton(Tree.class);
        
        for(int i = 0; i < spawnButtons.length; i++)
        {
            addObject(spawnButtons[i], 600 + i*60, 50);
        }
    }
}
