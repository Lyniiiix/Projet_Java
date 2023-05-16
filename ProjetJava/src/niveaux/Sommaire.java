package niveaux;


import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Sommaire extends BasicGameState {
	
	private Image img;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//img = new Image("res/images/deplacement.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Avant de commencer,", 440, 50);
		g.drawString("voici la liste des commandes qui vous aideront à survivre lors de ces épreuves", 170, 80);
		// LISTE DES COMMANDES A METTRE
		//g.drawImage(img, 200, 100);
		g.drawString("J'espère que vous saurez vous en servir.", 340, 300);
		g.drawString("Et, un conseil, ouvrez l'oeil et le bon,", 340, 400);
		g.drawString(" il est possible que vous passiez à côté de quelque chose d'important ;)", 200, 430);
		g.drawString("Appuyer sur ENTREE pour continuer", 380, 600);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input inp = gc.getInput();
		
		if(inp.isKeyPressed(Input.KEY_ENTER))
			sbg.enterState(0);
	}

	@Override
	public int getID() {
		return -1;
	}

}
