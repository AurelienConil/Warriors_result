package warriors.weapon;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import warriors.Fight;
import warriors.player.Player;
import warriors.player.Warrior;

public class Sword extends Weapon {
	
	public Sword() {
		super("Epee", 5);
		loadImage();
	}
	
	
	@Override
	public void interact(Player p, Fight f) {
		System.out.println("Vous venez de trouver :"+this.toString());
		if(p instanceof Warrior) {
			p.setWeapon(this);System.out.println("Vous force est desormais de : "+p.getFinalAttack() );
		}else {
			System.out.println("Vous ne pouvez pas l'utiliser");
		}
	}
	
	private void loadImage() {
		try {
			img = ImageIO.read(new File("data/img/epeeUI.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
}
