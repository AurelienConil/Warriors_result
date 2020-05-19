package warriors;
import java.util.ArrayList;
import warriors.player.*;
import warriors.weapon.*;
import warriors.ennemy.*;
import warriors.board.*;
import warriors.bonus.*;



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

	public Game(Menu m, int size, boolean debug) {
		
		menu = m;
		game_status = game_status.START_GAME;
		gameDebug = debug;
		fight = new Fight(this.menu);
		
		//Position where the player start = -1
		// Position 0 is the first case of the board game;
		playerPosition = -1;
		boardGame = new ArrayList<Case>();
		if(this.gameDebug) {
			
			this.initTestBoard();
			maxPosition = this.boardGame.size();
		}else {
			maxPosition = size;
			this.initBoard();
			
		}

		
	}
	
	private Menu menu;
	private Player player;
	private int playerPosition;
	private final int maxPosition;
	private  ArrayList<Case> boardGame;
	public Game_status game_status;
	private boolean gameDebug;
	private Fight fight;
	
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
		String listOfChoice[] = { 	"Créer votre personnage", 
								  	"Utiliser un personnage automatique",
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
									"Comencer la partie",
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
		//PRINTING STUFF
		menu.printLineH2("Nouveau tour de jeu");
		
		if(this.fight.getIsCurrentFighting()) {
			menu.printLine("Vous êtes en plein combat");
			boolean stillFighting = this.fight.updateFight();
			if(!stillFighting && !this.player.isAlive() ) {
				this.game_status = Game_status.END_GAME;
			}
		}else {
			this.moveToNextCase();
		}
		
		// ASK FOR ENTER -> next turn
		menu.printLine("Appuyer sur une touche pour continuer ( q pour quitter , i pour information )");
		String result = menu.askForStringOrEnter();
		if(result.contentEquals("q")) {
			this.game_status = Game_status.END_GAME;
		}
		else if(result.contentEquals("i")) {
			this.menu.printLineH2("information joueur");
			this.menu.printLine(this.player.toString());
		}else {
			int i=0;
		}
	}
	
	private void moveToNextCase() {
		
		if(this.playerPosition<0) {
			menu.printLine("Vous êtes sur la case départ");
		}else {
			menu.printLine("Votre position actuelle est : "+(playerPosition)+" / "+this.boardGame.size() ); 
		}
		
		// DICE AND SET POSITION
		int diceResult = this.virtualDice();
		boolean endOfGame = this.setPosition(playerPosition+diceResult);
		
		if(this.playerPosition<0) {
			menu.printLine("Vous êtes sur la case départ");
		}else {
			menu.printLine("Votre nouvelle position est : "+(playerPosition)+" / "+this.boardGame.size() ); 
		}
		
		// OUT OF BOARD ? = end of game
		if(!endOfGame) {
			try {
				boardGame.get(this.playerPosition).interact(this.player, this.fight);				
			}catch( IndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
				System.out.println("Array Size ="+this.boardGame.size()+"  player position="+this.playerPosition);
			}
		}else {
			menu.printLineH1("Bravo ! Vous avez atteint la fin du niveau");
		}
	}
	
	private boolean setPosition(int p) {

		
		if(( p)< this.maxPosition) {
			this.playerPosition = p;
			return false;
		}else {
			this.game_status = Game_status.END_GAME;
			return true;
		}
		
	}
	
	private int virtualDice() {
		menu.printLine("Appuyer sur entrée pour lancer le dé");
		menu.askForEnter();
		int result = (int) (Math.random() * 6 + 1);
		
		if(this.gameDebug) {
			//Fake Dice that return always 1
			menu.printLine("Le dé est pippé : 1");
			return 1;

		}else {
			menu.printLine("Le resultat est : "+result);
			return result;
		}
		
		
	}
	
	private void initTestBoard() {
		boardGame.add(new EmptyCase() );
		boardGame.add(new Wizard());
		boardGame.add(new Gobelin());
		boardGame.add(new Dragon());
		boardGame.add(new Sword());
		boardGame.add(new Club());
		boardGame.add(new FireBall());
		boardGame.add(new Lightning());
		boardGame.add(new SmallPotion());
		boardGame.add(new BigPotion());
			
	}
	
	private void initBoard() {
		this.boardGame.clear();
		
		//EMPTY CASE
		for(int i=0; i<this.maxPosition; i++) {
			this.boardGame.add(new EmptyCase());
		}
		// DRAGON
		int[] dragonList = {45,52,56,62};
		for(int i : dragonList) {
			this.boardGame.set(i, new Dragon());
		}
		// WIZARD
		int[] wizardList = {10,20,25,32,35,36,37,40,44,47};
		for(int i : wizardList) {
			this.boardGame.set(i, new Wizard() );
		}
		// Gobelin
		int[] gobelinList = {45,52,56,62};
		for(int i : gobelinList) {
			this.boardGame.set(i, new Gobelin() );
		}
		// Massue Club
		int[] clubList = {2,11,5,22,38};
		for(int i : clubList) {
			this.boardGame.set(i, new Club() );
		}
		// Epee Sword
		int[] swordList = {19,26,42,53};
		for(int i : swordList) {
			this.boardGame.set(i, new Sword()  );
		}
		//Lightning
		int[] lightningList = {1,4,8,17,23};
		for(int i : lightningList) {
			this.boardGame.set(i, new Lightning() );
		}
		// Boule de feu : fireball
		int[] fireballList = {48,49};
		for(int i :fireballList) {
			this.boardGame.set(i, new FireBall()  );
		}
		// Potion standard/petite : smallPotion
		int[] smallPotionList = {7,13,31,33,39,43};
		for(int i : smallPotionList) {
			this.boardGame.set(i, new SmallPotion() );
		}
		// Big Potion
		int[] bigPotionList = {28,41};
		for(int i : bigPotionList) {
			this.boardGame.set(i, new BigPotion() );
		}
			
	}
	
	private void initRandomBoard() {
		this.boardGame.clear();
		//EMPTY CASE
		for(int i=0; i<this.maxPosition; i++) {
			this.boardGame.add(new EmptyCase());
		}
		//NEED TO DO IT
		
		
	}
	
}
