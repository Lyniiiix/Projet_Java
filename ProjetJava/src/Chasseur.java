import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Chasseur extends Pieges {
		private float x = 250;
		private float y = 400;
		private int timer = 2000; // en ms   // on initialise le timer a 2s pour que le chasseur puisse tirer direct et pas attendre 2s avant de tirer

		private float xBalle1 = x, yBalle1 = y, xBalle2 = x, yBalle2 = y, xBalle3 = x, yBalle3 = y;
		private float vitesseBalles = 3;
		private int cadenceTir = 2000; // en milisecondes
		private int distanceTir = 200;
		private boolean peutTirer = false;

		public void setTimer(int delta) {
			timer += delta;
		}

		public int getTimer() {
			return timer;
		}
		
		public int getDistanceTir() {
			return distanceTir;
		}
		
		public float getX() {
			return x;
		}
		
		public float getY() {
			return y;
		}
		
		public float getXBalle2() {
			return xBalle2;
		}
		

		Chasseur(float x, float y) {
			if (x >= 0 && x <= 1024 && y >= 0 && y <= 720) {
				this.x = x;
				this.y = y;
				xBalle1 = x;
				xBalle2 = x;
				xBalle3 = x;
				yBalle1 = y;
				yBalle2 = y;
				yBalle3 = y;
				
			} else {
				this.x = 250;
				this.y = 400;
			}
		}

		public void dessiner(Graphics g) {
			g.setColor(Color.red);
			g.fillRect(x, y, 20, 20);
			g.drawRect(x-distanceTir+10, y-distanceTir+10, distanceTir*2, distanceTir*2);
			g.setColor(Color.white); // permet d éviter que tout le reste devienne rouge apres
		}

		public void dessinerBalles(Graphics g) {
			g.setColor(Color.white);
			g.fillOval(xBalle1, yBalle1, 20, 20);
			g.fillOval(xBalle2, yBalle2, 20, 20);
			g.fillOval(xBalle3, yBalle3, 20, 20);
		}

		public void tirer() {
			if (timer >= cadenceTir) {
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