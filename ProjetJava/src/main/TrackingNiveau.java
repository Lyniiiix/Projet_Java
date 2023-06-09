package main;
import org.newdawn.slick.state.StateBasedGame;

public class TrackingNiveau {
	
	public final int IDNiv1 = 1;
	public final int IDNiv2 = 2;
	public final int IDNiv3 = 3;
	public final int IDNiv4 = 4;
	public final int IDNiv5 = 5;
	
	private int actuel = 0;
	private int precedent = 0;
	
	
	public int getPrecedent(){
		return precedent;
	}
	
	public void IDActuel(StateBasedGame sbg){
		if(sbg.getCurrentStateID() != actuel){
			precedent = actuel;
			actuel = sbg.getCurrentStateID();
		}
	}
	
	public void retry(StateBasedGame sbg){
		sbg.enterState(precedent);
	}
	
	
}

