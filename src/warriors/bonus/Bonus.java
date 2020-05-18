package warriors.bonus;
import warriors.player.Player;
import warriors.board.Case;

public abstract class Bonus extends Case {
	
	public Bonus(String _name) {
		this.name = _name;
	}
	
	private String name;
	public String getName() {
		return this.name;
	}
	public String toString() {
		return "Bonus : "+this.getName();
	}
	
	
	
}
