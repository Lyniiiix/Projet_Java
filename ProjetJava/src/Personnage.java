import java.awt.Image;

import org.newdawn.slick.Input;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Personnage {
	private float x,y, vx, vy, graviteY;
	private int bord;
	private int masse;
	//Image img = new Image();
	private Color color;
	private int compteurDeSaut= 0;
	
	
	// CONSTRUCTEUR PAR DEFAUT
	Personnage(GameContainer gc){
		masse = 100;
		graviteY = masse*9.81f;
		bord = 20;
		this.x = bord;
		this.y = gc.getHeight() - (20 + bord);
		this.color = Color.white;
		vx = 1;
		vy = 0;
	}
	

	
	// GETTEURS ET SETTEURS

	public float getGraviteY() {
		return graviteY;
	}

	public void setGraviteY(float sautY) {
		this.graviteY = sautY;
	}

	public int getBord() {
		return bord;
	}

	public void setBord(int bord) {
		this.bord = bord;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}


	


	// DESSINER BONHOMME
	public void dessiner(Graphics g) {  // qd on aura l image y aura plus besoin de graphics g
		g.setColor(color);
		g.fillRect(x, y, 20, 20);
	}
	
	
	// DEPLACER BONHOMME
	public void deplacer(GameContainer gc, int delta) {
		Input mvt = gc.getInput();
		
		
		
		if(mvt.isKeyDown(Input.KEY_RIGHT) && x+vx <= gc.getWidth() - (bord+20)) {
			x += vx;
		}
		if(mvt.isKeyDown(Input.KEY_LEFT) && x-vx >= 0 + bord) {
			x -= vx;
		}
		if(mvt.isKeyPressed(Input.KEY_SPACE) && compteurDeSaut<1) {	
			vy = -400;	
			compteurDeSaut++;
		}
	}

	public void gravite(int delta) {
		if(y<700 - bord)
			vy+=graviteY*delta/1000f;
		else {
			vy=0;
			compteurDeSaut = 0;
		}
	}

	public void bouger(int delta) {
		x+=vx*delta/1000f;
		y+=vy*delta/1000f;
	}
}
