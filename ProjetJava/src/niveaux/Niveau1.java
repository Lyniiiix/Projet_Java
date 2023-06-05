package niveaux;

import org.newdawn.slick.Color;

import java.io.IOException;

import javax.sound.sampled.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
//import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import objets.*;
import main.*;

public class Niveau1 extends BasicGameState {    // niveau foret
	
	private Image image_fond; // image fond map
	private Image porteSortie; // porte de fin du niveau
	
	private TiledMap map; // schéma TMX map
	
	public Map mapN1; // instance d'une map
	
	//private Sound son;
	
	
	private Personnage joueur;
	private Chasseur chasseur1;
	private Chasseur chasseur2;
	private Chasseur chasseur3;
	
	
	private int timer = 0;
	
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



	private Collisions collisionsN1; // instance Collisions
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		joueur = new Personnage(); 
		chasseur1 = new Chasseur(4*32, 15*36);
		chasseur2 = new Chasseur(20*32, 6*36);
		chasseur3 = new Chasseur(14*32, 14*36);

		
		porteSortie = new Image("res/images/porte fermee.png");
		image_fond = new Image("res/niveau1/n1_fond.png");
		map = new TiledMap("res/niveau1/niveau1.tmx");
		
		mapN1 = new Map(gc, image_fond, map);
		
		
		// parcour les objets bloquant de la map
		for (int i = 0; i < mapN1.getObjets().length; i++)
			System.out.println( mapN1.getObjets()[i] );
		
		//System.out.println(joueur.getPosX_px() + " " + joueur.getPosY_px());

		
		collisionsN1 = new Collisions(mapN1);

		
		mapN1.toArray();
	}
	
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(image_fond, 0, 0); // dessiner image_fond
		map.render(0, 0); // dessiner la map a partir du .tmx correspondant
		
		g.drawImage(porteSortie, 896, 36, 896+3*32, 36+3*36, 0,0, porteSortie.getWidth(), porteSortie.getHeight());
		
		chasseur1.dessinerBalles(g);
		chasseur1.dessiner(g);
		
		chasseur2.dessinerBalles(g);
		chasseur2.dessiner(g);
		
		chasseur3.dessinerBalles(g);
		chasseur3.dessiner(g);
		
		joueur.dessiner(g);
		
		
		g.drawString(timer/1000f+" s", 100, 100);
		
		g.drawString("c", 1000, 810);  // 1 ere lettre de l easter egg 
		
	}
	
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		
		// chasseur 1 tirer
		if(joueur.getX()*32 >= chasseur1.getX() && joueur.getX()*32 <= chasseur1.getX() + chasseur1.getDistanceTir() +20 && joueur.getY()*36 >= chasseur1.getY() - chasseur1.getDistanceTir() && joueur.getY()*36 <= chasseur1.getY() + chasseur1.getDistanceTir() +20) {
			chasseur1.setTimer(delta);
			chasseur1.tirer();
		}
		else
			chasseur1.tirer();  
			
			
			
		// chasseur 2 tirer
		if(joueur.getX()*32 >= chasseur2.getX() && joueur.getX()*32 <= chasseur2.getX() + chasseur2.getDistanceTir() +20 && joueur.getY()*36 >= chasseur2.getY() - chasseur2.getDistanceTir() && joueur.getY()*36 <= chasseur2.getY() + chasseur2.getDistanceTir() +20) {
			chasseur2.setTimer(delta);
			chasseur2.tirer();
		}
		else
			chasseur2.tirer();  
			
			
			
		// chasseur 3 tirer
		if(joueur.getX()*32 >= chasseur3.getX() && joueur.getX()*32 <= chasseur3.getX() + chasseur3.getDistanceTir() +20 && joueur.getY()*36 >= chasseur3.getY() - chasseur3.getDistanceTir() && joueur.getY()*36 <= chasseur3.getY() + chasseur3.getDistanceTir() +20) {
			chasseur3.setTimer(delta);
			chasseur3.tirer();
		}
		else
			chasseur3.tirer();  
			
			
			
			
		boolean collisionY = true;//collisionsN1.autoriserGraviteY(joueur.getPosX_px(), joueur.getPosY_px());
		
		joueur.sauter(delta);
		joueur.gravite(delta, collisionY);
		
		// chasseur 1 mort
		if(chasseur1.getXBalle1()+20>=joueur.getPosX_px() && chasseur1.getXBalle1()<=(joueur.getPosX_px()+32) && chasseur1.getYBalle1()+20>=joueur.getPosY_px() && chasseur1.getYBalle1()<=joueur.getPosY_px()+36 ||
				chasseur1.getXBalle2()+20>=joueur.getPosX_px() && chasseur1.getXBalle2()<=(joueur.getPosX_px()+32) && chasseur1.getYBalle2()+20>=joueur.getPosY_px() && chasseur1.getYBalle2()<=joueur.getPosY_px()+36 ||
				chasseur1.getXBalle3()+20>=joueur.getPosX_px() && chasseur1.getXBalle3()<=(joueur.getPosX_px()+32) && chasseur1.getYBalle3()+20>=joueur.getPosY_px() && chasseur1.getYBalle3()<=joueur.getPosY_px()+36 ) {
			mort=true;
			//Personnage.niveauMort(sbg);
			sbg.enterState(404);
		}
			
		
		// chasseur 2 mort
		if(chasseur2.getXBalle1()+20>=joueur.getPosX_px() && chasseur2.getXBalle1()<=(joueur.getPosX_px()+32) && chasseur2.getYBalle1()+20>=joueur.getPosY_px() && chasseur2.getYBalle1()<=joueur.getPosY_px()+36 ||
				chasseur2.getXBalle2()+20>=joueur.getPosX_px() && chasseur2.getXBalle2()<=(joueur.getPosX_px()+32) && chasseur2.getYBalle2()+20>=joueur.getPosY_px() && chasseur2.getYBalle2()<=joueur.getPosY_px()+36 ||
				chasseur2.getXBalle3()+20>=joueur.getPosX_px() && chasseur2.getXBalle3()<=(joueur.getPosX_px()+32) && chasseur2.getYBalle3()+20>=joueur.getPosY_px() && chasseur2.getYBalle3()<=joueur.getPosY_px()+36 ) {
			mort=true;
			//Personnage.niveauMort(sbg);
			sbg.enterState(404);
		}
		
		
		// chasseur 3 mort
		if(chasseur3.getXBalle1()+20>=joueur.getPosX_px() && chasseur3.getXBalle1()<=(joueur.getPosX_px()+32) && chasseur3.getYBalle1()+20>=joueur.getPosY_px() && chasseur3.getYBalle1()<=joueur.getPosY_px()+36 ||
				chasseur3.getXBalle2()+20>=joueur.getPosX_px() && chasseur3.getXBalle2()<=(joueur.getPosX_px()+32) && chasseur3.getYBalle2()+20>=joueur.getPosY_px() && chasseur3.getYBalle2()<=joueur.getPosY_px()+36 ||
				chasseur3.getXBalle3()+20>=joueur.getPosX_px() && chasseur3.getXBalle3()<=(joueur.getPosX_px()+32) && chasseur3.getYBalle3()+20>=joueur.getPosY_px() && chasseur3.getYBalle3()<=joueur.getPosY_px()+36 ) {
			mort=true;
			//Personnage.niveauMort(sbg);
			sbg.enterState(404);
		}
		
		
		/*!!!!! COLLISION !!!!!*/
		
		int posX = (int)(Math.floor(joueur.getX())); // posX en index X de matrice
		int posY = (int)(Math.floor(joueur.getY())); // posY en index Y de matrice
		//System.out.println(String.format("Coord. perso : posXpx : %s , poxYpx : %s",joueur.getPosX_px(),joueur.getPosY_px()));
		//System.out.println(String.format("Coord. perso : posX : %s , poxY : %s",joueur.getX(),joueur.getY()));
		
		
		boolean collisionX = true;//collisionsN1.autoriserDeplacementX(joueur.getPosX_px(), joueur.getPosY_px());
		
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_SPACE)) {
			joueur.sautNormal(gc);
		}
		if(mvt.isKeyDown(Input.KEY_RIGHT)) {
			joueur.deplacer(gc,collisionX);
		}
		if(mvt.isKeyDown(Input.KEY_LEFT)) {
			joueur.deplacer(gc,collisionX);
		}
		
		
		// Permet de retourner au Launcher (pour plus de rapidite)
		if(mvt.isKeyPressed(Input.KEY_ENTER)) {
			//System.out.println(son.getClass());
			//son.play(son.length()); // marche pas g tout tester 
			sbg.enterState(0);
		}
		
		
		
		
		// timer
		timer = timer + delta;
		// PERMET DE REINITIALISER LE NIVEAU AU BOUT D UN CERTAIN TEMPS
		if(timer >= 300000) { //augmenter pour test
			timer = 0;
			//son.stop(); // marche pas
			
			mort = true;
			sbg.enterState(404);
		}
		
		
		
		// si le perso atteint la porte de sortie
		if(joueur.getPosX_px()>=896 && joueur.getPosX_px()<=896+3*32 && joueur.getPosY_px()>=36 && joueur.getPosY_px()<=36+3*36) {
			reussi=true;
			sbg.enterState(0);
		}
		
		
		// refait spawn le perso au début et pas la ou il était
		if(mort) {
			mort = false;
			joueur = new Personnage();
		}
	}
 	


	@Override
	public int getID() {
		return 1;
	}

}
