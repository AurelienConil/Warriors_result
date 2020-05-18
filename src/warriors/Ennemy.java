package warriors;

public class Ennemy extends Case {

	public Ennemy() {
		this.name = "Gros Mechant";
		this.attack = 3;
		this.life = 3;
	}
	
	public Ennemy(String _name, int _attack, int _life ) {
		this.name = _name;
		this.attack = _attack;
		this.life = _life;
	}
	
	private String name;
	private int attack;
	private int life;
	
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
	public void setLife(int i) {
		if(i>0) {
			this.life = i;
		}
	}
	public String toString() {
		return "Ennemy : "+this.getName()+" | Vie="+this.getLife()+" | Force="+this.getAttack()+" | Vie="+this.getLife();
	}
	
	@Override
	public void interact(Player p) {
		System.out.println("Vous venez de rencontrer :"+this.toString());
		
	}
	
}
