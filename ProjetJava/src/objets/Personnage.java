package objets;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Input;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import main.*;

public class Personnage {
	private Image image;
	private float x,y, vx, vy, graviteY;
	private int masse = 70; // masse (varier gravite)
	private float acc_p = 9.81f; // acceleration de la pesanteur
	
	private String direct_pers; // droite - gauche
	private String status_pers; // sol - vol
	
	private int posY_avant_saut; // juste test
	
	//Image image_perso = new Image();
	private Color color;
	
	private int compteurDeSaut = 0;	
	
	// CONSTRUCTEUR PAR DEFAUT
	public Personnage() throws SlickException{
		image = new Image("res/images/perso.png");
		graviteY = masse * acc_p;
		
		this.x = 1; // on place au debut en bas a gauche
		this.y = 18;
		
		this.color = Color.white;
		vx = 2; // vitesse horizontale
		vy = 0;
		
		this.direct_pers = "droite";
		this.status_pers = "sol";
	}


	// creer un perso aux cordonnées voulues
	public Personnage(float x, float y) throws SlickException
	{
		if(x >= 1 && x <= Constantes.MAP_X - 1 && y >= 1 && y <= Constantes.MAP_Y - 1) {  // grille de 32 par 23
			this.x = x;
			this.y = y;
		}
		else {
			this.x = 1;
			this.y = Constantes.MAP_Y - 1;
		}
		
		graviteY = masse * acc_p;
		
		this.color = Color.white;
		vx = 2 ;
		vy = 0;
		
		this.direct_pers = "droite";
		this.status_pers = "sol";
		
		image = new Image("res/images/perso.png");
	}

	
	// GETTEURS ET SETTEURS
	// *********************************************** //
	// Gravité
	public float getGraviteY() {
		return graviteY;
	}

	public void setGraviteY(float sautY) {
		this.graviteY = sautY;
	}
	// *********************************************** //
	// Coordonnées float
	public float getX() {
		return x;
	}

	public void setX(float x) {
		if(x >= 1 && x <= Constantes.MAP_X - 1)
			this.x = x;
	}
	
	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		if(y >= 1 && y <= Constantes.MAP_X - 1)
			this.y = y;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}
	public float getPosX_px()
	{
		return x * 32;
	}
	public float getPosY_px()
	{
		return y * 36;
	}
	// *********************************************** //
	// status personnage
	public String getStatus()
	{
		return status_pers;
	}
	
	public void setStatus(String status)
	{
		status_pers = status;
	}
	
	public String getDirect()
	{
		return direct_pers;
	}
	
	public void setDirect(String direct_pers)
	{
		this.direct_pers = direct_pers;
	}
	
	public int getPosY_avant_saut()
	{
		return posY_avant_saut;
	}
	
	public void setPosY_avant_saut(int posY_avant_saut)
	{
		this.posY_avant_saut = posY_avant_saut;
	}
	
	// *********************************************** //
	
	
	
	// DESSINER BONHOMME
	public void dessiner(Graphics g) {  // qd on aura l image y aura plus besoin de graphics g
		g.drawImage(image, x-32, y-36, x+32, y+36, 0, 0, image.getWidth(), image.getHeight());
		
		g.setColor(color);
		g.fillRect(x*32, y*36, 32, 36); // (x,y) en pos de matrice - (largeur,longueur)
		
	}
	
	
	public void deplacer(GameContainer gc)
	{
		
	}
	
	// DEPLACER BONHOMME
	public void deplacer(GameContainer gc, boolean collisionX) {
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_RIGHT) && !collisionX) { //&& x * Constantes.MAP_X + vx <= gc.getWidth() - (Constantes.LARGEUR_PERSO + 32)
			x += vx / Constantes.MAP_Y;
		}
		if(mvt.isKeyDown(Input.KEY_LEFT) && !collisionX) { //&& x * Constantes.MAP_X - vx >= Constantes.LARGEUR_PERSO
			x -= vx / Constantes.MAP_Y;
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
		if(y < Constantes.MAP_Y - 2)  // pour la bordure + la hauteur du bonhomme
			vy += graviteY * delta / 1000f;
		else {
			vy = 0;
			compteurDeSaut = 0;
		}
	}


	public void graviteInversee(int delta) {
		if(y > 1) {
			vy += graviteY * delta / 1000f;
		}
		else {
			vy=0;
			compteurDeSaut = 0;
		}
	}

	public void sauter(int delta) {
		y += (vy * delta / 1000f) / 36;
	}

	public void sauterInversee(int delta) {
		y -= (vy * delta / 1000f) / 36;
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
	
	
	
	public void glisser() {
		// permettra de faire glisser le perso sur la glace du niveau 5
	}
}
