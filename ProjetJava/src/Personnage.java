import java.awt.Image;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Personnage {
	private float x,y, vx, vy, graviteY;
	private int bordX = 32;
	private int bordY = 36;
	private int masse = 100;
	private float acc_ap = 9.81f;
	
	//Image image_perso = new Image();
	private Color color;
	private Sound son = new Sound("res/sons/musiquetest.wav");
	
	private int compteurDeSaut= 0;
	
	
	// CONSTRUCTEUR PAR DEFAUT
	Personnage() throws SlickException{
		graviteY = masse * acc_ap;
		
		this.x = bordX;
		this.y = 720 - (20 + bordY);
		
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
	
	Personnage(float x, float y) throws SlickException
	{
		son.play();
		this.x = x;
		this.y = y;
		
		graviteY = masse * acc_ap;
		
		this.color = Color.white;
		vx = 1 ;
		vy = 0;
	}

	
	// GETTEURS ET SETTEURS
	
	public float getGraviteY() {
		return graviteY;
	}

	public void setGraviteY(float sautY) {
		this.graviteY = sautY;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		if(x/32>=bordX && x/32<=1024/32-bordX)
			this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		if(y/36>=bordY && y/36<=720/36-bordY)
			this.y = y;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}

	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}
	

	


	// DESSINER BONHOMME
	public void dessiner(Graphics g) {  // qd on aura l image y aura plus besoin de graphics g
		g.setColor(color);
		g.fillRect(x*32, y*36, 32, 36);
		
	}
	
	
	// DEPLACER BONHOMME
	public void deplacer(GameContainer gc) {
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_RIGHT) && x*32 + vx*32 <= gc.getWidth() - (bordX + 32)) {
			x += vx;
		}
		if(mvt.isKeyDown(Input.KEY_LEFT) && x*32 - vx*32 >= bordX) {
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
		if(y/36 < 700/36 - bordY)
			vy += 36*graviteY*delta/1000f;
		else {
			vy = 0;
			compteurDeSaut = 0;
		}
	}


	public void graviteInversee(int delta) {
		if(y/36 > bordY) {
			vy += graviteY*delta/1000f;
		}
		else {
			vy=0;
			compteurDeSaut = 0;
		}
	}

	public void sauter(int delta) {
		y += vy*delta/1000f;
	}

	public void sauterInversee(int delta) {
		y -= vy*delta/1000f;
	}


	// permettrait a l ombre de suivre le bonhomme
	/*public static void suivre(Personnage j1, Personnage j2, StateBasedGame sbg) {
		// faire avancer j1 aux anciennes coordonnees de j2
		if(j1.getX() != j2.getX() && j1.getY() != j2.getY()) {
			j1.setX(j2.getX()-50);
			j1.setY(j2.getY());
		}
		
		// si j1 rattrape j2 => mort
		if((j1.getX()+20 >= j2.getX() && j1.getX() < j2.getX()) && (j1.getY()+20 >= j2.getY() || j1.getY() <= j2.getY()+20)) { // SI L OMBRE LE TOUCHE PAR LA GAUCHE
			System.out.println("t mort de la gauche");
			sbg.enterState(404);
		}
		if((j1.getX() <= j2.getX()+20 && j1.getX() > j2.getX()) && (j1.getY()+20 >= j2.getY() || j1.getY() <= j2.getY()+20)) {// SI L OMBRE LE TOUCHE PAR LA DROITE
			System.out.println("t mort de la droite");
			sbg.enterState(404);
		}
		if((j1.getY()+20 >= j2.getY() && j1.getY() < j2.getY())  && (j1.getX()+20 >= j2.getX() || j1.getX() <= j2.getX()+20)) {// SI L OMBRE LE TOUCHE PAR LE HAUT
			System.out.println("t mort d en haut");
			sbg.enterState(404);
		}
		if((j1.getY() <= j2.getY()+20 && j1.getY() > j2.getY())  && (j1.getX()+20 >= j2.getX() || j1.getX() <= j2.getX()+20)) {// SI L OMBRE LE TOUCHE PAR LE BAS
			System.out.println("t mort d en haut");
			sbg.enterState(404);
		}
	}*/
}
