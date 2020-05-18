package warriors;

public class Bonus extends Case {
	
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
	
	@Override
	public void interact(Player p) {
		System.out.println("Vous venez de rencontrer : "+this.toString());
		
	}
	
}
