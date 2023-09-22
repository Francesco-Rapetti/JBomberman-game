package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    BufferedImage pause, death;
    MyButton resume, options, quit, restart, quitDead;
    Font maruMonica;
    int buttonX, buttonW, buttonH;

    public UI(GamePanel gp)
    {
        this.gp = gp;
        setDefaultValues();
    }

    public void setDefaultValues()
    {
        initFont();
        buttonW = 200;
        buttonH = 100;
        buttonX = gp.TILE_SIZE*7 - gp.TILE_SIZE/4;
        resume = new MyButton("Resume", 57f, buttonX, gp.TILE_SIZE*6, buttonW, buttonH, "orange");
        options = new MyButton("Options", 57f, buttonX, gp.TILE_SIZE*8 - gp.TILE_SIZE/2, buttonW, buttonH, "orange");
        quit = new MyButton("Quit", 57f, buttonX, gp.TILE_SIZE*9, buttonW, buttonH, "orange");
        restart = new MyButton("Restart", 57f, gp.TILE_SIZE*4, gp.TILE_SIZE*8 + gp.TILE_SIZE/2, buttonW, buttonH, "black");
        quitDead = new MyButton("Quit", 57f, gp.TILE_SIZE*9 + gp.TILE_SIZE/2, gp.TILE_SIZE*8 + gp.TILE_SIZE/2, buttonW, buttonH, "black");
        loadImages();
    }

    public void initFont()
    {
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "font", "MaruMonica.ttf").toString()));
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadImages()
    {
        try {
            // pause = ImageIO.read(new FileInputStream("C:\\Users\\rapet\\Documents\\Università\\Metodologie di programmazione\\Progetto\\gioco2D\\src\\sprite\\PAUSED.png"));
            // death = ImageIO.read(new FileInputStream("C:\\Users\\rapet\\Documents\\Università\\Metodologie di programmazione\\Progetto\\gioco2D\\src\\sprite\\YOU DIED.png"));
            pause = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "UI", "PAUSED.png").toString()));
            death = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "UI", "YOU DIED.png").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        g2.setFont(maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));
        g2.setColor(Color.WHITE);

        if (GamePanel.gameState == gp.PAUSE_STATE) drawPauseScreen();
        if (GamePanel.gameState == gp.DEAD_STATE) drawDeathScreen();
    }

    public void drawPauseScreen() 
    {
        //paused image
        int boxX = gp.TILE_SIZE*4 + gp.TILE_SIZE/4, boxY = 120, boxW = 600, boxH = 300;
        g2.drawImage(pause, boxX, boxY, boxW, boxH, null);

        //paused text shadow
        String text = "PAUSED";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 200f));
        int w = g2.getFontMetrics().stringWidth(text);
        int h = g2.getFontMetrics().getHeight();
        int textX = boxX + (boxW-w)/2, textY = boxY + boxH - (boxH-h);
        g2.setColor(Color.BLACK);
        g2.drawString(text, textX-5, textY+5);

        //paused text
        g2.setColor(Color.GRAY);
        g2.drawString("PAUSED", textX, textY);
        
        //buttons draw
        resume.draw(g2);
        options.draw(g2);
        quit.draw(g2);

        //buttons behaviour
        if (resume.active) {
            GamePanel.gameState = gp.PLAY_STATE;
            resume.active = false;
        }
        if (quit.active) {
            quit.active = false;
            System.exit(0);
        }
    }

    public void drawDeathScreen()
    {
        int x;
        int y;
        int length = 128*gp.SCALE;

        x = gp.SCREEN_WIDTH/2 - length/2;
        y = gp.SCREEN_HEIGHT/3;

        //death image
        g2.drawImage(death, x, y, 128*gp.SCALE, 64*gp.SCALE, null);

        //buttons
        restart.draw(g2);
        quitDead.draw(g2);

        //buttons behaviour
        if (quitDead.active) {
            quit.active = false;
            System.exit(0);
        }
        if (restart.active) {
            restart.active = false;
            gp.restartGame();
        }
    }
}
