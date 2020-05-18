package warriors;

import java.math.*;

public abstract class Player {
	
	
	public Player() {;
		minAttack = 9;
		maxAttack = 15;
		minLife = 3;
		maxLife = 10;
	}
	
	/**
	 * Constructor of Player with information
	 * 
	 *
	 * @param String name , Player_type type
	 * @return void
	 */
	public Player(String _name, int _minLife, int _maxLife, int _minAttack, int _maxAttack ) {
		this.name = _name;
		
		this.minLife = _minLife;
		this.maxLife = _maxLife;
		this.minAttack = _minAttack;
		this.maxAttack = _maxAttack;
		this.life = (int)( Math.random()*(this.maxLife-this.minLife) + this.minLife  );
		this.attack = (int)(Math.random()*(this.maxAttack-this.minAttack) + this.minAttack);
		
	}
	
	private String name;
	private int life;
	public final int minLife;
	public final int maxLife;
	private int attack;
	public final int minAttack;
	public final int maxAttack;
	
	/**
	 * Return all information in one string
	 * 
	 *
	 * @param void
	 * @return String 
	 */
	public String toString() {
		return ("Personnage = "+this.name+" | type="+this.getPlayerTypeToString()+" | vie="+this.life+" | attack="+this.attack);
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String _name) {
		if(_name.length()>0) {
			this.name = _name;
		}
	}
	
	public String getPlayerTypeToString() {
		if( this instanceof Magician) {
			return "Magicien";
		}
		else if( this instanceof Warrior) {
			return "Guerrier";
		}
		else {
			return "UNDEFINED";
		}
	}
	
	
	
	/**
	 * Return a string version on any Player_type
	 * 
	 *
	 * @param Player_type
	 * @return String
	 */


	public int getLife() {
		return this.life;
	}
	
	public void setLife(int l) {

		if(l<=this.maxLife) {
			this.life = l;
		}else {
			this.life = maxLife;
		}
			
	}
	
	public int getAttack() {
		return this.attack;
	}
	public void setAttack(int a) {

		if(a <= this.maxAttack) {
			this.attack = a;
		}else {
			this.attack = this.maxAttack;
		}
		
	}
	

}
