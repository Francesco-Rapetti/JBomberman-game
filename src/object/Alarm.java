package object;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import main.GamePanel;
import tile.Tile;

public class Alarm extends Tile {
    int spriteCounter;
    int animationIndex;
    int animationBreakIndex;
    int[] animation = {0, 1, 2, 3};
    int[] animationShadow = {4, 5, 6, 7};
    int[] animationBreak = {8, 9, 10, 11, 12, 13};
    BufferedImage[] images;
    int spriteNum;
    GamePanel gp;

    public Alarm(GamePanel gp, boolean shadow, Tile previousTile)
    {
        this.gp = gp;
        this.previousTile = previousTile;
        this.shadow = shadow;
        breakable = true;
        images = new BufferedImage[14];
        collision = true;
        animatedTile = true;
        
        loadAlarmImage();
        // setPreviousTile();
    }

    public void loadAlarmImage()
    {
        try {
            images[0] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "Obstacle1.png").toString()));
            images[1] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "Obstacle2.png").toString()));
            images[2] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "Obstacle3.png").toString()));
            images[3] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "Obstacle4.png").toString()));
            images[4] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "ObstacleShadow1.png").toString()));
            images[5] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "ObstacleShadow2.png").toString()));
            images[6] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "ObstacleShadow3.png").toString()));
            images[7] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "ObstacleShadow4.png").toString()));
            images[8] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "ObstacleBroken1.png").toString()));
            images[9] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "ObstacleBroken2.png").toString()));
            images[10] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "ObstacleBroken3.png").toString()));
            images[11] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "ObstacleBroken4.png").toString()));
            images[12] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "ObstacleBroken5.png").toString()));
            images[13] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "ObstacleBroken6.png").toString()));
            // images[14] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "Floor.png").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = images[0];
    }

    // public void setPreviousTile()
    // {
    //     if (newLevel) {
    //         previousTile = new NewLevel();
    //     }
    // }

    public void update() {
        if (!destroying) {
            if (!shadow) {
                spriteCounter++;
                if (spriteCounter > 6) {
                    // System.out.println(spriteNum);
                    
                    if (animationIndex >= animation.length) animationIndex = 0;
                    spriteNum = animation[animationIndex];
                    animationIndex++;
                    image = images[spriteNum];
                    spriteCounter = 0;
                }
            } else {
                spriteCounter++;
                if (spriteCounter > 6) {
                    // System.out.println(spriteNum);
                    
                    if (animationIndex >= animationShadow.length) animationIndex = 0;
                    spriteNum = animationShadow[animationIndex];
                    animationIndex++;
                    image = images[spriteNum];
                    spriteCounter = 0;
                }
            }
        } else {
            spriteCounter++;
            if (spriteCounter > 6) {
                // System.out.println(spriteNum);
                if (animationBreakIndex >= animationBreak.length)
                {
                    destroy();
                }
                else {
                    spriteNum = animationBreak[animationBreakIndex];
                    animationBreakIndex++;
                    image = images[spriteNum];
                    spriteCounter = 0;
                }
            }
        }
    }

    // @Override
    // public void destroy()
    // {
    //     destroyed = true;
    //     // reset();
    // }

    // public void reset()
    // {
    //     image = previousTile.image;
    // }
}
