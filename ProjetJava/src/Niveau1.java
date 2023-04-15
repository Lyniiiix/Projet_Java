import org.newdawn.slick.Color;


import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Niveau1 extends BasicGameState {
	
	private Image image_fond;
	private TiledMap map;
	
	private Map mapNiveau1;
	private Sound son;
	
	private Personnage joueur;
	private int timer = 0;
	
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// coordonnées en bas a gauche
		
		joueur = new Personnage(); // j'ai rajt un constructeur pour test ce que je faisais
		
		son = new Sound("res/sons/musiquetest.wav"); // met la musique qd le niveau se lance
		
		image_fond = new Image("res/niveau1/n1_fond.png");
		map = new TiledMap("res/niveau1/niveau1.tmx");
		mapNiveau1 = new Map(gc,image_fond,map);  
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(image_fond,0,0); //dessine image_fond
		map.render(0, 0); //dessiner la map a partir du .tmx correspondant
		
		joueur.dessiner(g);
		g.drawString(timer/1000f+" s", 100, 100);
	}

	
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//son.play();
		
		joueur.sauter(delta);
		joueur.gravite(delta);
		
		
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_SPACE) ) {
			joueur.sautNormal(gc);
		}
		if(mvt.isKeyDown(Input.KEY_RIGHT)) {
			joueur.deplacer(gc);
			/*if(joueur.rebond())
				joueur.setX(10);*/
		}
		if(mvt.isKeyDown(Input.KEY_LEFT)) {
			joueur.deplacer(gc);
			/*if(joueur.rebond())
				joueur.setVx(joueur.getVx()+5);*/
		}
		
		
		// Permet de retourner au Launcher (pour plus de rapidite)
		if(mvt.isKeyPressed(Input.KEY_ENTER)) {
			//System.out.println(son.getClass());
			//son.play(son.length()); // marche pas g tout tester 
			sbg.enterState(0);
		}
		
		
		timer = timer+delta;
		// PERMET DE REINITIALISER LE NIVEAU AU BOUT D UN CERTAIN TEMPS
		if(timer>=30000) {
			timer=0;
			son.stop(); // marche pas
			sbg.enterState(404);
		}
		
		
	}

	@Override
	public int getID() {
		return 1;
	}

}
