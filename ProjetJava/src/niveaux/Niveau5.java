package niveaux;

import main.*;
import objets.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;


public class Niveau5 extends BasicGameState {   // niveau plage
	private Image image_fond;
	private TiledMap map;
	private Map mapN5;
	
	private Image porteSortie;
	
	private Personnage joueur;
	private int timer;
	
	
	private Mer niveauMer;
	
	
	// permet de savoir si le niveau est réussi
	private static boolean reussi = false;
	
	public static boolean getReussi() {
		return reussi;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		image_fond = new Image("res/niveau5/n5_fond.png");
		map = new TiledMap("res/niveau5/niveau5.tmx");
		mapN5 = new Map(gc,image_fond,map);
		
		porteSortie = new Image("res/images/porte fermee.png");
		
		joueur = new Personnage(mapN5);
		
		niveauMer = new Mer(45);
		
		timer =0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(image_fond,0,0);
		map.render(0, 0); // dessiner la map a partir du .tmx correspondant 
		
		g.drawImage(porteSortie, 896, 36, 896+3*32, 36+3*36, 0,0, porteSortie.getWidth(), porteSortie.getHeight());
		
		joueur.dessiner(g);
		
		
		g.drawString("o",450 , 600);// lettre pour l easter egg
		
		
		// dessiner l eau qui monte (on a pas d image)
		niveauMer.draw(g);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer += delta;		
		
		joueur.sauter(delta);
		
		boolean CollisionY = true;
		
		joueur.gravite(delta,CollisionY);

		
		
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_SPACE) ) {
			joueur.sautNormal(gc);
		}
		if(mvt.isKeyDown(Input.KEY_RIGHT)) {
			joueur.deplacer(gc, CollisionY);
		}
		if(mvt.isKeyDown(Input.KEY_LEFT)) {
			joueur.deplacer(gc, CollisionY);
		}
		
		
		
		// faire monter le niveau de l eau
		if(timer>=2000 && timer<17000) 
		{
			niveauMer.monter(delta);
		}
		
		
		// faire mourir le perso si mer arrive au dessus de sa tete
		if(niveauMer.getNiveauEau()<=joueur.getPosY_px()) 
		{
			sbg.enterState(404);
		}
			
		
		
		// si le perso atteint la porte de sortie
		if(joueur.getPosX_px()>=896 && joueur.getPosX_px()<=896+3*32 && joueur.getPosY_px()>=36 && joueur.getPosY_px()<=36+3*36) {
			reussi = true;
			sbg.enterState(0);
		}
		
		
		// Permet de retourner au Launcher (pour plus de rapidite)
		if(mvt.isKeyPressed(Input.KEY_ENTER))
			sbg.enterState(0);
	}

	
	@Override
	public int getID() {
		return 5;
	}

}
