package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import object.Alarm;
import object.Bomb;

public class TileManager {
    public Tile[] tile;
    GamePanel gp;
    Bomb bomb;
    KeyHandler keyH = new KeyHandler(gp);
    public int[][] mapTileNum, mapTileNumDefault;
    public Tile[][] mapTile;
    Alarm[] alarms;
    public Bomb[] bombs;

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tile = new Tile[33];    //number of tile types (can be changed)
        mapTileNum = new int[gp.MAX_SCREEN_COL][gp.MAX_SCREEN_ROW+1];
        mapTile = new Tile[gp.MAX_SCREEN_COL][gp.MAX_SCREEN_ROW+1];
        alarms = new Alarm[4]; 
        bombs = new Bomb[6];    //max bombs  
        setDefaultValues();
    }

    public void setDefaultValues()
    {
        getTileImage();
        loadMap("/maps/Level1Map.txt");
        loadMapTile(mapTileNum);
        
        printMap();
    }

    public void printMap()
    {
        //printa la mappa
        for (int i = 0; i < 13; i++) {
            System.out.println();
            for (int j = 0; j < 16; j++) {
                System.out.print((String.valueOf(mapTileNum[j][i]).length() == 1 ? "0"+mapTileNum[j][i]+" " : mapTileNum[j][i]+" "));
            }
        }
        System.out.println();
    }

    public void printMapTile()
    {
        for (int i = 0; i < 13; i++) {
            System.out.println();
            for (int j = 0; j < 16; j++) {
                System.out.print(mapTile[j][i]);
            }
        }
    }



    public void getTileImage() {
        try {
            tile[0] = new Floor(false);
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "AngleUpSx0_0.png").toString()));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "AngleUpSx0_1.png").toString()));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "AngleUpSx1_0.png").toString()));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "AngleUpSx1_1.png").toString()));
            tile[4].collision = true;   //questa tile non può essere colpestata dalle entità
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "Up.png").toString()));
            tile[5].collision = true;
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "AngleUpDx0_0.png").toString()));
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "AngleUpDx0_1.png").toString()));
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "AngleUpDx1_0.png").toString()));
            tile[8].collision = true;
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "AngleUpDx1_1.png").toString()));
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "Sx1.png").toString()));
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "Sx2.png").toString()));
            tile[11].collision = true;
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "Dx2.png").toString()));
            tile[12].collision = true;
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "Dx1.png").toString()));
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "AngleDownSx0.png").toString()));
            tile[15] = new Tile();
            tile[15].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "AngleDownSx1.png").toString()));
            tile[16] = new Tile();
            tile[16].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "AngleDownDx0.png").toString()));
            tile[16].collision = true;
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "AngleDownDx1.png").toString()));
            tile[18] = new Tile();
            tile[18].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "Down.png").toString()));
            tile[18].collision = true;
            tile[19] = new Tile();
            tile[19].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "NewLevel.png").toString()));
            tile[20] = new Floor(true);
            tile[22] = new Tile();
            tile[22].image = ImageIO.read(new FileInputStream(Paths.get(System.getProperty("user.dir"), "res", "sprite", "tiles", "Level1", "UnbreakableObstacle.png").toString()));
            tile[22].collision = true;
            tile[23] = new Alarm(gp, false, new Floor(false));  
            tile[24] = new Alarm(gp, true, new Floor(true));
            tile[25] = new Alarm(gp, false, new Floor(false));
            tile[26] = new Alarm(gp, true, new NewLevel());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String levelMapPath) {
        try {
            InputStream is = getClass().getResourceAsStream(levelMapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));      //leggiamo il contenuto del file

            int col = 0;
            int row = 0;

            while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {
                String line = br.readLine();
                String[] numbers = line.split(" ");

                while (col < gp.MAX_SCREEN_COL) {

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.MAX_SCREEN_COL) {
                    col = 0;
                    row++;
                }
            }

            //obstacles off map
            String line = br.readLine();
            String[] numbers = line.split(" ");
            for (int i = 0; i < numbers.length; i++) {
                mapTileNum[i][12] = Integer.parseInt(numbers[i]);
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMapTile(int[][] mapTileNum)
    {
        for (int i = 0; i < 13; i++) {
            System.out.println();
            for (int j = 0; j < 16; j++) {
                mapTile[j][i] = tile[mapTileNum[j][i]];
                System.out.print(mapTile[j][i]);
            }
        }
    }

    public void draw(Graphics2D g2)
    {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {

            g2.drawImage(mapTile[col][row].image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
            col++;
            x += gp.TILE_SIZE;

            if (col == gp.MAX_SCREEN_COL) {
                col = 0;
                x = 0;
                row++;
                y += gp.TILE_SIZE;
            }
        }

        x = 0;

        // System.out.println(mapTileNum[15][12]);
    }

    public void update()
    {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 16; j++) {
                if (!mapTile[j][i].destroyed) mapTile[j][i].update();
                else {
                    mapTile[j][i] = mapTile[j][i].previousTile;
                    // printMapTile();
                }
            }
        }
    }
}
