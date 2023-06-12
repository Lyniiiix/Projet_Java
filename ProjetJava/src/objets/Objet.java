// PERMET DE CREER UN OBJET (BLOC) BLOQUANT

package objets;

public class Objet {
	private int id_objet;
	
	private String nom;
	
	private int x1; // pt x (px) de depart
	private int y1; // pt y (px) de depart
 	private int x2;
	private int y2;
	
	public Objet(String nom, int id_objet, int x1, int y1)
	{
		this.id_objet = id_objet;
		this.nom = nom;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x1 + 32;
		this.y2 = y1 + 36;
	}
	
	// GETTEURS & SETTEURS : coordonnées de l'Objet
	
	public int getx1()
	{
		return x1;
	}
	
	public int gety1()
	{
		return y1;
	}
	
	public int getx2()
	{
		return x2;
	}
	
	public int gety2()
	{
		return y2;
	}
	
	public String toString() // permet d'afficher les instances d'Objet en detail pour debug
	{
		return String.format("Objet %s : %s , x1: %s , x2: %s | y1: %s , y2: %s",id_objet,nom,x1,x2,y1,y2);
	}
}
