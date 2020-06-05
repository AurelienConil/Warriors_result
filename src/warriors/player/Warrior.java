package warriors.player;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Warrior extends Player {

	//Player(String _name, int _minLife, int _maxLife, int _minAttack, int _maxAttack )
	public Warrior() {
		super("", 5,10,5,10);
		loadImage();
		
			
	}
	
	public Warrior(String name) {
		super(name, 5,10,5,10);
		loadImage();

	}
	
	private void loadImage() {
		try {
			img = ImageIO.read(new File("data/img/guerrierUI.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
