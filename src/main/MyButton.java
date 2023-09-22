package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class MyButton {
    int x, y, width, height, textX, textY, bPressedY, bReleasedY;
    float textSize;
    String text, color;
    Rectangle bounds;
    BufferedImage button, buttonPressed, image;
    boolean pressed, released;
    Color fontColor;
    public boolean active;

    public MyButton(String text, float textSize, int x, int y, int width, int height, String color)
    {
        this.text = text;
        this.textSize = textSize;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        textY = y+55;
        bReleasedY = textY;
        bPressedY = textY+16;
        pressed = false;
        released = false;
        active = false;
        initBounds();
        loadButtonImages();
        image = button;

        //messaggio di avvertimento
        if (text.length() > 7) System.out.println("ATTENZIONE: la scritta \""+text+"\" potrebbe uscire dai bordi del pulsante. Ricontrollare");
    }

    private void loadButtonImages()
    {
        try {
            switch (color) {
                case "orange":
                    button = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "UI", "OrangeButton.png").toString()));
                    buttonPressed = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "UI", "OrangeButtonPressed.png").toString()));
                    fontColor = Color.GRAY;
                    break;
                case "black":
                    button = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "UI", "GreyButton.png").toString()));
                    buttonPressed = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "UI", "GreyButtonPressed.png").toString()));
                    fontColor = Color.RED;
                    break;
                default:
                    button = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "UI", "OrangeButton.png").toString()));
                    buttonPressed = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "UI", "OrangeButtonPressed.png").toString()));
                    fontColor = Color.GRAY;
                    break;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    private void initBounds()
    {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void mouseClicked(int x, int y)
    {
        if (this.getBounds().contains(x, y)) {
            image = buttonPressed;
            textY = bPressedY;
            pressed = true;
        }
    }

    public void draw(Graphics2D g2)
    {
        //button actions
        if (MouseHandler.button1Pressed) {
            mouseClicked(MouseHandler.button1X, MouseHandler.button1Y);
        }
        if (!MouseHandler.button1Pressed && pressed) {
            image = button;
            textY = bReleasedY;
            released = true;
        }
        if (pressed && released) {
            active = true;
            pressed = false;
            released = false;
        }

        //button draw
        g2.drawImage(image, x, y, width, height, null);

        //text shadow
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, textSize));
        int w = g2.getFontMetrics().stringWidth(text);
        // int h = g2.getFontMetrics().getHeight();
        textX = x + (width-w)/2;
        g2.setColor(Color.BLACK);
        g2.drawString(text, textX-2, textY+2);

        //text
        g2.setColor(fontColor);
        g2.drawString(text, textX, textY);
    }

}
