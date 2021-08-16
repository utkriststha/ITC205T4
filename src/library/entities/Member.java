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
    private String firsTName;	    // changed variable name 'FiRsT_NaMe' to 'firstName'
    private String emailAddresS;	// changed variable name 'EmAiL_AdDrEsS' to 'emailAddress'
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

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(MeMbEr_Id).append("\n")
		  .append("  Name:  ").append(LaSt_NaMe).append(", ").append(FiRsT_NaMe).append("\n")
		  .append("  Email: ").append(EmAiL_AdDrEsS).append("\n")
		  .append("  Phone: ").append(PhOnE_NuMbEr)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", FiNeS_OwInG))
		  .append("\n");
		
		for (Loan LoAn : cUrReNt_lOaNs.values()) {
			sb.append(LoAn).append("\n");
		}		  
		return sb.toString();
	}

	
	public int GeT_ID() {
		return MeMbEr_Id;
	}

	
	public List<Loan> GeT_LoAnS() {
		return new ArrayList<Loan>(cUrReNt_lOaNs.values());
	}

	
	public int gEt_nUmBeR_Of_CuRrEnT_LoAnS() {
		return cUrReNt_lOaNs.size();
	}

	
	public double FiNeS_OwEd() {
		return FiNeS_OwInG;
	}

	
	public void TaKe_OuT_LoAn(Loan lOaN) {
		if (!cUrReNt_lOaNs.containsKey(lOaN.GeT_Id())) 
			cUrReNt_lOaNs.put(lOaN.GeT_Id(), lOaN);
		
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
