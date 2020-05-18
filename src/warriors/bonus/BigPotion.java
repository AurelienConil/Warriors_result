package warriors.bonus;

import warriors.player.Player;

public class BigPotion extends Bonus{
	
	public BigPotion() {
		super("Grande Potion");
	}
	
	@Override
	public void interact(Player p) {
		System.out.println("Vous venez de rencontrer : "+this.toString());
		
	}
}
