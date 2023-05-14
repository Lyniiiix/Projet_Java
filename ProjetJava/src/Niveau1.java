
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

public class Niveau1 extends BasicGameState {    // niveau foret
	
	private Image image_fond; // image fond map
	private TiledMap map; // schéma TMX map
	
	public Map mapN1; // instance d'une map
	
	//private Sound son;
	private Clip son;
	
	private Personnage joueur;
	private int timer = 0;
	private boolean mort = false;
	
	private Collisions collisionsN1; // instance Collisions
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		joueur = new Personnage(); 

		// son = new Sound("res/sons/musiquetest.wav"); // met la musique qd le niveau se lance
		// TOUT CA C POUR LA MUSIQUE MAIS CA MARCHE PAS  !!!!
		/*
		AudioInputStream audioInputStream;
		try {
			// Ouvrir le fichier audio
			audioInputStream = AudioSystem.getAudioInputStream(Niveau1.class.getResourceAsStream("musiquetest.wav"));
			
			// Obtenir le format audio du fichier
			AudioFormat format = audioInputStream.getFormat();
			
			// Créer un DataLine pour la lecture audio
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			son = (Clip) AudioSystem.getLine(info);
			
			// Ouvrir le flux audio et le charger dans le Clip
			son.open(audioInputStream);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		*/
		image_fond = new Image("res/niveau1/n1_fond.png");
		map = new TiledMap("res/niveau1/niveau1.tmx");
		
		mapN1 = new Map(gc, image_fond, map);
		
		/*
		// parcour les objets bloquant de la map
		for (int i = 0; i < mapN1.getObjets().length; i++)
			System.out.println( mapN1.getObjets()[i] );
		*/
		
		collisionsN1 = new Collisions(mapN1);
	}
	
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//son.play();
		
		
		/*
		if (!son.isActive()) {
            // Si la musique est terminée, la jouer à nouveau
            son.setFramePosition(0);
            son.start();
        }
		*/
		
		/* Calculs */
		joueur.sauter(delta);
		joueur.gravite(delta);
		
		
		int posX = (int)(Math.floor(joueur.getX())); // posX en index X de matrice
		int posY = (int)(Math.floor(joueur.getY())); // posY en index Y de matrice
		System.out.println(String.format("Coord. perso : posXpx : %s , poxYpx : %s",joueur.getPosX_px(),joueur.getPosY_px()));
		System.out.println(String.format("Coord. perso : posX : %s , poxY : %s",joueur.getX(),joueur.getY()));
		
		
		
		//System.out.println(collisionsN1.enCollision(joueur.getX(), joueur.getY()));
		
		
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_SPACE)) {
			joueur.setStatus("saut");
			joueur.sautNormal(gc);
		}
		if(mvt.isKeyDown(Input.KEY_RIGHT)) {
			joueur.deplacer(gc);
			joueur.setStatus("droite");
			/*if(joueur.rebond())
				joueur.setX(10);*/
		}
		if(mvt.isKeyDown(Input.KEY_LEFT)) {
			joueur.deplacer(gc);
			joueur.setStatus("gauche");
			/*if(joueur.rebond())
				joueur.setVx(joueur.getVx()+5);*/
		}
		
		
		// Permet de retourner au Launcher (pour plus de rapidite)
		if(mvt.isKeyPressed(Input.KEY_ENTER)) {
			//System.out.println(son.getClass());
			//son.play(son.length()); // marche pas g tout tester 
			sbg.enterState(0);
		}
		
		// timer
		timer = timer+delta;
		// PERMET DE REINITIALISER LE NIVEAU AU BOUT D UN CERTAIN TEMPS
		if(timer>=300000) { //augmenter pour test
			timer=0;
			//son.stop(); // marche pas
			
			mort = true;
			sbg.enterState(404);
		}
		
		
		// refait spawn le perso au début et pas la ou il était
		if(mort) {
			mort = false;
			joueur = new Personnage();
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(image_fond,0,0); // dessiner image_fond
		map.render(0, 0); // dessiner la map a partir du .tmx correspondant
		
		joueur.dessiner(g);
		
		g.drawString(timer/1000f+" s", 100, 100);
		
	}

	@Override
	public int getID() {
		return 1;
	}

}
