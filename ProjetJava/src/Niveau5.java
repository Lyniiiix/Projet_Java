
import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Niveau5 extends BasicGameState {   // niveau plage
	private Image image_fond;
	
	private Personnage joueur;
	private int timer;
	
	/*private Pieges laser;
	 */

	private Chasseur chasseur;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		image_fond = new Image("res/niveau5/n5_fond.png");
		
		joueur = new Personnage();
		timer =0;
		//laser = new Laser(10,50);
		chasseur = new Chasseur(250, 500);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(image_fond,0,0);
		
		joueur.dessiner(g);
		
		/*
		laser.dessiner(g);
		*/
		chasseur.dessinerBalles(g);
		chasseur.dessiner(g);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer += delta;		
		
		joueur.sauter(delta);
		joueur.gravite(delta);
		
		/*
		laser.setTimer(delta);
		*/
		if(joueur.getX()*32 >= chasseur.getX() - chasseur.getDistanceTir() && joueur.getX()*32 <= chasseur.getX() + chasseur.getDistanceTir() +20 && joueur.getY()*36 >= chasseur.getY() - chasseur.getDistanceTir() && joueur.getY()*36 <= chasseur.getY() + chasseur.getDistanceTir() +20) {
			chasseur.setTimer(delta);
			chasseur.tirer();
		}
		else
			chasseur.tirer();  // y a un pb qui fait que le chasseur arrete de tirer si j enleve le "else" et qd y a le "else" y a un pb avec le temps de tir
		
		
		
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
