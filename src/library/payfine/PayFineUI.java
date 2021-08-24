package library.payfine;
import java.util.Scanner;


public class PayFineUI { 

    // Changed indentation from 8 spaces from 4 spaces
    public static enum UIState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; // changed method name 'uI_sTaTe' to 'UIState'

    private PayFineControl control; //changed class name 'pAY_fINE_cONTROL' to 'PayFineControl' , 'CoNtRoL' to 'control'
    private Scanner input;
    private uI_sTaTe StAtE; //changed class name 'uI_sTaTe' to 'UIState' , 'StAtE' 'state'

	
    public PayFineUI(PayFineControl control) { //changed class name 'pAY_fINE_cONTROL' to 'PayFineControl'
	this.control = control; // changed variable name 'this.CoNtRoL' to 'this.control'
	input = new Scanner(System.in);  
	state = UIState.INITIALISED; // changed class name 'uI_sTaTe' to 'UIState' and 'StAtE' to 'state'
	control.setUI(this); // changed method name 'control.SeT_uI' to 'control.setUI'
	}
	
	
    public void setState(uI_sTaTe state) { // chnaged method name 'SeT_StAtE' to 'setState' and 'uI_sTaTe' to 'UIState'
	this.state = state; // changed variable name 'this.StAtE' to 'this.state'
	}


	public void RuN() {
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (StAtE) {
			
			case READY:
				String Mem_Str = input("Swipe member card (press <enter> to cancel): ");
				if (Mem_Str.length() == 0) {
					CoNtRoL.CaNcEl();
					break;
				}
				try {
					int Member_ID = Integer.valueOf(Mem_Str).intValue();
					CoNtRoL.CaRd_sWiPeD(Member_ID);
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double AmouNT = 0;
				String Amt_Str = input("Enter amount (<Enter> cancels) : ");
				if (Amt_Str.length() == 0) {
					CoNtRoL.CaNcEl();
					break;
				}
				try {
					AmouNT = Double.valueOf(Amt_Str).doubleValue();
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
