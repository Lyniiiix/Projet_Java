import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Pieges {
	protected float x, y;



	public class Laser extends Pieges{
		private float x,y;
		private int timer = 0;
		
		Laser(float x, float y){
			if(x>=0 && x<=1024 && y>=0 && y<=720) {  
				this.x = x;
				this.y = y;
			}
			else {
				this.x=10;
				this.y = 50;
			}
		}
		
		Laser(){
			this.x=10;
			this.y = 50;
		}
		
		public void dessiner(Graphics g) {
			g.setColor(Color.white);
			g.drawOval(x, y, 5, 5);
			g.drawOval(x+50, y, 5, 5);
			/*if(active(delta)) {
				g.drawLine(x, y, x+50, y);
			}*/
		}
		
		public boolean active(int delta) {
			timer += delta;
			if(timer < 4000)
				return false;
			if(timer >= 4000 && timer <= 6000)
				return true;
			else {
				timer = 0;
				return false;
			}
		}
	}
	
	
	
	
	
	public class Chasseur extends Pieges{
		private float x,y;
		private int timer = 0;
		
		Chasseur(float x, float y){
			if(x>=0 && x<=1024 && y>=0 && y<=720) {  
				this.x = x;
				this.y = y;
			}
			else {
				this.x=10;
				this.y = 50;
			}
		}
		
		
		public void dessiner(Graphics g) {
			g.setColor(Color.red);
			g.drawRect(x, y, 10, 10);
			/*if(active(delta)) {
				g.drawLine(x, y, x+50, y);
			}*/
		}
		
		
		public boolean active(int delta) {
			timer += delta;
			if(timer < 4000)
				return false;
			if(timer >= 4000 && timer <= 6000)
				return true;
			else {
				timer = 0;
				return false;
			}
		}
	}
	
}
/*
public class Bombes extends Pieges{
	
}


public class Archer extends Pieges{
	
}*/