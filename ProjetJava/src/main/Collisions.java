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

	public boolean autoriserDeplacementX(float new_x, float new_y)
	{
		for (Objet objet : objetsMap)
		{
			
			if (objet.getx2() >= (new_x-32) || objet.getx1() <= (new_x+64) ||
				objet.gety2() > (new_y-36) || objet.gety1() < (new_y+36)
				)
			{
				return false;
			}
		}
		return true;
	}


}

