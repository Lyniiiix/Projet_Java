package objets;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Stalagtite {
	private float x, y, vy;
	private int masse;
	
	public Stalagtite(float x) {
		if(x>=0 && x<1020)
			this.x=x;
		y=0;
		masse = 50;
		vy = masse*9.81f;
	}
	
	
	public void deplacer(int delta) {
		y+=vy*delta/1000f;
	}
	
	
	public boolean toucherSol() {
		if(y+2*36 >= 828-36)
		{
			vy=0;
			return true;
		}
		else
			return false;
	}
	
	
	public void dessin(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32/3, 2*36);
	}
	
}
