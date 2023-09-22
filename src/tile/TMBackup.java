// package tile;

// import java.awt.Graphics2D;
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;

// import javax.imageio.ImageIO;

// import main.GamePanel;
// import main.KeyHandler;
// import object.Alarm;
// import object.Bomb;

// public class TileManager {
//     public Tile[] tile;
//     GamePanel gp;
//     Bomb bomb;
//     KeyHandler keyH = new KeyHandler();
//     public int[][] mapTileNum, mapTileNumDefault;
//     Alarm[] alarms;
//     public Bomb[] bombs;

//     public TileManager(GamePanel gp)
//     {
//         this.gp = gp;
//         tile = new Tile[33];    //number of tile types (can be changed)
//         mapTileNum = new int[gp.MAX_SCREEN_COL][gp.MAX_SCREEN_ROW+1];
//         alarms = new Alarm[4]; 
//         bombs = new Bomb[6];    //max bombs  

//         getAlarmsTile();
//         getTileImage();
//         loadMap("/maps/Level1Map.txt");
//         mapTileNumDefault = mapTileNum;
        
//         printMap();
//     }

//     public void printMap()
//     {
//         //printa la mappa
//         for (int i = 0; i < 13; i++) {
//             System.out.println();
//             for (int j = 0; j < 16; j++) {
//                 System.out.print((String.valueOf(mapTileNum[j][i]).length() == 1 ? "0"+mapTileNum[j][i]+" " : mapTileNum[j][i]+" "));
//             }
//         }
//         System.out.println();
//     }

//     public void getAlarmsTile() {
//         alarms[0] = new Alarm(gp, 0, false);
//         alarms[1] = new Alarm(gp, 2, false);
//         alarms[2] = new Alarm(gp, 1, false);
//         alarms[3] = new Alarm(gp, 1, true);
//     }


//     public void getTileImage() {
//         try {
//             tile[0] = new Tile();
//             tile[0].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/Floor.png"));
//             tile[1] = new Tile();
//             tile[1].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/AngleUpSx0_0.png"));
//             tile[2] = new Tile();
//             tile[2].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/AngleUpSx0_1.png"));
//             tile[3] = new Tile();
//             tile[3].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/AngleUpSx1_0.png"));
//             tile[4] = new Tile();
//             tile[4].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/AngleUpSx1_1.png"));
//             tile[4].collision = true;   //questa tile non può essere colpestata dalle entità
//             tile[5] = new Tile();
//             tile[5].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/Up.png"));
//             tile[5].collision = true;
//             tile[6] = new Tile();
//             tile[6].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/AngleUpDx0_0.png"));
//             tile[7] = new Tile();
//             tile[7].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/AngleUpDx0_1.png"));
//             tile[8] = new Tile();
//             tile[8].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/AngleUpDx1_0.png"));
//             tile[8].collision = true;
//             tile[9] = new Tile();
//             tile[9].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/AngleUpDx1_1.png"));
//             tile[10] = new Tile();
//             tile[10].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/Sx1.png"));
//             tile[11] = new Tile();
//             tile[11].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/Sx2.png"));
//             tile[11].collision = true;
//             tile[12] = new Tile();
//             tile[12].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/Dx2.png"));
//             tile[12].collision = true;
//             tile[13] = new Tile();
//             tile[13].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/Dx1.png"));
//             tile[14] = new Tile();
//             tile[14].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/AngleDownSx0.png"));
//             tile[15] = new Tile();
//             tile[15].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/AngleDownSx1.png"));
//             tile[16] = new Tile();
//             tile[16].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/AngleDownDx0.png"));
//             tile[16].collision = true;
//             tile[17] = new Tile();
//             tile[17].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/AngleDownDx1.png"));
//             tile[18] = new Tile();
//             tile[18].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/Down.png"));
//             tile[18].collision = true;
//             tile[19] = new Tile();
//             tile[19].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/NewLevel.png"));
//             tile[20] = new Tile();
//             tile[20].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/FloorShadow.png"));
//             tile[21] = new Tile();
//             tile[21].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/FloorShadowRounded.png"));
//             tile[22] = new Tile();
//             tile[22].image = ImageIO.read(getClass().getResourceAsStream("/sprite/tiles/Level1/UnbreakableObstacle.png"));
//             tile[22].collision = true;
//             tile[23] = alarms[0];  
//             tile[24] = alarms[2];
//             tile[25] = alarms[1];
//             tile[26] = alarms[3];
            
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     public void loadMap(String levelMapPath) {
//         try {
//             InputStream is = getClass().getResourceAsStream(levelMapPath);
//             BufferedReader br = new BufferedReader(new InputStreamReader(is));      //leggiamo il contenuto del file

//             int col = 0;
//             int row = 0;

//             while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {
//                 String line = br.readLine();
//                 String[] numbers = line.split(" ");

//                 while (col < gp.MAX_SCREEN_COL) {

//                     int num = Integer.parseInt(numbers[col]);

//                     mapTileNum[col][row] = num;
//                     col++;
//                 }
//                 if (col == gp.MAX_SCREEN_COL) {
//                     col = 0;
//                     row++;
//                 }
//             }

//             //obstacles off map
//             String line = br.readLine();
//             String[] numbers = line.split(" ");
//             for (int i = 0; i < numbers.length; i++) {
//                 mapTileNum[i][12] = Integer.parseInt(numbers[i]);
//             }

//             br.close();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public void draw(Graphics2D g2)
//     {
//         int col = 0;
//         int row = 0;
//         int x = 0;
//         int y = 0;

//         while (col < gp.MAX_SCREEN_COL && row < gp.MAX_SCREEN_ROW) {
//             int tileNum = mapTileNum[col][row];

//             g2.drawImage(tile[tileNum].image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
//             col++;
//             x += gp.TILE_SIZE;

//             if (col == gp.MAX_SCREEN_COL) {
//                 col = 0;
//                 x = 0;
//                 row++;
//                 y += gp.TILE_SIZE;
//             }
//         }

//         x = 0;

//         // System.out.println(mapTileNum[15][12]);
//     }

//     public void update()
//     {
//         if (!alarms[0].destroyed) alarms[0].update();
//         else reloadMap(alarms[0], 0);
//         if (!alarms[1].destroyed) alarms[1].update();
//         if (!alarms[2].destroyed) alarms[2].update();
//         if (!alarms[3].destroyed) alarms[3].update();
//         else reloadMap(alarms[3], 19);
        
//     }




//     public void reloadMap(Tile tileRemoved, int newTile)
//     {
//         int tileNum = 0;
//         for (int i = 0; i < tile.length; i++) {
//             if (tile[i].equals(tileRemoved)) tileNum = i;
//         }
//         for (int i = 0; i < 13; i++) {
//             for (int j = 0; j < 16; j++) {
//                 if (mapTileNum[j][i] == tileNum && tileNum != 0) {
//                     mapTileNum[j][i] = newTile;
//                     mapTileNumDefault = mapTileNum;
//                     printMap();
//                     break;
//                 }
//             }
//         }
//     }
// }
