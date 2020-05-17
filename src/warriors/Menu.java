package warriors;
import java.util.Scanner;
import warriors.Player.Player_type;

public class Menu {
	
	public Menu() {
		
		scan = new Scanner(System.in);
	}
	
	Scanner scan;
	
	Player_type askForType() {
		
		int userChoice = 0;
		
		while(userChoice<1 || userChoice>2) {
			System.out.println("Choisir un type de personnage");
			System.out.println("(1) Magicien");
			System.out.println("(2) Guerrier");
			System.out.println("Taper votre choix ( 1 ou 2 )");
			
			if(this.scan.hasNextInt()) {
				userChoice = this.scan.nextInt();
			}else {
				this.scan.next();
			}
			
			
		}
		
		if(userChoice==1) {
			return Player_type.MAGICIEN;
		}else {
			return Player_type.GUERRIER;
		}

		
	}
	
	int askForInt(int min, int max) {
		
		int userChoice = 0;
		
		while(userChoice<(min) || userChoice>max) {
			System.out.println("Taper votre choix ( entre "+min+" et "+max+" )");
			if(this.scan.hasNextInt()) {
				userChoice = this.scan.nextInt();
			}else {
				this.scan.next();
			}
			
			
		}
		
		return userChoice;

		
	}
	
	
	String askForName() {
		
		String userName = "";
		while(userName.length()<1) {
			System.out.println("Entrer le nom de votre personnage");
			userName = this.scan.nextLine();
		}
		return userName;
		
	}
	
	int askForMenuGame() {
		
		int userChoice = 0;
		
		while(userChoice<1 || userChoice>3) {
			
			System.out.println("Menu Principal. ");
			System.out.println("(1) Afficher le personnage");
			System.out.println("(2) Modifier le personnage ");
			System.out.println("(3) Quitter le jeu ");
			
			if(this.scan.hasNextInt()) {
				userChoice = this.scan.nextInt();
			}else {
				this.scan.next();
			}
		
		}
		return userChoice;
	}

}
