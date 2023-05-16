package main;

import org.newdawn.slick.tiled.TiledMap;

import objets.*;
import niveaux.*;

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
	
	public boolean enCollisionX(float posX, float posY) {
	    
	    for (Objet objet : objetsMap) {
	    	/*
	    	System.out.println("posX : " + posX + " , " + "posY : " + posY);
		    System.out.println(objet);
		    System.out.println();*/
		   
		    
	        if (posY < objet.gety1() + Constantes.HAUTEUR_CASE ||
	        	posY + Constantes.HAUTEUR_PERSO > objet.gety1())
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
	*/

