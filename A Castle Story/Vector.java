public class Vector  
{
    // instance variables - replace the example below with your own
    public double x;
    public double y;

    /**
     * Constructor for objects of class Vector
     */
    public Vector(double x_, double y_)
    {
        x = x_;
        y = y_;
    }

    public Vector()
    {
        x = 0;
        y = 0;
    }

    public void add(Vector vector)
    {
        x += vector.x;
        y += vector.y;
    }

    public void sub(Vector vector)
    {
        x -= vector.x;
        y -= vector.y;
    }

    public void mult(double value)
    {
        x *= value;
        y *= value;
    }

    public void div(double value)
    {
        x /= value;
        y /= value;
    }

    public double mag()
    {
        return Math.sqrt(x * x + y * y);
    }

    public void setMag(double value)
    {
        if(mag() != 0)
        {
            double multiplicator = value/mag();
            x *= multiplicator;
            y *= multiplicator;
        }
    }

    public void clear()
    {
        x = 0;
        y = 0;
    }

    public void set(double x_, double y_)
    {
        x = x_;
        y = y_;
    }

    public double getAngle()
    {
        double angle;
        if(y * x < 0)
        {
            angle = 90 - Math.toDegrees(Math.atan(Math.abs(y)/Math.abs(x)));
        }
        else
        {
            angle = Math.toDegrees(Math.atan(Math.abs(y)/Math.abs(x)));
        }

        if(x < 0)
        {
            angle += 180;
            if(y < 0)
            {
                angle += 90;
            }
        }
        else if(y > 0)
        {
            angle += 90;
        }

        if(y == 0)
        {
            angle += 90;
        }
        if(x == 0 && y < 0)
        {
            angle -= 90;
        }

        return angle;
    }

    public double dist(Vector other)
    {
        Vector other_ = other.copy();
        other_.sub(this);
        return other_.mag();
    }

    public Vector copy()
    {
        return new Vector(x, y);
    }
    
    public void normalize()
    {
        x /= mag();
        y /= mag();
    }
}
