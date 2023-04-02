import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Niveau1 extends BasicGameState {
	
	private Image image_fond;
	private TiledMap map;
	
	private Map mapNiveau1;
	
	private Personnage joueur;
	private int timer = 0;
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// coordonnées en bas a gauche
		int x = 1;
		int y = 18;
		
		joueur = new Personnage(x,y); // j'ai rajt un constructeur pour test ce que je faisais
		
		image_fond = new Image("res/niveau1/n1_fond.png");
		map = new TiledMap("res/niveau1/niveau1.tmx");
		mapNiveau1 = new Map(gc,image_fond,map);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(image_fond,0,0); //dessine image_fond
		map.render(0, 0); //dessiner la map a partir du .tmx correspondant
		
		joueur.dessiner(g);
		g.drawString(timer/1000f+" s", 100, 100);
	}

	
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input mvt = gc.getInput();
		
		joueur.sauter(delta);
		/*
		joueur.gravite(delta);
		*/
		
		if(mvt.isKeyDown(Input.KEY_SPACE) ) {
			joueur.sautNormal(gc);
		}
		if(mvt.isKeyDown(Input.KEY_RIGHT)) {
			joueur.deplacer(gc);
		}
		if(mvt.isKeyDown(Input.KEY_LEFT)) {
			joueur.deplacer(gc);
		}
		
		
		// Permet de retourner au Launcher (pour plus de rapidite)
		if(mvt.isKeyPressed(Input.KEY_ENTER))
			sbg.enterState(0);
		
		
		timer = timer+delta;
		// PERMET DE REINITIALISER LE NIVEAU AU BOUT D UN CERTAIN TEMPS
		if(timer>=30000) {
			timer=0;
			sbg.enterState(404);
		}
		
		
	}

	@Override
	public int getID() {
		return 1;
	}

}
