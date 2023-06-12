// PERMET DE CREER UNE ZONE DE TEXTE

package main;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

public class ZoneTexte {
	
	//private TrueTypeFont font;
	private String txt;
	private int coX, coY;
	

	public ZoneTexte() {
		//font = new TrueTypeFont(new java.awt.Font("Comic sans MS",java.awt.Font.PLAIN , 42), false);
		txt="";
		coX = 465;
		coY = 300;
	}
	
	
	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public void resetTxt() {
		txt = "";
	}

	public void dessiner(Graphics g) {
		//g.setFont(font);
		g.drawString(txt, coX, coY);
	}
	
	
	public void maj(GameContainer gc) {
		Input input = gc.getInput();
		
		// permet de supprimer le texte
		if (txt.length() >= 1) {
			if (input.isKeyPressed(Input.KEY_BACK)) {
				txt = txt.substring(0,txt.length()-1);
			}
		}
		if (txt.length() < 20) {
			
			if (input.isKeyPressed(Input.KEY_SPACE)) {
				txt += " ";
			}
			

			// J ai juste pas trouve la touche MAJ ( celle avec le cadenas pour faire que des majuscules ) ET POURTANT JE LES AI TOUTES FAITES LES TOUCHES, ça m enerve !!!!!!!
			else if (input.isKeyPressed(Input.KEY_A)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="A";
				else
					txt += "a";
			}
			else if (input.isKeyPressed(Input.KEY_B)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="B";
				else
					txt += "b";
			}
			else if (input.isKeyPressed(Input.KEY_C)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="C";
				else
					txt += "c";
			}
			else if (input.isKeyPressed(Input.KEY_D)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="D";
				else
					txt += "d";
			}
			else if (input.isKeyPressed(Input.KEY_E)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="E";
				else
					txt += "e";
			}
			else if (input.isKeyPressed(Input.KEY_F)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="F";
				else
					txt += "f";
			}
			else if (input.isKeyPressed(Input.KEY_G)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="G";
				else
					txt += "g";
			}
			else if (input.isKeyPressed(Input.KEY_H)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="H";
				else
					txt += "h";
			}
			else if (input.isKeyPressed(Input.KEY_I)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="I";
				else
					txt += "i";
			}
			else if (input.isKeyPressed(Input.KEY_J)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="J";
				else
					txt += "j";
			}
			else if (input.isKeyPressed(Input.KEY_K)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="K";
				else
					txt += "k";
			}
			else if (input.isKeyPressed(Input.KEY_L)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="L";
				else
					txt += "l";
			}
			else if (input.isKeyPressed(Input.KEY_M)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="M";
				else
					txt += "m";
			}
			else if (input.isKeyPressed(Input.KEY_N)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="N";
				else
					txt += "n";
			}
			else if (input.isKeyPressed(Input.KEY_O)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="O";
				else
					txt += "o";
			}
			else if (input.isKeyPressed(Input.KEY_P)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="P";
				else
					txt += "p";
			}
			else if (input.isKeyPressed(Input.KEY_Q)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="Q";
				else
					txt += "q";
			}
			else if (input.isKeyPressed(Input.KEY_R)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="R";
				else
					txt += "r";
			}
			else if (input.isKeyPressed(Input.KEY_S)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="S";
				else
					txt += "s";
			}
			else if (input.isKeyPressed(Input.KEY_T)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="T";
				else
					txt += "t";
			}
			else if (input.isKeyPressed(Input.KEY_U)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="U";
				else
					txt += "u";
			}
			else if (input.isKeyPressed(Input.KEY_V)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="V";
				else
					txt += "v";
			}
			else if (input.isKeyPressed(Input.KEY_W)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="W";
				else
					txt += "w";
			}
			else if (input.isKeyPressed(Input.KEY_X)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="X";
				else
					txt += "x";
			}
			else if (input.isKeyPressed(Input.KEY_Y)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="Y";
				else
					txt += "y";
			}
			else if (input.isKeyPressed(Input.KEY_Z)) {
				if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))
					txt+="Z";
				else
					txt += "z";
			}
		}
	}
}
