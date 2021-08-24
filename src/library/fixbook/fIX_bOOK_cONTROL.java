package library.fixbook;
import library.entities.Book;
import library.entities.Library;

// Changed indentation from 8 spaces to 4 spaces.
public class FixBookControl { 	// Changed class name 'fIX_bOOK_cONTROL' to 'FixBookControl'. 
	
    private FixBookUI ui;						// Changed variable name 'Ui' to 'ui'.
    private enum ControlState { INITIALISED, READY, FIXING };		// Changed class name 'CoNtRoL_StAtE' to 'ControlState'.
    private ControlState state;						// Changed class name 'CoNtRoL_StAtE' to 'ControlState' and 'StAtE' to 'state'.
    private Library library;						// Changed variable name 'LiBrArY' to 'library'.
    private Book currentBook;						// Changed variable name 'CuRrEnT_BoOk' to 'currentBook'.


    public fixBookControl() {						// Changed method name 'fIX_bOOK_cONTROL' to 'fixBookControl'.
	this.library = Library.getInstance();				// Changed variable name 'LiBrArY' to 'library' and 'GeTiNsTaNcE()' to 'getInstance()'.
	state = ControlState.INITIALISED;				// Changed class name 'CoNtRoL_StAtE' to 'ControlState' and 'StAtE' to 'state'.
    }
	
	
    public void SeT_Ui(FixBookUI ui) {
	if (!StAtE.equals(CoNtRoL_StAtE.INITIALISED)) 
	    throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
			
        this.Ui = ui;
	ui.SeT_StAtE(FixBookUI.uI_sTaTe.READY);
	StAtE = CoNtRoL_StAtE.READY;		
    }


    public void BoOk_ScAnNeD(int BoOkId) {
	if (!StAtE.equals(CoNtRoL_StAtE.READY)) 
	    throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
			
	CuRrEnT_BoOk = LiBrArY.gEt_BoOk(BoOkId);
		
	if (CuRrEnT_BoOk == null) {
	    Ui.dIsPlAy("Invalid bookId");
	    return;
	}
	if (!CuRrEnT_BoOk.iS_DaMaGeD()) {
	    Ui.dIsPlAy("Book has not been damaged");
	    return;
	}
		Ui.dIsPlAy(CuRrEnT_BoOk.toString());
		Ui.SeT_StAtE(FixBookUI.uI_sTaTe.FIXING);
		StAtE = CoNtRoL_StAtE.FIXING;		
	}

    public void FiX_BoOk(boolean mUsT_FiX) {
        if (!StAtE.equals(CoNtRoL_StAtE.FIXING)) 
	    throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
			
	if (mUsT_FiX) 
	    LiBrArY.RePaIr_BoOk(CuRrEnT_BoOk);
	    CuRrEnT_BoOk = null;
	    Ui.SeT_StAtE(FixBookUI.uI_sTaTe.READY);
	    StAtE = CoNtRoL_StAtE.READY;		
    }

	
    public void SCannING_COMplete() {
	if (!StAtE.equals(CoNtRoL_StAtE.READY)) 
	    throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
			
	Ui.SeT_StAtE(FixBookUI.uI_sTaTe.COMPLETED);		
    }

}
