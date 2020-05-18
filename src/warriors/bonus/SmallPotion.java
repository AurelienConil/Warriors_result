package warriors.bonus;

import warriors.player.Player;

public class SmallPotion extends Bonus{
	
	public SmallPotion() {
		super("Petite Potion");
	}
	
	@Override
	public void interact(Player p) {
		System.out.println("Vous venez de rencontrer : "+this.toString());
		p.setLife(p.getLife() + 2);
		System.out.println("Votre vie est désormais de :"+p.getLife());
		
	}
}
