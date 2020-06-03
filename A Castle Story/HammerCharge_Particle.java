import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HammerCharge_Particle_Blue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HammerCharge_Particle extends Effect_Particles
{
    int distance;
    Actor player;
    public HammerCharge_Particle(Actor player_, int distance_, Effects.Colour colour, Vector size)
    {
        distance = distance_;
        player = player_;

        switch(colour)
        {
            case BLUE:
            setImage("button-blue.png");
            break;
            case GREEN:
            setImage("button-green.png");
            break;
            case RED:
            setImage("button-red.png");
            break;
            case YELLOW:
            setImage("button-yellow.png");
            break;
            case PURPLE:
            setImage("button-purple.png");
            break;
        }
        getImage().scale((int)size.x, (int)size.y);
    }

    private boolean firstFrame = true;
    public void act() 
    {
        if(firstFrame)
        {
            turn(Greenfoot.getRandomNumber(360));
            move(distance); 
            firstFrame = false;
        }

        turnTowards(player.getX(), player.getY());

        move(5);
        if(getOneIntersectingObject(Player.class) != null)
        {
            getWorld().removeObject(this);
        }
    }                
}
