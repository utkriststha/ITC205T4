package library.fixbook;
import java.util.Scanner;


public class FixBookUI {
    // Changed indentation from 8 spaces from 4 spaces
    public static enum UI_STATE { INITIALISED, READY, FIXING, COMPLETED };	// Change method name 'uI_sTaTe' to 'UI_STATE'.

    private FixBookControl CoNtRoL;		// Change class name 'fIX_bOOK_cONTROL' to 'FixBookControl' and 'CoNtRoL' to 'control'.
    private Scanner input;			// Change variable name 'InPuT' to 'input'.
    private UIState state;			// Change class name 'uI_sTaTe' to 'UIState' and 'StAtE' to 'state'.

	
    public FixBookUI(FixBookControl control) {		// Change class name 'fIX_bOOK_cONTROL' to 'FixBookControl' and 'CoNtRoL' to 'control'.
	this.control = control;				// Change variable name 'CoNtRoL' to 'control'.
	input = new Scanner(System.in);			// Change class name 'InPuT' to 'input'.
	state = UIState.INITIALISED;			// Change class name 'uI_sTaTe' to 'UIState' and 'StAtE' to 'state'.
	control.setUI(this);				// Change method name 'SeT_Ui' to 'setUI' and 'CoNtRoL' to 'control'.
    }


    public void SeT_StAtE(uI_sTaTe state) {
	this.StAtE = state;
    }

	
    public void RuN() {
	OuTpUt("Fix Book Use Case UI\n");
		
	while (true) {
			
	    switch (StAtE) {
			
	    case READY:
		String BoOk_EnTrY_StRiNg = iNpUt("Scan Book (<enter> completes): ");
		if (BoOk_EnTrY_StRiNg.length() == 0) 
		    CoNtRoL.SCannING_COMplete();
				
		else {
		    try {
		        int BoOk_Id = Integer.valueOf(BoOk_EnTrY_StRiNg).intValue();
			CoNtRoL.BoOk_ScAnNeD(BoOk_Id);
		    }
		    catch (NumberFormatException e) {
			OuTpUt("Invalid bookId");
		    }
	        }
		break;	
				
	    case FIXING:
		String AnS = iNpUt("Fix Book? (Y/N) : ");
		boolean FiX = false;
		if (AnS.toUpperCase().equals("Y")) 
		    FiX = true;
		    CoNtRoL.FiX_BoOk(FiX);
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
