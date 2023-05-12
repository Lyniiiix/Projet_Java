// initialise une Map
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.tiled.TiledMap;

public class Map {
	
	private String nom;
	
	private Image image_fond;

	private TiledMap map;
	
	private int[][] matrice_map = new int[23][32];	
	
	private Objet[] objetsMap;
	
	
	public Map(GameContainer gc, Image image_fond, TiledMap map){
		this.image_fond = image_fond;
		this.map = map;
		this.genererMatrice(); // 0 vide / 1 bloc bloquant
		this.genererObjets();
	}
	
	
	public int[][] getMatrice()
	{
		return matrice_map;
	}
	public TiledMap getMapTMX()
	{
		return map;
	}
	public Image getFond()
	{
		return image_fond;
	}
	public Objet[] getObjets()
	{
		return objetsMap;
	}
	
	public void genererMatrice()
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
	
	public void genererObjets() {
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
	                objetsMap[count] = new Objet("bloc", x * 32, y * 36);
	                count++;
	            }
	        }
	    }
	}

	
	
	public void toArray()
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

