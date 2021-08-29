package library.borrowbook;
import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UIState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };//Changed 'uI_STaTe' to 'UIState'

	private BorrowBookControl control; //Changed 'bORROW_bOOK_cONTROL' to 'BorrowBookControl' & 'CoNtRoL' to 'control'
	private Scanner input;//Changed 'InPuT' to 'input'
	private UIState state;//Changed 'uI_STaTe' to 'UIState' & 'StaTe' to 'state'

	
	public BorrowBookUI(BorrowBookControl control) { //Changed 'bORROW_bOOK_cONTROL' to 'BorrowBookControl'
		this.control = control; //Changed 'CoNtRoL' to 'control'
		input = new Scanner(System.in); //Changed 'InPuT' to 'input'
		state = UIState.INITIALISED; //Changed 'uI_STaTe' to 'UIState' & 'StaTe' to 'state'
		control.setUI(this);//Changed 'SeT_Ui' to 'setUI'
	}

	
	private String input(String prompt) {//Change 'iNpUT' to 'input' & 'PrOmPt' to 'prompt'
		System.out.print(prompt);//Changed 'PrOmPt' to 'prompt'
		return input.nextLine();//Change 'iNpUT' to 'input'
	}	
		
		
	private void output(Object object) {//Changed 'OuTpUt' to 'output' & 'ObJeCt' to 'object'
		System.out.println(object);//Changed 'ObJeCt' to 'object'
	}
	
	public void setState(UIState state) {//Changed 'uI_STaTe' to 'UIState' & 'StaTe' to 'state' & 'SeT_StAtE' to 'setState'
		this.state = state;//Changed 'StaTe' to 'state' & 'StAtE' to 'state'
	}

	
	public void run() {//Changed 'RuN' to 'run'
		output("Borrow Book Use Case UI\n");//Changed 'OuTpUt' to 'output'
		
		while (true) {
			
			switch (state) {//Changed 'StaTe' to 'state'			
			
			case CANCELLED:
				output("Borrowing Cancelled"); //Changed 'OuTpUt' to 'output'
				return;

				
			case READY:
				String inputMemberId = input("Swipe member card (press <enter> to cancel): ");//Changed 'MEM_STR' to 'inputMemberId' & 'iNput' to "input'
				if (inputMemberId.length() == 0) {//Changed 'MEM_STR' to 'inputMemberId'
					control.cancel();//Changed 'CoNtRoL' to 'control' & 'CaNcEl' to 'cancel'
					break;
				}
				try {
					int memberId = Integer.valueOf(inputMemberId).intValue();//Changed 'MeMbEr_Id' to 'memberId' & 'MEM_STR' to 'inputMemberId'
					control.swiped(memberId);//Changed 'MeMbEr_Id' to 'memberId' & 'CoNtRoL' to 'control' & 'SwIpEd' to 'swiped'
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");//Chnaged 'OuTpUt' to 'output' 
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel"); //Change 'iNpUT' to 'input'
				control.cancel(); //Changed 'CoNtRoL' to 'control' & 'CaNcEl' to 'cancel'
				break;
			
				
			case SCANNING:
				String bookStringInput = input("Scan Book (<enter> completes): "); //Change 'iNpUT' to 'input' & 'BoOk_StRiNg_InPuT' to 'bookStringInput'
				if (bookStringInput.length() == 0) {//Changed 'BoOk_StRiNg_InPuT' to 'bookStringInput'
					control.complete(); //Changed 'CoNtRoL' to 'control' & 'CoMpLeTe' to 'complete'
					break;
				}
				try {
					int bookId = Integer.valueOf(bookStringInput).intValue();//Changed 'BoOk_StRiNg_InPuT' to 'bookStringInput' & 'BiD' to 'bookId'
					control.scanned(bookId); //Changed 'CoNtRoL' to 'control' & 'ScAnNeD' to 'scanned & 'BiD' to 'bookId'
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id"); //Changed 'OuTpUt' to 'output'
				} 
				break;
					
				
			case FINALISING:
				String inputAnswer = input("Commit loans? (Y/N): "); //Changed 'AnS' to 'inputAnswer' & 'iNpUT' to 'input'
				if (inputAnswer.toUpperCase().equals("N")) { //Changed 'AnS' to 'inputAnswer'
					control.cancel(); //Changed 'CoNtRoL' to 'control' & 'CaNcEl' to 'cancel'
					
				} else {
					control.commitLoans(); //Changed 'CoNtRoL' to 'control' & 'CoMmIt_LoAnS' to 'commitLoans'
					input("Press <any key> to complete "); //Changed 'iNpUT' to 'input'
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed"); //Changed 'OuTpUt' to 'output'
				return;
	
				
			default:
				output("Unhandled state"); //Changed 'OuTpUt' to 'output'
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state);	//Changed 'StaTe' to 'state'		
			}
		}		
	}


	public void display(Object object) { //Changed 'DiSpLaY' to 'display'
		output(object);	//Changed 'OuTpUt' to 'output'	
	}


}
