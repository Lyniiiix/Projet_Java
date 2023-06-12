// PERMET DE CREER DES ECLAIRS

package objets;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Eclair {
	private float Xdepart, Ydepart, Yarrive;
	private boolean flash;
	
	
	
	public boolean getFlash() {
		return flash;
	}
	
	public void setFlash(boolean flash) {
		this.flash = flash;
	}

	public float getXdepart() {
		return Xdepart;
	}

	public float getYarrive() {
		return Yarrive;
	}


	
	public Eclair(float x, float Yperso) {
		flash = false;
		this.Xdepart=x;
		Ydepart = 0;
		Yarrive= Yperso;
	}
	
	
	public void dessiner(Graphics g) {
		g.setColor(Color.yellow);
		g.drawLine(Xdepart+32/2, Ydepart, Xdepart+32/2, Yarrive+36);
		g.setColor(Color.white);
	}
	
}
