import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Niveau2 extends BasicGameState {
	private Personnage joueur ;
	private Personnage ombre;
	private int timer = 0;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		joueur = new Personnage(gc);
		ombre = new Personnage();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		joueur.dessiner(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		joueur.bouger(delta);
		joueur.gravite(delta);
		
		
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_SPACE) ) {
			joueur.deplacer(gc,delta);
		}
		if(mvt.isKeyDown(Input.KEY_RIGHT)) {
			joueur.deplacer(gc,delta);
		}
		if(mvt.isKeyDown(Input.KEY_LEFT)) {
			joueur.deplacer(gc,delta);
		}
		
		
		// Permet de retourner au Launcher (pour plus de rapidite)
		if(mvt.isKeyPressed(Input.KEY_ENTER))
			sbg.enterState(0);
	}

	@Override
	public int getID() {
		return 2;
	}

}
