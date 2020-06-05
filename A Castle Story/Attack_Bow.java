import greenfoot.*;

public class Attack_Bow extends Attacks
{
    Vector dir;
    private int damage = 2;
    int amountOfArrows;
    int offset_ = 20;
    
    public Attack_Bow(Vector dir_, int amountOfArrows_)
    {
        dir = dir_;
        amountOfArrows = amountOfArrows_;
    }
    
    public void act() 
    {        
        Vector offset = new Vector();
        offset.x = -dir.y;
        offset.y = dir.x;
        offset.setMag(offset_);
        
        if(amountOfArrows == 1)
        {
            getWorld().addObject(new Attack_Bow_Piece(dir), getX(), getY());
        }
        else if(amountOfArrows == 2)
        {
            offset.setMag(offset_/2);
            getWorld().addObject(new Attack_Bow_Piece(dir), getX() + (int)offset.x, getY() + (int)offset.y);
            getWorld().addObject(new Attack_Bow_Piece(dir), getX() - (int)offset.x, getY() - (int)offset.y);
        }
        else if(amountOfArrows == 3)
        {
            getWorld().addObject(new Attack_Bow_Piece(dir), getX() + (int)offset.x, getY() + (int)offset.y);
            getWorld().addObject(new Attack_Bow_Piece(dir), getX(), getY());
            getWorld().addObject(new Attack_Bow_Piece(dir), getX() - (int)offset.x, getY() - (int)offset.y);
        }
        
        getWorld().removeObject(this);
    }    
}
