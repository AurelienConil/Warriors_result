package warriors;

public class EmptyCase extends Case  {
	
	public String toString() {
		return "Une case vide";
	}
	
	@Override
	public void interact(Player p) {
		System.out.println("Vous venez de rencontrer : "+this.toString());
		
	}
}
