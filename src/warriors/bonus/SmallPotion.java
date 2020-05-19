package warriors.bonus;

import warriors.Fight;
import warriors.player.Player;

public class SmallPotion extends Bonus{
	
	public SmallPotion() {
		super("Petite Potion");
	}
	
	@Override
	public void interact(Player p, Fight f) {
		System.out.println("Vous venez de rencontrer : "+this.toString());
		p.setLife(p.getLife() + 2);
		System.out.println("Votre vie est désormais de :"+p.getLife());
		
	}
}
