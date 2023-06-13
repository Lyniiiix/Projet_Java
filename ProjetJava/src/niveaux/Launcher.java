// PAGE AVEC LE CHOIX DU NIVEAU

package niveaux;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.*;

public class Launcher extends BasicGameState {
	private Image porte;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		porte = new Image("res/niveau2/porteNiv2.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//g.setBackground(Color.black);
		
		g.drawString("Choisissez maintenant votre niveau", 360, 50);
		// BOUTONS POUR ACCEDER AUX DIFF NIVEAUX
		g.drawRect(50, 200, 100, 150);
		g.drawString("Monde 1", 70, 370);
		
		//g.drawImage(porte, 250, 200, 350, 350, 0, 0, porte.getWidth(), porte.getHeight());
		g.drawRect(250, 200, 100, 150);
		g.drawString("Monde 2", 270, 370);
		
		g.drawRect(450, 200, 100, 150);
		g.drawString("Monde 3", 470, 370);
		
		g.drawRect(650, 200, 100, 150);
		g.drawString("Monde 4", 670, 370);
		
		g.drawRect(850, 200, 100, 150);
		g.drawString("Monde 5", 870, 370);

		g.drawRect(450, 450, 100, 150);
		g.drawString("Monde Bonus", 450, 620);
		
		
		
		// permet de faire spawn la derniere lettre de l easter egg si tous les niveaux sont finis avant
		if(Niveau1.getReussi() && Niveau2.getReussi() && Niveau3.getReussi() && Niveau4.getReussi() && Niveau5.getReussi())  			
			g.drawString("u",200 , 800);
	}
	

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input inp = gc.getInput();
		
		
		if(inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			//POUR ALLER DS NIVEAU 1
			if(inp.getMouseX()>=50 && inp.getMouseX()<=150 && inp.getMouseY()>=200 && inp.getMouseY()<=350)
				sbg.enterState(1);
			
			//POUR ALLER DS NIVEAU 2
			if(inp.getMouseX()>=250 && inp.getMouseX()<=350 && inp.getMouseY()>=200 && inp.getMouseY()<=350)
				sbg.enterState(2);
			
			//POUR ALLER DS NIVEAU 3
			if(inp.getMouseX()>=450 && inp.getMouseX()<=550 && inp.getMouseY()>=200 && inp.getMouseY()<=350)
				sbg.enterState(3);
			
			//POUR ALLER DS NIVEAU 4
			if(inp.getMouseX()>=650 && inp.getMouseX()<=750 && inp.getMouseY()>=200 && inp.getMouseY()<=350)
				sbg.enterState(4);
			
			
			//POUR ALLER DS NIVEAU 5
			if(inp.getMouseX()>=850 && inp.getMouseX()<=950 && inp.getMouseY()>=200 && inp.getMouseY()<=350)
				sbg.enterState(5);


			//POUR ALLER DS NIVEAU BONUS
			if(inp.getMouseX()>=450 && inp.getMouseX()<=550 && inp.getMouseY()>=450 && inp.getMouseY()<=600)
				sbg.enterState(10);
		}
	}

	
	@Override
	public int getID() {
		return 0;
	}

}
