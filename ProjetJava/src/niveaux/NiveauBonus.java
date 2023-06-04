package niveaux;

import objets.*;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class NiveauBonus extends BasicGameState {
	private Image porteSortie;
	private Personnage joueur ;
	private int timer; // en ms
	private int compteurGauche;
	private int compteurDroite;
	
	private ArrayList <Bulles> bullesGauche = new ArrayList<Bulles>();
	private ArrayList <Bulles> bullesDroite = new ArrayList<Bulles>();
	
	private boolean appuyerQ;
	private boolean appuyerZ;
	private boolean appuyerD;
	private boolean appuyerJ;
	private boolean appuyerI;
	private boolean appuyerL;
	

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		timer = 0;
		compteurGauche=0;
		compteurDroite=0;
		appuyerQ = false;
		appuyerZ = false;
		appuyerD = false;
		appuyerJ = false;
		appuyerI = false;
		appuyerL = false;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if(timer<8000) {
			g.drawString("TIME FOR A MULTIPLAYER GAME", 390, 50);
			
			g.drawString("L'écran est séparé en deux parties distinctes", 310, 280);
			g.drawString("Chacun d'entre vous aura des touches attribuées", 310, 340);
			g.drawString("Le premier avec 40 points gagne", 370, 400);
			g.drawString("Bonne chance, que le meilleur gagne", 370, 600);
		}
		
		
		if(timer>=8000) {
			// dessiner le terrain
			g.setColor(Color.darkGray);
			g.fillRect(0, 0, 1024/2, 720);
			
			g.setColor(Color.gray);
			g.fillRect(1024/2, 0, 1024, 720);
			g.setColor(Color.white);
			
			
			g.drawLine(1024/6, 0, 1024/6, 720);
			g.drawString("Q", 1024/12 - 5, 720-(720-640)/2 - 5);
			
			g.drawLine(2*1024/6, 0, 2*1024/6, 720);
			g.drawString("Z", 1024/6 + 1024/12 - 5, 720-(720-640)/2 - 5);
			
			g.setColor(Color.magenta);
			g.drawLine(3*1024/6, 0, 3*1024/6, 820);
			g.setColor(Color.white);
			g.drawString("D", 1024/3 + 1024/12 - 5, 720-(720-640)/2 - 5);
			
			g.drawLine(4*1024/6, 0, 4*1024/6, 720);
			g.drawString("J", 2*1024/3 - 1024/12 - 5, 720-(720-640)/2 - 5);
			
			g.drawLine(5*1024/6, 0, 5*1024/6, 720);
			g.drawString("I", 2*1024/3 + 1024/12 - 5, 720-(720-640)/2 - 5);
			
			g.drawString("L", 1024 - 1024/12 - 5, 720-(720-640)/2 - 5);
			
			
			// zone ou les bulles doivent etre eclatees
			g.drawLine(0, 580, 1020, 580);
			g.drawLine(0, 640, 1020, 640);
			
			
			// dessiner les compteurs
			g.drawString("gauche: "+compteurGauche, 1024/6 + 1024/12 - 30 , 828-(828-720)/2 - 5);
			g.drawString("droite: "+compteurGauche, 2*1024/3 + 1024/12 - 30 , 828-(828-720)/2 - 5);
			
			
			
			// dessiner les bulles
			for(int i=0; i<bullesGauche.size(); i++) {
				bullesGauche.get(i).dessiner(g);
			}
			for(int i=0; i<bullesDroite.size(); i++) {
				bullesDroite.get(i).dessiner(g);
			}
			
			
			
			// zone touche pressée
			if(appuyerQ) {
				g.setColor(Color.orange);
				g.fillRect(0, 580, 1024/6, 60);
				g.setColor(Color.white);
			}
			if(appuyerZ) {
				g.setColor(Color.orange);
				g.fillRect(1024/6, 580, 1024/6, 60);
				g.setColor(Color.white);
			}
			if(appuyerD) {
				g.setColor(Color.orange);
				g.fillRect(1024/3, 580, 1024/6, 60);
				g.setColor(Color.white);
			}
			if(appuyerJ) {
				g.setColor(Color.orange);
				g.fillRect(1024/3 + 1024/6, 580, 1024/6, 60);
				g.setColor(Color.white);
			}
			if(appuyerI) {
				g.setColor(Color.orange);
				g.fillRect(2*1024/3, 580, 1024/6, 60);
				g.setColor(Color.white);
			}
			if(appuyerL) {
				g.setColor(Color.orange);
				g.fillRect(2*1024/3 + 1024/6, 580, 1024/6, 60);
				g.setColor(Color.white);
			}
			
			
			
			if(timer<=10000)
				g.drawString("3", 500, 350);
			if(timer>10000 && timer<=12000)
				g.drawString("2", 500, 350);
			if(timer>12000 && timer<=14000)
				g.drawString("1", 500, 350);
			// Image géante 1, 2, 3 pour le compte a rebours
		}
		
		
			
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer += delta;
		
		if(timer>=14000) {
			for(int i=0; i<bullesDroite.size(); i++) {
				bullesDroite.get(i).deplacer(delta);
				bullesGauche.get(i).deplacer(delta);
			}
			
			/*
			int random = (int)(Math.random()*3);
			bullesDroite.get(random).deplacer(delta);
			bullesGauche.get(random).deplacer(delta);*/
		}
		
		
		
		Input mvt = gc.getInput();
		
		
		// creer les bulles ecran gauche et droite
		if(timer>=8000 && bullesGauche.size()<40) {
			for(int i=0; i<40; i++) {
				int random = (int)(Math.random()*3);
				
				if(random==0) {
					bullesGauche.add(new Bulles(1024/12 - 20 , -65*i));
					bullesDroite.add(new Bulles(2*1024/3 - 1024/12 - 20 ,-65*i));
				}
				if(random==1) {
					bullesGauche.add(new Bulles(1024/12 + 1024/6 - 20 , -65*i));
					bullesDroite.add(new Bulles(2*1024/3 + 1024/12 - 20 ,-65*i));
				}
				if(random==2) {
					bullesGauche.add(new Bulles(1024/12 + 1024/3 - 20 , -65*i));
					bullesDroite.add(new Bulles(1024 - 1024/12 - 20 ,-65*i));
				}
			}				
		}
		
		
		
		// enlever les bulles gauche si pas touchees
		 for(int i=0; i<bullesGauche.size(); i++) {
			if(bullesGauche.get(i).horsEcran()) {
				compteurGauche--;
				bullesGauche.remove(i);
				i--;
			}
		}
		 
		// enlever les bulles droite si pas touchees
		for(int i = 0; i < bullesDroite.size(); i++) {
			if(bullesDroite.get(i).horsEcran()) {
				compteurDroite--;
				bullesDroite.remove(i);
				i--;
			}
		}
		
		
		
		
		// enlever bulles si touchees
		if (mvt.isKeyPressed(Input.KEY_Q) && timer>=8000) {
			appuyerQ = true;
			if(bullesGauche.get(0).testCollision()) {
				bullesGauche.remove(0);
				compteurGauche++;
			}
		}
		if (mvt.isKeyPressed(Input.KEY_Z) && timer>=8000) {
			appuyerZ = true;
			if(bullesGauche.get(1).testCollision()) {
				bullesGauche.remove(1);
				compteurGauche++;
			}
		}
		if (mvt.isKeyPressed(Input.KEY_D) && timer>=8000) {
			appuyerD = true;
			if(bullesGauche.get(2).testCollision()) {
				bullesGauche.remove(2);
				compteurGauche++;
			}
		}
		if (mvt.isKeyPressed(Input.KEY_J) && timer>=8000) {
			appuyerJ = true;
			if(bullesDroite.get(0).testCollision()) {
				bullesDroite.remove(0);
				compteurDroite++;
			}
		}
		if (mvt.isKeyPressed(Input.KEY_I) && timer>=8000) {
			appuyerI = true;
			if(bullesDroite.get(1).testCollision()) {
				bullesDroite.remove(1);
				compteurDroite++;
			}
		}
		if (mvt.isKeyPressed(Input.KEY_L) && timer>=8000) {
			appuyerL = true;
			if(bullesDroite.get(2).testCollision()) {
				bullesDroite.remove(2);
				compteurDroite++;
			}
		}
		
		
		
		// Permet de retourner au Launcher (pour plus de rapidite)
		if(mvt.isKeyPressed(Input.KEY_ENTER))
			sbg.enterState(0);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 6;
	}

}
