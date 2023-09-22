package main;

import entity.Entity;
import tile.Tile;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        Tile tile1, tile2;
        int entityLeftX = entity.x + entity.solidBody.x;
        int entityRightX = entityLeftX + entity.solidBody.width;
        int entityTopY = entity.y + entity.solidBody.y;
        int entityBottomY = entityTopY + entity.solidBody.height;

        int entityLeftCol = entityLeftX/gp.TILE_SIZE;
        int entityRightCol = entityRightX/gp.TILE_SIZE;
        int entityTopRow = entityTopY/gp.TILE_SIZE;
        int entityBottomRow = entityBottomY/gp.TILE_SIZE;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed) / gp.TILE_SIZE;
                tile1 = gp.tileM.mapTile[entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTile[entityRightCol][entityTopRow];
                if (tile1.collision || tile2.collision) entity.collisionOn = true; 
                break;
            case "down": 
                entityBottomRow = (entityBottomY + entity.speed) / gp.TILE_SIZE;
                tile1 = gp.tileM.mapTile[entityLeftCol][entityBottomRow];
                tile2 = gp.tileM.mapTile[entityRightCol][entityBottomRow];
                if (tile1.collision || tile2.collision) entity.collisionOn = true; 
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / gp.TILE_SIZE;
                tile1 = gp.tileM.mapTile[entityLeftCol][entityTopRow];
                tile2 = gp.tileM.mapTile[entityLeftCol][entityBottomRow];
                if (tile1.collision || tile2.collision) entity.collisionOn = true; 
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed) / gp.TILE_SIZE;
                tile1 = gp.tileM.mapTile[entityRightCol][entityTopRow];
                tile2 = gp.tileM.mapTile[entityRightCol][entityBottomRow];
                if (tile1.collision || tile2.collision) entity.collisionOn = true; 
                break;
        }
    }
}
