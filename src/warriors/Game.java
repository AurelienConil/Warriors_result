package warriors;

import warriors.Player.Player_type;

public class Game {

	public Game(Menu m) {
		
		menu = m;
		game_status = game_status.START_GAME;
	}
	
	private Menu menu;
	private Player player;
	public Game_status game_status;
	
	public enum Game_status {
		START_GAME, MENU_GAME, EDIT_PLAYER,  END_GAME
	}
	
	
	
	
	/**
	 * Call each turn of the game from the main programm.
	 * Call the right function according to game.status
	 * 
	 *
	 * @param void
	 * @return void
	 */
	public void updateGame() {
		
		switch(this.game_status) {
		
			case START_GAME :
				createPlayer();
			break;
			case MENU_GAME :
				menuGame();
			break;
			case EDIT_PLAYER:
				editPlayer();
			break;
			case END_GAME:
				System.out.println("*** A très bientot sur Warriors ***");
			break;
		
		}
		
	}
	
	
	/**
	 * Create player asking information trough the menu
	 *
	 * 
	 * 
	 */
	private void createPlayer() {
		System.out.println("création du personnage");
		player= new Player(menu.askForName() , menu.askForType() );
		System.out.println(player.toString());
		game_status = Game_status.MENU_GAME;
		
	}
	
	private void editPlayer() {
		System.out.println("*** Edition de votre personage ***");
		System.out.println("Votre nom actuel est : "+player.getName());
		player.setName(menu.askForName());
		System.out.println("Votre nouveau nom est : "+player.getName());
		System.out.println("Votre type actuel est :"+player.getTypeToString(player.getType()));
		Player_type newType = menu.askForType();
		player.setType( newType);
		System.out.println("Votre vie actuelle est : "+player.getLife());
		System.out.println(" Taper votre nouvelle vie");
		player.setLife(menu.askForInt(player.minLife, player.maxLife));
		System.out.println("Votre nouvele vie est : "+player.getLife());
		System.out.println("Votre attaque actuelle est : "+player.getAttack());
		System.out.println(" Taper votre nouvelle attaque");
		player.setLife(menu.askForInt(player.minAttack, player.maxAttack));
		System.out.println("Votre nouvelle attaque est : "+player.getAttack());
		
		
	}
	
	private void menuGame() {
		// Affiche un menu à 3 choix . Afficher le personnage, Editer le personnnage, Quitter
		int choice = menu.askForMenuGame();
		switch(choice) {
			
		case 1:
			System.out.println("Voici les informations de votre personnage ");
			System.out.println(player.toString());
			break;
		case 2:
			editPlayer();
			break;
		case 3:
			this.game_status = Game_status.END_GAME;
			break;
		
		}
		
	}
	
}
