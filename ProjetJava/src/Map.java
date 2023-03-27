
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Map {
	
	private Image img;
	
	
	Map(GameContainer gc,Image img){
		this.img = img;
	}
	
	public void dessiner (Graphics g, int map[][])
	{
		
		int mapWidth = 42;  // faut diviser par 3 si tu reveux tes valeurs d avant
		int mapHeight = 30;
		
		int blockSize = 73;
		
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                if (map[i][j] == 0) {
                    int x = j * blockSize;
                    int y = i * blockSize;
                    //g.setColor(Color.white);
                    //g.drawRect(x, y, blockSize, blockSize);
                    g.drawImage(img, x, y);
                    g.drawImage(img, x, y);
                }
            }
        }
	}
}

