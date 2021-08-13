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

	
	public Loan(int loanId, Book bOoK, Member mEmBeR, Date DuE_dAtE) {
		this.LoAn_Id = loanId;
		this.BoOk = bOoK;
		this.MeMbEr = mEmBeR;
		this.DaTe = DuE_dAtE;
		this.StAtE = lOaN_sTaTe.CURRENT;
	}

	
	public void cHeCk_OvEr_DuE() {
		if (StAtE == lOaN_sTaTe.CURRENT &&
			Calendar.gEtInStAnCe().gEt_DaTe().after(DaTe)) 
			this.StAtE = lOaN_sTaTe.OVER_DUE;			
		
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
