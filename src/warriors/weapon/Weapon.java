package warriors.weapon;
import warriors.player.Player;

import java.awt.Image;

import warriors.Fight;
import warriors.board.Case;

public class Weapon implements Case {

	public Weapon( String _name, int _force) {
		
		this.name = _name;
		this.force = _force;
		this.MAX_FORCE = 15;
	}
	
	public Weapon() {
		this.name = "-vide-";
		this.force = 0;
		this.MAX_FORCE = 15;
	}
	
	private String name;
	private int force;
	private final int MAX_FORCE;
	protected Image img;
	
	
	
	public String getName() {
		return this.name;
	}
	// No needs to be setted
	public int getForce() {
		return this.force;
	}
	public void setForce(int i) {
		if(i<this.MAX_FORCE) {
			this.force = i;
		}else {
			this.force = this.MAX_FORCE;
		}
	}
	
	public String toString() {
		return this.name+" | Force="+this.force;
	}
	
	@Override
	public void interact(Player p, Fight f) {
		System.out.println("Vous de rencontrer :"+this.toString());
		
	}
	
	public Image getImage() {
		return img;
	}
	
}
