import greenfoot.*;

/**
 * Write a description of class Animation_Controller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Animation_Controller  
{
    public GreenfootImage[] images;
    public double startTimeBtwFrames;
    private double timeBtwFrames;
    private int frameCount = 0;
    private Actor actor;
    private boolean animate = true;
    public Animation_Controller(double timeBtwFrames_, String[] images_, Actor actor_)
    {
        startTimeBtwFrames = timeBtwFrames_;
        actor = actor_;

        for(int i = 0; i < images_.length; i++)
        {
            images[i] = new GreenfootImage(images_[i]);
        }
    }

    public void stop()
    {
        animate = false;
    }

    public void start()
    {
        animate = true;
    }

    public void update()
    {
        if(animate)
        {
            if(timeBtwFrames <= 0)
            {
                if(frameCount >= images.length)
                {
                    frameCount = 0;
                }

                actor.setImage(images[frameCount++]);
                timeBtwFrames = startTimeBtwFrames;
            }
            else
            {
                timeBtwFrames -= 0.017;
            }
        }
    }
    
    public void resetImages()
    {
        actor.setImage(images[0]);
    }
    
    public void resetAnimation()
    {
        frameCount = 0;
    }
}
