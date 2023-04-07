import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Presentation extends BasicGameState {

	public Presentation() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Bienvenue dans notre monde,", 390, 50);
		// LA ON MET LE NOM DU JEU
		g.drawString("Angry Jump", 465, 150);
		
		g.drawString("Vous incarnerez ici un personnage ayant de gros cuissots et dont le seul objectif dans la vie est de grimper.", 20, 250);
		g.drawString("Manque de pot, pas mal de choses vont essayer de vous empecher d'accomplir votre passion favorite.", 80, 280);
		g.drawString("Les niveaux que vous traverserez seront assez alambiqués, votre patience mise à rude épreuve", 100, 310);
		g.drawString("pourra céder à tout moment", 390, 340);
		g.drawString("Vous apprendrez ici la dure loi du 'Alt F4'.", 300, 440);
		g.drawString("Mesdames, messieurs, nous vous souhaitons un agréable gameplay", 240, 490);
		g.drawString("Appuyer sur ENTREE pour continuer", 380, 600);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input inp = gc.getInput();
		
		if(inp.isKeyPressed(Input.KEY_ENTER))
			sbg.enterState(-1);
	}

	@Override
	public int getID() {
		return -2;
	}

}
