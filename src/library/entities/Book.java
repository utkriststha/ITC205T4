package library.entities;
import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable {
	
	private String title; //Changed 'tItLe' to 'title' 
	private String author;//Changed 'AuThOr' to 'author'
	private String callNo;//Changed 'CALLNO' to 'callNo'
	private int id;//Changed 'iD' to 'id'
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };//Changed 'sTaTe' to 'State'
	private State state;//Changed ' sTaTe StAtE' to ' State state'
	
	
	public Book(String author, String title, String callNo, int id) {
		this.author = author;// Changed 'AuThOr' to 'author'
		this.title = title;// Changed 'tItLe ' to 'title'
		this.callNo = callNo;//Changed 'CALLNO' to 'callNo'
		this.id = id;//Changed 'iD' to 'id'
		this.state = state.AVAILABLE;//Changed 'StAtE ' to 'state' and 'sTaTe.AVAILABLE' to 'state.AVAILABLE'
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(id).append("\n")// Changed 'iD' to 'id'
		  .append("  Title:  ").append(title).append("\n")// Changed 'tItLe' to 'title'
		  .append("  Author: ").append(author).append("\n")//Changed 'AuThOr' to 'author'
		  .append("  CallNo: ").append(callNo).append("\n")// Changed 'CALLNO' to 'callNo'
		  .append("  State:  ").append(state);//Changed 'StAtE ' to 'state'
		
		return sb.toString();
	}

	public Integer getId() {//Changed 'gEtId' to 'getId'
		return id;//Changed 'iD' to 'id'
	}

	public String getTitle() {// Changed 'gEtTiTlE' to 'getTitle'
		return title;//Changed 'tItLe' to 'title'
	}


	
	public boolean isAvailable() {// Changed 'iS_AvAiLaBlE' to 'isAvailable'
		return state == state.AVAILABLE;// Changed 'StAtE' to 'state'  
	}

	
	public boolean isOnLoan() {// Changed 'iS_On_LoAn' to 'isON_LOAN'
		return state == state.ON_LOAN;//Changed 'StAtE' to 'state'
	}

	
	public boolean isDAMAGED() {//Changed 'iS_DaMaGeD' to 'isDAMAGED'
		return state == state.DAMAGED;//Changed 'StAtE' to 'state'
	}

	
	public void borrow() {// Changed 'BoRrOw' to 'borrow'
		if (state.equals(state.AVAILABLE)) // Changed 'StAtE' to 'state' and 'sTaTe' to 'state'
			state = state.ON_LOAN;//Changed 'StAtE' to 'state' and 'sTaTe' to 'state'
		
		else 
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));//Changed 'StAtE' to 'state'
		
		
	}


	public void return(boolean DAMAGED) {//Changed 'ReTuRn' to 'return' and ' DaMaGeD' to 'DAMAGED'
		if (state.equals(state.ON_LOAN)) //Changed 'StAtE' to 'state' and 'sTaTe' to 'state'
			if (DAMAGED) // Changed 'DaMaGeD' to 'DAMAGED'
				state = state.DAMAGED;//Changed 'StAtE' to 'state' and 'sTaTe' to 'state'
			
			else 
				state = state.AVAILABLE;//Changed 'StAtE' to 'state' and 'sTaTe' to 'state'
			
		
		else 
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));//Changed 'StAtE' to 'state'
				
	}

	
	public void repair() {//Changed 'RePaIr' to 'repair'
		if (state.equals(state.DAMAGED))//Changed 'StAtE' to 'state' and 'sTaTe' to 'state' 
			state = state.AVAILABLE;//Changed 'StAtE' to 'state' and 'sTaTe' to 'state'
		
		else 
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));//Changed 'StAtE' to 'state'
		
	}


}
