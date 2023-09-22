package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import object.Bomb;
import tile.Floor;
import tile.Tile;

public class Player extends Entity {
    KeyHandler keyH;
    int[] movementAnimation = {1, 2, 3, 2};
    int animationIndex;
    public int currentTileX;
    public int currentTileY;
    public int bombX, bombY;
    int currentTileNum;
    public Bomb bomb;
    public Tile previousTile, currentTile;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();

        
    }

    public void setDefaultValues()
    {
        dead = false;
        x = 35*gp.SCALE;
        y = 25*gp.SCALE;
        tileW = 11*gp.SCALE;
        tileH = 17*gp.SCALE;
        direction = "down";
        currentTileX = (x+(tileW/2)-8)/gp.TILE_SIZE;
        currentTileY = ((y+1)+(tileH-16))/gp.TILE_SIZE;
        // System.out.println(currentTileX+", "+currentTileY);
        currentTileNum = gp.tileM.mapTileNum[currentTileX][currentTileY];
        currentTile = gp.tileM.mapTile[currentTileX][currentTileY];
        health = 3;
        
        // System.out.println(currentTile);
        setDefaultSpeed();
        solidBody = new Rectangle((int) (1*gp.SCALE), (11*gp.SCALE), (int) (tileW-(2*gp.SCALE)), (int) (tileH-(12.8*gp.SCALE)));   
        
        getPlayerImage();
    }

    public void setDefaultSpeed()
    {
        speed = (int) (1.2*gp.SCALE);
    }

    public void getPlayerImage() {
        up = new BufferedImage[3];
        down = new BufferedImage[3];
        left = new BufferedImage[3];
        right = new BufferedImage[3];
        death = new BufferedImage[8];
        try {
            up[0] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanUp0.png").toString()));
            up[1] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanUp1.png").toString()));
            up[2] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanUp2.png").toString()));
            down[0] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanDown0.png").toString()));
            down[1] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanDown1.png").toString()));
            down[2] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanDown2.png").toString()));
            left[0] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanLeft0.png").toString()));
            left[1] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanLeft1.png").toString()));
            left[2] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanLeft2.png").toString()));
            right[0] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanRight0.png").toString()));
            right[1] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanRight1.png").toString()));
            right[2] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanRight2.png").toString()));

            death[0] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanDeath1.png").toString()));
            death[1] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanDeath2.png").toString()));
            death[2] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanDeath3.png").toString()));
            death[3] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanDeath4.png").toString()));
            death[4] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanDeath5.png").toString()));
            death[5] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanDeath6.png").toString()));
            death[6] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanDeath7.png").toString()));
            death[7] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "player", "JBombermanDeath8.png").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    

    public void update() {
        if (!dead) {
            if (KeyHandler.upPressed || KeyHandler.downPressed || KeyHandler.leftPressed || KeyHandler.rightPressed) {
                // System.out.println(x+", "+y);
                spriteCounter++;
                if (spriteCounter > 6) {
                    // System.out.println(spriteNum);
                    
                    if (animationIndex >= movementAnimation.length) animationIndex = 0;
                    spriteNum = movementAnimation[animationIndex];
                    animationIndex++;

                    spriteCounter = 0;
                }
            } else spriteNum = 2;

            setDefaultSpeed();
            if (KeyHandler.upPressed == true) direction = "up";
            else if (KeyHandler.downPressed == true) direction = "down";
            else if (KeyHandler.leftPressed == true) direction = "left";
            else if (KeyHandler.rightPressed == true) direction = "right";
            else speed = 0;
            
            if (KeyHandler.spaceBarPressed && currentTile instanceof Floor) {
                
                bombX = currentTileX;
                bombY = currentTileY;
                gp.tileM.mapTile[bombX][bombY] = new Bomb(gp, false);
                KeyHandler.spaceBarPressed = false;
            }


            if (KeyHandler.ePressed) {
                if (!gp.tileM.mapTile[10][10].destroyed) gp.tileM.mapTile[10][10].destroying = true;
                // if (!gp.tileM.mapTile[3][10].destroyed) gp.tileM.mapTile[3][10].destroying = true;
                // if (!gp.tileM.mapTile[4][5].destroyed) gp.tileM.mapTile[4][5].destroying = true;
                // if (!gp.tileM.mapTile[11][11].destroyed) gp.tileM.mapTile[11][11].destroying = true;
            }

            //check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //if collision is false player can move 
            if (!collisionOn) {
                switch (direction) {
                    case "up": y -= speed; break;
                    case "down": y += speed; break;
                    case "left": x -= speed; break;
                    case "right": x += speed; break;
                }
            }
            currentTileX = (x+(tileW/2)-8)/gp.TILE_SIZE;
            currentTileY = ((y+1)+(tileH-16))/gp.TILE_SIZE;
            // System.out.println(currentTileX+", "+currentTileY);
            currentTileNum = gp.tileM.mapTileNum[currentTileX][currentTileY];
            currentTile = gp.tileM.mapTile[currentTileX][currentTileY];
            // System.out.println(currentTile);
            // System.out.println(x+", "+y);

            if (currentTile.damageDealer && !immunity) {
                health--;
                immunity = true;
                System.out.println("current health: "+health);
            }
            if (immunity) {
                if (immunityCount > 50) {
                    immunity = false;
                    immunityCount = 0;
                }
                else immunityCount++;
            }
            if (health <= 0) {
                immunity = false;
                spriteCounter = 0;
                spriteNum = 0;
                dead = true;
            }
        } else {
            GamePanel.gameState = gp.DEATH_STATE;
            spriteCounter++;
            if (spriteCounter > 6) {
                spriteCounter = 0;
                if (spriteNum < death.length) image = death[spriteNum++];
                else GamePanel.gameState = gp.DEAD_STATE;
            }
        }
    }

    public void draw(Graphics2D g2)
    {

        if (!dead) {
            switch (direction) {
                case "up": 
                    if (spriteNum == 1) {
                        image = up[0];
                    }
                    if (spriteNum == 2) {
                        image = up[1];
                    }
                    if (spriteNum == 3) {
                        image = up[2];
                    } 
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down[0];
                    }
                    if (spriteNum == 2) {
                        image = down[1];
                    }
                    if (spriteNum == 3) {
                        image = down[2];
                    } 
                    break;
                case "left": 
                    if (spriteNum == 1) {
                        image = left[0];
                    }
                    if (spriteNum == 2) {
                        image = left[1];
                    }
                    if (spriteNum == 3) {
                        image = left[2];
                    } 
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right[0];
                    }
                    if (spriteNum == 2) {
                        image = right[1];
                    }
                    if (spriteNum == 3) {
                        image = right[2];
                    } 
                    break;
            }
        }
        if (immunity) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }
        g2.drawImage(image, x, y, tileW, tileH, null);        //draw an image on the screen
        // g2.setColor(Color.BLUE);
        // g2.fillRect((int) (1*gp.SCALE), (11*gp.SCALE), (int) (tileW-(2*gp.SCALE)), (int) (tileH-(12.8*gp.SCALE)));
        
        //currentTile checker
        // g2.drawImage(image, x+(tileW/2)-8, y+tileH-16, 16, 16, null);
    }
}
