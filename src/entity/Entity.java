package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import tile.Tile;

public abstract class Entity {
    public int x;
    public int y;
    public int speed;
    public int tileW;
    public int tileH;
    public int health;
    public boolean immunity;
    protected int immunityCount;
    BufferedImage image;
    GamePanel gp;

    protected BufferedImage[] up, down, left, right, death;
    public String direction;

    protected int spriteCounter = 0;
    protected int spriteNum = 2;
    protected boolean reverseCount;
    protected boolean dead;
    public Rectangle solidBody;  //parte solida dell'entità che non può entrare in contatto con determinate tile
    public boolean collisionOn;
    public Tile currentTile;
}
