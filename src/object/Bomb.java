package object;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import main.GamePanel;
import tile.NewLevel;
import tile.Tile;

public class Bomb extends Tile {
    GamePanel gp;
    BufferedImage[] images;
    int[] bombAnimation = {2, 1, 0, 1, 2, 1, 0, 1, 2, 6, 7, 8, 9, 10};
    int[] specialBombAnimation = {5, 4, 3, 4, 5, 4, 3, 4, 5};
    int spriteCounter, animationIndex, spriteNum, animationCounter = 0, 
    upIndex = 11, downIndex = 16, leftIndex = 21, rightIndex = 26, verticalIndex = 31, horizontalIndex = 36;
    Tile up1, up2, down1, down2, left1, left2, right1, right2;
    boolean special;
    final int x, y;

    
    public Bomb(GamePanel gp, boolean special)
    {
        this.gp = gp;
        this.special = special;
        x = gp.player.bombX;
        y = gp.player.bombY;
        images = new BufferedImage[41];
        loadBombImage();
        previousTile = gp.tileM.mapTile[x][y];
        setExplosionTiles();
    }

    public void setExplosionTiles()
    {
        if (!special) {
            up2 = new Tile();
            up2.image = images[upIndex];
            up2.previousTile = gp.tileM.mapTile[x][y-1];
            up2.damageDealer = true;
            down2 = new Tile();
            down2.image = images[downIndex];
            down2.previousTile = gp.tileM.mapTile[x][y+1];
            down2.damageDealer = true;
            left2 = new Tile();
            left2.image = images[leftIndex];
            left2.previousTile = gp.tileM.mapTile[x-1][y];
            left2.damageDealer = true;
            right2 = new Tile();
            right2.image = images[rightIndex];
            right2.previousTile = gp.tileM.mapTile[x+1][y];
            right2.damageDealer = true;
        } else {
            up1 = new Tile();
            up1.image = images[verticalIndex];
            up1.previousTile = gp.tileM.mapTile[x][y-1];
            up1.damageDealer = true;
            up2 = new Tile();
            up2.image = images[upIndex];
            up2.previousTile = gp.tileM.mapTile[x][y-2];
            up2.damageDealer = true;
            down1 = new Tile();
            down1.image = images[verticalIndex];
            down1.previousTile = gp.tileM.mapTile[x][y+1];
            down1.damageDealer = true;
            down2 = new Tile();
            down2.image = images[downIndex];
            down2.previousTile = gp.tileM.mapTile[x][y+2];
            down2.damageDealer = true;
            left1 = new Tile();
            left1.image = images[horizontalIndex];
            left1.previousTile = gp.tileM.mapTile[x-1][y];
            left1.damageDealer = true;
            left2 = new Tile();
            left2.image = images[leftIndex];
            left2.previousTile = gp.tileM.mapTile[x-2][y];
            left2.damageDealer = true;
            right1 = new Tile();
            right1.image = images[horizontalIndex];
            right1.previousTile = gp.tileM.mapTile[x+1][y];
            right1.damageDealer = true;
            right2 = new Tile();
            right2.image = images[rightIndex];
            right2.previousTile = gp.tileM.mapTile[x+2][y];
            right2.damageDealer = true;
        }
    }

    public void destroyExplosionTiles()
    {
        if (!special) {
            up2.destroyed = true;
            down2.destroyed = true;
            left2.destroyed = true;
            right2.destroyed = true;
        } else {
            up1.destroyed = true;
            up2.destroyed = true;
            down1.destroyed = true;
            down2.destroyed = true;
            left1.destroyed = true;
            left2.destroyed = true;
            right1.destroyed = true;
            right2.destroyed = true;
        }
    }

    public void loadBombImage() 
    {
        try {
            images[0] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "Bomb1.png").toString()));
            images[1] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "Bomb2.png").toString()));
            images[2] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "Bomb3.png").toString()));
            images[3] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "BombSpecial1.png").toString()));
            images[4] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "BombSpecial2.png").toString()));
            images[5] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "BombSpecial3.png").toString()));
            images[6] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionCentre1.png").toString()));
            images[7] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionCentre2.png").toString()));
            images[8] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionCentre3.png").toString()));
            images[9] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionCentre4.png").toString()));
            images[10] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionCentre5.png").toString()));
            images[11] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionUp1.png").toString()));
            images[12] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionUp2.png").toString()));
            images[13] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionUp3.png").toString()));
            images[14] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionUp4.png").toString()));
            images[15] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionUp5.png").toString()));
            images[16] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionDown1.png").toString()));
            images[17] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionDown2.png").toString()));
            images[18] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionDown3.png").toString()));
            images[19] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionDown4.png").toString()));
            images[20] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionDown5.png").toString()));
            images[21] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionLeft1.png").toString()));
            images[22] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionLeft2.png").toString()));
            images[23] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionLeft3.png").toString()));
            images[24] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionLeft4.png").toString()));
            images[25] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionLeft5.png").toString()));
            images[26] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionRight1.png").toString()));
            images[27] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionRight2.png").toString()));
            images[28] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionRight3.png").toString()));
            images[29] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionRight4.png").toString()));
            images[30] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionRight5.png").toString()));
            images[31] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionVertical1.png").toString()));
            images[32] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionVertical2.png").toString()));
            images[33] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionVertical3.png").toString()));
            images[34] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionVertical4.png").toString()));
            images[35] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionVertical5.png").toString()));
            images[36] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionHorizontal1.png").toString()));
            images[37] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionHorizontal2.png").toString()));
            images[38] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionHorizontal3.png").toString()));
            images[39] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionHorizontal4.png").toString()));
            images[40] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "objects", "bombs", "ExplosionHorizontal5.png").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = images[0];
    }


    

    public void update()
    {
        spriteCounter++;
        if (spriteCounter > 6) {
            // System.out.println(spriteNum);
            
            spriteNum = bombAnimation[animationIndex];
            animationIndex++;
            image = images[spriteNum];
            spriteCounter = 0;
            animationCounter++;
            if (animationCounter >= 10 && animationCounter < bombAnimation.length) {
                damageDealer = true;
                if (!special) {
                    if (!up2.previousTile.collision && !(up2.previousTile instanceof Bomb) && !(up2.previousTile instanceof NewLevel)) {
                        up2.image = images[upIndex];
                        gp.tileM.mapTile[x][y-1] = up2;
                    } else if (up2.previousTile.collision && up2.previousTile.breakable) {
                        gp.tileM.mapTile[x][y-1].destroying = true;
                    }

                    if (!down2.previousTile.collision && !(down2.previousTile instanceof Bomb) && !(down2.previousTile instanceof NewLevel)) {
                        down2.image = images[downIndex];
                        gp.tileM.mapTile[x][y+1] = down2;
                    } else if (down2.previousTile.collision && down2.previousTile.breakable) {
                        gp.tileM.mapTile[x][y+1].destroying = true;
                    }

                    if (!left2.previousTile.collision && !(left2.previousTile instanceof Bomb) && !(left2.previousTile instanceof NewLevel)) {
                        left2.image = images[leftIndex];
                        gp.tileM.mapTile[x-1][y] = left2;
                    } else if (left2.previousTile.collision && left2.previousTile.breakable) {
                        gp.tileM.mapTile[x-1][y].destroying = true;
                    }

                    if (!right2.previousTile.collision && !(right2.previousTile instanceof Bomb) && !(right2.previousTile instanceof NewLevel)) {
                        right2.image = images[rightIndex];
                        gp.tileM.mapTile[x+1][y] = right2;
                    } else if (right2.previousTile.collision && right2.previousTile.breakable) {
                        gp.tileM.mapTile[x+1][y].destroying = true;
                    }
                } else {

                }

                upIndex++;
                downIndex++;
                leftIndex++;
                rightIndex++;
                verticalIndex++;
                horizontalIndex++;
            }
            else if (animationCounter >= bombAnimation.length) {
                animationCounter = 0;
                animationIndex = 0;
                damageDealer = false;
                destroyed = true;
                destroyExplosionTiles();
            }
        }
    }
}
