// PERMET DE CREER UN OURS POLAIRE

package objets;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class OursPolaire {
	private float x = 500;
	private float y = 400;
	private float vitesse = 60;
	private int timer = 0; // en ms
	
	private int tempsDeplacement =0; //en ms
	private float vitesseAttaque = 1000; // temps en milisecondes
	private int distanceAttaque = 2*36;
	private boolean peutAttaquer = false;
	
	

	public float getX() {
		return x;
	}

	public void setX(float x) {
		if(x>=0 && x<=1024)
			this.x = x;
	}

	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		if(y>=0 && y<=828)
			this.y = y;
	}

	public int getDistanceAttaque() {
		return distanceAttaque;
	}

	public boolean getPeutAttaquer() {
		return peutAttaquer;
	}

	
	
	
	
	

	public OursPolaire(float x, float y) {
		if(x>=0 && x<=1024 && y>=0 && y<=828) {
			this.x = x;
			this.y = y;
		}
		else {
			this.x = 500;
			this.y = 400;
		}
	}
	
	
	
	public void dessiner(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 32, 36);
		g.drawRect(x-distanceAttaque+32/2, y-distanceAttaque, distanceAttaque*2, distanceAttaque+36);
		g.setColor(Color.white);
	}	
	
	
	
	public void deplacer(int delta) {
		tempsDeplacement += delta;
		
		if(tempsDeplacement < 2500) {
			x+=vitesse*delta/1000f;
		}
		else if(tempsDeplacement >= 2500 && tempsDeplacement <= 5000) {
			x-=vitesse*delta/1000f;
		}
		else
			tempsDeplacement =0;
	}
	
	
	
	public void attaquer(int delta) {
		timer += delta;
		
		if(peutAttaquer) { // permet d eviter qu il attaque tout le temps
			peutAttaquer = false;
		}
		
		if(timer >= vitesseAttaque) {
			timer = 0;
			peutAttaquer = true;
		}

	}
}

