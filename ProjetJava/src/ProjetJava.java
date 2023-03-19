import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ProjetJava extends StateBasedGame {

	public ProjetJava(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new Presentation());
		addState(new Sommaire());
		addState(new Launcher());
		addState(new Niveau1());
		addState(new Niveau2());
		addState(new Niveau3());
		addState(new Niveau4());
		addState(new Niveau5());
		enterState(-2);
	}

}

