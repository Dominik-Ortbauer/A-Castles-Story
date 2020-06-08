import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor
{
    private int speed = 5;
    public int damage = 1;

    private boolean mouseDown;

    public boolean isDashing;
    private int dashTimer;
    private int timeBtwDash;

    public int hammerChargeTime = 120;
    public int startHammerChargeTime = 120;
    public int bowChargeTime = 120;
    public int startBowChargeTime = 120;

    Vector movement = new Vector();
    public Vector pos = new Vector();

    MouseInfo mouse = Greenfoot.getMouseInfo();

    private double timeBtwAttack = 0;

    private boolean firstFrame = true;

    public Player_Body playerBody = new Player_Body();
    public Player_Weapons currentWeapon = new Player_Sword();

    private boolean isWalking = true;

    public void act()
    {
        if (mouseDown && (Greenfoot.mouseDragEnded(null) || Greenfoot.mouseClicked(null))) mouseDown = false;
        if (!mouseDown && Greenfoot.mousePressed(null)) mouseDown = true;

        if(firstFrame)
        {
            getWorld().addObject(playerBody, getX(), getY());
            getWorld().addObject(currentWeapon, getX(), getY());
        }

        mouse = Greenfoot.getMouseInfo();
        movement();

        if(movement.mag() == 0)
        {
            stopWalking();            
            isWalking = false;
        }
        else
        {
            startWalking();
            isWalking = true;
        }
        
        if(Greenfoot.isKeyDown("space") && timeBtwDash > 30)
        {
            isDashing = true;
            timeBtwDash = 0;
        }
        if(isDashing && dashTimer <= 6)
        {
            dashTimer++;
            
            movement.setMag(15);
            setLocation(getX() + (int)movement.x, getY() + (int)movement.y);
        }
        else
        {
            dashTimer = 0;
            isDashing = false;
        }
        timeBtwDash++;
        
        if(!(getWorld() instanceof Shop))
        {
            if(mouse != null && mouseDown)
            {
                attack();            
            }
            else if(mouse != null && currentWeapon instanceof Player_Hammer)
            {
                Vector target = new Vector(mouse.getX(), mouse.getY());
                target.sub(pos);

                if(hammerChargeTime <= startHammerChargeTime * -1)
                {
                    getWorld().addObject(new Attack_Hammer(target, 5), (int)pos.x, (int)pos.y);  
                    timeBtwAttack = 0; 
                }
                else if(hammerChargeTime <= 0)
                {
                    getWorld().addObject(new Attack_Hammer(target, 3), (int)pos.x, (int)pos.y);  
                    timeBtwAttack = 0; 
                }
                else if(hammerChargeTime <= startHammerChargeTime / 2)
                {
                    getWorld().addObject(new Attack_Hammer(target, 2), (int)pos.x, (int)pos.y);   
                    timeBtwAttack = 0;
                }
                else if(hammerChargeTime <= startHammerChargeTime - 1)
                {
                    getWorld().addObject(new Attack_Hammer(target, 1), (int)pos.x, (int)pos.y);   
                    timeBtwAttack = 0;
                }
                hammerChargeTime = startHammerChargeTime;
            }
            else if(mouse != null && currentWeapon instanceof Player_Bow)
            {
                Vector target = new Vector(mouse.getX(), mouse.getY());
                target.sub(pos);

                if(bowChargeTime <= 0)
                {
                    getWorld().addObject(new Attack_Bow(target, 3), (int)pos.x, (int)pos.y);  
                    timeBtwAttack = 0; 
                }
                else if(bowChargeTime <= startBowChargeTime / 2)
                {
                    getWorld().addObject(new Attack_Bow(target, 2), (int)pos.x, (int)pos.y);   
                    timeBtwAttack = 0;
                }
                else if(bowChargeTime <= startBowChargeTime - 1)
                {
                    getWorld().addObject(new Attack_Bow(target, 1), (int)pos.x, (int)pos.y);   
                    timeBtwAttack = 0;
                }
                bowChargeTime = startBowChargeTime;
            }
        }
        else
        {
            bowChargeTime = startBowChargeTime;
            hammerChargeTime = startHammerChargeTime;
            timeBtwAttack = 0; 
        }

        pos.x = getX();
        pos.y = getY();
        timeBtwAttack++;
    } 

    private void stopWalking()
    {
        playerBody.images.stop();
        currentWeapon.images.stop();
        playerBody.images.resetImages();
        currentWeapon.images.resetImages();
    }

    private void startWalking()
    {
        playerBody.images.start();
        currentWeapon.images.start();        
    }

    private void attack()
    {
        if(currentWeapon instanceof Player_Sword && timeBtwAttack >= currentWeapon.timeBtwAttacks)
        {
            Vector target = new Vector(mouse.getX(), mouse.getY());
            target.sub(pos);
            timeBtwAttack = 0;
            getWorld().addObject(new MeeleAttack(target, 1), (int)pos.x, (int)pos.y);
        }
        if(currentWeapon instanceof Player_Hammer && timeBtwAttack >= currentWeapon.timeBtwAttacks)
        {      
            if(hammerChargeTime <= startHammerChargeTime * -1)
            {
                getWorld().addObject(new HammerCharge_Particle(this, 100, Effects.Colour.PURPLE, new Vector(20, 7)), getX(), getY());
            }
            else if(hammerChargeTime <= 0)
            {
                getWorld().addObject(new HammerCharge_Particle(this, 100, Effects.Colour.YELLOW, new Vector(20, 7)), getX(), getY());
            }
            else if(hammerChargeTime <= startHammerChargeTime/2)
            {
                getWorld().addObject(new HammerCharge_Particle(this, 100, Effects.Colour.GREEN, new Vector(15, 5)), getX(), getY());
            }
            else
            {
                getWorld().addObject(new HammerCharge_Particle(this, 100, Effects.Colour.BLUE, new Vector(15, 5)), getX(), getY());
            }

            if(hammerChargeTime == startHammerChargeTime / 2)
            {
                getWorld().addObject(new Effect(Effects.Colour.GREEN, new Vector(15, 5), 20, 5.0),getX(), getY());
            }
            else if(hammerChargeTime == 0)
            {
                getWorld().addObject(new Effect(Effects.Colour.YELLOW, new Vector(30, 10), 20, 7.0), getX(), getY());
            }
            else if(hammerChargeTime == startHammerChargeTime * -1)
            {
                getWorld().addObject(new Effect(Effects.Colour.PURPLE, new Vector(30, 10), 40, 10.0), getX(), getY());
            }
            hammerChargeTime--;
        }
        if(currentWeapon instanceof Player_Bow && timeBtwAttack >= currentWeapon.timeBtwAttacks)
        {            
            if(bowChargeTime <= 0)
            {
                getWorld().addObject(new HammerCharge_Particle(this, 100, Effects.Colour.YELLOW, new Vector(20, 7)), getX(), getY());
            }
            else if(bowChargeTime <= startBowChargeTime / 2)
            {
                getWorld().addObject(new HammerCharge_Particle(this, 100, Effects.Colour.GREEN, new Vector(15, 5)), getX(), getY());
            }
            else
            {
                getWorld().addObject(new HammerCharge_Particle(this, 100, Effects.Colour.BLUE, new Vector(15, 5)), getX(), getY());
            }

            if(bowChargeTime == startBowChargeTime / 2)
            {
                getWorld().addObject(new Effect(Effects.Colour.GREEN, new Vector(15, 5), 20, 5.0),getX(), getY());
            }
            else if(bowChargeTime == 0)
            {
                getWorld().addObject(new Effect(Effects.Colour.YELLOW, new Vector(30, 10), 20, 7.0), getX(), getY());
            }

            bowChargeTime--;                        
        }
    }

    private void getInput()
    {
        movement.clear();

        if(Greenfoot.isKeyDown("a"))
        {
            movement.x -= speed;            
        }
        if(Greenfoot.isKeyDown("d"))
        {
            movement.x += speed;            
        }
        if(Greenfoot.isKeyDown("w"))
        {
            movement.y -= speed;
        }
        if(Greenfoot.isKeyDown("s"))
        {
            movement.y += speed;
        }
        movement.setMag(speed);
    }

    public void increaseDamage(int value)
    {
        damage += value;
    }

    private void updatePosition(int x, int y)
    {
        setLocation(x, y);
        playerBody.setLocation(x, y);
        currentWeapon.setLocation(x, y);
    }

    private boolean facingRight = true;
    public void updateImages()
    {
        if(mouse != null)
        {
            if(mouse.getX() < getX() && facingRight)
            {
                changeImagesToLeft();
                facingRight = false;
            }
            else if(mouse.getX() > getX() && !facingRight)
            {
                changeImagesToRight();
                facingRight = true;
            }
        }
    }

    public void forceUpdateImages()
    {
        if(mouse != null)
        {
            if(mouse.getX() < getX())
            {
                changeImagesToLeft();
                facingRight = false;
            }
            else if(mouse.getX() > getX())
            {
                changeImagesToRight();
                facingRight = true;
            }
        }
    }

    private void movement()
    {
        getInput();        
        updatePosition(getX() + (int)movement.x, getY() + (int)movement.y);
        updateImages();

        if(getOneIntersectingObject(Environment.class) != null)
        {
            updatePosition((int)pos.x + (int)movement.x, (int)pos.y);

            if(getOneIntersectingObject(Environment.class) != null)
            {
                updatePosition((int)pos.x, (int)pos.y + (int)movement.y);

                if(getOneIntersectingObject(Environment.class) != null)
                {
                    updatePosition((int)pos.x, (int)pos.y);
                }
            }
        }                
    }

    private void changeImagesToLeft()
    {
        playerBody.changeToLeftImage();
        currentWeapon.changeToLeftImage();
    }

    private void changeImagesToRight()
    {
        playerBody.changeToRightImage();
        currentWeapon.changeToRightImage();
    }
}
