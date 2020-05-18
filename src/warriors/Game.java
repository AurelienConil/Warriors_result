package warriors;
import java.util.ArrayList;


/**
* This is the Game engine
* Contains of the informaiton about the current game
* What is the player, the board, pointer to menu
* The state/status of the game
* The current the position
* The function to execute according to the current state.
* <p>
* This should separated from any content related
* to game.
* <p>
* Menu is related to java console and scanner. 
*/
public class Game {

	public Game(Menu m, int size) {
		
		menu = m;
		game_status = game_status.START_GAME;
		
		//Position where the player start = -1
		// Position 0 is the first case of the board game;
		playerPosition = -1;
		maxPosition = size;
		boardGame = new ArrayList<Case>(maxPosition);
		this.initBoard();
		
	}
	
	private Menu menu;
	private Player player;
	private int playerPosition;
	private final int maxPosition;
	private  ArrayList<Case> boardGame;
	public Game_status game_status;
	
	public enum Game_status {
		START_GAME, MENU_GAME, EDIT_PLAYER, PLAYING_GAME,  END_GAME
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
				this.game_status = Game_status.MENU_GAME;
			break;
			case PLAYING_GAME:
				playGame();
			break;
			case END_GAME:
				menu.printLineH1("A très bientot sur Warriors ");
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
		String listOfChoice[] = { "Créer votre personnage", 
								  " Utiliser un personnage autonmatique",
									"Quitter le jeu"};
		int choice = menu.askForMenuChoice(listOfChoice);
		switch(choice) {
		
		case 1 :
			menu.printLine("Création du personnage");
			menu.printLine("Entrer votre nom :");
			String name = menu.askForString(2);
			String listOfPlayerType[] = { "Magicien", "Guerrier"};
			int choiceType = menu.askForMenuChoice(listOfPlayerType);
			if(choiceType==1) {
				player = new Magician(name);
			}else if(choiceType ==2) {
				player = new Warrior(name);
			}else {
				this.game_status = Game_status.END_GAME;
			}
						
			menu.printLine(player.toString());
			game_status = Game_status.MENU_GAME;
			break;
		case 2:
			player = new Magician("Merlin");
			menu.printLine(player.toString());
			game_status = Game_status.MENU_GAME;
			break;
		case 3:
			this.game_status = Game_status.END_GAME;
			break;
			
		}
		
		
		
	}
	
	private void editPlayer() {
		System.out.println("*** Edition de votre personage ***");
		System.out.println("Votre nom actuel est : "+player.getName());
		System.out.println("Rentrer votre nouveau nom");
		player.setName(menu.askForString(2));
		System.out.println("Votre nouveau nom est : "+player.getName());
		System.out.println("Votre type actuel est :"+"-- UNDEFINED --");
		System.out.println("Votre vie actuelle est : "+player.getLife());
		System.out.println(" Taper votre nouvelle vie");
		player.setLife(menu.askForInt(player.minLife, player.maxLife));
		System.out.println("Votre nouvele vie est : "+player.getLife());
		System.out.println("Votre attaque actuelle est : "+player.getAttack());
		System.out.println(" Taper votre nouvelle attaque");
		player.setAttack(menu.askForInt(player.minAttack, player.MAX_ATTACK));
		System.out.println("Votre nouvelle attaque est : "+player.getAttack());
		
	}
	
	private void menuGame() {

		menu.printLineH1("menu principal");
		String listOfChoice[] = { 	"Afficher les informations du personnage",
									"Editer le personnage", 
									" Comencer la partie",
									"Quitter le jeu"};
		
		int choice = menu.askForMenuChoice(listOfChoice);
		switch(choice) {
			
		case 1:
			menu.printLineH2("Voici les informations de votre personnage ");
			menu.printLine(player.toString());
			menu.askForEnter();
			
			break;
		case 2:
			this.game_status = Game_status.EDIT_PLAYER;
			break;
		case 3:
			menu.printLineH1("Que l'aventure commnence");
			this.game_status= Game_status.PLAYING_GAME;
			break;
		case 4:
			this.game_status = Game_status.END_GAME;
			break;
		
		}
		
	}
	
	private void playGame() {
		menu.printLineH2("Nouveau tour de jeu");
		if(this.playerPosition<0) {
			menu.printLine("Vous êtes sur la case départ");
		}else {
			menu.printLine("Votre position actuelle est : "+(playerPosition+1)+" / "+this.boardGame.size() ); 
		}
		
		int diceResult = this.virtualDice();
		boolean endOfGame = this.setPosition(playerPosition+diceResult);
		
		if(this.playerPosition<0) {
			menu.printLine("Vous êtes sur la case départ");
		}else {
			menu.printLine("Votre nouvelle position est : "+(playerPosition+1)+" / "+this.boardGame.size() ); 
		}
		
		
		if(!endOfGame) {
			boardGame.get(this.playerPosition).interact(this.player);
		}
		
		menu.printLine("Appuyer sur une touche pour continuer");
		menu.askForEnter();
	}
	
	private boolean setPosition(int p) {

		
		if(( p)< this.maxPosition) {
			this.playerPosition = p;
			return false;
		}else {
			menu.printLineH1("Bravo ! vous avez atteint la fin du niveau");
			this.game_status = Game_status.END_GAME;
			return true;
		}
		
	}
	
	private int virtualDice() {
		menu.printLine("Appuyer sur entrée pour lancer le dé");
		menu.askForEnter();
		int result = (int) (Math.random() * 6 + 1);
		menu.printLine("Le resultat est : "+result);
		//------- Fake dice -----
		//Dé pipé
		//return result;
		return 1;
		
		
	}
	
	private void initBoard() {
		boardGame.add(new EmptyCase() );
		boardGame.add(new Ennemy("Dindon enragé", 2, 2));
		boardGame.add(new Weapon("dé à coudre", 2));
		boardGame.add(new Bonus("Jus de gingembre"));
				
	}
	
}
