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

public class Niveau2 extends BasicGameState {  // niveau chateau abandonne
	private Image image_fond;
	
	private Image porteSortie;
	private Personnage joueur ;
	private Personnage ombre;
	private int timer;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		image_fond = new Image("res/niveau2/n2_fond.png");
		
		porteSortie = new Image("res/images/porte fermee.png");
		joueur = new Personnage();
		ombre = new Personnage();
		timer = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(image_fond,0,0,1024,828,0,0,image_fond.getWidth(), image_fond.getHeight());
		
		g.drawImage(porteSortie, 800, 20, 800+3*36, 20+3*32, 0,0, porteSortie.getWidth(), porteSortie.getHeight());
		
		joueur.dessiner(g);

		// UN 2 EME PERSO SPAWN AU BOUT DE 2S 
		if(timer>=2000) 
			ombre.dessiner(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		joueur.sauter(delta);
		joueur.gravite(delta);
		

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
		
		
		// Permet de retourner au Launcher (pour plus de rapidite)
		if(mvt.isKeyPressed(Input.KEY_ENTER))
			sbg.enterState(0);
	}

	@Override
	public int getID() {
		return 2;
	}

}
