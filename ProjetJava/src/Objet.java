
public class Objet {
	private static int compteur_objets = 1;
	
	private String nom;
	
	private int id_tuile;
	
	private int x1; // pt x en px de depart
	private int y1; // pt y en px d'arrivee
 	private int x2;
	private int y2;
	
	public Objet(String nom, int x1, int y1)
	{
		compteur_objets ++;
		this.nom = nom;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x1 + 32;
		this.y2 = y1 + 36;
	}
	
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
	
	public void afficherDepartPX()
	{
		System.out.println(String.format("%s : x1 : %s ; y1 : %s", nom,x1,y1));
	}
	
	public String toString()
	{
		return String.format("Objet %s : %s , x1: %s , x2 %s | y1: %s , y2: %s ",compteur_objets,nom,x1,x2,y1,y2);
	}
}
