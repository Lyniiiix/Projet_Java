package niveaux;

import main.*;
import objets.*;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Niveau4 extends BasicGameState {   // niveau glace
	private Image image_fond;
	private TiledMap map;
	private Map mapN4;
	
	private int timer;
	
	
	private Image porteSortie;
	
	private Personnage joueur ;
	private OursPolaire ours;
	
	/*
	private Plateforme p1, p2, p3, p4, p5;*/
	
	private ArrayList<Stalagtite> stalagtites = new ArrayList<>();
	private int difficulte; // nmbre de stalagtites qui tombent
	
	
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
		image_fond = new Image("res/niveau4/n4_fond.png");
		
		map = new TiledMap("res/niveau4/niveau4.tmx");
		mapN4 = new Map(gc,image_fond,map);
		
		timer =0;
		
		porteSortie = new Image("res/images/porte fermee.png");
		
		joueur = new Personnage(mapN4);
		ours = new OursPolaire(5*32, 21*36);
		
		difficulte = 3;
		
		/*
		p1= new Plateforme(100, 580, 1000); // la 3 eme cordonnee c le temps pour le changement de direction en ms
		p2= new Plateforme(500, 480, 1000);
		p3= new Plateforme(350, 380, 1000);
		p4= new Plateforme(200, 280, 1000);
		p5= new Plateforme(600, 80, 1000);
		*/
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.drawImage(image_fond,0,0);
		map.render(0, 0); // dessiner la map a partir du .tmx correspondant 
		
		g.drawImage(porteSortie, 896, 36, 896+3*32, 36+3*36, 0,0, porteSortie.getWidth(), porteSortie.getHeight());
		
		ours.dessiner(g);
		
		joueur.dessiner(g);
		
		for(int i=0; i<stalagtites.size(); i++)
			stalagtites.get(i).dessin(g);
		
		/*
		p1.draw(g);
		p2.draw(g);
		p3.draw(g);
		p4.draw(g);
		p5.draw(g);
		*/
		
		g.drawString("c",505 , 50); // lettre pour l easter egg
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// permet de rajouter des stalagtites à chaque fois qu elles respawn
		if(timer == 0)
			difficulte++;
		
		if(difficulte == 9)  // permet de mettre au max 8 stalagtites
			difficulte--;
		
		timer+=delta;
		
		
		joueur.sauter(delta);
		
		boolean CollisionY = true;
		
		joueur.gravite(delta);
		
		ours.deplacer(delta);
		ours.attaquer(delta);
		
		/*
		p1.deplacement(delta);
		p2.deplacement(delta);
		p3.deplacement(delta);
		p4.deplacement(delta);
		p5.deplacement(delta);
		*/
		
		
		// creer les stalagtites
		if(stalagtites.size()<difficulte)
		{
			float random = (float)(Math.random()*(1024-32));
			stalagtites.add(new Stalagtite(random));
		}
		
		
		for(int i=0; i<stalagtites.size(); i++)
		{
			if(timer >=1000) 
			{
				stalagtites.get(i).toucherSol();
				stalagtites.get(i).deplacer(delta);
			}
		}
		
		
		// enlever stalagtites qd touchent le sol
		for(int i=0; i<stalagtites.size(); i++)
		{
			if(stalagtites.get(i).toucherSol())
			{
				timer = 0;
				stalagtites.remove(i);
				i--;
			}
		}
		
		
		// si perso et ours fight
		if(ours.getPeutAttaquer() && joueur.getPosX_px() + 32 >= ours.getX()+10-ours.getDistanceAttaque() && joueur.getPosX_px()<= ours.getX()+ours.getDistanceAttaque()+32 && joueur.getPosY_px() + 36 >=  ours.getY()-ours.getDistanceAttaque()  && joueur.getPosY_px()<= ours.getY()+36) 
		{
			mort=true;
			sbg.enterState(404);
		}
			
		
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_SPACE) ) {
			joueur.sautNormal(gc);
		}
		if(mvt.isKeyDown(Input.KEY_RIGHT)) {
			joueur.deplacer(gc, CollisionY);
		}
		if(mvt.isKeyDown(Input.KEY_LEFT)) {
			joueur.deplacer(gc, CollisionY);
		}
		
		
		
		// si perso se prend stalagtite
		for(int i=0; i<stalagtites.size(); i++)
			if(stalagtites.get(i).getY()!=0 && joueur.getPosX_px()+32 >= stalagtites.get(i).getX() && joueur.getPosX_px() <= stalagtites.get(i).getX()+(32/3) && joueur.getPosY_px() <= stalagtites.get(i).getY()+2*36 && joueur.getPosY_px()+36 >= stalagtites.get(i).getY()+2*36)
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
			joueur = new Personnage(mapN4);
		}
		
		
		// Permet de retourner au Launcher (pour plus de rapidite)
		if(mvt.isKeyPressed(Input.KEY_ENTER))
			sbg.enterState(0);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

}
