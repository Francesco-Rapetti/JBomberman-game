package tile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Floor extends Tile {

    public Floor(boolean shadow)
    {
        this.shadow = shadow;
        setDefaultImage();
    }

    public void setDefaultImage()
    {
        if (shadow) {
            try {
                image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "FloorShadow.png").toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "Floor.png").toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
