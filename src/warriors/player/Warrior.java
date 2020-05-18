package warriors.player;

public class Warrior extends Player {

	//Player(String _name, int _minLife, int _maxLife, int _minAttack, int _maxAttack )
	public Warrior() {
		super("", 5,10,5,10);
			
	}
	
	public Warrior(String name) {
		super(name, 5,10,5,10);
	}
	
	
	
}
