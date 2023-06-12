// PERMET DE CREER LE PERSONNAGE

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
	private Image image_d; // image perso profil droit
	private Image image_g; // image perso profil gauche
	
	private float x,y, vx, vy, graviteY; // variables de positions et de vitesses
	private int masse = 70; // masse (pour modifier la gravite)
	private float acc_p = 9.81f; // acceleration de la pesanteur
	
	private boolean detection_sol; // pour les collisions avec le sol
	private Map map; // map sur laquelle est le personnage
	
	private int compteurDeSaut = 0; // permet de limiter le saut
	private String orientation; // orientation 
	
	
	
	// CONSTRUCTEUR PAR DEFAUT
		public Personnage(Map map) throws SlickException
		{
			
			this.map = map;
			
			image_d = new Image("res/images/perso.png");
			image_g = new Image("res/images/perso g.png");
			
			graviteY = masse * acc_p;
			
			// on place au debut en bas a gauche
			this.x = 1;
			this.y = 18;
			
			vx = 3; // vitesse horizontale
			vy = 0;
			
			this.orientation = "droite";
		}


	
	// creer un perso aux cooordonnées voulues
	public Personnage(float x, float y) throws SlickException
	{
		image_d = new Image("res/images/perso.png");
		image_g = new Image("res/images/perso g.png");
		
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
	
	private boolean detecterSol()
	{
		Objet[] objets = map.getObjets();
		for (int i = 0; i < objets.length; i++)
		{
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

	
	private boolean detecterCoinGauche()
	{
	    Objet[] objets = map.getObjets();
	    float posY_centre = getPosY_px() + (Constantes.HAUTEUR_PERSO / 2);

	    for (int i = 0; i < objets.length; i++)
	    {

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

	private boolean detecterCoinDroit()
	{
	    Objet[] objets = map.getObjets();
	    float posX_droit = getPosX_px() + (Constantes.LARGEUR_PERSO);
	    float posY_centre = getPosY_px() + (Constantes.HAUTEUR_PERSO / 2);

	    for (int i = 0; i < objets.length; i++)
	    {

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
	
	public void detecterPlafond()
	{
		int count = 0;
		
	    Objet[] objets = map.getObjets();
	    float posY = getPosY_px();
	    float posX = getPosX_px();
	    float posX_droite = posX + Constantes.LARGEUR_PERSO;

	    for (int i = 0; i < objets.length; i++)
	    {
	        int objetX1 = objets[i].getx1();
	        int objetX2 = objets[i].getx2();
	        int objetY2 = objets[i].gety2();

	        // Vérification du plafond
	        if (posY < objetY2 &&
	        	posX >= objetX1 &&
	            posX_droite <= objetX2)
	        {
	        	count ++;
	        	System.out.println("yes" + " " + count);
	        }
	    }
	}





	
	// *********************************************** //
	// DESSINER BONHOMME
	public void dessiner(Graphics g) {  // qd on aura l image y aura plus besoin de graphics g
		//g.drawRect(x*32-32, y*36-36, 3*32, 2*36);
		if (this.orientation == "droite")
		{
			g.drawImage(image_d, x*32, y*36, x*32+32, y*36+36, 0, 0, image_d.getWidth(), image_d.getHeight());

		} else {
			g.drawImage(image_g, x*32, y*36, x*32+32, y*36+36, 0, 0, image_g.getWidth(), image_g.getHeight());

		}
	}
	
	
	
	// DEPLACER BONHOMME
	public void deplacer(GameContainer gc) {
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_RIGHT) && !detecterCoinDroit())
		{
			this.orientation = "droite";
			x += vx / Constantes.MAP_X;
		}
		if(mvt.isKeyDown(Input.KEY_LEFT) && !detecterCoinGauche())
		{
			this.orientation = "gauche";
			x -= vx / Constantes.MAP_X;
		}	
	
	}
	
	
	public void deplacerInverse(GameContainer gc) {
		Input mvt = gc.getInput();
		
		if(mvt.isKeyDown(Input.KEY_RIGHT))
		{
			x -= vx / Constantes.MAP_X;
		}
		if(mvt.isKeyDown(Input.KEY_LEFT))
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
	



	
	//PERMET LA GRAVITE ET LES SAUTS DU BONHOMME
	public void gravite(int delta) {
		//System.out.println(x + " " + y);
		if(!detecterSol())  // pour la bordure + la hauteur du bonhomme 
			{
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


	
	
	
	public static int niveauMort(StateBasedGame sbg){
		return sbg.getCurrentStateID();
	}
}
