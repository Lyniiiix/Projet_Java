package objets;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Plateforme {
	private float x,y,vx,vy;
	private int timerChangementDirection, time;
	private Color c;
	
	public Plateforme(float x, float y, int timerChangementDirection) {
		c = Color.white;
		vx=5;
		vy=0;
		
		if(x>=20 && x<=1024-20) { // le 20 correspond a la bordure
			this.x=x;
		}
		else
			this.x=100;
		if(y>=20 && y<=720-20) { // le 20 correspond a la bordure
			this.y=y;
		}
		else
			this.y=500;
		
		if(timerChangementDirection>=0)
			this.timerChangementDirection=timerChangementDirection;
		else
			timerChangementDirection=1000;
	}
	
	
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, 3*32, 36);
	}
	
	public void deplacement(int delta) {

		time+=delta;
		
		if(time >= timerChangementDirection) {
			time=0;
			vx = -vx;
		}
		
		x+=vx;
	}
}

