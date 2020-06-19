import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MeeleAttack extends Attacks
{
    private final int startWaitTime = 4;
    private int waitTime = 4;
    private int frameCount = 1;

    private String[] frames_ = {"Knight/Meele/Meele_F1.png","Knight/Meele/Meele_F2.png","Knight/Meele/Meele_F3.png","Knight/Meele/Meele_F4.png","Knight/Meele/Meele_F5.png"};
    private GreenfootImage[] frames;
    private int size = 75;

    private Vector dir;

    private double offset = 40;

    private GreenfootSound hitSound = new GreenfootSound("Wuhuuu.mp3");

    private int damage;
    public MeeleAttack(Vector dir_, int damage_)
    {
        hitSound.setVolume(30);
        dir = dir_;
        dir.setMag(offset);
        damage = damage_;

        frames = new GreenfootImage[frames_.length];
        for(int i = 0; i < frames_.length; i++)
        {
            frames[i] = new GreenfootImage(frames_[i]);
            //frames[i].scale(size, size);
        }                
    }

    boolean firstFrame = true;
    public void act() 
    {
        if(firstFrame)
        {
            turnTowards((int)dir.x + getX(), (int)dir.y + getY());
            turn(120);
            firstFrame = false;
        }

        Player player = (Player)getWorld().getObjects(Player.class).get(0);
        setLocation((int)player.pos.x + (int)dir.x, (int)player.pos.y + (int)dir.y);

        if(waitTime <= 0)
        {
            if(frameCount < frames.length)
            {
                setImage(frames[frameCount]);
                frameCount++;
                waitTime = startWaitTime;
            }
            else
            {
                getWorld().removeObject(this);
            }
        }
        else
        {
            waitTime --;
        } 

        if(frameCount == 4 && waitTime == (startWaitTime/2))
        {
            if(getOneIntersectingObject(Club.class) != null)
            {
                ((Club)getOneIntersectingObject(Club.class)).hitByPlayer();
            }

            Object hitBoss = getOneIntersectingObject(Zyklope.class);
            if(hitBoss != null)
            {
                ((Zyklope)hitBoss).takeDamage(damage);
            }

            Object[] hits = getIntersectingObjects(Enemy.class).toArray();

            if(hits.length != 0)
            {
                getWorld().addObject(new Effect(Effects.Colour.GREEN, new Vector(15, 5), 20), getX(), getY());
            }

            for(int i = 0; i < hits.length; i++)
            {
                ((Enemy)hits[i]).takeDamage(damage);
            }
        }
    }    
}
