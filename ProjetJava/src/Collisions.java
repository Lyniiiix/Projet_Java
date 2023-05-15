import org.newdawn.slick.tiled.TiledMap;

public class Collisions {
	
	private Map map;
	private TiledMap mapTMX;
	private int[][] matriceMap;
	private Objet[] objetsMap;
	
	public Collisions (Map map)
	{
		this.map = map;
		this.mapTMX = map.getMapTMX();
		this.matriceMap = map.getMatrice();
		this.objetsMap = map.getObjets();
	}
	
	
	public boolean enCollision(float posX, float posY) {
		/*
		// Vérifier si le joueur est en dehors de la carte
	    if (posX < 0 || posX + Constantes.LARGEUR_PERSO > Constantes.MAP_X * Constantes.LARGEUR_PERSO || posY < Constantes.HAUTEUR_ECRAN - Constantes.HAUTEUR_CASE || posY + Constantes.HAUTEUR_PERSO > Constantes.MAP_Y * Constantes.HAUTEUR_PERSO) {
	        return true;
	    }
	    */
	    
	    for (Objet objet : objetsMap) {
	    	System.out.println("posX : " + posX + " , " + "posY : " + posY);
		    System.out.println(objet);
	        if ((posX + Constantes.LARGEUR_PERSO) <= objet.getx1() &&
	        	posX >= (objet.getx1() + Constantes.LARGEUR_CASE) &&
	        	posY >= (objet.gety1() + Constantes.HAUTEUR_CASE) &&
	        	(posY + Constantes.HAUTEUR_PERSO) >= objet.gety1()
	        )
	        {
	            return true;
	        }
	    }
	    
	    return false;
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public boolean collisionX(int posX, int posY, String status_perso)
	{
		//System.out.println(map.getTileId(posX + 1, posY, map.getLayerIndex("bloc")) == 1);
		
		
		switch (status_perso) {
		case "droite": {
			return mapTMX.getTileId(posX + 1, posY, mapTMX.getLayerIndex("bloc")) == 1;
		}
		case "gauche": {
			return mapTMX.getTileId(posX, posY, mapTMX.getLayerIndex("bloc")) == 1;
		}
		default:
			return true;
		}
	}
	
	// pour tracker le bloc sous le Perso
	public void detecterSol(float posX, float posY)
	{
		
	}

	
	*/
	
	
	
	
	/*
	// marche pas je pense
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
	*/
	
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
	
	
	/*
	// pour tracker le bloc sous le Perso
	public void detecterSol(float posX, float posY)
	{
		if (mapTMX.getTileId(posX, posY +1, mapTMX.getLayerIndex("bloc")) == 1)
		{
			System.out.println("Sol");
		} else {
			System.out.println("Pas Sol");
		}
		
		
	}
	*/