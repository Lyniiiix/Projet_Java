// initialise une Map
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.tiled.TiledMap;

public class Map {
	
	private String nom;
	
	private Image image_fond;
	
	private TiledMap map;
	
	
	Map(GameContainer gc, Image image_fond, TiledMap map){
		this.image_fond = image_fond;
		this.map = map;
	}
}

