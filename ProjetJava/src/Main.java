import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
	
	public static void main(String[] args) throws SlickException {
		
		//test
		ProjetJava jeuProjet = new ProjetJava("Mon jeu");
		AppGameContainer app= new AppGameContainer(jeuProjet);
		app.setDisplayMode(1024, 720, false);
		app.setTargetFrameRate(60);
		app.setShowFPS(false);
		app.start();
		
		
	}
	
}
