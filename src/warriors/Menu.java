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
			this.printLine("Choisir un type de personnage");
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
			this.printLine("Taper votre choix ( entre "+min+" et "+max+" )");
			if(this.scan.hasNextInt()) {
				userChoice = this.scan.nextInt();
			}else {
				this.scan.next();
			}
			
			
		}
		
		return userChoice;

		
	}
	
	
	String askForString(int minLength) {
		
		String userName = "";
		while(userName.length()<minLength) {
			userName = this.scan.nextLine();
		}
		return userName;
		
	}
	
	int askForMenuChoice(String listOfChoice[]) {
		
		int userChoice = 0;
		
		while(userChoice<1 || userChoice>listOfChoice.length) {
			
			this.printLine(" Selectionner un choix ");
			for(int i=0; i<listOfChoice.length ; i++) {
				System.out.println("("+(i+1)+") "+listOfChoice[i]);
			}
		
			if(this.scan.hasNextInt()) {
				userChoice = this.scan.nextInt();
			}else {
				this.scan.next();
			}
		
		}
		return userChoice;
	}
	
	void askForEnter() {
		if(this.scan.hasNextLine())
			this.scan.nextLine();
		this.scan.hasNextLine();
	}
	
	void printLine(String s) {
		System.out.println(s);
	}
	
	void printLineH1(String s) {
		System.out.println("******************************");
		System.out.println(s.toUpperCase());
		System.out.println("******************************");

	}
	
	void printLineH2(String s) {
		System.out.println("*** "+s+" ****");
	}

	
	

}
