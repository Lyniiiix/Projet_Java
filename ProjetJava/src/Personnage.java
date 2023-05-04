import java.awt.Image;


import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Personnage {
	private float x,y, vx, vy, graviteY;
	private int bordX = 32;
	private int bordY = 36;
	private int masse = 70;
	private float acc_ap = 9.81f;
	
	private int case_MatX = 32;
	private int case_MatY = 23;
	
	//Image image_perso = new Image();
	private Color color;
	
	private int compteurDeSaut= 0;
	
	
	// CONSTRUCTEUR PAR DEFAUT
	Personnage(){
		graviteY = masse * acc_ap;
		
		this.x = 1;
		this.y = 18;
		
		this.color = Color.white;
		vx = 2;
		vy = 0;
	}


	// creer un perso aux cordonnées voulues
	
	Personnage(float x, float y)
	{
		if(x>=1 && x<=case_MatX-1 && y>=1 && y<=case_MatY-1) {  // pasque c une grille de 32 par 20
			this.x = x;
			this.y = y;
		}
		else {
			this.x=1;
			this.y = case_MatY-1;
		}
		
		graviteY = masse * acc_ap;
		
		this.color = Color.white;
		vx = 2 ;
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
		if(x>=1 && x<=case_MatX-1)
			this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		if(y>=1 && y<=case_MatX-1)
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
		
		if(mvt.isKeyDown(Input.KEY_RIGHT) && x*32 + vx <= gc.getWidth() - (bordX + 32)) {
			x += vx/case_MatY;
		}
		if(mvt.isKeyDown(Input.KEY_LEFT) && x*32 - vx >= bordX) {
			x -= vx/case_MatY;
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
		if(y < case_MatY - 2)  // pour la bordure + la hauteur du bonhomme
			vy += graviteY*delta/1000f;
		else {
			vy = 0;
			compteurDeSaut = 0;
		}
	}


	public void graviteInversee(int delta) {
		if(y > 1) {
			vy += graviteY*delta/1000f;
		}
		else {
			vy=0;
			compteurDeSaut = 0;
		}
	}

	public void sauter(int delta) {
		y += (vy*delta/1000f)/36;
	}

	public void sauterInversee(int delta) {
		y -= (vy*delta/1000f)/36;
	}


	// permettrait a l ombre de suivre le bonhomme                  !!!!! MARCHE PAS !!!!
	public static void suivre(Personnage j1, Personnage j2) {
		// faire avancer j1 aux anciennes coordonnees de j2
		if(j1.getX() != j2.getX() || j1.getY() != j2.getY()) {
			j1.setX(j2.getX()-2);
			j1.setY(j2.getY());
		}
		
		// si j1 rattrape j2 => mort
		
		if((j1.getX()+1 >= j2.getX() && j1.getX() < j2.getX()) && (j1.getY()+1 >= j2.getY() || j1.getY() <= j2.getY()+1)) { // SI L OMBRE LE TOUCHE PAR LA GAUCHE
			System.out.println("t mort de la gauche");
		}
		if((j1.getX() <= j2.getX()+1 && j1.getX() > j2.getX()) && (j1.getY()+1 >= j2.getY() || j1.getY() <= j2.getY()+1)) {// SI L OMBRE LE TOUCHE PAR LA DROITE
			System.out.println("t mort de la droite");
		}
		if((j1.getY()+1 >= j2.getY() && j1.getY() < j2.getY())  && (j1.getX()+1 >= j2.getX() || j1.getX() <= j2.getX()+1)) {// SI L OMBRE LE TOUCHE PAR LE HAUT
			System.out.println("t mort d en haut");
		}
		if((j1.getY() <= j2.getY()+1 && j1.getY() > j2.getY())  && (j1.getX()+1 >= j2.getX() || j1.getX() <= j2.getX()+1)) {// SI L OMBRE LE TOUCHE PAR LE BAS
			System.out.println("t mort d en haut");
		}
	}
	
	
	public boolean rebond() {
		if(x==32 || x==1)
			return true;
		else
			return false;
	}
	
	
	public void glisser() {
		// permettra de faire glisser le perso sur la glace du niveau 5
	}
}
