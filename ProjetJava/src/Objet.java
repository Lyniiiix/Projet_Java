
public class Objet {
	private String nom;
	
	private int id_tuile;
	
	private int x1; // pt x en px de depart
	private int y1; // pt y en px d'arrivee
 	private int x2 = x1 + 32;
	private int y2 = y1 + 36;
	
	public Objet(String nom, int x1, int y1)
	{
		this.nom = nom;
		this.x1 = x1;
		this.y1 = y1;
	}
	
	public void afficherDepartPX()
	{
		System.out.println(String.format("%s : x1 : %s ; y1 : %s", nom,x1,y1));
	}
}
