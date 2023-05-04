import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class Pieges {

	public Pieges() {
		
	}




	public class Laser extends Pieges {
		private float x, y;
		private int timer = 0;

		Laser(float x, float y) {
			if (x >= 0 && x <= 1024 && y >= 0 && y <= 720) {
				this.x = x;
				this.y = y;
			} 
			else {
				this.x = 10;
				this.y = 50;
			}
		}

		Laser() {
			this.x = 10;
			this.y = 50;
		}

		public void setTimer(int delta) {
			timer += delta;
		}

		public int getTimer() {
			return timer;
		}

		
		public void dessiner(Graphics g) {
			if (timer < 2000) {
				g.setColor(Color.white);
				g.fillOval(x, y, 20, 20);
				g.fillOval(x + 70, y, 20, 20);
			}

			if (timer >= 2000 && timer <= 4000) {
				g.setColor(Color.blue);
				g.fillOval(x, y, 20, 20);
				g.fillOval(x + 70, y, 20, 20);
				g.fillRect(x + 10, y + 5, 70, 10);
			}

			if (timer > 4000)
				timer = 0;
		}
	}




	public class Chasseur extends Pieges {
		private float x = 50;
		private float y = 400;
		private int timer = 0;

		private float xBalle1 = x, yBalle1 = y, xBalle2 = x, yBalle2 = y, xBalle3 = x, yBalle3 = y;
		private float vitesseBalles = 3;
		private int cadenceTir = 2; // en secondes
		private int distanceTir = 200;
		private boolean peutTirer = false;

		public void setTimer(int delta) {
			timer += delta;
		}

		public int getTimer() {
			return timer;
		}

		Chasseur(float x, float y) {
			if (x >= 0 && x <= 1024 && y >= 0 && y <= 720) {
				this.x = x;
				this.y = y;
			} else {
				this.x = 10;
				this.y = 50;
			}
		}

		public void dessiner(Graphics g) {
			g.setColor(Color.red);
			g.fillRect(x, y, 20, 20);
		}

		public void dessinerBalles(Graphics g) {
			g.setColor(Color.white);
			g.fillOval(xBalle1, yBalle1, 20, 20);
			g.fillOval(xBalle2, yBalle2, 20, 20);
			g.fillOval(xBalle3, yBalle3, 20, 20);
		}

		public void tirer() {
			if (timer >= cadenceTir * 1000) {
				timer = 0;
				peutTirer = true;
			}

			if (peutTirer) {
				xBalle1 += vitesseBalles;
				yBalle1 -= vitesseBalles;

				xBalle2 += vitesseBalles;

				xBalle3 += vitesseBalles;
				yBalle3 += vitesseBalles;
			}

			if (xBalle2 >= x + distanceTir) {
				xBalle1 = x;
				yBalle1 = y;

				xBalle2 = x;
				yBalle2 = y;

				xBalle3 = x;
				yBalle3 = y;
				peutTirer = false;
			}
		}
	}


}
