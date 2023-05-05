import org.newdawn.slick.tiled.TiledMap;

public class Collisions {
	
	public static boolean collision(TiledMap map)
	{
		
		int mapWidth = map.getWidth();
		int mapHeight = map.getHeight();

		for (int y = 0; y < mapHeight; y++) {
		    for (int x = 0; x < mapWidth; x++) {
		        int tileId = map.getTileId(x, y, 1); // layer d'index 1
		        if (tileId == 0) {
		            System.out.print("0 ");
		        } else {
		            System.out.print("1 ");
		        }
		    }
		    System.out.println();
		}



		
		return false;
	}
	
}