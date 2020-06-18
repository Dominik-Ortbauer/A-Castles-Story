import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.reflect.Constructor;

/**
 * Write a description of class SpawnButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpawnButton extends Buttons
{
    private Class enemyToSpawnClass;
    
    public SpawnButton(Class myClass)
    {
        enemyToSpawnClass = myClass;
        try
        {
            Constructor constructor = myClass.getConstructor();
            Object instanceOfMyClass = constructor.newInstance();
        }
        catch(Exception ex)
        {
            System.out.print("Error while creating Enemy");
        }
    }
    
    public void addedToWorld(World world)
    {
        getWorld().addObject(new Label(enemyToSpawnClass.getName(), 12), getX(), getY() + 50);
        // enemyToSpawnClass.getName();
    }   

    public void act() 
    {
        if(Greenfoot.mouseClicked(this))
        {
            getWorld().addObject(instantiateEnemy(enemyToSpawnClass), 0, Greenfoot.getRandomNumber(700) + 50);
        }
    }   
    
    public Enemy instantiateEnemy(Class myClass)
    {
        try
        {
            Constructor constructor = myClass.getConstructor();
            Object instanceOfMyClass = constructor.newInstance();
            return (Enemy)instanceOfMyClass;
        }
        catch(Exception ex)
        {
            System.out.print("Error while creating Enemy");
            return null;
        }
    }
}
