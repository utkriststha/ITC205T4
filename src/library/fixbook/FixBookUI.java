package library.fixbook;
import java.util.Scanner;


public class FixBookUI {
    // Changed indentation from 8 spaces from 4 spaces
    public static enum UIState { INITIALISED, READY, FIXING, COMPLETED };	// Change method name 'uI_sTaTe' to 'UIState'.

    private FixBookControl control;		// Change class name 'fIX_bOOK_cONTROL' to 'FixBookControl' and 'CoNtRoL' to 'control'.
    private Scanner input;			// Change variable name 'InPuT' to 'input'.
    private UIState state;			// Change class name 'uI_sTaTe' to 'UIState' and 'StAtE' to 'state'.

	
    public FixBookUI(FixBookControl control) {		// Change class name 'fIX_bOOK_cONTROL' to 'FixBookControl' and 'CoNtRoL' to 'control'.
	this.control = control;				// Change variable name 'CoNtRoL' to 'control'.
	input = new Scanner(System.in);			// Change class name 'InPuT' to 'input'.
	state = UIState.INITIALISED;			// Change class name 'uI_sTaTe' to 'UIState' and 'StAtE' to 'state'.
	control.setUI(this);				// Change method name 'SeT_Ui' to 'setUI' and 'CoNtRoL' to 'control'.
    }


    public void setState(uI_sTaTe state) {		// Change method name 'SeT_StAtE' to 'setState' and 'uI_sTaTe' to 'UIState'.
	this.state = state;				// Change variable name 'StAtE' to 'state'.
    }

	
    public void run() {					// Change method name 'RuN' to 'run'.
	output("Fix Book Use Case UI\n");		// Change method name 'OuTpUt' to 'output'.
		
	while (true) {
			
	    switch (state) {				// Change variable name 'StAtE' to 'state'.
			
	    case READY:
		String bookEntryString = input("Scan Book (<enter> completes): ");	// Change variable name 'BoOk_EnTrY_StRiNg' to 'bookEntryString' and 'iNpUt' to 'input'.
		if (bookEntryString.length() == 0) 					// Change variable name 'BoOk_EnTrY_StRiNg' to 'bookEntryString'.
		    control.scanningComplete();						// Change variable name 'CoNtRoL' to 'control' and 'SCannING_COMplete' to 'scanningComplete'.
				
		else {
		    try {
		        int bookId = Integer.valueOf(bookEntryString).intValue();	// Change variable name 'BoOk_Id' to 'bookId' and 'BoOk_EnTrY_StRiNg' to 'bookEntryString'. 
			control.bookScanned(bookId);					/** Change variable name 'CoNtRoL' to 'control', 'BoOk_ScAnNeD' to 'bookScanned', 
			    								 and 'BoOk_Id' to'bookId'. **/
		    }
		    catch (NumberFormatException e) {
			output("Invalid bookId");					// Change variable name 'OuTpUt' to 'output'.
		    }
	        }
		break;	
				
	    case FIXING:
		String answer = input("Fix Book? (Y/N) : ");			// Change variable name 'AnS' to 'answer' and 'iNpUt' to 'input'.
		boolean fix = false;						// Change variable name 'FiX' to 'fix'.
		if (answer.toUpperCase().equals("Y")) 				// Change variable name 'AnS' to 'answer'
		    FiX = true;							// Change variable name 'FiX' to 'fix'.
		    CoNtRoL.FiX_BoOk(fix);
		    break;
								
		case COMPLETED:
		    OuTpUt("Fixing process complete");
		    return;
			
		default:
		    OuTpUt("Unhandled state");
			throw new RuntimeException("FixBookUI : unhandled state :" + StAtE);			
			
			}		
		}
		
	}

	
    private String iNpUt(String prompt) {
	System.out.print(prompt);
	return InPuT.nextLine();
    }	
		
		
    private void OuTpUt(Object object) {
	System.out.println(object);
    }
	

   public void dIsPlAy(Object object) {
	OuTpUt(object);
   }
	
	
}
