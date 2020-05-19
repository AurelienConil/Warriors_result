package warriors.board;
import warriors.player.Player;

public class EmptyCase implements Case  {
	
	public String toString() {
		return "Une case vide";
	}
	
	@Override
	public void interact(Player p) {
		System.out.println("Vous venez de rencontrer : "+this.toString());
		
	}
}
