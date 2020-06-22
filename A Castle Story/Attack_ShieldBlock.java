import greenfoot.*;

public class Attack_ShieldBlock extends Attacks
{
    private MouseInfo mouse = Greenfoot.getMouseInfo();
    private boolean mouseDown;
    
    private Enemy enemy = Game.player.getIntersectingEnemy();
    
    public void act() 
    {
        if (mouseDown && (Greenfoot.mouseDragEnded(null) || Greenfoot.mouseClicked(null)))
        {
            mouseDown = false;
        }
        if (!mouseDown && Greenfoot.mousePressed(null))
        {
            mouseDown = true;
        }
        
        if(enemy != null)
        {
            enemy.stun(1);
        }
        
        if(enemy instanceof StoneProjectile)
        {
            getWorld().removeObject(enemy);
        }
        
        if(!mouseDown)
        {
            getWorld().removeObject(this);
            return;
        }
    }    
}
