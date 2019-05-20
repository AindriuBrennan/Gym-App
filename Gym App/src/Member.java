import java.util.Scanner;

/**
 * 
 * @author Andrew Brennan 20079247
 * @version 1.0
 * @since 12-0-18
 * this is the member class for the gym app,this class contains the 
 * constructor getters and setters, validation and other methods
 * for calculations off user input for the members
 *
 */



public class Member {
	
	/********************INSTANCE FIELDS********************/
	private double height;
	private double startingWeight;
	private String gender;
	private String memberName;
	private int    memberId;
	private String memberAddress;
	private Scanner input;
	private double bodyWeight;
	private double idealBodyWeight;
	
	
	
	
	/********************CONSTRUCTOR********************/
	
	/**
	 * @member this is the member constructor for the member object, validation is performed here.
	 * 
	 * @param memberId member id and validation is performed
	 * @param memberName member name and the string length is shortened if its longer than 30 characters
	 * @param memberAddress member address and no validation is performed
	 * @param height validation is performed here
	 * @param startingWeight the starting weight of the member is validated
	 * @param gender validation is performed on the member gender
	 */
	
	public Member(int memberId, String memberName, String memberAddress, double height, double startingWeight, String gender) {
		
		if((memberId > 100000) && (memberId <= 999999)){ 
			this.memberId = memberId;
			}
			else { this.memberId = 100000;
			}
		
		if(memberName.length() > 30) { 
		this.memberName = memberName.substring(0,30);
		}
		else {
			this.memberName = memberName;
		}
		
		this.memberAddress  = memberAddress;
		
		if((height >= 1.00 ) && (height <= 3.00)) {
			this.height = height; 
			}
		
		if((startingWeight >= 35.00) && (startingWeight <= 250.00)) {
			this.startingWeight = startingWeight;
			}
		
		if((gender == "M") || (gender == "m")) {
		    this.gender = "M";
		}
		else if ((gender == "F") || (gender == "f")) {
			this.gender = "F";
		}
		else this.gender = "Unspecified";
		
		
	}
		 
	
	public Member() {
		input = new Scanner(System.in);    
	}
	
	
	/********************METHODS********************/
	
	/**
	 * 
	 * @calculateBMI
	 * the members BMI is calculated 
	 * 
	 */
	
	public double calculateBMI(){
		double BMI =(getStartingWeight() / (getHeight()*getHeight())); 
			return toTwoDecimalPlaces(BMI);
	}
	
	/**
	 * 
	 * @determineBMICategory
	 * the members bmi category is calculated based off their bmi result
	 */
	
	public String determineBMICategory() {
		String bmiCategory = "";
		if(calculateBMI() < 15) {
			bmiCategory = (" is \"VERY SEVERELY UNDERWEIGHT\"");
		}
		else if((calculateBMI()  >= 15)  && (calculateBMI() < 16)) {
			bmiCategory = (" is \"SEVERELY UNDERWEIGHT\"");
		}
		else if((calculateBMI()  >= 16)  && (calculateBMI() < 18.5)) {
			bmiCategory = (" is \"UNDERWEIGHT\"");
		}
		else if((calculateBMI() >= 18.5) && (calculateBMI() < 25)) {
	    	 bmiCategory = (" is \"NORMAL\"");
		}
		else if((calculateBMI() >= 25)   && (calculateBMI() < 30)) {
			 bmiCategory = (" is \"OVERWEIGHT\"");
		}
		else if((calculateBMI() >= 30)   && (calculateBMI() < 35)) {
			 bmiCategory = (" is \"MODERATELY OBESE\"");
		 }
		else if((calculateBMI() >= 35)   && (calculateBMI() < 40)) {
			 bmiCategory = (" is \"SEVERELY OBESE\"");
		 }
		else if(calculateBMI() >= 40) {
			 bmiCategory = (" is \"VERY SEVERELY OBESE\"");
		 }
		 return bmiCategory;	
	}
	
	/**
	 * 
	 * @isIdealBodyWeight
	 * the members bodyweight is checked to see  if its the ideal weight for their height
	 * 
	 */
	
	public boolean isIdealBodyWeight() {
	boolean isIdeal = false;
		if((convertHeightMetresToInches() <= 60.0) && (getMemberGender() == "M")) {
			idealBodyWeight = 50.0;
			
		}
		else if(convertHeightMetresToInches() <= 60.0) {
			idealBodyWeight = 45.5;	
		}
		
		if((convertHeightMetresToInches() > 60.0) && (getMemberGender() == "M")) {
			bodyWeight = (convertHeightMetresToInches() - 60.0)*(2.3); 
			idealBodyWeight =  bodyWeight + 50.0;
			if((idealBodyWeight - 2 <= getStartingWeight()) && (idealBodyWeight + 2 >= getStartingWeight())) {
			isIdeal = true;
			}
		}
		else if(convertHeightMetresToInches() > 60.0) {
			bodyWeight = (convertHeightMetresToInches() - 60)*(2.3);
			idealBodyWeight = bodyWeight + 45.5;
			if((idealBodyWeight - 2 <= getStartingWeight()) && (idealBodyWeight + 2 >= getStartingWeight())) {
			isIdeal = true;	
			}
		}
		if((convertHeightMetresToInches() < 60) && (getMemberGender() == "M")) {
			if((idealBodyWeight - 2 <= getStartingWeight()) && (idealBodyWeight + 2  >= getStartingWeight())) {
				isIdeal = true;
			}
		}
		else if (convertHeightMetresToInches() < 60) {
			if((idealBodyWeight - 2 <= getStartingWeight()) && (idealBodyWeight + 2 >= getStartingWeight())) {
				isIdeal = true;
			}
		}
		return isIdeal;
	}
	
	/**
	 * 
	 * @convertHeightMetresToInches
	 * the members height is converted from metres to inches
	 */
	
	public double convertHeightMetresToInches() {
		double metersToInches = (getHeight()*39.37);
		return toTwoDecimalPlaces(metersToInches);
		
	}
	
	/**
	 * 
	 * @convertWeightKGtoPounds
	 * method for converting the member weight from kg to pounds
	 */
	public double convertWeightKGtoPounds() {
		double kgToPounds = (getStartingWeight()* 2.2);
		return toTwoDecimalPlaces(kgToPounds);
		
	}
	
	/**
	 * @toString 
	 * toString method for outputting the the member details
	 */
	
	public String toString() {
		
		return "Member name: " 			    + memberName + "\n"
			+	"Member ID: "  		        + memberId   + "\n"
			+	"Member address: " 	        + memberAddress + "\n"
			+	"Member gender: " 	        + gender + "\n"
			+   "Member Height: " 	        + height + "\n"
			+   "Member starting Weight: "  + startingWeight +"\n"
			+   "BMI of "                   + determineBMICategory();
		
	}
	
	/**
	 * 
	 * @toTwoDecimalPlaces
	 * this tidies up a double to two decimal places and makes it read better for the user
	 */
	
	 private double toTwoDecimalPlaces(double num)
	    {
	        return (int) (num *100 ) /100.0; 
	    }
	
	/********************GETTERS********************/
	
	public double getHeight(){
		
		return height;
	}
	
	public double getStartingWeight(){
		
		return startingWeight;
	}
	
	public String getMemberGender() {
		
		return gender;
	}
	
	public String getMemberName() {
		
		return memberName;
	}
	
	public int getMemberId() {
		
		return memberId;
	}
	
	
	public String getMemberAddress(){
		
		return memberAddress;
	}
	
	/********************SETTERS********************/
	
	

	
	public void setHeight(double height) {
		
		if((height >= 1.00 ) && (height <= 3.00)) {
		this.height = height; 
		}
	}
	
	public void setStartingWeight(double startingWeight){
		
		if((startingWeight >= 35.00) && (startingWeight <= 250.00)) {
		this.startingWeight = startingWeight;
		}
	}
	
	public void setGender(String gender) {
		if((gender == "M") || (gender == "m")) {
		    this.gender = "M";
		}
		else if ((gender == "F") || (gender == "f")) {
			this.gender = "F";
		}
		
	}
	
	public void setMemberName(String memberName) {
		
		if(memberName.length() > 30) { 
			this.memberName = memberName.substring(0,30);
			}
			else {
				this.memberName = memberName;
			}
	}
	
	public void setMemberId(int memberId) {
		
		if((memberId > 100000) && (memberId <= 999999)) {
		this.memberId = memberId;
		}
		
	}
	
	public void setMemberAddress(String memberAddress){
		
		this.memberAddress = memberAddress;
	}
	
}
