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
	

	public boolean autoriserDeplacementX(float x, float y)
	{
		for (Objet objet : objetsMap)
		{
			//System.out.println((x) + " " + y);
			if (objet.getx2() >= (x) && (x)>=objet.getx1() &&(objet.gety1() <= y) && objet.gety2() >= y+36)
			{
				System.out.println(objet);
				return false;
			}
		}
		return true;
	}
	

	public boolean autoriserGraviteY(float x, float y)
	{
		if (y < Constantes.HAUTEUR_ECRAN - Constantes.HAUTEUR_PERSO*2) {
            return true;
        }
		return false;
	}


}

