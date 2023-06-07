package objets;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Mer {

	private float niveauEau;
	private float vitesseMontee;
	
	
	public float getNiveauEau() {
		return niveauEau;
	}
	
	public Mer(float vitesseMontee) {
		niveauEau=827;
		if(vitesseMontee>=0)
			this.vitesseMontee=vitesseMontee;
	}
	
	
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, niveauEau, 1024, 828-niveauEau);
		g.setColor(Color.white);
	}
	
	public void monter(int delta) {
		niveauEau-=vitesseMontee*delta/1000f;
	}
	
}
