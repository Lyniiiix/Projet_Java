package niveaux;

import main.*;
import objets.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Niveau3 extends BasicGameState {   
	private Personnage joueur ;
	private Image porteSortie;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		porteSortie = new Image("res/images/porte fermee.png");
		joueur = new Personnage(30, 1);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.drawImage(porteSortie, 800, 20, 800+3*36, 20+3*32, 0,0, porteSortie.getWidth(), porteSortie.getHeight());
		joueur.dessiner(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		joueur.sauterInversee(delta);
		joueur.graviteInversee(delta);
		
		
		
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
		// TODO Auto-generated method stub
		return 3;
	}

}
