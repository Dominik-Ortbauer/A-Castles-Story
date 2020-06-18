import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor
{
    private int speed = 5;
    public int damage = 1;

    private boolean mouseDown;
    private boolean rightMouseDown;

    public static boolean isDashing = false;
    private int dashTimer;
    private int timeBtwDash;

    public static boolean isStunned;
    public int stunTimer;
    public int stunTime;

    public int hammerChargeTime = 120;
    public int startHammerChargeTime = 120;
    public int bowChargeTime = 60;
    public int startBowChargeTime = 60;

    Vector movement = new Vector();
    Vector dashDir;
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
        
        rightMouseDown = mouse.getButton() == 3;
        
        pos.x = getX();
        pos.y = getY();

        if(firstFrame)
        {
            getWorld().addObject(playerBody, getX(), getY());
            getWorld().addObject(currentWeapon, getX(), getY());
        }

        mouse = Greenfoot.getMouseInfo();

        if(!isDashing && (!isStunned || isKnockedBack))
        {
            movement();
        }

        if(movement.mag() == 0 || isDashing || isStunned)
        {
            stopWalking();
            isWalking = false;
        }
        else
        {
            startWalking();
            isWalking = true;
        }

        if(Greenfoot.isKeyDown("space") && timeBtwDash > 30 && !isStunned)
        {
            isDashing = true;
            playerBody.images.stop();
            timeBtwDash = 0;

            movement.setMag(20);
            dashDir = movement.copy();
        }
        if(isDashing && dashTimer <= 6)
        {
            dashTimer++;

            // setLocation(getX() + (int)dashDir.x, getY() + (int)dashDir.y);
            updatePosition((int)dashDir.x, (int)dashDir.y);
        }
        else if(isDashing)
        {
            dashTimer = 0;
            isDashing = false;
            playerBody.images.start();
        }
        timeBtwDash++;

        if(isStunned)
        {
            stunTimer++;
            if(stunTimer >= stunTime)
            {
                isStunned = false;
                stunTimer = 0;
                getWorld().removeObject(stunnedEffect);
            }
        }

        if(!(getWorld() instanceof Shop) && !isStunned)
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
        if(currentWeapon instanceof Player_Shield)
        {
            if(mouse.getButton() == 3)
            {
                
            }
            if(timeBtwAttack >= currentWeapon.timeBtwAttacks)
            {
                
            }
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
        setLocation((int)pos.x + x, (int)pos.y + y);
        playerBody.setLocation((int)pos.x + x, (int)pos.y + y);
        currentWeapon.setLocation((int)pos.x + x, (int)pos.y + y);

        if(getOneIntersectingObject(Environment.class) != null)
        {
            setLocation((int)pos.x + x, (int)pos.y);
            playerBody.setLocation((int)pos.x + x, (int)pos.y + y);
            currentWeapon.setLocation((int)pos.x + x, (int)pos.y + y);

            if(getOneIntersectingObject(Environment.class) != null)
            {
                setLocation((int)pos.x, (int)pos.y + y);
                playerBody.setLocation((int)pos.x + x, (int)pos.y + y);
                currentWeapon.setLocation((int)pos.x + x, (int)pos.y + y);

                if(getOneIntersectingObject(Environment.class) != null)
                {
                    setLocation((int)pos.x, (int)pos.y);
                    playerBody.setLocation((int)pos.x + x, (int)pos.y + y);
                    currentWeapon.setLocation((int)pos.x + x, (int)pos.y + y);
                }
            }
        }
    }

    StunnedEffect stunnedEffect = new StunnedEffect();
    public void stun(int stunTime_)
    {
        isDashing = false;
        isStunned = true;
        stunTime = stunTime_;
        getWorld().addObject(stunnedEffect, getX(), getY() - 50);
        isDashing = false;
        stopWalking();
        isWalking = false;
        movement.set(0, 0);
    }

    private boolean isKnockedBack = false;
    public void knockBack(Vector dir, double speed, int time)
    {
        stun(time);
        dir.setMag(speed);
        movement = dir;
        isKnockedBack = true;
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
        if(!isStunned || !isKnockedBack)
        {
            getInput();
        }

        updatePosition((int)movement.x, (int)movement.y);

        updateImages();
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
