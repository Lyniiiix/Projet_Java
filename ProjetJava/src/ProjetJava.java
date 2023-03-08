import org.newdawn.slick.BasicGame;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class ProjetJava extends BasicGame {
	private Personnage joueur ;
	private int timer = 0;

	public ProjetJava(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		joueur = new Personnage(gc);
	}
	
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		joueur.dessiner(g);
		g.drawString(timer/1000f+" s", 100, 100);
	}


	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input mvt = gc.getInput();
		
		
		joueur.bouger(delta);
		joueur.gravite(delta);
		
		if(mvt.isKeyDown(Input.KEY_SPACE)) {
			joueur.deplacer(gc,delta);
		}
		if(mvt.isKeyDown(Input.KEY_RIGHT)) {
			joueur.deplacer(gc,delta);
		}
		if(mvt.isKeyDown(Input.KEY_LEFT)) {
			joueur.deplacer(gc,delta);
		}
		
		timer = timer+delta;
	}

}
