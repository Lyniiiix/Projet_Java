// PERMET DE CREER/GENERER UNE ENTITE MAP AVEC .TMX & IMAGE DE FOND

package objets;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.tiled.TiledMap;

import main.Constantes;


public class Map {
	
	private Image image_fond; // image de fond de la map

	private TiledMap map; // .tmx de map
	
	private int[][] matrice_map = new int[23][32]; // matrice de 0 et de 1 de la map
	
	private Objet[] objetsMap; // tableau contenant les objets bloquant de la map
	
	public Map(GameContainer gc, Image image_fond, TiledMap map){
		this.image_fond = image_fond;
		this.map = map;
		this.genererMatrice(); // genere la matrice a partir du tmx 0=vide/1=objet bloquant
		this.genererObjets(); // genere le tableau d'objets bloquant
	}
	
	
	public int[][] getMatrice() // retourne la matrice de 0 et de 1
	{
		return matrice_map;
	}
	public TiledMap getMapTMX() // retourne le .tmx
	{
		return map;
	}
	public Image getFond() // retourne l'image de fond
	{
		return image_fond;
	}
	public Objet[] getObjets() // retourne le tableau d'objets bloquant
	{
		return objetsMap;
	}
	
	public void genererMatrice() // genere la matrice a partir du .tmx
	{
		for (int y = 0; y < 23; y++) {
		    for (int x = 0; x < 32; x++) {
		        int tileId = map.getTileId(x, y, map.getLayerIndex("bloc"));
		        if (tileId == 0) {
		        	matrice_map[y][x] = 0;
		        } else {
		        	matrice_map[y][x] = 1;
		        }
		    }
		}
	}
	
	public void genererObjets() // creer une instance d'Objet pour chaque objet bloquant
	{
	    int count = 0;
	    for (int y = 0; y < 23; y++) {
	        for (int x = 0; x < 32; x++) {
	            if (matrice_map[y][x] == 1) {
	                count += 1;
	            }
	        }
	    }
	    
	    objetsMap = new Objet[count];
	    
	    count = 0;
	    
	    for (int y = 0; y < 23; y++) {
	        for (int x = 0; x < 32; x++) {
	            if (matrice_map[y][x] == 1) {
	                objetsMap[count] = new Objet("bloc",count + 1 ,x * 32, y * 36);
	                count++;
	            }
	        }
	    }
	}
	
	public int getNbObjets() // recup le nb d'Objet pour debug
	{
		return objetsMap.length;
	}
	
	public void toArray() // affiche dans la console la map de 0 et de 1
	{
		for (int y = 0; y < Constantes.MAP_Y; y++) {
		    for (int x = 0; x < Constantes.MAP_X; x++) {
		        int tileId = map.getTileId(x, y, map.getLayerIndex("bloc")); // layer d'index 1
		        if (tileId == 0) {
		            System.out.print("0 ");
		        } else {
		            System.out.print("1 ");
		        }
		    }
		    System.out.println();
		}
		System.out.println();

	}
	
	
}

