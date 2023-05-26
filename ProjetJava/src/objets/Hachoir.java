package objets;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Hachoir {
	private float pointfixeX;
	private float pointfixeY;
	private float x;
	private float y;
	private float vx = 10;
	private float vy = 10;
	private float rayonDeBase;
	private int masse = 100;
	private float gravite = 9.81f;
	
	private int traction = 10;
	private float teta = (float)(Math.PI/2);
	
	private int timer = 0; // en ms
	
	
	public Hachoir(float x, float y, float pointfixeX, float pointfixeY) {
		if(x>=0 && x<=1024 && y>=0 && y<=720) {
			this.x = x;
			this.y = y;
			this.pointfixeX = pointfixeX;
			this.pointfixeY = pointfixeY;
			this.rayonDeBase = pointfixeX-x;
		}
		else {
			this.x = 400;
			this.y = 200;
			this.pointfixeX = 550;
			this.pointfixeY = 200;
			this.rayonDeBase = 150;
		}
	}
	
	
	   
	public void dessiner(Graphics g) {
		g.setColor(Color.white);
		g.drawLine(x, y, pointfixeX, pointfixeY);
	}	
	
	
	public void deplacement() {
		x+=vx;
		y+=vy;
	}
	
	public void poids() {
		vy+=masse*gravite;
	}  
	
	public void tractionFil() {
		vy -= (Math.cos(teta))*traction;
		vx += (Math.sin(teta))*traction ;
	}
	
	public void solidite() {
		float dx = 0;
		float dy = 0;
		float d = 0;
		
		dx = pointfixeX - x;
		dy = pointfixeY - y;
		d = (float) Math.sqrt(dx*dx+dy*dy);
		
		if(d-rayonDeBase > 0) {
			
		}
		else {
			
		}
	}
}

