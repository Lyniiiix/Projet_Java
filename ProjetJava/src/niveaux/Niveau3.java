// NIVEAU 3

package niveaux;

import main.*;
import objets.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Niveau3 extends BasicGameState {   
	private Image image_fond;
	private TiledMap map;
	private Map mapN3;
	
	private Personnage joueur ;
	private Image porteSortie;
	private Laser laser1;
	private Laser laser2;
	private Laser laser3;
	private Laser laser4;
	
	
	private static boolean mort = false;  // permet si le perso est mort de refaire spawn le perso au debut et non pas la ou il est mort
	
	// permet de savoir si le perso est mort ds ce niveau
	public static boolean getMort() {
		return mort;
	}

	
	// permet de savoir si le niveau est réussi
	private static boolean reussi = false;
	
	public static boolean getReussi() {
		return reussi;
	}
	
		

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		image_fond = new Image("res/niveau3/n3_fond.png");
		map = new TiledMap("res/niveau3/niveau3.tmx");
		mapN3 = new Map(gc,image_fond,map);
		
		porteSortie = new Image("res/images/porte fermee.png");
		joueur = new Personnage(mapN3);
		laser1 = new Laser(7*32, 7*36);
		laser2 = new Laser(15*32, 6*36);
		laser3 = new Laser(10*32, 13*36);
		laser4 = new Laser(23*32, 16*36);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(Color.darkGray);
		map.render(0, 0);
		
		g.drawImage(porteSortie, 896, 36, 896+3*32, 36+3*36, 0,0, porteSortie.getWidth(), porteSortie.getHeight());
		laser1.vertical(g);
		laser2.horizontal(g);
		laser3.horizontal(g);
		laser4.vertical(g);
		
		joueur.dessiner(g);
		
		g.drawString("u",100 , 100);  // 3 eme lettre de l easter egg
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		joueur.sauter(delta);
		joueur.gravite(delta);
		
		
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
		
		
		
		laser1.setTimer(delta);
		laser2.setTimer(delta);
		laser3.setTimer(delta);
		laser4.setTimer(delta);
		
		
		/* --------   HORIZONTAL   ---------*/
		
		// si le perso touche le laser 2 qd actif
		if(laser2.getActif() && joueur.getPosX_px()+32>=laser2.getX()+20 && joueur.getPosX_px()<=laser2.getX()+70 && joueur.getPosY_px()+36>=laser2.getY()+5 && joueur.getPosY_px()<=laser2.getY()+15)
		{
			mort = true;
			sbg.enterState(404);
		}
				
				
		// si le perso touche le laser 3 qd actif
		if(laser3.getActif() && joueur.getPosX_px()+32>=laser3.getX()+20 && joueur.getPosX_px()<=laser3.getX()+70 && joueur.getPosY_px()+36>=laser3.getY()+5 && joueur.getPosY_px()<=laser3.getY()+15)
		{
			mort = true;
			sbg.enterState(404);
		}
		
		
		
		/* --------   VERTICAL   ---------*/
		
		// si le perso touche le laser 1 qd actif
		if(laser1.getActif() && joueur.getPosX_px()+32>=laser1.getX() && joueur.getPosX_px()<=laser1.getX()+20 && joueur.getPosY_px()+36>=laser1.getY()+20 && joueur.getPosY_px()<=laser1.getY()+70)
		{
			mort = true;
			sbg.enterState(404);
		}
		
		// si le perso touche le laser 4 qd actif
		if(laser4.getActif() && joueur.getPosX_px()+32>=laser4.getX() && joueur.getPosX_px()<=laser4.getX()+20 && joueur.getPosY_px()+36>=laser4.getY()+20 && joueur.getPosY_px()<=laser4.getY()+70)
		{
			mort = true;
			sbg.enterState(404);
		}

		
		
		
		
		// si le perso atteint la porte de sortie
		if(joueur.getPosX_px()>=896 && joueur.getPosX_px()<=896+3*32 && joueur.getPosY_px()>=36 && joueur.getPosY_px()<=36+3*36) {
			reussi = true;
			sbg.enterState(0);
		}
		
		
		// refait spawn le perso au début et pas la ou il était
		if(mort) {
			mort = false;
			//joueur = new Personnage(mapN3);
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
