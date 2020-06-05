package warriors.player;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import warriors.weapon.Club;
import warriors.weapon.FireBall;
import warriors.weapon.Lightning;

public class Magician extends Player {
		
	//Player(String _name, int _minLife, int _maxLife, int _minAttack, int _maxAttack )
	public Magician() {
		super("", 3,6,8,15);
		loadImage();
		
			
	}
	
	public Magician(String name) {
		super(name, 3,6,8,15);
		loadImage();
			
	}
	
	private void loadImage() {
		try {
			img = ImageIO.read(new File("data/img/magicienUI.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
