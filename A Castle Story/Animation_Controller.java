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

    private boolean animatingOnce = false;    

    public Animation_Controller(double timeBtwFrames_, String[] images_, Actor actor_)
    {
        startTimeBtwFrames = timeBtwFrames_;
        actor = actor_;

        images = new GreenfootImage[images_.length];
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

    public void animateOnce()
    {
        animatingOnce = true;
        animate = true;
        frameCount = 0;
        timeBtwFrames = startTimeBtwFrames;
    }

    public boolean update()
    {
        if(animate)
        {
            if(timeBtwFrames <= 0)
            {
                actor.setImage(images[frameCount++]);
                timeBtwFrames = startTimeBtwFrames;

                if(frameCount >= images.length)
                {
                    frameCount = 0;

                    if(animatingOnce)
                    {
                        animatingOnce = false;
                        animate = false;
                        return true;
                    }
                }
            }
            else
            {
                timeBtwFrames -= 0.017;
            }
        }   
        return false;
    }

    public boolean update(int[] returnValues)
    {
        if(animate)
        {
            if(timeBtwFrames <= 0)
            {
                actor.setImage(images[frameCount++]);
                timeBtwFrames = startTimeBtwFrames;

                if(frameCount >= images.length)
                {
                    frameCount = 0;

                    if(animatingOnce)
                    {
                        animatingOnce = false;
                        animate = false;
                    }
                }                
                else if(Game.indexOf(returnValues, frameCount) != -1)
                {
                    return true;
                }                
            }
            else
            {
                timeBtwFrames -= 0.017;
            }
        }   
        return false;
    }
    
    public boolean update(int returnValue)
    {
        if(animate)
        {
            if(timeBtwFrames <= 0)
            {
                actor.setImage(images[frameCount++]);
                timeBtwFrames = startTimeBtwFrames;

                if(frameCount >= images.length)
                {
                    frameCount = 0;

                    if(animatingOnce)
                    {
                        animatingOnce = false;
                        animate = false;
                        return true;
                    }
                }                
                else if(frameCount == returnValue)
                {
                    return true;
                }                
            }
            else
            {
                timeBtwFrames -= 0.017;
            }
        }   
        return false;
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
