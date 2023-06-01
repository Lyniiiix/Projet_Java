package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TrackingNiv extends BasicGameState {

	private TrackingNiveau traqueur;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		traqueur = new TrackingNiveau();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		traqueur.IDActuel(sbg);
		traqueur.retry(sbg);
	}

	@Override
	public int getID() {
		return 1000;
	}

}
