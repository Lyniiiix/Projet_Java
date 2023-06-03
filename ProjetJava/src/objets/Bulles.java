package objets;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Bulles {
	private float x, y;
	private int diametre;
	private float vy;
	

	public Bulles(float x, float y) {
		diametre = 40;
		vy = 9.81f;
		if(y<=0)
			this.y = y;
		if(x>=0 && x<=1024) {
			this.x = x;
		}
	}
	
	public void dessiner(Graphics g) {
		g.drawOval(x, y, diametre, diametre);
	}
	
	public void deplacer(int delta) {
		y+=vy*delta/100f;
	}
	
	public boolean testCollision() {
		if(this.y >= 580 && this.y <= 620) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public boolean horsEcran() {
		if(y>620) 
			return true;
		else
			return false;
	}
	
}

