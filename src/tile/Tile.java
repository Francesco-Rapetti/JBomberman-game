package tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision, breakable, animatedTile, activated, destroyed, destroying, shadow, damageDealer;
    public Tile previousTile;



    public void destroy()
    {
        destroyed = true;
        System.out.println(this.getClass().getName()+" is destroyed");
    }

    @Override
    public String toString()
    {
        return getClass().getName()+"\t";
    }

    public void update()
    {

    }

    public void reset()
    {
        
    }
}
