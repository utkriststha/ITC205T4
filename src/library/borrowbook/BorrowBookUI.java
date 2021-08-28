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
	
			//This line needs to checked
	public void setState(UIState StAtE) {//Changed 'uI_STaTe' to 'UIState' & 'StaTe' to 'state' & 'SeT_StAtE' to 'setState'
		this.StaTe = StAtE;
	}

	
	public void run() {//Changed 'RuN' to 'run'
		output("Borrow Book Use Case UI\n");//Changed 'OuTpUt' to 'output'
		
		while (true) {
			
			switch (state) {//Changed 'StaTe' to 'state'			
			
			case CANCELLED:
				output("Borrowing Cancelled");//Changed 'OuTpUt' to 'output'
				return;

				
			case READY:
				String inputMemberId = iNpUT("Swipe member card (press <enter> to cancel): ");//Changed 'MEM_STR' to 'inputMemberId'
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
					control.scaned(bookId); //Changed 'CoNtRoL' to 'control' & 'ScAnNeD' to 'scaned & 'BiD' to 'bookId'
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id"); //Chnaged 'OuTpUt' to 'output'
				} 
				break;
					
				
			case FINALISING:
				String AnS = iNpUT("Commit loans? (Y/N): ");
				if (AnS.toUpperCase().equals("N")) {
					CoNtRoL.CaNcEl();
					
				} else {
					CoNtRoL.CoMmIt_LoAnS();
					iNpUT("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				OuTpUt("Borrowing Completed");
				return;
	
				
			default:
				OuTpUt("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + StaTe);			
			}
		}		
	}


	public void DiSpLaY(Object object) {
		OuTpUt(object);		
	}


}
