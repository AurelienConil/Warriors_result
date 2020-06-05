package warriors.player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;
import warriors.weapon.Weapon;

public abstract class Player extends JPanel {
	
	
	public Player() {
		super();
		this.name = "Anonyme";
		this.minAttack = 9;
		this.MAX_ATTACK = 15;
		this.minLife = 3;
		this.maxLife = 10;
		this.weapon = new Weapon();
		this.isAlive = true;
		initUI();
		
		
	}
	
	/**
	 * Constructor of Player with information
	 * 
	 *
	 * @param String name , Player_type type
	 * @return void
	 */
	public Player(String _name, int _minLife, int _maxLife, int _minAttack, int _maxAttack ) {
		super();
		this.name = _name;
		this.minLife = _minLife;
		this.maxLife = _maxLife;
		this.minAttack = _minAttack;
		this.MAX_ATTACK = _maxAttack;
		this.life = (int)( Math.random()*(this.maxLife-this.minLife) + this.minLife  );
		this.attack = (int)(Math.random()*(this.MAX_ATTACK-this.minAttack) + this.minAttack);
		this.isAlive = true;
		weapon = new Weapon();
		initUI();
		
	}
	
	//Name of player
	private String name;
	// Life lever until dying
	private int life;
	// Is alive of dead
	private boolean isAlive;
	// Minimum life when initialized
	public final int minLife;
	// Maximum life during all the game ( init + game )
	public final int maxLife;
	// Level of attack without weapon
	private int attack;
	// Minimum level of attack when initialized
	public final int minAttack;
	// Maximum level of attack considering the addition of the weapon attack
	public final int MAX_ATTACK;
	//Weapon
	private Weapon weapon;
	//Image
	protected Image img;
	
		
	/**
	 * Return all information in one string
	 * 
	 *
	 * @param void
	 * @return String 
	 */
	public String toString() {
		return ("Personnage = "+this.name+" | type="+this.getPlayerTypeToString()+" | vie="+this.life+" | attack="+this.attack+" | Arme="+this.weapon.getName());
	}
	
	/**
	 * Getter from name attribute
	 * 
	 *
	 * @param void
	 * @return String 
	 */
	public String getName() {
		return this.name;
	}
	
	
	/**
	 * Setter from name attribute
	 * 
	 *
	 * @param String new name
	 * @return Void 
	 */
	public void setName(String _name) {
		if(_name.length()>0) {
			this.name = _name;
		}
	}
	
	/**
	 * Get type of concrete abstraction of player type, 
	 * given in String 
	 *
	 * @param void
	 * @return String 
	 */
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
	 * Getter from life attribute
	 * 
	 *
	 * @param void
	 * @return int life level
	 */
	public int getLife() {
		return this.life;
	}
	
	/**
	 * Getter from isAlive attribute
	 * 
	 *
	 * @param void
	 * @return int life level
	 */
	public boolean isAlive() {
		return this.isAlive;
	}
	
	
	
	/**
	 * Setter from life attribute
	 * 
	 *
	 * @param int new life
	 * @return void
	 */
	public void setLife(int l) {

		if(l<=this.maxLife) {
			this.life = l;
		}else {
			this.life = maxLife;
		}
			
	}
	
	/**
	 * Decrease life
	 * 
	 *
	 * @param int value to decrease life from
	 * @return void
	 */
	public void reduceLife(int i) {
		if(i<this.life) {
			this.life -= i;
		}else {
			this.life=0;
			this.isAlive = false;
		}
	}
	
	/**
	 * Getter from attack attribute
	 * 
	 *
	 * @param void
	 * @return int attack level
	 */
	public int getAttack() {
		return this.attack;
	}
	
	/**
	 * Get the addition of player force + weapon force
	 * This addition cannot exceed player max force
	 * 
	 *
	 * @param void
	 * @return int attack level
	 */
	public int getFinalAttack() {
		if( (this.attack + this.weapon.getForce()) > this.MAX_ATTACK) {
			return this.MAX_ATTACK;
		}else {
			return (this.attack + this.weapon.getForce());
		}
	}
	
	/**
	 * Setter from attack attribute
	 * 
	 *
	 * @param void
	 * @return int attack level
	 */
	public void setAttack(int a) {

		if(a <= this.MAX_ATTACK) {
			this.attack = a;
		}else {
			this.attack = this.MAX_ATTACK;
		}
		
	}
	
	public void setWeapon(Weapon w) {
		if(w != null) {
			this.weapon = w;
		}
	}
	
	private void initUI() {
		this.setSize(200, 400);
		this.setPreferredSize(new Dimension(200, 400));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(this.img != null) {
			g2d.drawImage(img, 0, 0, Color.white, null);
		}
		if(this.weapon != null) {
			if(this.weapon.getImage() != null) {
				g2d.drawImage(this.weapon.getImage(), 0, 0, null, null);
			}
		}
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font( "SansSerif", Font.BOLD, 20 ));
		g2d.drawString(this.name, 10, 20);

	}
	

}
