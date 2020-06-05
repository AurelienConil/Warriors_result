package warriors.board;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import warriors.Game;

public class Dice extends JPanel implements ActionListener {

	public Dice(Game g) {
		super();
		try {
			diceImg = ImageIO.read(new File("data/img/dice.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.parentGame = g;
		
		this.setSize(200, 400);
		this.setPreferredSize(new Dimension(200, 400));
		startDice = new JButton("trow it");
		this.add(startDice);
		resultDice = new JTextField();
		resultDice.setPreferredSize(new Dimension(80,20));
		this.add(resultDice);
		resultDice.setText("---");
		
		//Action Listener
		startDice.addActionListener(this);
		
		
		
	}
	
	
	BufferedImage diceImg ;
	JButton startDice;
	JTextField resultDice;
	private int result;
	Game parentGame;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.diceImg, 0, 60 ,150,150, null);
		

		
	}
	
	public void setEnable(boolean b) {
		startDice.setEnabled(b);
	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	result = (int) (Math.random() * 6 + 1);
    	resultDice.setText(Integer.toString(result));
    	repaint();
    	this.parentGame.updateGame(Game.Game_progession.NEXT_STEP);
    	
    	

    }
    
    public int getResult() {
    	return result;
    }
	
	
}
