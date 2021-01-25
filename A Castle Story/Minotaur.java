import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Minotaur here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Minotaur extends Bosses
{
    private String[] spinImages = {"Minotaur/SpinLoop/Minotaur_SpinLoop_F1.png", "Minotaur/SpinLoop/Minotaur_SpinLoop_F2.png", "Minotaur/SpinLoop/Minotaur_SpinLoop_F3.png"};
    private Animation_Controller spinAnim = new Animation_Controller(0.1, spinImages, this);

    private String[] meeleImages = {"Minotaur/Meele/Minotaur_Meele_F1.png", "Minotaur/Meele/Minotaur_Meele_F2.png", "Minotaur/Meele/Minotaur_Meele_F3.png", "Minotaur/Meele/Minotaur_Meele_F4.png", "Minotaur/Meele/Minotaur_Meele_F5.png", "Minotaur/Meele/Minotaur_Meele_F6.png", "Minotaur/Meele/Minotaur_Meele_F7.png", "Minotaur/Meele/Minotaur_Meele_F8.png"};
    private Animation_Controller meeleAnim = new Animation_Controller(0.1, meeleImages, this);

    private String[] idleImages = {"Minotaur/Idle/Minotaur_Idle_F1.png", "Minotaur/Idle/Minotaur_Idle_F2.png", "Minotaur/Idle/Minotaur_Idle_F3.png", "Minotaur/Idle/Minotaur_Idle_F4.png"};
    private Animation_Controller idleAnim = new Animation_Controller(0.1, idleImages, this);

    private String[] runImages = {"Minotaur/Run/Minotaur_Run_F1.png", "Minotaur/Run/Minotaur_Run_F2.png", "Minotaur/Run/Minotaur_Run_F3.png", "Minotaur/Run/Minotaur_Run_F4.png", "Minotaur/Run/Minotaur_Run_F5.png", "Minotaur/Run/Minotaur_Run_F6.png", "Minotaur/Run/Minotaur_Run_F7.png", "Minotaur/Run/Minotaur_Run_F8.png"};
    private Animation_Controller runAnim = new Animation_Controller(0.1, runImages, this);

    private boolean isSpinning = false;
    private boolean isMeeleing = false;

    private int goldToDrop = 50;
    private int scoreToDrop = 10000;

    private int health = 20;

    private Label UI = new Label("HP: " + health, 48);

    private static enum Phases
    {
        INTRO, CHOOSE, SPIN, LEAP, ATTACK_CASTLE, DAZED;
    }

    private Phases phases = Phases.INTRO;

    private int halfImageHeight = getImage().getHeight()/2;
    private int halfImageWidth = getImage().getWidth()/2;

    public void addedToWorld(World world)
    {
        world.addObject(UI, getX(), getY() - 100);
        getWorld().addObject(canvas, 600, 400);
    }

    public void act() 
    {
        UI.setLocation(getX(), getY() - 100);

        switch(phases)
        {
            case INTRO:
            intro();
            break;
            case CHOOSE:
            choose();
            break;
            case SPIN:
            spin();
            break;
            case LEAP:
            leap();
            break;
            case ATTACK_CASTLE:
            attackCastle();
            break; 
            case DAZED:
            dazed();
            break;            
        }
    } 

    public void intro()
    {
        move(2);
        runAnim.update();
        if(getX() > 200)
        {
            phases = Phases.CHOOSE;
        }
    }

    int chooseTime = 120;
    public void choose()
    {
        idleAnim.update();
        if(chooseTime <= 0)
        {
            int dice = Greenfoot.getRandomNumber(2);
            
            if(dice == 0)
            {
                phases = Phases.SPIN;
            }
            else if(dice == 1)
            {
                phases = Phases.LEAP;
                setImage("Minotaur_Meele_F1.png");
            }
            chooseTime = 120;
        }
        else
        {
            chooseTime--;
        }
    }

    private Vector dir = new Vector();
    private boolean startedSpinning = false;
    private double spinSpeed = 15;

    private int spinDuration = 300;
    public void spin()
    {
        if(!startedSpinning)
        {            
            dir.set((Greenfoot.getRandomNumber(2) * 2)-1, (Greenfoot.getRandomNumber(2) * 2)-1);
            dir.setMag(spinSpeed);
            startedSpinning = true;
        }

        spinAnim.update();

        if(isAtEdge() || getX() + halfImageWidth >= 1000)
        {
            dir.set(Game.player.getX(), Game.player.getY());
            dir.sub(new Vector(getX(), getY()));
            dir.setMag(spinSpeed);
        }

        Player player = (Player)getOneIntersectingObject(Player.class);
        if(player != null)
        {
            player.stun(120);
            spinDuration = 300;
            startedSpinning = false;
            phases = Phases.ATTACK_CASTLE;
            return;
        }

        if(spinDuration <= 0)
        {
            phases = Phases.DAZED;
            spinDuration = 300;
            startedSpinning = false;
            return;
        }
        else
        {
            spinDuration--;
        }

        setLocation(getX() + (int)dir.x, getY() + (int)dir.y);
    }

    int leapCount = 0;
    int leapSpeed = 25;
    boolean isLeaping = false;
    boolean firstLeapFrame = true;
    boolean isChargingLeap = true;
    Vector target;
    Vector targetPos;

    int chargeTime = 30;
    
    GreenfootImage image;
    
    Canvas canvas = new Canvas();
    public void leap()
    {
        if(!isLeaping && !isChargingLeap)
        {
            if(meeleAnim.update(7))
            {                
                isChargingLeap = true;
                setImage("Minotaur_Meele_F1.png");
                if(leapCount >= 3)
                {
                    phases = Phases.CHOOSE;
                    firstLeapFrame = true;
                    leapCount = 0;
                    isChargingLeap = true;
                    return;
                }
            }            
        }

        if(isChargingLeap)
        {     
            if(chargeTime <= 0)
            {
                isLeaping = true;
                isChargingLeap = false;
                image.clear();
                chargeTime = 30;
            }
            else
            {                                
                image = canvas.getImage();
                canvas.getImage().clear();
                image.setColor(Color.BLACK);
                image.drawLine(getX(), getY(), Game.player.getX(), Game.player.getY());
                chargeTime--;
            }
        }

        if(isLeaping)
        {       
            if(firstLeapFrame)
            {
                firstLeapFrame = false;
                target = new Vector(Game.player.getX(), Game.player.getY());
                target.sub(new Vector(getX(), getY()));
                targetPos = target.copy();
                target.setMag(leapSpeed);
            }

            setLocation(getX() + (int)target.x, getY() + (int)target.y);
            if(getX() >= 1000 || isAtEdge())
            {
                isLeaping = false;
                firstLeapFrame = true;
                setLocation(getX() - (int)target.x, getY() - (int)target.y);
                leapCount++;
            }
        }

        Player player = (Player)getOneIntersectingObject(Player.class);
        if(player != null)
        {
            player.stun(120);
            leapCount = 0;
            isLeaping = false;
            firstLeapFrame = true;
            phases = Phases.ATTACK_CASTLE;
            isChargingLeap = true;
            return;
        }

    }
    boolean isJumpingBack = false;
    public void attackCastle()
    {
        Castle castle = (Castle)getOneIntersectingObject(Castle.class);
        if(isJumpingBack)
        {
            if(getX() >= 200)
            {
                move(-10);
            }
            else
            {
                phases = Phases.CHOOSE;
                isJumpingBack = false;
            }
        }
        else
        {
            if(castle != null)
            {
                if(meeleAnim.update(7))
                {
                    castle.takeDamage(2);
                    isJumpingBack = true;
                    setImage("Minotaur.png");
                }
            }
            else
            {
                move(15);
            }
        }
    }

    int dazedTime = 240;
    boolean dazed = false;
    StunnedEffect stunEffect = new StunnedEffect();
    public void dazed()
    {
        if(!dazed)
        {
            setImage("Minotaur.png");
            getWorld().addObject(stunEffect, getX(), getY() - 50);
            dazed = true;
        }

        if(dazedTime <= 0)
        {
            phases = Phases.CHOOSE;
            getWorld().removeObject(stunEffect);
            dazedTime = 240;
            dazed = false;
        }
        else
        {
            dazedTime--;
        }   
    }

    public void takeDamage(int value)
    {
        if(phases == Phases.DAZED)
        {
            health -= value;
            UI.update("HP: " + health, 48);
        }

        if(health <= 0)
        {
            defeat();
        }
    }

    public void defeat()
    {
        GoldCounter.gold += goldToDrop;
        ScoreCounter.score += scoreToDrop;
        
        ((Door)getWorld().getObjects(Door.class).get(0)).openDoor();
        getWorld().removeObject(UI);
        getWorld().removeObject(stunEffect);
        getWorld().removeObject(this);
    }
}
