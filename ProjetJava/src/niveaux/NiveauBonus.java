package niveaux;

import objets.*;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class NiveauBonus extends BasicGameState {
	private int timer; // en ms
	private int compteurGauche;
	private int compteurDroite;
	
	private ArrayList <Bulles> bullesGauche1 = new ArrayList<Bulles>();
	private ArrayList <Bulles> bullesGauche2 = new ArrayList<Bulles>();
	private ArrayList <Bulles> bullesGauche3 = new ArrayList<Bulles>();
	private ArrayList <Bulles> bullesDroite1 = new ArrayList<Bulles>();
	private ArrayList <Bulles> bullesDroite2 = new ArrayList<Bulles>();
	private ArrayList <Bulles> bullesDroite3 = new ArrayList<Bulles>();
	
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
			g.drawString("droite: "+compteurDroite, 2*1024/3 + 1024/12 - 30 , 828-(828-720)/2 - 5);
			
			
			
			// dessiner les bulles
			for(int i=0; i<bullesGauche1.size(); i++) {
				bullesGauche1.get(i).dessiner(g);
			}
			for(int i=0; i<bullesGauche2.size(); i++) {
				bullesGauche2.get(i).dessiner(g);
			}
			for(int i=0; i<bullesGauche3.size(); i++) {
				bullesGauche3.get(i).dessiner(g);
			}
			for(int i=0; i<bullesDroite1.size(); i++) {
				bullesDroite1.get(i).dessiner(g);
			}
			for(int i=0; i<bullesDroite2.size(); i++) {
				bullesDroite2.get(i).dessiner(g);
			}
			for(int i=0; i<bullesDroite3.size(); i++) {
				bullesDroite3.get(i).dessiner(g);
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
			for(int i=0; i<bullesGauche1.size(); i++) {
				bullesGauche1.get(i).deplacer(delta);
			}
			for(int i=0; i<bullesGauche2.size(); i++) {
				bullesGauche2.get(i).deplacer(delta);
			}
			for(int i=0; i<bullesGauche3.size(); i++) {
				bullesGauche3.get(i).deplacer(delta);
			}
			
			for(int i=0; i<bullesDroite1.size(); i++) {
				bullesDroite1.get(i).deplacer(delta);
			}
			for(int i=0; i<bullesDroite2.size(); i++) {
				bullesDroite2.get(i).deplacer(delta);
			}
			
			for(int i=0; i<bullesDroite3.size(); i++) {
				bullesDroite3.get(i).deplacer(delta);
			}
		}
		
		
		
		// accelerer les bulles au bout d un certain temps
		if(timer >= 24000) {
			for(int i=0; i<bullesDroite1.size(); i++) {
				bullesDroite1.get(i).setVy(bullesDroite1.get(i).getVy()+50);
				bullesGauche1.get(i).setVy(bullesGauche1.get(i).getVy()+50);
			}
			for(int i=0; i<bullesDroite2.size(); i++) {
				bullesDroite2.get(i).setVy(bullesDroite2.get(i).getVy()+50);
				bullesGauche2.get(i).setVy(bullesGauche2.get(i).getVy()+50);
			}
			for(int i=0; i<bullesDroite3.size(); i++) {
				bullesDroite3.get(i).setVy(bullesDroite3.get(i).getVy()+50);
				bullesGauche3.get(i).setVy(bullesGauche3.get(i).getVy()+50);
			}
			timer = 14000;
		}
		
		
		
		Input mvt = gc.getInput();
		
		
		// creer les bulles ecran gauche et droite
		if(timer>=8000 && bullesGauche1.size()<10 && bullesGauche2.size()<10 && bullesGauche3.size()<10) {
			for(int i=0; i<30; i++) {
				int random = (int)(Math.random()*3);
				
				if(random==0) {
					bullesGauche1.add(new Bulles(1024/12 - 20 , -50*i));
					bullesDroite1.add(new Bulles(2*1024/3 - 1024/12 - 20 ,-50*i));
				}
				if(random==1) {
					bullesGauche2.add(new Bulles(1024/12 + 1024/6 - 20 , -50*i));
					bullesDroite2.add(new Bulles(2*1024/3 + 1024/12 - 20 ,-50*i));
				}
				if(random==2) {
					bullesGauche3.add(new Bulles(1024/12 + 1024/3 - 20 , -50*i));
					bullesDroite3.add(new Bulles(1024 - 1024/12 - 20 ,-50*i));
				}
			}						
		}
		
		
		
		// enlever les bulles gauche si pas touchees
		for(int i = 0; i < bullesGauche1.size(); i++) {
			if(bullesGauche1.get(i).horsEcran()) {
				compteurGauche--;
				bullesGauche1.remove(i);
				i--;
			}
		}
		for(int i = 0; i < bullesGauche2.size(); i++) {
			if(bullesGauche2.get(i).horsEcran()) {
				compteurGauche--;
				bullesGauche2.remove(i);
				i--;
			}
		}
		for(int i = 0; i < bullesGauche3.size(); i++) {
			if(bullesGauche3.get(i).horsEcran()) {
				compteurGauche--;
				bullesGauche3.remove(i);
				i--;
			}
		}
		
		
		 
		// enlever les bulles droite si pas touchees
		for(int i = 0; i < bullesDroite1.size(); i++) {
			if(bullesDroite1.get(i).horsEcran()) {
				compteurDroite--;
				bullesDroite1.remove(i);
				i--;
			}
		}
		for(int i = 0; i < bullesDroite2.size(); i++) {
			if(bullesDroite2.get(i).horsEcran()) {
				compteurDroite--;
				bullesDroite2.remove(i);
				i--;
			}
		}
		for(int i = 0; i < bullesDroite3.size(); i++) {
			if(bullesDroite3.get(i).horsEcran()) {
				compteurDroite--;
				bullesDroite3.remove(i);
				i--;
			}
		}
		
		
		
		
		// enlever bulles si touchees
		if (mvt.isKeyPressed(Input.KEY_Q) && timer>=8000) {
			appuyerQ = true;
			for(int i = 0; i < bullesGauche1.size(); i++) {
				if(bullesGauche1.get(i).testCollision()) {
					bullesGauche1.remove(i);
					compteurGauche++;
					i--;
				}
			}
		}
		if (mvt.isKeyPressed(Input.KEY_Z) && timer>=8000) {
			appuyerZ = true;
			for(int i = 0; i < bullesGauche2.size(); i++) {
				if(bullesGauche2.get(i).testCollision()) {
					bullesGauche2.remove(i);
					compteurGauche++;
					i--;
				}
			}
		}
		if (mvt.isKeyPressed(Input.KEY_D) && timer>=8000) {
			appuyerD = true;
			for(int i = 0; i < bullesGauche3.size(); i++) {
				if(bullesGauche3.get(i).testCollision()) {
					bullesGauche3.remove(i);
					compteurGauche++;
					i--;
				}
			}
		}
		if (mvt.isKeyPressed(Input.KEY_J) && timer>=8000) {
			appuyerJ = true;
			for(int i = 0; i < bullesDroite1.size(); i++) {
				if(bullesDroite1.get(i).testCollision()) {
					bullesDroite1.remove(i);
					compteurDroite++;
				}
			}
		}
		if (mvt.isKeyPressed(Input.KEY_I) && timer>=8000) {
			appuyerI = true;
			for(int i = 0; i < bullesDroite2.size(); i++) {
				if(bullesDroite2.get(i).testCollision()) {
					bullesDroite2.remove(i);
					compteurDroite++;
				}
			}
		}
		if (mvt.isKeyPressed(Input.KEY_L) && timer>=8000) {
			appuyerL = true;
			for(int i = 0; i < bullesDroite3.size(); i++) {
				if(bullesDroite3.get(i).testCollision()) {
					bullesDroite3.remove(i);
					compteurDroite++;
				}
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
