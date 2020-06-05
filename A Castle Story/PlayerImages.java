import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerImages here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerImages extends Actor
{
    public Animation_Controller images = new Animation_Controller(0.1, new String[0], this);
    private GreenfootImage[] leftImages;
    private GreenfootImage[] rightImages;
       
    public void act()
    {
        images.update();
    }
    
    public void setImages(String[] images_)
    {
        leftImages = new GreenfootImage[images_.length];
        rightImages = new GreenfootImage[images_.length];
        
        for(int i = 0; i < leftImages.length; i++)
        {
            rightImages[i] = new GreenfootImage(images_[i] + ".png");
            leftImages[i] = new GreenfootImage(images_[i] + "_Left.png");
        }
             
        images.images = rightImages;
    }
        
    public void changeToLeftImage()
    {
        images.images = leftImages;
        images.resetImages();
    }
    
    public void changeToRightImage()
    {
        images.images = rightImages;
        images.resetImages();
    }
    
    public void setSizes(int width, int heigth)
    {
        for(int i = 0; i < images.images.length; i++)
        {
            images.images[i].scale(width, heigth);
            leftImages[i].scale(width, heigth);
            rightImages[i].scale(width, heigth);
        }
        
        images.resetImages();
    }
}
