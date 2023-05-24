package objets;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class OursPolaire {
	private float x = 500;
	private float y = 400;
	private float vitesse = (float) 0.5;
	private int timer = 0; // en ms
	
	private int tempsDeplacement =0; //en ms
	private float vitesseAttaque = 500; // temps en milisecondes
	private int distanceAttaque = 100;
	private boolean peutAttaquer = false;
	
	
	
	public OursPolaire(float x, float y) {
		if(x>=0 && x<=1024 && y>=0 && y<=720) {
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
		g.fillRect(x, y, 20, 20);
		g.drawRect(x-distanceAttaque+10, y-distanceAttaque+10, distanceAttaque*2, distanceAttaque*2);
	}	
	
	
	
	public void deplacer(int delta) {
		tempsDeplacement += delta;
		
		if(tempsDeplacement < 5000) {
			x+=vitesse;
		}
		else if(tempsDeplacement >= 5000 && tempsDeplacement <= 10000) {
			x-=vitesse;
		}
		else
			tempsDeplacement =0;
	}
	
	
	
	public void attaquer(int delta) {
		timer += delta;
		if(timer >= vitesseAttaque) {
			timer = 0;
			peutAttaquer = true;
		}
		
		if(peutAttaquer) {
			System.out.println("c la merde");
			peutAttaquer = false;
		}
	}
}

