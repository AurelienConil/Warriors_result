package warriors;

import java.math.*;

public class Player {
	
	
	public Player() {
		minAttack= 6;
		maxAttack = 15;
		minLife = 6;
		maxLife = 10;
	}
	
	/**
	 * Constructor of Player with information
	 * 
	 *
	 * @param String name , Player_type type
	 * @return void
	 */
	public Player(String _name, Player_type _type ) {
		name = _name;
		player_type = _type;
		life = (int) (Math.random() * 4 + 6);
		attack = (int) (Math.random() * 9 + 6);
		minLife = 6;
		maxLife = 10;
		minAttack= 6;
		maxAttack = 15;
		
		
	}
	
	private String name;
	public Player_type player_type;
	public enum Player_type {
		MAGICIEN, GUERRIER
	}
	private int life;
	public final int maxLife;
	public final int minLife;
	private int attack;
	public final int maxAttack;
	public final int minAttack;
	
	/**
	 * Return all information in one string
	 * 
	 *
	 * @param void
	 * @return String 
	 */
	public String toString() {
		return ("Personnage = "+this.name+" | type= Magicien | vie="+this.life+" | attack="+this.attack);
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String _name) {
		if(_name.length()>0) {
			this.name = _name;
		}
	}
	public Player_type getType() {
		return this.player_type;
	}
	
	
	
	/**
	 * Return a string version on any Player_type
	 * 
	 *
	 * @param Player_type
	 * @return String
	 */
	public String getTypeToString(Player_type type) {
		if(type==Player_type.MAGICIEN) {
			return "Magicien";
		}else if(type==Player_type.GUERRIER) {
			return "Guerrier";
		}else {
			return "";
		}
	}
	public void setType(Player_type _type) {
		this.player_type = _type;
	}
	public int getLife() {
		return this.life;
	}
	public void setLife(int l) {
		if(l >= this.minLife) {
			if(l<=this.maxLife) {
				this.life = l;
			}else {
				this.life = maxLife;
			}
			
		}else {
			this.life = minLife;
		}
	}
	public int getAttack() {
		return this.attack;
	}
	public void setAttack(int a) {
		if(a >= this.minAttack) {
			if(a <= this.maxAttack) {
				this.attack = a;
			}else {
				this.attack = this.maxAttack;
			}
		}else {
			this.attack = minAttack;
		}
	}
	

}
