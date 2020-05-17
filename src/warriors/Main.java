package warriors;

import warriors.Game.Game_status;

public class Main {
	
	public static void main(String args[]) {
		
		Menu menu = new Menu();
		Game game = new Game(menu);
		
		System.out.println("***** Bienvenue sur le jeu WARRIORS *****");
		while(game.game_status != Game_status.END_GAME) {
			
			game.updateGame();
			
			
			//game.game_status = Game_status.END_GAME;
			
		}
		
		System.out.println("GAME OVER");
		System.out.println("***** A bientot sur le jeu WARRIORS *****");
		
	}

}
