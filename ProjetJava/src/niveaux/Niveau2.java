package niveaux;

import objets.*;
import main.*;

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
	private Personnage ombre;
	private int timer;
	
	private TiledMap map; // schéma TMX map
	
	public Map mapN2; // instance d'une map
	
	private Collisions collisionsN2; // instance Collisions
	
	
	// permet de savoir si le niveau est réussi
	private static boolean reussi = false;
	
	public static boolean getReussi() {
		return reussi;
	}

	
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		image_fond = new Image("res/niveau2/n2_fond.png");
		
		porteSortie = new Image("res/images/porte fermee.png");
		joueur = new Personnage();
		ombre = new Personnage();
		timer = 0;
		
		map = new TiledMap("res/niveau2/niveau2.tmx");
		mapN2 = new Map(gc, image_fond, map);
		
		collisionsN2 = new Collisions(mapN2);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(image_fond,0,0,1024,828,0,0,image_fond.getWidth(), image_fond.getHeight());
		
		g.drawImage(porteSortie, 896, 36, 896+3*32, 36+3*36, 0,0, porteSortie.getWidth(), porteSortie.getHeight());
		
		map.render(0, 0); // dessiner la map a partir du .tmx correspondant 
		
		joueur.dessiner(g);

		// UN 2 EME PERSO SPAWN AU BOUT DE 2S 
		if(timer>=2000) 
			ombre.dessiner(g);
		
		g.drawString("o", 90, 100);  // 2 eme lettre de l easter egg
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		joueur.sauter(delta);
		
		boolean CollisionY = true;
		
		joueur.gravite(delta, CollisionY);
		

		timer+=delta;
		if(timer>=2000) {
			Personnage.suivre(ombre, joueur);
			/*ombre.sauter(delta);
			ombre.gravite(delta);*/
		}
		
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
		return 2;
	}

}
