import greenfoot.*;

public class Zyklope extends Enemy
{
    public void act() 
    {
        if(getOneIntersectingObject(Player.class) != null)
        {
            swingClub((Player)getOneIntersectingObject(Player.class));
        }
    }
    
    private void stompAttack()
    {
        getWorld().addObject(new ShockWave(), getX(), getY());
    }
    
    private void throwClub()
    {
        getWorld().addObject(new Club(), getX(), getY());
    }
    
    private void swingClub(Player player)
    {
        Vector knockBackDir = new Vector(player.getX(), player.getY());
        knockBackDir.sub(new Vector(getX(), getY()));
        Game.player.knockBack(knockBackDir, 15, 120);
    }
}
