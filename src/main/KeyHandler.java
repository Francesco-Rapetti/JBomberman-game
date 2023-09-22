package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public static boolean upPressed, downPressed, leftPressed, rightPressed, spaceBarPressed, ePressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();      //ritorna l'intero associato al tasto dell'evento
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if (code == KeyEvent.VK_SPACE) {
            spaceBarPressed = true;
        }

        if (code == KeyEvent.VK_ESCAPE) {
            if (GamePanel.gameState == gp.PLAY_STATE) {
                GamePanel.gameState = gp.PAUSE_STATE;
            } else {
                GamePanel.gameState = gp.PLAY_STATE;
            }
        }

        if (code == KeyEvent.VK_E) {
            ePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();      //ritorna l'intero associato al tasto dell'evento
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if (code == KeyEvent.VK_SPACE) {
            spaceBarPressed = false;
        }

        if (code == KeyEvent.VK_E) {
            ePressed = false;
        }
    }
    
}
