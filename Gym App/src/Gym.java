import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 * 
 * @author Andrew Brennan 20079247
 * @version 1.0
 * @since 12-03-18
 *
 */



public class Gym {
	
	/********************INSTANCE FIELDS********************/
	private ArrayList<Member> members;
	
	private String gymName;
	private String managerName;
	private String phoneNumber;
	
	
	
	
	/********************CONSTRUCTOR********************/
	
	public Gym(String gymName, String managerName) {
		
		if(gymName.length() > 30) { 
			this.gymName = gymName.substring(0,30);
			}
			else {
				this.gymName = gymName;
			}
		this.managerName = managerName;
		this.phoneNumber = "unknown";
		
		members = new ArrayList<Member>();
	}
	
	
	
	public Gym(String gymName, String managerName, String phoneNumber) {
		
		if(gymName.length() > 30) { 
			this.gymName = gymName.substring(0,30);
			}
			else {
				this.gymName = gymName;
			}
		this.managerName = managerName;
		
		if(verifyNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		}
		else {
			this.phoneNumber = "unknown";
		}
		
		
		members = new ArrayList<Member>();
		
	}

	
	/*****************helperMethod******************/
	/**
	 * 
	 * @verifyNumber 
	 * takes in the phone number string and checks if it contains numbers
	 * 
	 */
	public boolean verifyNumber(String phoneNumber) {
		
		for(int i = 0; i < phoneNumber.length(); i++) {
			
			if(!Character.isDigit(phoneNumber.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	/********************METHODS********************/
	
	/**
	 * 
	 * @add  
	 * adds a member to the member arraylist
	 */
	
	public void add(Member member) {
		
		members.add(member);
	}
	
	/**
	 * 
	 * @size gets the size of the arraylist
	 */
	
	public int size() {
		
		return members.size();
	}
	
	/**
	 * 
	 * @remove
	 * removes the index no
	 * 
	 */
	
	   public void remove(int index) {
	    
	        if (isValidIndex(index)) {
	        
	            members.remove(index);
	        }
	    }

	   /**
	    * 
	    * @Member 
	    * gets the index number of the member in the arraylist
	    * 
	    */
	   
	    public Member get(int index) {
	    
	        if (isValidIndex(index)) {
	        
	            return members.get(index);
	       }
	        return null;
	    }

	    /**
	     * 
	     * @isValidIndex
	     * checks that the index number is valid
	     *
	     */
	    
	    public boolean isValidIndex(int index) {
	    
	    	return ( (index >= 0) && (index < members.size()) );
	    }
	
	
	    /**
	     * 
	     * @listBySpecificBMICategory
	     * list members in the arraylist by a specific bmi category
	     */
	    
	public String listBySpecificBMICategory(String categories) {
		String string = "";
		if( members.size() == 0) {
			return "No Members";
		}
		
		for(Member member : members) {
			if(member.determineBMICategory().contains(categories)) 
				string += member.getMemberName() + " , " + member.getMemberAddress() + "\n" + member.getHeight() + 
				" , " + member.getStartingWeight() + " , ";
		}
		if (string.equals("")) {
			
			return "there are no members in this BMI category";
		}
		else {
			return string;
		}

	}
	/***Gym to string***/
  
	
	
	@Override
	public String toString() {
		return    "Gym Name: "    + gymName     + "\n"
				+"Manager Name: " + managerName + "\n"
				+"Phone Number: " + phoneNumber;
	}

    /**
     * 
     * @listMemberDetailsImperialAndMetric
     * list the members weight and height in imperial and metric
     */
	
	
	public String listMemberDetailsImperialAndMetric() {
		
		String bothUnits = "";
		for(Member member : members) {
			bothUnits += member.getMemberName() + ": " + member.getStartingWeight() + "( " + member.convertWeightKGtoPounds() + " )" + member.getHeight() +
					"( " + member.convertHeightMetresToInches() + " )" + "\n"; 
		}
		if (bothUnits.equals("")) {
			
			return "No members in gym";
		}
		else {
			return bothUnits;
		}
		
	}
	
	/**
	 * 
	 * @listMembers
	 * list all members in the member arraylist
	 */
	
	public String listMembers() {
		
        String listOfMembers = "";
        for (int index = 0; index < members.size(); index++) {
        
            listOfMembers = listOfMembers + index + ": " + members.get(index).toString() + "\n";
        }
        if (listOfMembers.equals("")) {
        
            return "No members in gym";
        }
        else {
        
            return listOfMembers;
        }  
		
   
	}
	
	/**
	 * 
	 * @listMembersWithIdealWeight
	 * lists members that fall into the ideal weight category
	 */
	
	public String listMembersWithIdealWeight() {
		if( members.size() == 0) {
			return "No Members";
		}
		
		  String membersWithIdealBodyWeight = "";
	        for (Member member : members) {
	        
	           if (member.isIdealBodyWeight()) {
	           
	                membersWithIdealBodyWeight +=  member.toString()
	                     + "\n";  
	        }

	        if (membersWithIdealBodyWeight.equals("")) {
	        
	            return "No members with Ideal bodyweight";
	        }
	       }
	        return membersWithIdealBodyWeight;
	}
	
	
	public int numberOfMembers() {
		
		return members.size();
		
	}
	
/******serialise-and-deserialize******/
	
	/**
	 * 
	 * @load  load data saved to the xml file
	 */
	
	
	  @SuppressWarnings("unchecked")
	    public void load() throws Exception
	    {
	        XStream xstream = new XStream(new DomDriver());
	        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("members.xml"));
	        members = (ArrayList<Member>) is.readObject();
	        is.close();
	    }
	  
	  /**
	   * 
	   * @save
	   * save data to the xml file
	   */

	    public void save() throws Exception
	    {
	        XStream xstream = new XStream(new DomDriver());
	        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("members.xml"));
	        out.writeObject(members);
	        out.close();    
	    }
	

	/********************GETTERS********************/
	
	public ArrayList<Member> getMembers() {
		
		return members;
	}
	
	public String getGymName() {
		
		return gymName;
	}
	
	public String getManagerName() {
		
		return managerName;
	}
	
	public String getPhoneNumber() {
		
		return phoneNumber;
	}
	
	
	
	/********************SETTERS********************/
	
	public void setMembers(ArrayList<Member> members) {
		
		this.members = members;
	}
	
	public void setGymName(String gymName) {
		if(gymName.length() > 30) { 
			this.gymName = gymName.substring(0,30);
			}
			else {
				this.gymName = gymName;
			}
	}
	
	public void setManagerName(String managerName) {
		
		this.managerName = managerName;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		if(verifyNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		}
		
	}

	

}
