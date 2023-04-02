import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MainProjet {

	public static void main(String[] args) throws SlickException {
		ProjetJava jeuProjet = new ProjetJava("Projet Java");
		AppGameContainer app= new AppGameContainer(jeuProjet);
		app.setDisplayMode(1024, 720, false);
		app.setTargetFrameRate(60);
		app.setShowFPS(true);
		app.start();
	}
}
