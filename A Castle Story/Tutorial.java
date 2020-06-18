
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Tutorial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tutorial extends World
{
    private static enum Steps
    {
        MOVEMENT, ATTACK, CASTLE, ENEMIES, SANDBOX;
    }
    Steps steps = Steps.MOVEMENT;

    private int timeBtwSteps = 60;

    public Tutorial()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 

        prepare();
    }

    Label label = new Label("Press WASD to move and Space to dash", 36);
    private void prepare()
    {
        addObject(Game.player, 600, 400);
        addObject(label, 600, 50);
    }

    public void act()
    {
        switch(steps)
        {
            case MOVEMENT:
            movement();
            break;
            case ATTACK:
            attack();
            break;
            case CASTLE:
            castle();
            break;
            case ENEMIES:
            enemies();
            break;
            case SANDBOX:
            sandbox();
            break;
        }
    }
    
    private void sandbox()
    {
        if(timeBtwSteps <= 0)
        {
            removeObject(label);
        }
        else
        {
            timeBtwSteps--;
        }
    }
    
    private void enemies()
    {
        if(firstEnemy.health == 0)
        {
            timeBtwSteps--;
        }
        
        if(timeBtwSteps <= 0)
        {
            steps = Steps.ENEMIES;
            label.update("This is the sandbox mode try everything out and have fun!", 36);
            timeBtwSteps = 240;
            //add SpawnButtons
        }
    }

    private Aligator firstEnemy = new Aligator();
    private void castle()
    {
        if(timeBtwSteps <= 0)
        {
            timeBtwSteps = 60;
            steps = Steps.ENEMIES;
            label.update("This is an enemy. Stop him before he reaches the castle!", 36);
            addObject(firstEnemy, 0, Greenfoot.getRandomNumber(400) + 200);
        }
        else
        {
            timeBtwSteps--;
        }
    }

    boolean moved = false;
    boolean dashed = false;
    private void movement()
    {
        if(Game.player.movement.mag() > 0)
        {
            moved = true;
        } 

        if(Game.player.isDashing)
        {
            dashed = true;
        }

        if(dashed && moved)
        {
            if(timeBtwSteps < 0)
            {
                steps = Steps.ATTACK;
                label.update("Press any mousebutton to attack \n some Weapons need you to charge up the attack first by holding down the button", 36);
                addObject(new ShopSword(), 500, 750);
                addObject(new ShopHammer(true), 600, 750);
                addObject(new ShopBow(true), 700, 750);
                timeBtwSteps = 60;
            }
            else
            {
                timeBtwSteps--;
            }
        }
    }

    private boolean usedHammer = false;
    private boolean usedBow = false;
    Castle castle = new Castle();
    Door door = new Door(new MainMenu());
    private void attack()
    {
        if(Game.player.currentWeapon instanceof Player_Hammer && Game.player.hammerChargeTime < 0)        
        {
            usedHammer = true;
        }
        if(Game.player.currentWeapon instanceof Player_Bow && Game.player.bowChargeTime < 0)        
        {
            usedBow = true;
        }

        if(usedHammer && usedBow)
        {
            if(timeBtwSteps < 0)
            {
                timeBtwSteps = 360;
                steps = Steps.CASTLE;
                addObject(castle, 1100, 400);
                addObject(door, 1064, 400);
                castle.addUI();
                label.update("This is your castle.\nIt is your job to protect it from attackers", 36);
                label.setLocation(600, 150);
            }
            else
            {
                timeBtwSteps--;
            }
        }
    }
}
