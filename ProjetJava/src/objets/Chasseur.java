package objets;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Chasseur {
		private Image image;
		private float x = 250;
		private float y = 400;
		private int timer = 2000; // en ms   // on initialise le timer a 2s pour que le chasseur puisse tirer direct et pas attendre 2s avant de tirer

		private float xBalle1 = x, yBalle1 = y, xBalle2 = x, yBalle2 = y, xBalle3 = x, yBalle3 = y;
		private float vitesseBalles = 3;
		private int cadenceTir = 800; // en milisecondes
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
		
		public float getXBalle1() {
			return xBalle1;
		}
		
		public float getYBalle1() {
			return yBalle1;
		}
		
		public float getXBalle2() {
			return xBalle2;
		}
		
		public float getYBalle2() {
			return yBalle2;
		}
		
		public float getXBalle3() {
			return xBalle3;
		}
		
		public float getYBalle3() {
			return yBalle3;
		}
		

		public Chasseur(float x, float y) throws SlickException {
			image = new Image("res/images/chasseur.png");
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
			g.drawImage(image, x-32, y-36, x+32, y+36, 0, 0, image.getWidth(), image.getHeight());
			//g.drawRect(x, y-distanceTir, distanceTir, distanceTir*2);
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