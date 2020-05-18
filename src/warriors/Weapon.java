package warriors;

public class Weapon {

	public Weapon( String _name, int _force) {
		
		this.name = _name;
		this.force = _force;
		this.MAX_FORCE = 15;
	}
	
	private String name;
	private int force;
	private final int MAX_FORCE;
	
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
	
}
