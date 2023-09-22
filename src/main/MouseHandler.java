package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
    GamePanel gp;
    public static boolean button1Pressed;
    public static int button1X, button1Y;

    public MouseHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            switch (GamePanel.gameState) {
                case 1:     //play state 
                    break;      
                case 2:     //pause state
                    // System.out.println(e.getX()+", "+e.getY()); 
                    button1Pressed = true;
                    button1X = e.getX();
                    button1Y = e.getY();
                    break;            
                case 3:     //death state 
                    break;      
                case 4:     //dead state 
                    button1Pressed = true;
                    button1X = e.getX();
                    button1Y = e.getY();
                    break;      
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            switch (GamePanel.gameState) {
                case 1:     //play state 
                    break;      
                case 2:     //pause state
                    // System.out.println(e.getX()+", "+e.getY()); 
                    button1Pressed = false;
                    button1X = 0;
                    button1Y = 0;
                    break;            
                case 3:     //death state 
                    
                    break;      
                case 4:     //dead state 
                    System.out.println(e.getX()+", "+e.getY()); 
                    button1Pressed = false;
                    button1X = 0;
                    button1Y = 0;
                    break;      
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
