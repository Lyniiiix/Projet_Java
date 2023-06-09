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
	private Laser laser1;
	private Laser laser2;
	private Laser laser3;
	private Laser laser4;
	
	
	// permet de savoir si le niveau est réussi
	private static boolean reussi = false;
	
	public static boolean getReussi() {
		return reussi;
	}
	
		

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		porteSortie = new Image("res/images/porte fermee.png");
		joueur = new Personnage(30, 1);
		laser1 = new Laser(5*32, 7*36);
		laser2 = new Laser(17*32, 4*36);
		laser3 = new Laser(10*32, 15*36);
		laser4 = new Laser(20*32, 14*36);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.drawImage(porteSortie, 32, 684, 32+3*32, 684+3*36, 0,0, porteSortie.getWidth(), porteSortie.getHeight());
		laser1.horizontal(g);
		laser2.horizontal(g);
		laser3.horizontal(g);
		laser4.vertical(g);
		
		joueur.dessiner(g);
		
		g.drawString("u",100 , 100);  // 3 eme lettre de l easter egg
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
			//joueur.deplacerInverse(gc, CollisionY);
		}
		if(mvt.isKeyDown(Input.KEY_LEFT)) {
			//joueur.deplacerInverse(gc, CollisionY);
		}
		
		
		
		laser1.setTimer(delta);
		laser2.setTimer(delta);
		laser3.setTimer(delta);
		laser4.setTimer(delta);
		
		
		/* --------   HORIZONTAL   ---------*/
		// si le perso touche le laser 1 qd actif
		if(laser1.getActif() && joueur.getPosX_px()+32>=laser1.getX()+20 && joueur.getPosX_px()<=laser1.getX()+70 && joueur.getPosY_px()+36>=laser1.getY()+5 && joueur.getPosY_px()<=laser1.getY()+15)
			sbg.enterState(404);
		
		
		// si le perso touche le laser 2 qd actif
		else if(laser2.getActif() && joueur.getPosX_px()+32>=laser2.getX()+20 && joueur.getPosX_px()<=laser2.getX()+70 && joueur.getPosY_px()+36>=laser2.getY()+5 && joueur.getPosY_px()<=laser2.getY()+15)
			sbg.enterState(404);
				
				
		// si le perso touche le laser 3 qd actif
		else if(laser3.getActif() && joueur.getPosX_px()+32>=laser3.getX()+20 && joueur.getPosX_px()<=laser3.getX()+70 && joueur.getPosY_px()+36>=laser3.getY()+5 && joueur.getPosY_px()<=laser3.getY()+15)
			sbg.enterState(404);
		
		
		
		/* --------   VERTICAL   ---------*/
		
		// si le perso touche le laser 4 qd actif
		else if(laser4.getActif() && joueur.getPosX_px()+32>=laser4.getX() && joueur.getPosX_px()<=laser4.getX()+20 && joueur.getPosY_px()+36>=laser4.getY()+20 && joueur.getPosY_px()<=laser4.getY()+70)
			sbg.enterState(404);

		
		
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
