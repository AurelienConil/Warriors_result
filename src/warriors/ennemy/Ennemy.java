package warriors.ennemy;
import warriors.player.Player;
import warriors.Fight;
import warriors.board.Case;

public abstract class Ennemy implements Case {

	public Ennemy() {
		this.name = "Gros Mechant";
		this.attack = 3;
		this.life = 3;
		this.isAlive = true;
	}
	
	public Ennemy(String _name, int _attack, int _life ) {
		this.name = _name;
		this.attack = _attack;
		this.life = _life;
		this.isAlive = true;
	}
	
	private String name;
	private int attack;
	private int life;
	private boolean isAlive;
	
	public String getName() {
		return this.name;
	}
	public int getAttack() {
		return this.attack;
	}
	public void setAttack(int i) {
		if(i>0) {
			this.attack = i;
		}
	}
	public int getLife() {
		return this.life;
	}
	public boolean isAlive() {
		return this.isAlive;
	}
	public void setLife(int i) {
		if(i>0) {
			this.life = i;
		}else {
			this.life = 0;
			this.isAlive = false;
		}
	}
	public void reduceLife(int i) {
		if(i<this.life) {
			this.life -= i;
		}else {
			this.life=0;
			this.isAlive = false;
		}
	}
	public String toString() {
		return "Ennemy : "+this.getName()+" | Vie="+this.getLife()+" | Force="+this.getAttack()+" | Vie="+this.getLife();
	}
	
	@Override
	public void interact(Player p, Fight f) {
		
		f.initFight(p, this);
		
	}
}
	

