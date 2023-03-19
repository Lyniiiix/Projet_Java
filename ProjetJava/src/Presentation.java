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
		g.drawString("Vous incarnerez un personnage élémentaire de type feu ou eau en fonction des épreuves", 120, 150);
		g.drawString("Les niveaux que vous traverserez seront assez alambiqués, votre patience mise à rude épreuve", 100, 180);
		g.drawString("pourra céder à tout moment", 390, 210);
		g.drawString("Vous apprendrez ici la dure loi du 'Alt F4'.", 300, 240);
		g.drawString("Mesdames, messieurs, nous vous souhaitons un agréable gameplay", 240, 350);
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
