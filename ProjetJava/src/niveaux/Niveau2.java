// NIVEAU 2

package niveaux;

import objets.*;
import main.*;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Niveau2 extends BasicGameState {  // niveau chateau abandonne
	private Image image_fond;
	
	private Image porteSortie;
	private Personnage joueur ;
	
	/*private Personnage ombre;
	private int timerOmbre;*/
	
	
	private int timer;
	private boolean nuit;
	private Eclair eclair;
	
	private TiledMap map; // schéma TMX map
	
	public Map mapN2; // instance d'une map
	
	
	
	private static boolean mort = false;  // permet si le perso est mort de refaire spawn le perso au debut et non pas la ou il est mort
	
	// permet de savoir si le perso est mort ds ce niveau
	public static boolean getMort() {
		return mort;
	}

	
	
	// permet de savoir si le niveau est réussi
	private static boolean reussi = false;
	
	public static boolean getReussi() {
		return reussi;
	}

	
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		image_fond = new Image("res/niveau2/n2_fond.png");
		
		map = new TiledMap("res/niveau2/niveau2.tmx");
		mapN2 = new Map(gc, image_fond, map);
		
		porteSortie = new Image("res/images/porte fermee.png");
		joueur = new Personnage(mapN2);
		
		/*
		ombre = new Personnage(1,18);
		timerOmbre = 0;*/
		
		timer=0;
		nuit = false;
		eclair = new Eclair(joueur.getPosX_px(), joueur.getPosY_px());
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if(!nuit) 
		{
			g.drawImage(image_fond,0,0,1024,828,0,0,image_fond.getWidth(), image_fond.getHeight());
			map.render(0, 0); // dessiner la map a partir du .tmx correspondant 
		}
		
		if(eclair.getFlash())
			eclair.dessiner(g);
		
		g.drawImage(porteSortie, 896, 36, 896+3*32, 36+3*36, 0,0, porteSortie.getWidth(), porteSortie.getHeight());
		
		
		
		joueur.dessiner(g);
		
		
		
		//      !!!!!!!!!  UN 2 EME PERSO SPAWN AU BOUT DE 2S  !!!!!!!!!!!
		/*if(timerOmbre>=2000) 
			ombre.dessiner(g);
		*/
		
		
		if(!nuit) 
			g.drawString("o", 90, 100);  // 2 eme lettre de l easter egg que qd c jour
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer+=delta;
		
		// permet de mettre un fond noir toute les 3 secondes
		if(timer>=3000 && timer<=6000)
			nuit = true;
		
		if(timer>6000)
		{
			nuit = false;
			timer = 0;
			eclair.setFlash(false);
		}
		
		
		// permet de dire ou vas frapper l eclair
		if(timer>=3000 && timer<=4000)
		{
			eclair = new Eclair(joueur.getPosX_px(), joueur.getPosY_px());
		}
		
		
		// permet de faire spawn l eclair
		if(timer>=5000 && timer<=6000)
			eclair.setFlash(true);
		
		
		joueur.sauter(delta);
		
		joueur.gravite(delta);
		
		/*
		timerOmbre+=delta;
		
		if(timerOmbre>=2000) {
			ombre = new Personnage(joueur.getX()-2, joueur.getY());
		}*/
		
		
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_SPACE) ) {
			joueur.sautNormal(gc);
		}
		if(mvt.isKeyDown(Input.KEY_RIGHT)) {
			joueur.deplacer(gc);
		}
		if(mvt.isKeyDown(Input.KEY_LEFT)) {
			joueur.deplacer(gc);
		}
		
		
		
		// si l eclair touche le perso
		if(eclair.getFlash() && joueur.getPosX_px()+32 >= eclair.getXdepart()+32/2 && joueur.getPosX_px() <= eclair.getXdepart()+32/2 && joueur.getPosY_px() <= eclair.getYarrive()+36)
		{
			mort=true;
			sbg.enterState(404);
		}
		
		
		// si le perso atteint la porte de sortie
		if(joueur.getPosX_px()>=896 && joueur.getPosX_px()<=896+3*32 && joueur.getPosY_px()>=36 && joueur.getPosY_px()<=36+3*36) {
			reussi = true;
			joueur = new Personnage(mapN2);
			sbg.enterState(0);
		}
		
		
		// refait spawn le perso au début et pas la ou il était
		if(mort) {
			mort = false;
			joueur = new Personnage(mapN2);
		}
		
		
		// Permet de retourner au Launcher (pour plus de rapidite)
		if(mvt.isKeyPressed(Input.KEY_ENTER))
			sbg.enterState(0);
	}

	@Override
	public int getID() {
		return 2;
	}

}
