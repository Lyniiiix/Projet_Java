// PERMET DE CREER UN LASER : NIVEAU 2

package objets;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Laser {
	private float x, y;
	private boolean actif = false;
	private int timer = 0;

	public Laser(float x, float y) {
		if (x >= 0 && x <= 1024 && y >= 0 && y <= 8280) {
			this.x = x;
			this.y = y;
		} 
		else {
			this.x = 10;
			this.y = 50;
		}
	}

	public Laser() {
		this.x = 10;
		this.y = 50;
	}

	
	
	public boolean getActif() {
		return actif;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setTimer(int delta) {
		timer += delta;
	}

	public int getTimer() {
		return timer;
	}

	
	public void horizontal(Graphics g) {
		if (timer < 2000) {
			g.setColor(Color.white);
			g.fillOval(x, y, 20, 20);
			g.fillOval(x + 70, y, 20, 20);
		}

		if (timer >= 2000 && timer <= 4000) {
			actif= true;
			g.setColor(Color.blue);
			g.fillOval(x, y, 20, 20);
			g.fillOval(x + 70, y, 20, 20);
			g.fillRect(x + 10, y + 5, 70, 10);
			g.setColor(Color.white);
			
		}

		if (timer > 4000)
			timer = 0;
	}
	
	
	public void vertical(Graphics g) {
		if (timer < 2000) {
			g.setColor(Color.white);
			g.fillOval(x, y, 20, 20);
			g.fillOval(x, y+70, 20, 20);
		}

		if (timer >= 2000 && timer <= 4000) {
			actif= true;
			g.setColor(Color.blue);
			g.fillOval(x, y, 20, 20);
			g.fillOval(x, y+70, 20, 20);
			g.fillRect(x + 5, y + 10, 10, 70);
			g.setColor(Color.white);
			
		}

		if (timer > 4000)
			timer = 0;
	}
}
