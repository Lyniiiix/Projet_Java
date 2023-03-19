import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Niveau1 extends BasicGameState {
	
	private Image img;
	
	private Personnage joueur ;
	private int timer = 0;
	
	
	private int mapWidth = 14;
	private int mapHeight = 10;
	private Map mapNiveau1;
	
	private int[][] map;

	public Niveau1() {
		// INIT LA MAP 0=VIDE
		map = new int[mapHeight][mapWidth];

		for (int i = 0; i < mapHeight; i++) {
		    for (int j = 0; j < mapWidth; j++) {
		        map[i][j] = 0;
		    }
		}
	}

	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		joueur = new Personnage(gc);
		img = new Image("images/bloc73.png");
		mapNiveau1 = new Map(gc,img);

	}

	
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		joueur.dessiner(g);
		g.drawString(timer/1000f+" s", 100, 100);
		mapNiveau1.dessiner(g,map);
	}

	
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		joueur.bouger(delta);
		joueur.gravite(delta);
		
		
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_SPACE) ) {
			joueur.deplacer(gc,delta);
		}
		if(mvt.isKeyDown(Input.KEY_RIGHT)) {
			joueur.deplacer(gc,delta);
		}
		if(mvt.isKeyDown(Input.KEY_LEFT)) {
			joueur.deplacer(gc,delta);
		}
		
		timer = timer+delta;
		
		
		// Permet de retourner au Launcher (pour plus de rapidite)
		if(mvt.isKeyPressed(Input.KEY_ENTER))
			sbg.enterState(0);
	}

	@Override
	public int getID() {
		return 1;
	}

}
