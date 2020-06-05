import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Effect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Effect extends Effects
{
    //hitEffect: new Effect(Effects.Colour.GREEN, new Vector(15, 5), 20)
    //DeathEffect: new Effect(Effects.Colour.Yellow, new Vector(30, 10), 20)
    
    int amountOfParticles;
    Effects.Colour colour;
    Vector size;
    double speed = 8.5;
    double increase = 0.1;
    
    public Effect(Effects.Colour colour_, Vector size_, int amountOfParticles_)
    {
        amountOfParticles = amountOfParticles_;
        colour = colour_;
        size = size_;
    }
    
    public Effect(Effects.Colour colour_, Vector size_, int amountOfParticles_, double speed_)
    {
        amountOfParticles = amountOfParticles_;
        colour = colour_;
        size = size_;
        speed = speed_;
    }
    
    public Effect(Effects.Colour colour_, Vector size_, int amountOfParticles_, double speed_, double increase_)
    {
        amountOfParticles = amountOfParticles_;
        colour = colour_;
        size = size_;
        speed = speed_;
        increase = increase_;
    }

    public void act() 
    {        
        Random dice = new Random();
        for(int i = 0; i < amountOfParticles; i++)
        {
            Effect_Particle particle = new Effect_Particle(colour, dice.nextInt(360), speed, increase);
            getWorld().addObject(particle, getX(), getY());
            particle.getImage().scale((int)size.x, (int)size.y);
        }

        getWorld().removeObject(this);
    }   
}
