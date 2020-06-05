import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Attack_Hammer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Attack_Hammer extends Attacks
{
    Vector dir;
    int distance = 60;
    int timeBtwAttacks = 20;
    int timer = 0;
    int fountains;

    public Attack_Hammer(Vector dir_, int fountains_)
    {
        dir = dir_;
        fountains = fountains_;
    }

    Attack_Hammer_Piece[] pieces = new Attack_Hammer_Piece[10];

    public void act()
    {
        turnTowards((int)dir.x + getX(), (int)dir.y + getY());

        for(int i = 0; i < fountains + 2; i++)
        {
            if(timer == (timeBtwAttacks * i))
            {
                move(distance);
                if(i > 0 && i < fountains + 1 && i < 11)
                {
                    pieces[i-1].doDamage(dir, distance);
                }
                if(i < fountains && i < 10)
                {
                    pieces[i] = new Attack_Hammer_Piece();
                    getWorld().addObject(pieces[i], getX(), getY());
                    pieces[i].stunEnemies();
                }                
                if(i > 1 && i < 12 && i < fountains + 2)
                {
                    getWorld().removeObject(pieces[i-2]);
                }
            }
        }

        timer++;
    }
}
