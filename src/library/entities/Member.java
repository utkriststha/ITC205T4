package library.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable {
    //Indentation was reduced from 8 spaces to 4 spaces
    private String lastName;        // chnaged variable name 'LaSt_NaMe' to 'lastName'
    private String firstName;	    // changed variable name 'FiRsT_NaMe' to 'firstName'
    private String emailAddress;	// changed variable name 'EmAiL_AdDrEsS' to 'emailAddress'
    private int phoneNumber;	    // changed variable name 'PhOnE_NuMbEr' to 'phoneNumber'
    private int memberId;		    // changed variable name 'MeMbEr_Id' to ' memberId'
    private double finesOwing;	    // changed variable name 'FiNeS_OwInG' to 'finesOwing'
    //Indentation was reduced from 8 spaces to 4 spaces
    private Map<Integer, Loan> currentLoans; //changed variable name 'cUrReNt_lOaNs' to 'currentLoans'

    //Indentation was reduced from 8 spaces to 4 spaces
    public Member(String lastName, String firstName, String emailAddress, int phoneNumber, int memberId) {n // changed variable name 'lAsT_nAmE' to 'lastName', 'fIrSt_nAmE' to 'firstName', 'eMaIl_aDdReSs' to 'emailAddress' ,'mEmBeR_iD' to 'memberId'  
    this.lastName = lastName; // changed variable name 'this.LaSt_NaMe' to 'this.lastName', 'lAsT_nAmE' to 'lastName'
    this.firstName = firstName; // changed variable name 'this.FiRsT_NaMe' to 'this.firstName', 'fIrSt_nAmE' to 'firstName'
    this.emailAddress = emailAddress; // changed variable name 'this.EmAiL_AdDrEsS' to 'this.emailAddress', 'eMaIl_aDdReSs' to 'emailAddress'
    this.phoneNumber = phoneNumber; // changed variable name 'this.PhOnE_NuMbEr' to 'this.phoneNumber', 'pHoNe_nUmBeR' to 'phoneNumber'
    this.memberId = memberId; // changed variable name 'this.MeMbEr_Id' to 'this.memberId', 'mEmBeR_iD' to 'memberId'
		
    this.currentLoans = new hashMap<>(); // changed variable name 'this.cUrReNt_lOaNs' to 'this.currentLoans', 'HashMap' to 'hashMap'
	}

    //Indentation was reduced from 8 spaces to 4 spaces
    public String toString() {
    stringBuilder sb = new stringBuilder(); //changed variable name 'StringBuilder' to 'stringbuilder'
      .append("Member:  ").append(memberId).append("\n") // chanaged variable name 'MeMbEr_Id' to 'memberId'
      .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n") // changed variable name 'LaSt_NaMe' to 'lastName', 'FiRsT_NaMe' to 'firstName'
      .append("  Email: ").append(emailAddress).append("\n") // changed variable name 'EmAiL_AdDrEsS' to 'emailAddress'
      .append("  Phone: ").append(phoneNumber) // changed variable name 'PhOnE_NuMbEr' to 'phoneNumber'
      .append("\n")
      .append(String.format("  Fines Owed :  $%.2f", finesOwing)) // changed variable name 'FiNeS_OwInG' to 'finesOwing'
      .append("\n");
		
    for (Loan loan : currentLoans.values()) { // changed variable name 'Loan LoAn' to 'Loan loan', 'cUrReNt_lOaNs' to 'currentLoans'
    sb.append(Loan).append("\n"); // changed variable name 'LoAn' to 'Loan'
		}		  
		return sb.toString();
	}

	
	public int getId() { //changed method name 'GeT_ID' to 'getId'
		return memberId; // changed variable name 'MeMbEr_Id' to 'memberId'
	}

	
	public List<Loan> getLoans() { // changed method name 'GeT_LoAnS' to 'getLoans'
		return new ArrayList<Loan>(currentLoans.values()); //changed variable name 'cUrReNt_lOaNs' to 'currentLoans'
	}

	
	public int getNumberOfCurrentLoans() { // changed method name 'gEt_nUmBeR_Of_CuRrEnT_LoAnS' to 'getNumberOfCurrentLoans'
		return currentLoans.size(); // changed variable name 'cUrReNt_lOaNs' to 'currentLoans'
	}

	
	public double finesOwed() { // changed method name 'FiNeS_OwEd' to 'finesOwed'
		return finesOwing; // changed variable name 'FiNeS_OwInG' to 'finesOwing'
	}

	
	public void TaKe_OuT_LoAn(Loan loan) { // changed method name 'TaKe_OuT_LoAn' to 'takeOutLoan" , 'lOaN' to 'loan'
		if (!currentLoans.containsKey(loan.getID())) // changed variable name 'cUrReNt_lOaNs' to 'currentLoans', 'lOaN.GeT_Id' to 'loan.getID'
			currentLoans.put(loan.getId(), loan); // changed variable name 'cUrReNt_lOaNs' to 'currentLoans', 'lOaN.GeT_Id' to 'loan.getId' , 'lOaN' to 'loan'
		
		else 
			throw new RuntimeException("Duplicate loan added to member");
				
	}

	
	public String GeT_LaSt_NaMe() {
		return LaSt_NaMe;
	}

	
	public String GeT_FiRsT_NaMe() {
		return FiRsT_NaMe;
	}


	public void AdD_FiNe(double fine) {
		FiNeS_OwInG += fine;
	}
	
	public double PaY_FiNe(double AmOuNt) {
		if (AmOuNt < 0) 
			throw new RuntimeException("Member.payFine: amount must be positive");
		
		double change = 0;
		if (AmOuNt > FiNeS_OwInG) {
			change = AmOuNt - FiNeS_OwInG;
			FiNeS_OwInG = 0;
		}
		else 
			FiNeS_OwInG -= AmOuNt;
		
		return change;
	}


	public void dIsChArGeLoAn(Loan LoAn) {
		if (cUrReNt_lOaNs.containsKey(LoAn.GeT_Id())) 
			cUrReNt_lOaNs.remove(LoAn.GeT_Id());
		
		else 
			throw new RuntimeException("No such loan held by member");
				
	}

}
