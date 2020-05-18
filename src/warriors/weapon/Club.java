package warriors.weapon;

import warriors.player.Player;
import warriors.player.Warrior;

public class Club extends Weapon {
	public Club() {
		super("Massue", 3);
	}
	
	@Override
	public void interact(Player p) {
		System.out.println("Vous venez de trouver :"+this.toString());
		if(p instanceof Warrior) {
			p.setWeapon(this);
			System.out.println("Vous force est desormais de : "+p.getFinalAttack() );
		}else {
			System.out.println("Vous ne pouvez pas l'utiliser");
		}
		
	}
}

