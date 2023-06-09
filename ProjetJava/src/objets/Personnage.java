package objets;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
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
	
	private boolean detection_sol;
	private Map map;
	
	private int compteurDeSaut = 0;	
	
	
	
	// CONSTRUCTEUR PAR DEFAUT
		public Personnage(Map map) throws SlickException
		{
			
			this.map = map;
			
			image = new Image("res/images/perso.png");
			
			graviteY = masse * acc_p;
			
			// on place au debut en bas a gauche
			this.x = 1;
			this.y = 18;
			
			vx = 3; // vitesse horizontale
			vy = 0;
		}


	
	// creer un perso aux cooordonnées voulues
	public Personnage(float x, float y) throws SlickException
	{
		image = new Image("res/images/perso.png");
		
		graviteY = masse * acc_p;
		
		this.x = x;
		this.y = y;
		
		vx = 3;
		vy = 0;
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
	
	// Coordonnées float
	public float getX() {
		return x;
	}
	public float getPosX_px()
	{
		return x * 32;
	}
	public void setX(float x) {
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
	public float getPosY_px()
	{
		return y * 36;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getVy() {
		return vy;
	}
	public void setVy(float vy) {
		this.vy = vy;
	}
	
	public boolean detecterSol()
	{
		Objet[] objets = map.getObjets();
		for (int i = 0; i < objets.length; i++)
		{
			System.out.println(objets[i]);
			if ((getPosY_px() + Constantes.HAUTEUR_CASE) >= (objets[i].gety1()) 
					&& (getPosY_px() + Constantes.HAUTEUR_CASE) < (objets[i].gety2()) 
					&& (getPosX_px()) >= objets[i].getx1() 
					&& (getPosX_px()) <= objets[i].getx2()
				)
			{
				return true;
			}
		}
		return false;
	}

	
	public boolean detecterCoinGauche()
	{
	    Objet[] objets = map.getObjets();
	    float posY_centre = getPosY_px() + (Constantes.HAUTEUR_PERSO / 2);

	    for (int i = 0; i < objets.length; i++)
	    {
	        System.out.println(objets[i]);

	        int objetX1 = objets[i].getx1();
	        int objetX2 = objets[i].getx2();
	        int objetY1 = objets[i].gety1();
	        int objetY2 = objets[i].gety2();

	        // Vérification du coin gauche
	        if (posY_centre >= objetY1 &&
	            posY_centre < objetY2 &&
	            getPosX_px() >= objetX1 &&
	            getPosX_px() <= objetX2)
	        {
	            return true;
	        }
	    }

	    return false;
	}

	public boolean detecterCoinDroit()
	{
	    Objet[] objets = map.getObjets();
	    float posX_droit = getPosX_px() + (Constantes.LARGEUR_PERSO);
	    float posY_centre = getPosY_px() + (Constantes.HAUTEUR_PERSO / 2);

	    for (int i = 0; i < objets.length; i++)
	    {
	        System.out.println(objets[i]);

	        int objetX1 = objets[i].getx1();
	        int objetX2 = objets[i].getx2();
	        int objetY1 = objets[i].gety1();
	        int objetY2 = objets[i].gety2();

	        // Vérification du coin droit
	        if (posY_centre >= objetY1 &&
	            posY_centre < objetY2 &&
	            posX_droit >= objetX1 &&
	            posX_droit <= objetX2)
	        {
	            return true;
	        }
	    }

	    return false;
	}



	
	// *********************************************** //
	// DESSINER BONHOMME
	public void dessiner(Graphics g) {  // qd on aura l image y aura plus besoin de graphics g
		g.drawImage(image, x*32, y*36, x*32+32, y*36+36, 0, 0, image.getWidth(), image.getHeight());
		//g.drawRect(x*32-32, y*36-36, 3*32, 2*36);
	}
	
	
	
	// DEPLACER BONHOMME
	public void deplacer(GameContainer gc, boolean collisionX) {
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_RIGHT) && !detecterCoinDroit())
		{
			x += vx / Constantes.MAP_X;
		}
		if(mvt.isKeyDown(Input.KEY_LEFT) && !detecterCoinGauche())
		{
			x -= vx / Constantes.MAP_X;
		}	
	
	}
	
	
	public void deplacerInverse(GameContainer gc, boolean collisionX) {
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_RIGHT) && collisionX)
		{
			x -= vx / Constantes.MAP_X;
		}
		if(mvt.isKeyDown(Input.KEY_LEFT) && collisionX)
		{
			x += vx / Constantes.MAP_X;
		}	
	
	}


	// PERMET DE FAIRE DES SAUTS EN APPUYANTS UNE FOIS SUR LA BARRE ESPACE
	public void sautNormal(GameContainer gc) {
		Input mvt = gc.getInput();
		
		if(mvt.isKeyPressed(Input.KEY_SPACE) && compteurDeSaut <1 ) {	
			vy = -400;	
			compteurDeSaut++;
		}
		
	}
	
	/*
	public boolean detecterSol()
	{
		System.out.println(getPosX_px() + " " + getPosY_px() + " - " + 22*36 + " " + 23*36);
		//if (getPosY_px() < Constantes.HAUTEUR_ECRAN - Constantes.HAUTEUR_PERSO*2)
		if ((getPosY_px() + 36) >= (22*36) && (getPosY_px() + 36) < (23*36))// && (getPosX_px() + 16) >= 32 && (getPosX_px() + 16) <= 64)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	*/
	



	
	//PERMET LA GRAVITE ET LES SAUTS DU BONHOMME
	public void gravite(int delta) {
		//System.out.println(x + " " + y);
		if(!detecterSol())  // pour la bordure + la hauteur du bonhomme 
			{
			System.out.println(vy);
			vy += graviteY * delta / 1000f;
	}
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
		y += (vy * delta / 1000f) / Constantes.HAUTEUR_CASE;
	}

	public void sauterInversee(int delta) {
		y -= (vy * delta / 1000f) / Constantes.HAUTEUR_CASE;
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
	
	
	
	public static int niveauMort(StateBasedGame sbg){
		return sbg.getCurrentStateID();
	}
}
