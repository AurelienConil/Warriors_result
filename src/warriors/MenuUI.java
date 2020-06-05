package warriors;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
* Menu aim to be separated from the game content
* This just an interface tool, to display message
* display menu, and ask information from the user.
* <p>
* This should separated from any content related
* to game.
* <p>
* Menu is related to java console and scanner. 
*/
public class MenuUI extends JPanel implements ActionListener{
	
	public MenuUI(Game g) {
		super(new GridLayout(2,0));
		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		this.textArea.setFont(new Font("Roboto", Font.PLAIN, 18));
		this.setPreferredSize(new Dimension(500,200));
		this.question = new JPanel(new GridLayout(0,1));
		this.question.setPreferredSize(new Dimension(500, 100));
		this.msgToDisplay = "";
		
		this.add(textArea);
		this.add(question);

		this.setPreferredSize(new Dimension(500, 300));
		this.flagAnswer = false;
		this.answer = "";
		this.gameParent  = g;
	}
	
	String msgToDisplay;
	JTextArea textArea;
	private JPanel question;
	// All question are answer with a string( int , String, etc... )
	private String answer;
	// Game instance is accessible from here, in order to update game
	private Game gameParent;
	
	//Flag answer is setted to true when a button and click and
	// go back to false when a new question is asked
	private boolean flagAnswer;
	

	int askForInt(int min, int max) {
		flagAnswer = false;
		int userChoice = 0;
		

		
		return userChoice;

		
	}
	
	
	public String askForString(int minLength) {
		//Clear al previous lines
		flagAnswer = false;
		String userName = "";
		this.question.removeAll();
		JTextField textinput = new JTextField();
		textinput.addActionListener(this);
		this.question.add(textinput);
		this.repaint();
		return userName;
		
	}
	
	public String askForStringOrEnter() {
		String readString = "";
		flagAnswer = false;

	    return readString;
		
	}
	
	public int askForMenuChoice(String listOfChoice[]) {
		flagAnswer = false;
		this.question.removeAll();
		
		for(String s : listOfChoice) {
			JButton b = new JButton(s);
			b.addActionListener(this);
			this.question.add(b);
		}
		
		this.repaint();
	

		return 1;
	}
	
	public void askForEnter() {

	}
	
	public void printLine(String s) {
		this.textArea.setText(s);
	}
	
	public void printLineH1(String s) {
		String finalMsg = "******************************\n";
		finalMsg += s;
		finalMsg +="******************************\n";
		this.textArea.setText(finalMsg);

	}
	
	public void printLineH2(String s) {
		String finalMsg = "*** "+s+" ****";
		this.textArea.setText(finalMsg);
		
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			this.answer = ((JButton) e.getSource()).getText();
			this.flagAnswer = true;
			this.gameParent.updateGame(Game.Game_progession.NEXT_STEP);
		}
		if(e.getSource() instanceof JTextField) {
			this.answer = ((JTextField) e.getSource()).getText();
			this.flagAnswer = true;
			this.gameParent.updateGame(Game.Game_progession.NEXT_STEP);
		}



	}
	
	public boolean getFlagAnswer() {
		return this.flagAnswer;
	}
	
	public String getAnswer() {
		return this.answer;
	}

	
	

}
