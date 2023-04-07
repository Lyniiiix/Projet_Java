import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class NiveauBonus extends BasicGameState {
	private Personnage joueur ;
	private float timer =0;
	private int compteurChangements =0; // PERMET DE COMPTER LES NMB DE CHANGEMENTS DEPUIS LE DEBUT
	private int changement = 0; // 

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		joueur = new Personnage();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		joueur.dessiner(g);
		
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
		
		
		
		timer+=delta;
		
		if(timer>=3000) {
			compteurChangements++;
			timer=0;
			
			
			
			int random = (int)(Math.random()*6);

			switch(random){
				case(1):
					if(compteurChangements>1) {
						switch(changement) {       // CONDITION QUI PERMET DE REMETTRE LA CONDITION D AVANT A L ETAT INITIAL
							case(1):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(2):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(3):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(4):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(5):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
						}	
					}
				
					joueur.setGraviteY(joueur.getGraviteY()*2); // DOUBLE LA GRAVITE
					break;
				
				case(2) :
					if(compteurChangements>1) {
						switch(changement) {
							case(1):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(2):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(3):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(4):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(5):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
						}
					}
				
				 // PERMET DE FAIRE ALLER LE   A DROITE QD ON VEUT ALLER A GAUCHE ET INVERSEMENT
					if(mvt.isKeyDown(Input.KEY_RIGHT)) {         
						joueur.setX(joueur.getX()-2*joueur.getX());
					}
					if(mvt.isKeyDown(Input.KEY_LEFT)) {
						joueur.setX(joueur.getX()+2*joueur.getX());
					}
					break;
				
				case(3) :
					if(compteurChangements>1) {
						switch(changement) {
							case(1):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(2):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(3):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(4):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(5):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
						}
					}
				
					joueur.setGraviteY(joueur.getGraviteY()*2);
					break;
				
				case(4) :
					if(compteurChangements>1) {
						switch(changement) {
							case(1):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(2):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(3):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(4):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(5):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
						}
					}
				
					joueur.setGraviteY(joueur.getGraviteY()*2);
					break;
				
				case(5) :
					if(compteurChangements>1) {
						switch(changement) {
							case(1):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(2):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(3):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(4):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
							case(5):
								joueur.setGraviteY(joueur.getGraviteY()/2);
								break;
						}
					}
				
					joueur.setGraviteY(joueur.getGraviteY()*2);
					break;
			}
			
			// PERMET DE RETENIR LE RANDOM D AVANT
			changement = random;
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
