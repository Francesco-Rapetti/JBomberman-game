package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.InanimatedObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    
    // screen settings
    public final int ORIGINAL_TILE_SIZE = 16;                  //16x16 tile, dimensione standard
    public final int SCALE = 5;                                //n volte più grande
    public final int TILE_SIZE = ORIGINAL_TILE_SIZE*SCALE;
    public final int MAX_SCREEN_COL = 16;
    public final int MAX_SCREEN_ROW = 12;                      //ratio 4:3
    public final int SCREEN_WIDTH = TILE_SIZE*MAX_SCREEN_COL;  //1280 pixels
    public final int SCREEN_HEIGHT = TILE_SIZE*MAX_SCREEN_ROW; //960 pixels

    //FPS
    int FPS = 60;

    public static int level = 1;

    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public MouseHandler mouseH = new MouseHandler(this);
    public UI ui = new UI(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    public InanimatedObject obj[] = new InanimatedObject[20];

    //game state
    public static int gameState;
    public final int PLAY_STATE = 1;
    public final int PAUSE_STATE = 2;
    public final int DEATH_STATE = 3;
    public final int DEAD_STATE = 4;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));         //set the size of this class (JPanel)
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);               //if true all the drawing drom this component will be done in an offscreen painting buffer (better rendering performance)
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true);                //with this the GamePanel can be "focused" to receive key input
    }

    public void setUpGame() {
        gameState = PLAY_STATE;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void restartGame()
    {
        gameState = PLAY_STATE;
        player.setDefaultValues();
        tileM.setDefaultValues();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000/FPS;        //0.01666 seconds
        double nextDrawTime = System.nanoTime()+drawInterval;

        while (gameThread != null) {
            // long currentTime = System.nanoTime();       //returns the current value of the running JVM's high-resolution time source, in nanoseconds
            // long currentTime2 = System.currentTimeMillis();     //returns a less precise time.


            // System.out.println("The game loop is running");
            //1 UPDATE: update information such as character positions
            update();
            //2 DRAW: draw the screen with the updated information
            repaint();      //così chiamiamo il paintComponent
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1_000_000;        //convert to millisecs

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (gameState == PLAY_STATE) {
            player.update();
            tileM.update();
        }
        if (gameState == PAUSE_STATE) {
            
        }
        if (gameState == DEATH_STATE) {
            player.update();
        }
        if (gameState == DEAD_STATE) {
            
        }

        // obj[0].update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (!player.immunity) {
            tileM.draw(g2);
            player.draw(g2);
            ui.draw(g2);
        } else {
            tileM.draw(g2);
            player.draw(g2);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            ui.draw(g2);
        }

        g2.dispose();       //buona pratica per salvare un po' di memoria è di usare dispose() una volta finito di disegnare
    }
}
