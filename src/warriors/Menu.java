package warriors;
import java.util.Scanner;

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
public class Menu {
	
	public Menu() {
		
		scan = new Scanner(System.in);
	}
	
	Scanner scan;
	
	
	
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
