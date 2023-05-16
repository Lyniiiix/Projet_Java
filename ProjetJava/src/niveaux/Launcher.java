package niveaux;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.*;

public class Launcher extends BasicGameState {

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Choisissez maintenant votre niveau", 360, 50);
		// BOUTONS POUR ACCEDER AUX DIFF NIVEAUX
		g.drawRect(50, 200, 100, 100);
		g.drawString("Monde 1", 70, 320);
		
		g.drawRect(250, 200, 100, 100);
		g.drawString("Monde 2", 270, 320);
		
		g.drawRect(450, 200, 100, 100);
		g.drawString("Monde 3", 470, 320);
		
		g.drawRect(650, 200, 100, 100);
		g.drawString("Monde 4", 670, 320);
		
		g.drawRect(850, 200, 100, 100);
		g.drawString("Monde 5", 870, 320);

		g.drawRect(450, 450, 100, 100);
		g.drawString("Monde Bonus", 450, 570);
	}
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input inp = gc.getInput();
		
		
		if(inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			//POUR ALLER DS NIVEAU 1
			if(inp.getMouseX()>=50 && inp.getMouseX()<=150 && inp.getMouseY()>=200 && inp.getMouseY()<=300)
				sbg.enterState(1);
			
			//POUR ALLER DS NIVEAU 2
			if(inp.getMouseX()>=250 && inp.getMouseX()<=350 && inp.getMouseY()>=200 && inp.getMouseY()<=300)
				sbg.enterState(2);
			
			//POUR ALLER DS NIVEAU 3
			if(inp.getMouseX()>=450 && inp.getMouseX()<=550 && inp.getMouseY()>=200 && inp.getMouseY()<=300)
				sbg.enterState(3);
			
			//POUR ALLER DS NIVEAU 4
			if(inp.getMouseX()>=650 && inp.getMouseX()<=750 && inp.getMouseY()>=200 && inp.getMouseY()<=300)
				sbg.enterState(4);
			
			
			//POUR ALLER DS NIVEAU 5
			if(inp.getMouseX()>=850 && inp.getMouseX()<=950 && inp.getMouseY()>=200 && inp.getMouseY()<=300)
				sbg.enterState(5);


			//POUR ALLER DS NIVEAU BONUS
			if(inp.getMouseX()>=450 && inp.getMouseX()<=550 && inp.getMouseY()>=450 && inp.getMouseY()<=550)
				sbg.enterState(10);
		}
	}

	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
