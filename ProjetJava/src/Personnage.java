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
	Personnage(){
		masse = 100;
		graviteY = masse*9.81f;
		bord = 20;
		this.x = bord;
		this.y = 720 - (20 + bord);
		this.color = Color.white;
		vx = 1;
		vy = 0;
		compteurDeMort = 0;
	}


	// creer un perso aux cordonnées voulues
	Personnage(float x, float y){
		masse = 100;
		graviteY = masse*9.81f;
		bord = 20;
		vx = 1;
		vy = 0;
		compteurDeMort = 0;
		this.color = Color.white;
		
		if(x>=bord && x<=1024-bord && y>=bord && y<=720-bord) {
			this.x = x;
			this.y = y;
		}
		else {
			this.x=bord;
			this.y = 720 - (20 + bord);
		}
	}
	

	
	// GETTEURS ET SETTEURS
	
	public int getCompteurDeMort() {
		return compteurDeMort;
	}

	public void setCompteurDeMort(int compteurDeMort) {
		if(compteurDeMort>=0)
			this.compteurDeMort = compteurDeMort;
	}
	
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
		if(bord>=0)
			this.bord = bord;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		if(x>=bord && x<=1024-bord)
			this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		if(y>=bord && y<=720-bord)
			this.y = y;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
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


	public void graviteInversee(int delta) {
		if(y>bord) {
			vy+=graviteY*delta/1000f;
		}
		else {
			vy=0;
			compteurDeSaut = 0;
		}
	}

	public void sauter(int delta) {
		x+=vx*delta/1000f;
		y+=vy*delta/1000f;
	}

	public void sauterInversee(int delta) {
		y-= vy*delta/1000f;
	}


	// COMPTE LE NOMBRE DE MORT DU PERSO
	public int mort() {
		compteurDeMort++;
		return compteurDeMort;
	}
}
