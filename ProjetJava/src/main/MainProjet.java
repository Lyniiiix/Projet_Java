package main;

import niveaux.*;
import objets.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainProjet {

	public static void main(String[] args) throws SlickException {
		ProjetJava jeuProjet = new ProjetJava(Constantes.NOM_JEU);
		AppGameContainer app= new AppGameContainer(jeuProjet);
		app.setDisplayMode(Constantes.LARGEUR_ECRAN, Constantes.HAUTEUR_ECRAN, false);
		app.setTargetFrameRate(Constantes.FPS);
		app.setShowFPS(false);
		app.start();
	}
}
