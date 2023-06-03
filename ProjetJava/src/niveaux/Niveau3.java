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
	private Laser laser;
	
	
	// permet de savoir si le niveau est réussi
	private static boolean reussi = false;
	
	public static boolean getReussi() {
		return reussi;
	}
	
		

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		porteSortie = new Image("res/images/porte fermee.png");
		joueur = new Personnage(30, 1);
		laser = new Laser();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.drawImage(porteSortie, 32, 684, 32+3*32, 684+3*36, 0,0, porteSortie.getWidth(), porteSortie.getHeight());
		laser.dessiner(g);
		joueur.dessiner(g);
		
		g.drawString("u",100 , 100);  // 3 eme lettre de l easter egg
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		joueur.sauterInversee(delta);
		joueur.graviteInversee(delta);
		
		laser.setTimer(delta);
		
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
		if(joueur.getPosX_px()>=32 && joueur.getPosX_px()<=32+3*32 && joueur.getPosY_px()>=684 && joueur.getPosY_px()<=684+3*36) {
			reussi = true;
			sbg.enterState(0);
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
