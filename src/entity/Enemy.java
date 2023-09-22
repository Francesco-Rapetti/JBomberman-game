package entity;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Enemy extends Entity{
    int initX, initY;
    String enemyType;
    
    public Enemy(GamePanel gp, int x, int y, String enemyType)
    {
        this.gp = gp;
        initX = x;
        initY = y;
        this.enemyType = enemyType;
        setDefaultValues();
        buildEnemy();
    }

    public void setDefaultValues()
    {
        x = initX;
        y = initY;
        dead = false;
        immunity = false;
    }

    public void buildEnemy()
    {
        switch (enemyType) {
            case "normal":

                //setting images
                up = new BufferedImage[4];
                down = new BufferedImage[4];
                left = new BufferedImage[4];
                right = new BufferedImage[4];
                try {
                    up[0] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "up0.png").toString()));  
                    up[1] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "up1.png").toString()));  
                    up[2] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "up2.png").toString()));  
                    up[3] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "up3.png").toString()));  
                    down[0] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "down0.png").toString()));  
                    down[1] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "down1.png").toString()));  
                    down[2] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "down2.png").toString()));  
                    down[3] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "down3.png").toString()));
                    left[0] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "left0.png").toString()));
                    left[1] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "left1.png").toString()));
                    left[2] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "left2.png").toString()));
                    left[3] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "left3.png").toString()));
                    left[3] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "left3.png").toString()));
                    right[0] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "right0.png").toString()));
                    right[1] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "right1.png").toString()));
                    right[2] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "right2.png").toString()));
                    right[3] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "right3.png").toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "special":
                
                //setting images
                up = new BufferedImage[4];
                try {
                    up[0] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "updownleftright0.png").toString()));  
                    up[1] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "updownleftright1.png").toString()));  
                    up[2] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "updownleftright2.png").toString()));  
                    up[3] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "updownleftright3.png").toString()));  
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default: System.out.println("This enemy does not exist"); break;
        }

        //setting death images
        death = new BufferedImage[8];
        try {
            death[0] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "enemyDeath0.png").toString()));
            death[1] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "enemyDeath1.png").toString()));
            death[2] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "enemyDeath2.png").toString()));
            death[3] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "enemyDeath3.png").toString()));
            death[4] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "enemyDeath4.png").toString()));
            death[5] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "enemyDeath5.png").toString()));
            death[6] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "enemyDeath6.png").toString()));
            death[7] = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "enemies", "enemyDeath7.png").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
