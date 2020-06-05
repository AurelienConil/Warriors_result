package warriors;

import java.awt.EventQueue;

import javax.swing.JFrame;

import warriors.Game.Game_status;

public class Main extends JFrame{
	
	public Main() {
		initGame();
	}
	
	private void initGame() {
		
		//Menu menu = new Menu();
		Game game = new Game(64, false);
		add(game);
		setSize(900,800);
		
		setTitle(" Bienvenue sur le jeu WARRIORS ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
		
		/*
		 * while(game.game_status != Game_status.END_GAME) {
		 * 
		 * game.updateGame();
		 * 
		 * }
		 * 
		 * menu.printLineH2("GAME OVER");
		 * menu.printLineH1("A bientot sur le jeu WARRIORS ");
		 */
	}
	
	public static void main(String args[])  {
		
		EventQueue.invokeLater(() -> {
            Main ex = new Main();
            ex.setVisible(true);
        });
		
		
	}

}