package warriors.weapon;

import warriors.Fight;
import warriors.player.Magician;
import warriors.player.Player;

public class FireBall extends Weapon {
	
	
	public FireBall() {
		super("Boule de feu", 7);
	}
	
	
	@Override
	public void interact(Player p, Fight f) {
		System.out.println("Vous venez de trouver :"+this.toString());
		if(p instanceof Magician) {
			p.setWeapon(this);System.out.println("Vous force est desormais de : "+p.getFinalAttack() );
		}else {
			System.out.println("Vous ne pouvez pas l'utiliser");
		}
	}
}
