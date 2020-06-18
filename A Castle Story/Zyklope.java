
import greenfoot.*;

public class Zyklope extends Actor
{
    private String[] throwImages = {"Ogre/ThrowAnimation/Ogre_Throw_F1.png", "Ogre/ThrowAnimation/Ogre_Throw_F2.png", "Ogre/ThrowAnimation/Ogre_Throw_F3.png", "Ogre/ThrowAnimation/Ogre_Throw_F4.png", "Ogre/ThrowAnimation/Ogre_Throw_F5.png", "Ogre/ThrowAnimation/Ogre_Throw_F6.png"};
    private Animation_Controller throwAnim = new Animation_Controller(0.1, throwImages, this);

    private String[] moveImages = {"Ogre/Run/Ogre_F1.png", "Ogre/Run/Ogre_F2.png", "Ogre/Run/Ogre_F3.png", "Ogre/Run/Ogre_F4.png", "Ogre/Run/Ogre_F5.png", "Ogre/Run/Ogre_F6.png", "Ogre/Run/Ogre_F7.png", "Ogre/Run/Ogre_F8.png"};
    private Animation_Controller moveAnim = new Animation_Controller(0.1, moveImages, this);

    private String[] idleImages = {"Ogre/Idle/Ogre_Idle_F1.png", "Ogre/Idle/Ogre_Idle_F2.png", "Ogre/Idle/Ogre_Idle_F3.png", "Ogre/Idle/Ogre_Idle_F4.png"};
    private Animation_Controller idleAnim = new Animation_Controller(0.1, idleImages, this);

    private String[] shockWaveImages = {"Ogre/ShockWave/Ogre_ShockWave_F1.png", "Ogre/ShockWave/Ogre_ShockWave_F2.png", "Ogre/ShockWave/Ogre_ShockWave_F3.png", "Ogre/ShockWave/Ogre_ShockWave_F4.png", "Ogre/ShockWave/Ogre_ShockWave_F5.png", "Ogre/ShockWave/Ogre_ShockWave_F6.png", "Ogre/ShockWave/Ogre_ShockWave_F7.png", "Ogre/ShockWave/Ogre_ShockWave_F8.png", "Ogre/ShockWave/Ogre_ShockWave_F9.png", "Ogre/ShockWave/Ogre_ShockWave_F10.png"};
    private Animation_Controller shockWaveAnim = new Animation_Controller(0.1, shockWaveImages, this);

    private boolean firstFrame;

    private int health = 30;
    boolean isDamageable = false;

    private int goldToDrop = 50;
    private int scoreToDrop = 10000;

    private int chooseAttack = 100;
    private int chooseTimeBtwAttack = 0;
    private int timeBtwAttack = 120;
    private int attackTimer;

    private boolean isThrowing = false;
    private boolean throwed = false;

    private boolean isShootingWave = false;
    private boolean shot = false;

    private int stunTimer = 0;
    
    private Label UI = new Label("HP: " + health, 48);

    public Zyklope()
    {
        firstFrame = true;
    }

    public void act()
    {
        if(firstFrame)
        {
            getWorld().addObject(UI, getX(), getY() - 80);
            firstFrame = false;
        }

        if(isThrowing)
        {
            if(throwAnim.update(3))
            {
                if(!throwed)
                {
                    getWorld().addObject(new Club(), getX(), getY());
                    throwed = true;
                }
                else
                {
                    attackTimer = 0;
                    isThrowing = false;
                    chooseAttack = Greenfoot.getRandomNumber(100);
                    chooseTimeBtwAttack = Greenfoot.getRandomNumber(1) + 1;
                    timeBtwAttack = chooseTimeBtwAttack * 60 + 120;
                    throwed = false;
                    setImage("Giant.png");
                    setLocation(getX() - 20, getY());
                }
            }
        }
        else if(isShootingWave)
        {
            if(shockWaveAnim.update(7))
            {
                if(!shot)
                {
                    getWorld().addObject(new ShockWave(), 200, 400);
                    shot = true;
                }
                else
                {
                    attackTimer = 0;
                    isShootingWave = false;
                    chooseAttack = Greenfoot.getRandomNumber(100);
                    chooseTimeBtwAttack = Greenfoot.getRandomNumber(1) + 1;
                    timeBtwAttack = chooseTimeBtwAttack * 60 + 120;
                    shot = false;
                    setImage("Giant.png");
                    setLocation(getX(), getY() + 25);
                }
            }
        }
        else
        {
            idleAnim.update();
        }

        if(stunTimer <= 0)
        {
            isDamageable = false;
            getWorld().removeObject(stunnedEffect);
        }
        else
        {
            stunTimer--;
        }

        if(!isDamageable)
        {
            if(getX() < 150)
            {
                move(1);
                moveAnim.update();
                UI.setLocation(getX(), getY()-80);
            }
            else
            {                
                if(getOneIntersectingObject(Player.class) != null)
                {
                    swingClub((Player)getOneIntersectingObject(Player.class));
                }
                else
                {
                    attackRandomly();
                }
            }
        }
    }
    
    public void takeDamage(int amount)
    {
        if(isDamageable)
        {
            health -= amount;

            UI.update("HP: " + health, 48);

            if(health <= 0)
            {
                defeated();
            }
        }
    }

    private void defeated()
    {
        getWorld().addObject(new Effect(Effects.Colour.PURPLE, new Vector(50, 25), 60, 15), getX(), getY());
        getWorld().removeObject(UI);
        getWorld().removeObject(stunnedEffect);
        GoldCounter.gold += goldToDrop;
        ScoreCounter.score += scoreToDrop;
        ((Door)getWorld().getObjects(Door.class).get(0)).openDoor();
        Game.levelCount++;
        getWorld().removeObject(this);
    }

    public void shockwave()
    {
        if(!isShootingWave)
        {
            setLocation(getX(), getY() - 25);
            shockWaveAnim.resetImages();
            isShootingWave = true;
            shockWaveAnim.animateOnce();
        }
    }

    public void throwClub()
    {
        if(!isThrowing)
        {
            setLocation(getX() + 20, getY());
            throwAnim.resetImages();
            isThrowing = true;
            throwAnim.animateOnce();
        }
    }

    public void swingClub(Player player)
    {
        Vector knockBackDir = new Vector(player.getX(), player.getY());
        knockBackDir.sub(new Vector(getX(), getY()));
        Game.player.knockBack(knockBackDir, 15.0, 12);
    }

    StunnedEffect stunnedEffect = new StunnedEffect();
    public void stun(boolean damageable)
    {
        stunTimer = 240;
        attackTimer = 0;
        if(damageable)
        {
            isDamageable = true;
            getWorld().addObject(stunnedEffect, getX(), getY() - 50);
        }
    }

    private void attackRandomly()
    {        
        if(chooseAttack <= 25 && attackTimer >= timeBtwAttack)
        {
            throwClub();
        }
        else if(chooseAttack > 25 && attackTimer >= timeBtwAttack)
        {
            shockwave();

        }
        else
        {
            attackTimer++;
        }
    }
}
