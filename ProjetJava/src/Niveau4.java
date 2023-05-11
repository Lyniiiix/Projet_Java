
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Niveau4 extends BasicGameState {   // niveau glace
	private Image image_fond;
	
	private Personnage joueur ;
	private Plateforme p1, p2, p3, p4, p5;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		image_fond = new Image("res/niveau4/n4_fond.png");
		
		joueur = new Personnage();
		p1= new Plateforme(100, 580, 1000); // la 3 eme cordonnee c le temps pour le changement de direction
		p2= new Plateforme(500, 480, 1000);
		p3= new Plateforme(350, 380, 1000);
		p4= new Plateforme(200, 280, 1000);
		p5= new Plateforme(600, 80, 1000);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(image_fond,0,0);
		
		joueur.dessiner(g);
		p1.draw(g);
		p2.draw(g);
		p3.draw(g);
		p4.draw(g);
		p5.draw(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		joueur.sauter(delta);
		joueur.gravite(delta);
		

		p1.deplacement(delta);
		p2.deplacement(delta);
		p3.deplacement(delta);
		p4.deplacement(delta);
		p5.deplacement(delta);
		
		
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
		
		
		
		// Permet de retourner au Launcher (pour plus de rapidite)
		if(mvt.isKeyPressed(Input.KEY_ENTER))
			sbg.enterState(0);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

}
