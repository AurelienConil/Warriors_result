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
				userChoice = Integer.parseInt(this.scan.nextLine());
			}else {
				this.scan.next();
			}
			
			
		}
		
		return userChoice;

		
	}
	
	
	public String askForString(int minLength) {
		//Clear al previous lines
		String userName = "";
		while(userName.length()<minLength) {
			userName = this.scan.nextLine();
		}
		return userName;
		
	}
	
	public String askForStringOrEnter() {
		String readString = this.scan.nextLine();
	    while(readString!=null) {

	        if (readString.isEmpty()) {
	        	break;
	        }
	        
	        if( readString.length() > 0) {
	        	break;
	        }

	        if (this.scan.hasNext()) {
	            readString = this.scan.nextLine();
	        } else {
	            readString = null;
	        }
	    };
	    return readString;
		
	}
	
	public int askForMenuChoice(String listOfChoice[]) {
		
		int userChoice = 0;
		
		while(userChoice<1 || userChoice>listOfChoice.length) {
			
			this.printLine(" Selectionner un choix ");
			for(int i=0; i<listOfChoice.length ; i++) {
				System.out.println("("+(i+1)+") "+listOfChoice[i]+"");
			}
		
			String userAnswer = this.scan.nextLine();
			if(userAnswer.length()>0) {
				userChoice = Integer.parseInt(userAnswer);
			}
			
		
		}
		return userChoice;
	}
	
	public void askForEnter() {
		String readString = this.scan.nextLine();
	    while(readString!=null) {

	        if (readString.isEmpty()) {
	        	break;
	        }
	        
	        if( readString.length() > 0) {
	        	break;
	        }

	        if (this.scan.hasNext()) {
	            readString = this.scan.nextLine();
	        } else {
	            readString = null;
	        }
	    };
	}
	
	public void printLine(String s) {
		System.out.println(s);
	}
	
	public void printLineH1(String s) {
		System.out.println("******************************");
		System.out.println(s.toUpperCase());
		System.out.println("******************************");

	}
	
	public void printLineH2(String s) {
		System.out.println("*** "+s+" ****");
	}

	
	

}
