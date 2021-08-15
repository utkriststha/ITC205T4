package library.entities;
import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable {
	
	private String title; //Changed 'tItLe' to 'title' 
	private String author;//Changed 'AuThOr' to 'author'
	private String callNo;//Changed 'CALLNO' to 'callNo'
	private int id;//Changed 'iD' to 'id'
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };//Changed 'sTaTe' to 'State'
	private state;//Changed 'private sTaTe StAtE' to 'private state'
	
	
	public Book(String author, String title, String callNo, int id) {
		this.author = author;// Changed 'AuThOr' to 'author'
		this.title = title;// Changed 'tItLe ' to 'title'
		this.callNo = callNo;//Changed 'CALLNO' to 'callNo'
		this.iD = id;//Changed 'iD' to 'id'
		this.state = state;//Changed 'StAtE ' to 'state' and 'sTaTe.AVAILABLE' to 'state'
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(iD).append("\n")
		  .append("  Title:  ").append(tItLe).append("\n")
		  .append("  Author: ").append(AuThOr).append("\n")
		  .append("  CallNo: ").append(CALLNO).append("\n")
		  .append("  State:  ").append(StAtE);
		
		return sb.toString();
	}

	public Integer gEtId() {
		return iD;
	}

	public String gEtTiTlE() {
		return tItLe;
	}


	
	public boolean iS_AvAiLaBlE() {
		return StAtE == sTaTe.AVAILABLE;
	}

	
	public boolean iS_On_LoAn() {
		return StAtE == sTaTe.ON_LOAN;
	}

	
	public boolean iS_DaMaGeD() {
		return StAtE == sTaTe.DAMAGED;
	}

	
	public void BoRrOw() {
		if (StAtE.equals(sTaTe.AVAILABLE)) 
			StAtE = sTaTe.ON_LOAN;
		
		else 
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", StAtE));
		
		
	}


	public void ReTuRn(boolean DaMaGeD) {
		if (StAtE.equals(sTaTe.ON_LOAN)) 
			if (DaMaGeD) 
				StAtE = sTaTe.DAMAGED;
			
			else 
				StAtE = sTaTe.AVAILABLE;
			
		
		else 
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", StAtE));
				
	}

	
	public void RePaIr() {
		if (StAtE.equals(sTaTe.DAMAGED)) 
			StAtE = sTaTe.AVAILABLE;
		
		else 
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", StAtE));
		
	}


}
