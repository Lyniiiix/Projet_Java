package main;
import org.newdawn.slick.state.StateBasedGame;

public class TrackingNiveau {
	private int precedent=0, actuel=0;
	
	
	public int getPrecedent() {
		return precedent;
	}
	
	public void pagePrecedente(StateBasedGame sbg) {
		if(precedent != actuel) {
			precedent = actuel;
		}
	}
	
	public void pageActuelle(StateBasedGame sbg) {
		actuel = sbg.getCurrentStateID();
	}
}
