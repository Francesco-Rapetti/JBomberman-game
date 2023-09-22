package tile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class NewLevel extends Tile {

    public NewLevel()
    {
        setDefaultImage();
    }

    public void setDefaultImage()
    {
        try {
            image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "NewLevel.png").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
