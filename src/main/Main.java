package main;
//ricomincia da parte 4
import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("JBomberman");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();          //causes this window to be sized to fit the preferred size and layouts of its subcomnponents (GamePanel)
        
        window.setLocationRelativeTo(null);     //la finestra apparirà al centro dello schermo
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }
}
