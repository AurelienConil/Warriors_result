package warriors.bonus;

import warriors.Fight;
import warriors.player.Player;

public class BigPotion extends Bonus{
	
	public BigPotion() {
		super("Grande Potion");
	}
	
	@Override
	public void interact(Player p, Fight f) {
		System.out.println("Vous venez de rencontrer : "+this.toString());
		p.setLife(p.getLife() + 5);
		System.out.println("Votre vie est désormais de :"+p.getLife());
		
	}
}
