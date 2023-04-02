import java.awt.Image;

import org.newdawn.slick.Input;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Personnage {
	private float x,y, vx, vy, graviteY;
	private int bord = 32;
	private int masse = 100;
	private float acc_ap = 9.81f;
	
	//Image image_perso = new Image();
	private Color color;
	
	private int compteurDeSaut= 0;
	
	
	// CONSTRUCTEUR PAR DEFAUT
	Personnage(){
		graviteY = masse * acc_ap;
		
		this.x = bord;
		this.y = 720 - (20 + bord);
		
		this.color = Color.white;
		vx = 1;
		vy = 0;
	}


	// creer un perso aux cordonnées voulues
	
	/*
	Personnage(float x, float y){
		graviteY = masse*9.81f;
		bord = 20;
		vx = 1;
		vy = 0;
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
	*/
	
	Personnage(float x, float y)
	{
		this.x = x;
		this.y = y;
		
		graviteY = masse * acc_ap;
		
		this.color = Color.white;
		vx = 0.5f ;
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
		g.fillRect(x*32, y*36, 32, 36);
	}
	
	
	// DEPLACER BONHOMME
	public void deplacer(GameContainer gc) {
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_RIGHT) && x*32 + vx*32 <= gc.getWidth() - (bord + 20)) {
			x += vx;
		}
		if(mvt.isKeyDown(Input.KEY_LEFT) && x*32 - vx*32 >= 0 + bord) {
			x -= vx;
		}
	}


	// PERMET DE FAIRE DES SAUTS EN APPUYANTS UNE FOIS SUR LA BARRE ESPACE
	public void sautNormal(GameContainer gc) {
		Input mvt = gc.getInput();
		
		if(mvt.isKeyPressed(Input.KEY_SPACE) && compteurDeSaut<1) {	
			vy = -400;	
			compteurDeSaut++;
		}
	}

	
	//PERMET LA GRAVITE ET LES SAUTS DU BONHOMME

	public void gravite(int delta) {
		if(y*32 < 700 - bord)
			vy += 36*graviteY*delta/1000f;
		else {
			vy = 0;
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
		y += 36*vy*delta/1000f;
	}

	public void sauterInversee(int delta) {
		y-= vy*delta/1000f;
	}

}
