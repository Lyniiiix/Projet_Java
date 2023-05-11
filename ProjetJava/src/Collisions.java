import org.newdawn.slick.tiled.TiledMap;

public class Collisions {
	
	private TiledMap map;
	
	public Collisions (TiledMap map)
	{
		this.map = map;
	}
	
	
	public boolean collisionSelonPos(int posX, int posY, String status_perso)
	{
		//System.out.println(map.getTileId(posX + 1, posY, map.getLayerIndex("bloc")) == 1);
		
		
		switch (status_perso) {
		case "droite": {
			return map.getTileId(posX + 1, posY, map.getLayerIndex("bloc")) == 1;
		}
		case "gauche": {
			return map.getTileId(posX, posY, map.getLayerIndex("bloc")) == 1;
		}
		case "saut": {
			if (map.getTileId(posX, posY - 1, map.getLayerIndex("bloc")) == 1) { // Si la case au-dessus est un bloc solide
                return true; // Collision avec le plafond
            } else {
                // Vérifier la présence de blocs solides sur les côtés
                boolean collisionGauche = map.getTileId(posX, posY, map.getLayerIndex("bloc")) == 1;
                boolean collisionDroite = map.getTileId(posX + 1, posY, map.getLayerIndex("bloc")) == 1;
                return collisionGauche || collisionDroite;
            }
		}
		default:
			return true;
		}
	}
	
	public static void detecterCollisionSaut(TiledMap map)
	{
		for (int y = 0; y < Constantes.LARGEUR_ECRAN; y++) {
		    for (int x = 0; x < Constantes.HAUTEUR_ECRAN; x++) {
		        int tileId = map.getTileId(x, y, map.getLayerIndex("bloc")); // layer d'index 1
		        if (tileId == 0) // si c'est le premier layer
		        {
		            System.out.print("0 ");
		        } else {
		            System.out.print("1 ");
		        }
		    }
		    System.out.println();
		}
	}
	
	
	
	public static void detecterSol(int posX, int posY, TiledMap map)
	{
		if (map.getTileId(posX, posY +1, map.getLayerIndex("bloc")) == 1)
		{
			System.out.println("Sol");
		} else {
			System.out.println("Pas Sol");
		}
	}

	
	
	
	
	
	
	
	
	/*
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
	*/
	
}