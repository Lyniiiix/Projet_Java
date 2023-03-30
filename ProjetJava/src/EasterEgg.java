import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EasterEgg extends BasicGameState {
	private ZoneTexte zone = new ZoneTexte();

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Avez vous trouvez l'easter egg caché dans l'un des différents mondes?", 200, 125);
		g.drawString("Tapez le nom de l'easter egg", 365, 250);
		zone.dessiner(g);
		g.drawString("Appuyez sur ENTREE pour valider", 380, 600);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		zone.maj(gc);
		
		// si le mot est bon => alors on peut passer au niveau sinon retour au choix des levels
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_ENTER)) {
			
			if(zone.getTxt()=="coucou") {  // je comprends pas pq sa marche pas !!!!!
				sbg.enterState(6);
			}
			else {
				zone.resetTxt();
				sbg.enterState(0);
			}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 10;
	}

}
