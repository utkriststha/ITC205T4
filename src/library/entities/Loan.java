package library.entities;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable {
	
    //  Indentation was reduced from 8 spaces to 4 spaces.
    public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED };     // Changed class name 'lOaN_sTaTe' to LoanState
	
    private int loanId;         // Changed variable name 'LoAn_Id' to 'loanId'
    private Book book;          // Changed variable name 'BoOk' to 'book'
    private Member member;      // Changed variable name 'MeMbEr' to 'member'
    private Date date;          // Changed variable name 'DaTe' to 'date'
    private LoanState state;    // Changed variable name 'lOaN_sTaTe' to 'LoanState' and class name 'StAtE' to 'state'
	

    //  Indentation was reduced from 8 spaces to 4 spaces.
    public Loan(int loanId, Book book, Member member, Date dueDate) {   // Changed variable name 'bOoK' to 'book', 'mEmBeR' to 'member', and 'DuE_dAtE' to 'dueDate'.
        this.loanId = loanId;											// Changed variable name 'this.LoAn_Id' to 'this.loanId'.
        this.book = book;												// Changed variable name 'this.BoOk' to 'this.book' and 'bOoK' to 'book'.
        this.member = member;											// Changed variable name 'this.MeMbEr' to 'this.member' and 'mEmBeR' to 'member'.
        this.date = dueDate;											// Changed variable name 'this.DaTe' to 'this.date' and 'DuE_dAtE' to 'dueDate'. 
        this.state = LoanState.CURRENT;									// Changed variable name 'this.StAtE' to 'this.state' and 'lOaN_sTaTe.CURRENT' to 'LoanState.CURRENT'.
	}

	
	public void checkOverDue() {							   // Changed method name 'cHeCk_OvEr_DuE' to 'checkOverDue'.
		if (StAtE == lOaN_sTaTe.CURRENT &&					   // Changed variable name 'StAtE' to 'state' and 'lOaN_sTaTe.CURRENT' to 'LoanState.CURRENT'. 
			Calendar.getInstance().getDate().after(date)) 	   // Changed variable name  'Calendar.gEtInStAnCe().gEt_DaTe().after(DaTe)' to 'Calendar.getInstance().getDate().after(date)'.
			this.state = LoanState.OVER_DUE;			 	   // Changed variable name 'this.StAtE' to 'this.state' and  'lOaN_sTaTe.OVER_DUE' to 'LoanState.OVER_DUE'.
	}

	
	public boolean Is_OvEr_DuE() {
		return StAtE == lOaN_sTaTe.OVER_DUE;
	}

	
	public Integer GeT_Id() {
		return LoAn_Id;
	}


	public Date GeT_DuE_DaTe() {
		return DaTe;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(LoAn_Id).append("\n")
		  .append("  Borrower ").append(MeMbEr.GeT_ID()).append(" : ")
		  .append(MeMbEr.GeT_LaSt_NaMe()).append(", ").append(MeMbEr.GeT_FiRsT_NaMe()).append("\n")
		  .append("  Book ").append(BoOk.gEtId()).append(" : " )
		  .append(BoOk.gEtTiTlE()).append("\n")
		  .append("  DueDate: ").append(sdf.format(DaTe)).append("\n")
		  .append("  State: ").append(StAtE);		
		return sb.toString();
	}


	public Member GeT_MeMbEr() {
		return MeMbEr;
	}


	public Book GeT_BoOk() {
		return BoOk;
	}


	public void DiScHaRgE() {
		StAtE = lOaN_sTaTe.DISCHARGED;		
	}

}
