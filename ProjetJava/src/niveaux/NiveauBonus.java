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
	
	private ArrayList<Bulles>  bullesGauche = new ArrayList<Bulles>();
	private ArrayList<Bulles> bullesDroite = new ArrayList<Bulles>();

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		timer = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if(timer<1000) {
			g.drawString("TIME FOR A MULTIPLAYER GAME", 390, 50);
			
			g.drawString("L'écran est séparé en deux parties distinctes", 310, 280);
			g.drawString("Chacun d'entre vous aura des touches attribuées", 310, 350);
			g.drawString("Bonne chance, que le meilleur gagne", 370, 600);
		}
		
		
		if(timer>=1000) {
			// dessiner le terrain
			g.setColor(Color.darkGray);
			g.fillRect(0, 0, 1024/2, 720);
			
			g.setColor(Color.gray);
			g.fillRect(1024/2, 0, 1024, 720);
			g.setColor(Color.white);
			
			
			g.drawLine(1024/6, 0, 1024/6, 820);
			g.drawString("Q", 1024/12 - 5, 828-(828-720)/2 - 5);
			
			g.drawLine(2*1024/6, 0, 2*1024/6, 820);
			g.drawString("Z", 1024/6 + 1024/12 - 5, 828-(828-720)/2 - 5);
			
			g.setColor(Color.magenta);
			g.drawLine(3*1024/6, 0, 3*1024/6, 820);
			g.setColor(Color.white);
			g.drawString("D", 1024/3 + 1024/12 - 5, 828-(828-720)/2 - 5);
			
			g.drawLine(4*1024/6, 0, 4*1024/6, 820);
			g.drawString("J", 2*1024/3 - 1024/12 - 5, 828-(828-720)/2 - 5);
			
			g.drawLine(5*1024/6, 0, 5*1024/6, 820);
			g.drawString("I", 2*1024/3 + 1024/12 - 5, 828-(828-720)/2 - 5);
			
			g.drawString("L", 1024 - 1024/12 - 5, 828-(828-720)/2 - 5);
			
			
			
			g.drawLine(0, 660, 1020, 660);
			g.drawLine(0, 720, 1020, 720);
			
			
			
			// dessiner les bulles
			for(int i=0; i<bullesGauche.size(); i++) {
				bullesGauche.get(i).dessiner(g);
			}
			for(int i=0; i<bullesDroite.size(); i++) {
				bullesDroite.get(i).dessiner(g);
			}
			
			
			
			if(timer<=2000)
				g.drawString("3", 500, 350);
			if(timer>2000 && timer<=4000)
				g.drawString("2", 500, 350);
			if(timer>4000 && timer<=6000)
				g.drawString("1", 500, 350);
			// Image géante 1, 2, 3 pour le compte a rebours
		}
		
		
			
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer += delta;
		
		if(timer>=1000+6000) {
			int random = (int)(Math.random()*3);
			bullesDroite.get(random).deplacer(delta);
			bullesGauche.get(random).deplacer(delta);
		}
		
		
		
		Input mvt = gc.getInput();
		
		
		// creer les bulles ecran gauche
		if(timer>=1000 && bullesGauche.size()<3) {
			for(int i=0; i<3; i++) {
				if(i==0)
					bullesGauche.add(new Bulles(1024/12 - 20));
				if(i==1)
					bullesGauche.add(new Bulles(1024/12 + 1024/6 - 20));
				if(i==2)
					bullesGauche.add(new Bulles(1024/12 + 1024/3 - 20));
			}
				
		}
		
		// creer les bulles ecran droite
		if(timer>=1000 && bullesDroite.size()<3) {
			for(int j=0; j<3; j++)
				if(j==0)
					bullesDroite.add(new Bulles(2*1024/3 - 1024/12 - 20));
				else if(j==1)
					bullesDroite.add(new Bulles(2*1024/3 + 1024/12 - 20));
				else if(j==2)
					bullesDroite.add(new Bulles(1024 - 1024/12 - 20));
		}
		
		
		
		// enlever les bulles si pas touchees
		/*for(int i=0; i<3; i++) {
			if(bullesGauche.get(i).getY() >= 600)
				bullesGauche.remove(i);
			if(bullesDroite.get(i).getY() >= 600)
				bullesDroite.remove(i);
		}
		
		if(bullesGauche.get(0).getY() >= 600)
			bullesGauche.remove(0);
		if(bullesGauche.get(1).getY() >= 600)
			bullesGauche.remove(1);
		if(bullesGauche.get(2).getY() >= 600)
			bullesGauche.remove(2);
		
		if(bullesDroite.get(0).getY() >= 600)
			bullesDroite.remove(0);
		if(bullesDroite.get(1).getY() >= 600)
			bullesDroite.remove(1);
		if(bullesDroite.get(2).getY() >= 600)
			bullesDroite.remove(2);
		*/
		
		
		
		// enlever bulles si touchees
		if (mvt.isKeyPressed(Input.KEY_Q)) {
			if(bullesGauche.get(0).testCollision(gc))
				bullesGauche.remove(0);
		}
		if (mvt.isKeyPressed(Input.KEY_Z)) {
			if(bullesGauche.get(1).testCollision(gc))
				bullesGauche.remove(1);
		}
		if (mvt.isKeyPressed(Input.KEY_D)) {
			if(bullesGauche.get(2).testCollision(gc))
				bullesGauche.remove(2);
		}
		if (mvt.isKeyPressed(Input.KEY_J)) {
			if(bullesDroite.get(0).testCollision(gc))
				bullesDroite.remove(0);
		}
		if (mvt.isKeyPressed(Input.KEY_I)) {
			if(bullesDroite.get(1).testCollision(gc))
				bullesDroite.remove(1);
		}
		if (mvt.isKeyPressed(Input.KEY_L)) {
			if(bullesDroite.get(2).testCollision(gc))
				bullesDroite.remove(2);
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
