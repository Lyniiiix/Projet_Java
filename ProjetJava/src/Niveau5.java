import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Niveau5 extends BasicGameState {
	private Personnage joueur;
	private int timer;
	
	/*private Pieges laser;
	private Pieges chasseur = new Chasseur();*/
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		joueur = new Personnage();
		timer =0;
		//laser = new Laser(10,50);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		joueur.dessiner(g);
		
		/*
		laser.dessiner(g);
		chasseur.dessinerBalles(g);
		chasseur.dessiner(g);
		*/
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer += delta;		
		
		joueur.sauter(delta);
		joueur.gravite(delta);
		
		/*
		laser.setTimer(delta);
		
		if(joueur.getX() >= chasseur.getX() - chasseur.getDistanceTir()+10 && joueur.getX() <= chasseur.getX() + chasseur.getDistanceTir()+10 && joueur.getY() >= chasseur.getY() - chasseur.getDistanceTir()+10 && joueur.getY() <= chasseur.getY() + chasseur.getDistanceTir()+10) {
			chasseur.setTimer(delta);
			chasseur.tirer();
		}
		*/
		
		
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
		return 5;
	}

}
