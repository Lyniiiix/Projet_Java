import org.newdawn.slick.tiled.TiledMap;

public class Collisions {
	
	private TiledMap map;
	
	private int mapWidth;
	private int mapHeight;
	
	public Collisions (TiledMap map)
	{
		this.map = map;
		this.mapWidth = map.getWidth();
		this.mapHeight = map.getHeight();
	}
	
	public void estEnCollision(int posX, int posY)
	{
		for (int y = 0; y < mapHeight; y++) {
		    for (int x = 0; x < mapWidth; x++) {
		        int tileId = map.getTileId(x, y, map.getLayerIndex("bloc")); // layer d'index 1
		        if (tileId == 0) {
		            System.out.print("0 ");
		        } else {
		            System.out.print("1 ");
		        }
		    }
		    System.out.println();
		}
		
		//return map.getTileId(posX + 1, posY + 1, map.getLayerIndex("bloc"));
	}
	
	public boolean collisionSelonPos(int posX, int posY, String status_perso)
	{
		System.out.println(map.getTileId(posX + 1, posY, map.getLayerIndex("bloc")) == 1);
		
		
		switch (status_perso) {
		case "droite": {
			return map.getTileId(posX + 1, posY, map.getLayerIndex("bloc")) == 1;
		}
		case "gauche": {
			return map.getTileId(posX, posY, map.getLayerIndex("bloc")) == 1;
		}
		case "saut": {
			return map.getTileId(posX, posY - 1, map.getLayerIndex("bloc")) == 1;
		}
		default:
			return true;
		}
	}

	
}