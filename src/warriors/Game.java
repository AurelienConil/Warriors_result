package warriors;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

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
public class Game extends JPanel {

	public Game( int size, boolean debug) {
		
		super(new GridBagLayout());
		menu = new MenuUI(this);
		gameDebug = debug;
		fight = new Fight(this.menu);
		dice = new Dice(this);
		
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
		
		//UI settings
        setBackground(Color.BLACK);
		setFocusable(true);		
		gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		this.add(dice,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		JButton plateauButton = new JButton("Plateau");
		plateauButton.setPreferredSize(new Dimension(200, 100));
		plateauButton.setSize(80, 110);
		this.add(plateauButton,gbc); 
		
		//gbc.fill = GridBagConstraints.HORIZONTAL; 
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(menu,gbc); 
		
		
		
		
		
		
		updateGameStatus(Game_status.START_GAME);

		
	}
	
	private MenuUI menu;
	private Player player;
	private int playerPosition;
	private final int maxPosition;
	private  ArrayList<Case> boardGame;
	public Game_status game_status;
	private boolean gameDebug;
	private Fight fight;
	private Dice dice;
	private int step;
	private GridBagConstraints gbc;
	
	//All temporary Variable needed during a form
	private String tempName;
	
	public enum Game_status {
		START_GAME, MENU_GAME, EDIT_PLAYER, PLAYING_GAME,  END_GAME
	}
	
	public enum Game_progession {
		RESET, SAME, NEXT_STEP
	}
	
	
	
	
	
	/**
	 * Call each turn of the game from the main programm.
	 * Call the right function according to game.status
	 * 
	 *
	 * @param void
	 * @return void
	 */
	public void updateGame(Game_progession progress) {
		
		switch(this.game_status) {
		
			case START_GAME :
				createPlayer(progress);
			break;
			case MENU_GAME :
				menuGame(progress);
			break;
			case EDIT_PLAYER:
				editPlayer(progress);
				setGameStatus(Game_status.MENU_GAME);
			break;
			case PLAYING_GAME:
				playGame(progress);
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
	private void createPlayer(Game_progession progress) {
		
		switch(progress) {
		
			case NEXT_STEP:
				this.step++;
				break;
			case RESET:
				this.step = 1;
				this.tempName = "no name";
			break;
		}
		
		
		
		// ALL LOCAL VARIABLE NEEDED
		final String listOfChoice[] = { 	"Créer votre personnage", 
			  	"Utiliser un personnage automatique",
				"Quitter le jeu"};
		final String listOfPlayerType[] = { "Magicien", "Guerrier"};

		
		
		//INTRO
		switch(this.step) {
		case 1:
			this.menu.printLineH1("Bienvenue dans Warriors");
			int choice = menu.askForMenuChoice(listOfChoice);
			
		break;
		
		// ANSWER FROM THE FIRST QUESTION
		case 2:
			if(menu.getFlagAnswer()) {

				if(menu.getAnswer().equals(listOfChoice[0])) {
					
					menu.printLine("Création du personnage");
					menu.printLine("Entrer votre nom :");
					menu.askForString(2);
				
				}
				if(menu.getAnswer().equals(listOfChoice[1])) {
					player = new Magician("Merlin");
					addPersoToUI();
					updateGameStatus(Game_status.MENU_GAME);
					
				}
				if(menu.getAnswer().equals(listOfChoice[2])) {
					addPersoToUI();
					updateGameStatus(Game_status.END_GAME);
					
				}
				
				
			}
		
		break;
		
		// ANSWER FROM THE NAME QUESTION
		case 3:
			if(menu.getFlagAnswer()) {
				this.tempName = menu.getAnswer();
				menu.printLine("Création du personnage");
				menu.askForMenuChoice(listOfPlayerType);
			}else {
				updateGameStatus(Game_status.START_GAME);
			}
		break;
		
		// ANSWER FROM THE TYPE
		case 4:
			if(menu.getFlagAnswer()) {
				if(menu.getAnswer().equals(listOfPlayerType[0]) ) {
						this.player = new Magician(this.tempName);
				}
				if(menu.getAnswer().equals(listOfPlayerType[1]) ) {
						this.player = new Warrior(this.tempName);

				}
				addPersoToUI();
				updateGameStatus(Game_status.MENU_GAME);
			}
			
		break;
		
		
		
		}
		
		
		
		
		
	}
	
	private void editPlayer(Game_progession progress) {
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
	
	private void menuGame(Game_progession progress) {

		switch(progress) {
		
		case NEXT_STEP:
			this.step++;
			break;
		case RESET:
			this.step = 1;
		break;
		}
		
		
		String listOfChoice[] = { 	"Afficher les informations du personnage",
									"Editer le personnage", 
									"Comencer la partie",
									"Quitter le jeu"};
		
		switch(this.step) {
		
		// Display the menu	
		case 1:
			menu.printLineH1("menu principal");
			menu.askForMenuChoice(listOfChoice);			
			break;
		// Compute the answer
		case 2:
			if(menu.getFlagAnswer()) {

				if(menu.getAnswer().equals(listOfChoice[0])) {
					
					menu.printLine(this.player.toString());
					this.step--;

				}
				if(menu.getAnswer().equals(listOfChoice[1])) {
					updateGameStatus(Game_status.EDIT_PLAYER);
					
				}
				if(menu.getAnswer().equals(listOfChoice[2])) {
					updateGameStatus(Game_status.PLAYING_GAME);
				}
				
				if(menu.getAnswer().equals(listOfChoice[3])) {
					updateGameStatus(Game_status.END_GAME);
				}
				
				
			}
		break;

		
		}
		
	}
	
	private void playGame(Game_progession progress) {
		
		switch(progress) {
		
		case NEXT_STEP:
			this.step++;
			break;
		case RESET:
			this.step = 1;
		break;
	}
		


		menu.printLineH2("Nouveau tour de jeu");
		
		if(this.fight.getIsCurrentFighting()) {
			menu.printLine("Vous êtes en plein combat");
			boolean stillFighting = this.fight.updateFight();
			if(!stillFighting && !this.player.isAlive() ) {
				setGameStatus(Game_status.END_GAME);
			}
		}else {
			this.moveToNextCase();
		}
		
		// ASK FOR ENTER -> next turn
		menu.printLine("Appuyer sur une touche pour continuer ( q pour quitter , i pour information )");
		String result = menu.askForStringOrEnter();
		if(result.contentEquals("q")) {
			setGameStatus(Game_status.END_GAME);
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
			setGameStatus(Game_status.END_GAME);
			return true;
		}
		
	}
	
	private void setGameStatus(Game_status s) {
		this.game_status = s;
		this.step = 0;
	}
	
	private void updateGameStatus(Game_status s) {
		this.game_status = s;
		this.step = 0;
		this.updateGame(Game_progession.RESET);
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
	
	private void addPersoToUI() {
		if(this.player != null) {
			gbc.gridx = 2;
			gbc.gridy = 0;  
			gbc.gridheight = 2;
			gbc.fill = GridBagConstraints.BOTH;
			this.add(this.player,gbc); 
			this.repaint();
		}
		
	}
	
}
