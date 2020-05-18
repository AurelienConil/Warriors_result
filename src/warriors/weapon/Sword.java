package warriors.weapon;

import warriors.player.Player;
import warriors.player.Warrior;

public class Sword extends Weapon {
	
	public Sword() {
		super("Epee", 5);
	}
	
	
	@Override
	public void interact(Player p) {
		System.out.println("Vous venez de trouver :"+this.toString());
		if(p instanceof Warrior) {
			p.setWeapon(this);System.out.println("Vous force est desormais de : "+p.getFinalAttack() );
		}else {
			System.out.println("Vous ne pouvez pas l'utiliser");
		}
	}
		
	
}
