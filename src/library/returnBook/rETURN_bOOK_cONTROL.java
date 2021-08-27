package library.returnBook;
import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;

public class ReturnBookControl {// Class name changed from 'rETURN_bOOK_cONTROL' to 'ReturnBookControl'
// indents size are changed to 4 spaces
	private ReturnBookUI Ui;
	private enum ControlState { INITIALISED, READY, INSPECTING };// Changed 'cOnTrOl_sTaTe' to 'ControlState'
	private ControlState State;// Changed class name 'cOnTrOl_sTaTe sTaTe' to 'ControlState State'
	
	private Library library;// Changed class name 'lIbRaRy' to 'library'
	private Loan CurrentLoan;// Changed 'CurrENT_loan' to 'CurrentLoan'
	

	public ReturnBookControl() {// Changed class name 'rETURN_bOOK_cONTROL' to 'ReturnBookControl'
		this.lIbRaRy = Library.getInstanc();//Changed variable name 'lIbRaRy' to 'library' and 'GeTiNsTaNcE' to 'getInstance'
		state = controlState.INITIALISED;// Changed class name 'sTaTe' to 'state' and 'cOnTrOl_sTaTe' to 'controlState'
	}
	
	
	public void setUI(ReturnBookUI ui) {// Changed 'sEt_uI' to 'setUI' and  ' uI' to 'ui'
		if (!state.equals(controlState.INITIALISED)) // Changed 'sTaTe' to 'state' and 'cOnTrOl_sTaTe' to 'ControlState'
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		
		this.ui = ui;// Changed 'Ui' to 'ui' and 'uI' to 'ui'
		ui.setState(ReturnBookUI.UIstate.READY);//// Changed 'uI' to 'ui', 'set_sTaTe' to 'setState' and 'uI_sTaTe' to 'UIstate'
		state = controlState.READY;// Changed class name 'sTaTe' to 'state' and 'cOnTrOl_sTaTe' to 'controlState'		
	}


	public void bOoK_sCaNnEd(int bOoK_iD) {
		if (!sTaTe.equals(cOnTrOl_sTaTe.READY)) 
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		
		Book cUrReNt_bOoK = lIbRaRy.gEt_BoOk(bOoK_iD);
		
		if (cUrReNt_bOoK == null) {
			Ui.DiSpLaY("Invalid Book Id");
			return;
		}
		if (!cUrReNt_bOoK.iS_On_LoAn()) {
			Ui.DiSpLaY("Book has not been borrowed");
			return;
		}		
		CurrENT_loan = lIbRaRy.GeT_LoAn_By_BoOkId(bOoK_iD);	
		double Over_Due_Fine = 0.0;
		if (CurrENT_loan.Is_OvEr_DuE()) 
			Over_Due_Fine = lIbRaRy.CaLcUlAtE_OvEr_DuE_FiNe(CurrENT_loan);
		
		Ui.DiSpLaY("Inspecting");
		Ui.DiSpLaY(cUrReNt_bOoK.toString());
		Ui.DiSpLaY(CurrENT_loan.toString());
		
		if (CurrENT_loan.Is_OvEr_DuE()) 
			Ui.DiSpLaY(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));
		
		Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.INSPECTING);
		sTaTe = cOnTrOl_sTaTe.INSPECTING;		
	}


	public void sCaNnInG_cOmPlEtE() {
		if (!sTaTe.equals(cOnTrOl_sTaTe.READY)) 
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
			
		Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.COMPLETED);		
	}


	public void dIsChArGe_lOaN(boolean iS_dAmAgEd) {
		if (!sTaTe.equals(cOnTrOl_sTaTe.INSPECTING)) 
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		
		lIbRaRy.DiScHaRgE_LoAn(CurrENT_loan, iS_dAmAgEd);
		CurrENT_loan = null;
		Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.READY);
		sTaTe = cOnTrOl_sTaTe.READY;				
	}


}
