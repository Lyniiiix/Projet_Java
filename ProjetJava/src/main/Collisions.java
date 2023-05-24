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
	
	/*
	public void afficherObjetsLigne(int posY) {
	    for (Objet objet : objetsMap) {
	        if (objet.gety1() < posY && posY < objet.gety2()) {
	            return true;
	        }
	    }
	}
	*/
	
	public boolean autoriserDeplacementX(float new_x, float new_y) {
	    for (Objet objet : objetsMap) {
	        if (new_y >= objet.gety1() && new_y <= objet.gety2()) {
	            if (new_x <= objet.getx1() && (new_x + Constantes.LARGEUR_PERSO) >= objet.getx1()) {
	                return false; // Collision à gauche de l'objet
	            }
	            if (new_x >= objet.getx2() && new_x <= (objet.getx2() + Constantes.LARGEUR_PERSO)) {
	                return false; // Collision à droite de l'objet
	            }
	        }
	    }
	    return true; // Aucune collision
	}



}

