import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoblinWarrior here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GoblinWarrior extends Enemy
{
    private String[] images = {"GoblinWarrior/Run/GoblinWarrior_F1.png", "GoblinWarrior/Run/GoblinWarrior_F2.png", "GoblinWarrior/Run/GoblinWarrior_F3.png", "GoblinWarrior/Run/GoblinWarrior_F4.png", "GoblinWarrior/Run/GoblinWarrior_F5.png", "GoblinWarrior/Run/GoblinWarrior_F6.png", "GoblinWarrior/Run/GoblinWarrior_F7.png", "GoblinWarrior/Run/GoblinWarrior_F8.png"};
    private Animation_Controller animation = new Animation_Controller(0.2, images, this);    

    private String[] jumpImages = {"GoblinWarrior/Jump/GoblinWarrior_Jump_F1.png", "GoblinWarrior/Jump/GoblinWarrior_Jump_F2.png", "GoblinWarrior/Jump/GoblinWarrior_Jump_F3.png", "GoblinWarrior/Jump/GoblinWarrior_Jump_F4.png", "GoblinWarrior/Jump/GoblinWarrior_Jump_F5.png", "GoblinWarrior/Jump/GoblinWarrior_Jump_F6.png", "GoblinWarrior/Jump/GoblinWarrior_Jump_F7.png", "GoblinWarrior/Jump/GoblinWarrior_Jump_F8.png", "GoblinWarrior/Jump/GoblinWarrior_Jump_F9.png", "GoblinWarrior/Jump/GoblinWarrior_Jump_F10.png"};
    private Animation_Controller jumpAnimation = new Animation_Controller(0.1, jumpImages, this);    

    private String[] attackImages = {"GoblinWarrior/Attack/GoblinWarrior_Attack_F1.png", "GoblinWarrior/Attack/GoblinWarrior_Attack_F2.png", "GoblinWarrior/Attack/GoblinWarrior_Attack_F3.png", "GoblinWarrior/Attack/GoblinWarrior_Attack_F4.png", "GoblinWarrior/Attack/GoblinWarrior_Attack_F5.png", "GoblinWarrior/Attack/GoblinWarrior_Attack_F6.png", "GoblinWarrior/Attack/GoblinWarrior_Attack_F7.png", "GoblinWarrior/Attack/GoblinWarrior_Attack_F8.png"};
    private Animation_Controller attackAnimation = new Animation_Controller(0.1, attackImages, this);    

    private String[] throwImages = {"GoblinWarrior/Throw/GoblinWarrior_TrowAxe_F1.png", "GoblinWarrior/Throw/GoblinWarrior_TrowAxe_F2.png", "GoblinWarrior/Throw/GoblinWarrior_TrowAxe_F3.png", "GoblinWarrior/Throw/GoblinWarrior_TrowAxe_F4.png", "GoblinWarrior/Throw/GoblinWarrior_TrowAxe_F5.png", "GoblinWarrior/Throw/GoblinWarrior_TrowAxe_F6.png", "GoblinWarrior/Throw/GoblinWarrior_TrowAxe_F7.png", "GoblinWarrior/Throw/GoblinWarrior_TrowAxe_F8.png"};
    private Animation_Controller throwAnimation = new Animation_Controller(0.1, throwImages, this);    

    private boolean firstAttacking = true;
    private boolean attacking = false;

    private int timer = 0;
    private int throwTimer = 0;
    private boolean isThrowing = false;
    private boolean axeThrown = false;
    private int[] animationPoints = {5, 7};
    
    public GoblinWarrior()
    {
        setHealth(4);
        setScore(1000);
        setGoldAmount(5);
    }
    
    public void act() 
    {
        if(update())
        {
            return;
        }
        
        if(getX() < 870)
        {
            if(throwTimer == 120)
            {
                isThrowing = true;
                throwAnimation.animateOnce();
            }
            if(isThrowing && throwAnimation.update(animationPoints))
            {     
                if(axeThrown)
                {
                    isThrowing = false;
                    throwTimer = 0;
                    axeThrown = false;
                }
                else
                {
                    getWorld().addObject(new GoblinWarrior_Axe(), getX() + 50, getY());
                    axeThrown = true;   
                }
            }

            if(!isThrowing)
            {
                move(3);            
                animation.update();
            }

            throwTimer++;
        }
        else
        {
            if(firstAttacking)
            {
                setLocation(getX() + 40, getY() - 27);
                firstAttacking = false;
                jumpAnimation.animateOnce();
                jumpAnimation.resetImages();
            } 

            if(!attacking && timer == 30)
            {
                Game.battlefield.castle.takeDamage(1);
                timer = 0;
            }

            if(jumpAnimation.update())
            {
                attacking = true;
                setLocation(getX() + 40, getY() + 27);
            }

            if(attacking)
            {
                
                if(attackAnimation.update(5))
                {
                    Game.battlefield.castle.takeDamage(1);
                }
            }
            timer++;
        }
    }    
}
