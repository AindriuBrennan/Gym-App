import java.util.Scanner;
/**
 * Menu controller for the gym application. In this class 
 * you can set the gym name, manager name and gym phone number
 * you can also add members to the gym and find out info and display it.
 * the gym members can also be saved
 * 
 * @author Andrew Brennan
 * @version 1.0
 * @since 12-03-18
 *
 */



public class MenuController {
	
	/********************INSTANCE FIELDS********************/
	
	private Scanner input;
	private Gym gym;
	
	
	
	/********************Application Starter********************/
	
	  public static void main (String args[]){
	     new MenuController();
	     
	    }
	
	/********************CONSTRUCTOR********************/
	
	public MenuController() {
		
		 input = new Scanner(System.in); 
		 System.out.println("Please enter the Gym...");
		 System.out.println("Name: ");
		 String gName = input.nextLine();
		 System.out.println("Manager name: ");
		 String mName = input.nextLine();
		 System.out.println("Phone number: ");
		 String pNumber = input.nextLine();
		 
	     gym = new Gym(gName, mName, pNumber);
	     runMenu();
		
	}
	

	  
	  /********************Methods********************/
	
	/**
	 * 
	 * @mainMenu() 
	 * this method displays a list of options and  reads the user input and displays back the information of their choice
	 */
	
	
	private int mainMenu() {
		
		System.out.println("Gym Menu");
		System.out.println("----------");
		System.out.println("  1)   Add a member");
		System.out.println("  2)   List all members");
		System.out.println("  3)   Remove a member (by index)");
		System.out.println("  4)   Number of members in the gym");
		System.out.println("----------");
		System.out.println("  5)   List gym details");
		System.out.println("  6)   List members with ideal starting weight");
		System.out.println("  7)   List members with a specific BMI category");
		System.out.println("  8)   List all members stats imperically and metrically");
		System.out.println("----------");
		System.out.println("  9)   Save to XML");
		System.out.println("  10)  Load from XML");
		System.out.println("  0)   Exit");
		System.out.println("====>");
		int option = input.nextInt();
		return option;	
	}
	
	
	/**
	 * @runMenu()
	 * this method takes the users input from mainMenu and performs actions depending on their choice from
	 * that list and issues a response. exception handling is performed here too incase the user enters invalid inputs.
	 * 
	 */
	
	private void runMenu() {
		int option = mainMenu();
		while (option != 0) {
			switch (option) {
			case 1:		addMember();
			break;
			case 2:		System.out.println(gym.listMembers());
			break;
			case 3:		gym.remove(getIndex());
			break;
			case 4:		System.out.println(gym.numberOfMembers());
			break;
			case 5:		System.out.println(gym.toString());
			break;
			case 6:		System.out.println(gym.listMembersWithIdealWeight());
			break;
			case 7:		System.out.println("Enter a weight category you wish to search by..");
						input.nextLine();
						String categories = input.nextLine().toUpperCase();
						System.out.println(gym.listBySpecificBMICategory(categories));
			break;
			case 8:		System.out.println(gym.listMemberDetailsImperialAndMetric());
			break;
			case 9:    try {
				gym.save();
			}
			catch (Exception e) {
				System.err.println("Error writing to file: " + e);
			}
			break;
			case 10:    try {
				gym.load();
			}
			catch (Exception e) {
				System.err.println("Error reading from file: " + e);
			}
			break;
			default:   System.out.println("Invalid option entered: " + option);
			break;
			}
			//pause the program so that the user can read what was printed
			System.out.println("\nPress any key to continue...");
			input.nextLine();
			//display the main menu again
			option = mainMenu();
		}
		
	
		//the user chose option 0, so exit the program
				System.out.println("Exiting... bye");
				System.exit(0);
	}
 
	/**
	 * @addMember()
	 * this method adds a member object to the gym class member array
	 * the user inputs 
	 * @param id integer id between 100000 and 999999
	 * @param name a string that gets shortened if it is longer than 30 characters
	 * @param addrs the address for the new member
	 * @param hght height for the new member which is between 1 and 3 metres no validation is performed
	 * @param wght user inputs the weight of the user which is ment to be between 35 and 250 no validation is performed here
	 */
	
	private void addMember() {
		
		System.out.print("please enter the following member details...");
		
		System.out.print("ID (between 100000 and 999999): ");
		int id = input.nextInt();
		System.out.print("Name (max 30 chars): ");
		String name = input.nextLine();
		name = input.nextLine();
		if(name.length() >= 30) {
			   name = 	name.substring(0, 29);
			}
		System.out.print("Adress: ");
		String addrs = input.nextLine();
		System.out.print("Height (between 1 and 3 metres): ");
		double hght = input.nextDouble();
		System.out.print("Starting weight (between 35kg and 250kg): ");
		double wght = input.nextDouble();
		System.out.print("Gender (M/F): ");
		char chooseGender = input.next().charAt(0);
		String gender = "";
		if((chooseGender == 'M') || (chooseGender == 'm')) {
		    gender = "M";
		}
		else if ((chooseGender == 'F') || (chooseGender == 'f')) {
		    gender = "F";
		} 
		else {
			gender = "unspecified";
		}
		gym.add(new Member(id, name, addrs, hght, wght, gender));
		
	}
	
	/**
	 * 
	 * @getIndex 
	 * gets the indext number of the member object in the member arraylist
	 */
	
	private int getIndex() {
		System.out.println(gym.listMembers());
		if (gym.size() > 0) {
			int index = validNextInt("Please enter the index of the member: ");
			if (gym.isValidIndex(index)) {
				return index;
			}
			else {
				System.out.println("Invalid index no entered: " + index );
				return -1;
			}
		}
		else { 
			return -2;
		}
	}
	
	
	
	
	/*****Helper methods and validation****/
	
	/**
	 * 
	 * @validNextInt
	 * method for checking if the user input is a valid int
	 */
	
	@SuppressWarnings("resource")
	public static int validNextInt(String prompt) {
		Scanner input = new Scanner(System.in);
		do {
			try {
				System.out.print(prompt);
				return Integer.parseInt(input.next());
			}
			catch (NumberFormatException e) { 
				System.err.println("\tEnter a number please.");
			}
		}  while (true);
	}
	
	/**
	 * 
	 * @validNextDouble
	 * method for checking if the user input is a valid double
	 */
	
	@SuppressWarnings("resource")
	public static double validNextDouble(String prompt) {
		Scanner input = new Scanner(System.in);
		do {
			try {
				System.out.print(prompt);
				return Double.parseDouble(input.next());
			}
			catch (NumberFormatException e) { 
				System.err.println("\tEnter a decimal number please.");
			}
		}  while (true);
	}
	
	
	

}
