package niveaux;

import main.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState {
	private Image image_fond;
	
	private float timer;
	private int compteurDeMort;
	
	private TrackingNiveau retry = new TrackingNiveau();  // c pareil avec le traqueur de niveau ça marche pas !!!!!

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		image_fond = new Image("res/images/mort.jpg");
		timer =0;
		compteurDeMort =0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(image_fond, 0, 0, 1024, 828, 0, 0, image_fond.getWidth(),  image_fond.getHeight());
		g.drawString("YOU DIE", 470, 200);
		g.drawString("Morts : "+compteurDeMort, 465, 300);
		
		g.drawRect(150, 500, 200, 50);
		g.drawString("Retry", 225, 515);
		
		g.drawRect(650, 500, 200, 50);
		g.drawString("Quit", 730, 515);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer+=delta;
		if(timer<=delta+1) {
			compteurDeMort++;
		}
		
		Input mvt = gc.getInput();
		
		if(mvt.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			//BOUTON POUR RETRY LE NIVEAU
			if(mvt.getMouseX()>=150 && mvt.getMouseX()<=350 && mvt.getMouseY()>=500 && mvt.getMouseY()<=550){
				timer=0;
				sbg.enterState(retry.getPrecedent());
			}
			
			// BOUTON POUR REVENIR AU LAUNCHER
			if(mvt.getMouseX()>=650 && mvt.getMouseX()<=850 && mvt.getMouseY()>=500 && mvt.getMouseY()<=550)
				sbg.enterState(0);
		}
			
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 404;
	}

}