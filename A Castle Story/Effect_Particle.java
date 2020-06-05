import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Effect_Particle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Effect_Particle extends Effect_Particles
{
    public Effect_Particle(Effects.Colour colour, int rotation)
    {
        getImage().scale(15, 5);
        setRotation(rotation);   
        switch(colour)
        {
            case BLUE:
            setImage("button-blue.png");
            getImage().scale(15, 5);
            break;
            case GREEN:
            setImage("button-green.png");
            getImage().scale(15, 5);
            break;
            case RED:
            setImage("button-red.png");
            getImage().scale(15, 5);
            break;
            case YELLOW:
            setImage("button-yellow.png");
            getImage().scale(15, 5);
            break;
            case PURPLE:
            setImage("button-purple.png");
            getImage().scale(15, 5);
            break;
        }
    }   
    
    public Effect_Particle(Effects.Colour colour, int rotation, double speed)
    {
        getImage().scale(15, 5);
        setRotation(rotation);   
        setSpeed(speed, 3);
        
        switch(colour)
        {
            case BLUE:
            setImage("button-blue.png");
            getImage().scale(15, 5);
            break;
            case GREEN:
            setImage("button-green.png");
            getImage().scale(15, 5);
            break;
            case RED:
            setImage("button-red.png");
            getImage().scale(15, 5);
            break;
            case YELLOW:
            setImage("button-yellow.png");
            getImage().scale(15, 5);
            break;
            case PURPLE:
            setImage("button-purple.png");
            getImage().scale(15, 5);
            break;
        }
    }   
    
    public Effect_Particle(Effects.Colour colour, int rotation, double speed, double increase)
    {
        getImage().scale(15, 5);
        setRotation(rotation);   
        setSpeed(speed, 3);
        setIncrease(increase, 0.2);
        
        switch(colour)
        {
            case BLUE:
            setImage("button-blue.png");
            getImage().scale(15, 5);
            break;
            case GREEN:
            setImage("button-green.png");
            getImage().scale(15, 5);
            break;
            case RED:
            setImage("button-red.png");
            getImage().scale(15, 5);
            break;
            case YELLOW:
            setImage("button-yellow.png");
            getImage().scale(15, 5);
            break;
            case PURPLE:
            setImage("button-purple.png");
            getImage().scale(15, 5);
            break;
        }
    }   
}
