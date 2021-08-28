package library.payfine;
import java.util.Scanner;


public class PayFineUI { 

    // Changed indentation from 8 spaces from 4 spaces
    public static enum UIState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; // changed method name 'uI_sTaTe' to 'UIState'

    private PayFineControl control; //changed class name 'pAY_fINE_cONTROL' to 'PayFineControl' , 'CoNtRoL' to 'control'
    private Scanner input;
    private UIState state; //changed class name 'uI_sTaTe' to 'UIState' , 'StAtE' 'state'

	
    public PayFineUI(PayFineControl control) { //changed class name 'pAY_fINE_cONTROL' to 'PayFineControl'
	this.control = control; // changed variable name 'this.CoNtRoL' to 'this.control'
	input = new Scanner(System.in);  
	state = UIState.INITIALISED; // changed class name 'uI_sTaTe' to 'UIState' and 'StAtE' to 'state'
	control.setUI(this); // changed method name 'control.SeT_uI' to 'control.setUI'
	}
	
	
    public void setState(UIState state) { // chnaged method name 'SeT_StAtE' to 'setState' and 'uI_sTaTe' to 'UIState'
	this.state = state; // changed variable name 'this.StAtE' to 'this.state'
	}


    public void run() { // changed method name 'RuN' to 'run'
		output("Pay Fine Use Case UI\n");
		
	while (true) {
			
		switch (state) { // changed variable name 'StAtE' to 'state'
			
		case READY:
		String memberString = input("Swipe member card (press <enter> to cancel): "); // changed variable name 'Mem_Str' to 'memberString'
		if (memberString.length() == 0) { // changed variable name 'Mem_Str.length' to 'memberString.length'
		   control.CaNcEl(); // changed variable name 'CoNtRoL' to 'control'
			break;
			}
			try {
			int memberId = Integer.valueOf(memberString).intValue(); // changed variable name 'Member_ID' to 'memberId'
					control.cardSwiped(memberId); // changed variable name 'CoNtRoL.CaRd_sWiPeD' to 'control.cardSwiped'
			}
			catch (NumberFormatException e) {
				output("Invalid memberId");
			}
			break;
				
		case PAYING:
			double amount = 0; // changed variable name 'AmouNT' to 'amount'
			String amountString = input("Enter amount (<Enter> cancels) : "); // changed variable name 'Amt_Str' to 'amountString'
			if (amountString.length() == 0) {
				control.cancel(); // changed variable name 'CoNtRoL.CaNcEl' to 'control.cancel'
				break;
			}
			try {
				amount = Double.valueOf(amountString).doubleValue(); // changed variable name 'AmouNT' to 'amount' and 'Amt_Str' to 'amountString'
				}
				catch (NumberFormatException e) {}
				if (AmouNT <= 0) {
					output("Amount must be positive");
					break;
				}
				CoNtRoL.PaY_FiNe(AmouNT);
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + StAtE);			
			
			}		
		}		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}	
			

	public void DiSplAY(Object object) {
		output(object);
	}


}
