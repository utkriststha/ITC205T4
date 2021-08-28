package library.returnBook;
import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;

public class ReturnBookControl {// Class name changed from 'rETURN_bOOK_cONTROL' to 'ReturnBookControl'
// indents size are changed to 4 spaces
	private ReturnBookUI Ui;
	private enum controlState { INITIALISED, READY, INSPECTING };// Changed 'cOnTrOl_sTaTe' to 'controlState'
	private controlState State;// Changed class name 'cOnTrOl_sTaTe sTaTe' to 'ControlState State'
	
	private Library library;// Changed class name 'lIbRaRy' to 'library'
	private Loan currentLoan;// Changed 'CurrENT_loan' to 'CurrentLoan'
	

	public ReturnBookControl() {// Changed class name 'rETURN_bOOK_cONTROL' to 'ReturnBookControl'
		this.library = Library.getInstanc();//Changed variable name 'lIbRaRy' to 'library' and 'GeTiNsTaNcE' to 'getInstance'
		state = controlState.INITIALISED;// Changed class name 'sTaTe' to 'state' and 'cOnTrOl_sTaTe' to 'controlState'
	}
	
	
	public void setUI(ReturnBookUI ui) {// Changed 'sEt_uI' to 'setUI' and  ' uI' to 'ui'
		if (!state.equals(controlState.INITIALISED)) // Changed 'sTaTe' to 'state' and 'cOnTrOl_sTaTe' to 'ControlState'
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		
		this.ui = ui;// Changed 'Ui' to 'ui' and 'uI' to 'ui'
		ui.setState(ReturnBookUI.UIstate.READY);//// Changed 'uI' to 'ui', 'set_sTaTe' to 'setState' and 'uI_sTaTe' to 'UIstate'
		state = controlState.READY;// Changed class name 'sTaTe' to 'state' and 'cOnTrOl_sTaTe' to 'controlState'		
	}


	public void bookScanned(int bookId) {// Changed 'bOoK_sCaNnEd' to 'bookScanned' and 'bOoK_iD' to 'bookId'
		if (!state.equals(controlState.READY)) // Changed 'sTaTe' to 'state' and 'cOnTrOl_sTaTe' to 'controlState'
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		
		Book currentBook =library.getBook(bookId);// Changed 'cUrReNt_bOoK ' to 'currentBook', 'lIbRaRy' to 'library', 'gEt_BoOk' to 'getBook' and 'bookId'
		
		if (currentBook == null) {// Changed variable name 'cUrReNt_bOoK' to 'currentBook'
			ui.display"Invalid Book Id");// Changed 'Ui' to 'ui' and 'DiSpLaY' to 'display'
			return;
		}
		if (!currentBook.isOnLoan()) {// Changed 'cUrReNt_bOoK' to 'currentBook' and 'iS_On_LoAn' to 'isOnLoan'
			ui.display("Book has not been borrowed");// Changed 'Ui' to 'ui' and 'DiSpLaY' to 'display'
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);	// Changed variable name 'CurrENT_loan' to 'currentLoan', 'lIbRaRy' to 'library','GeT_LoAn_By_BoOkId' to 'getLoanByBookId' and 'bOoK_iD' to 'bookId'
		double overDueFine = 0.0;// Changed 'Over_Due_Fine' to 'overDueFine'
		if (currentLoan.isOverDue()) //Changed variable name 'CurrENT_loan' to 'currentLoan' and 'Is_OvEr_DuE' to 'isOverDue'
			overDueFine = library.calculateOverDueFine(currentLoan);// Changed 'Over_Due_Fine' to 'overDueFine','lIbRaRy' to 'library','CaLcUlAtE_OvEr_DuE_FiNe' to 'calculateOverDueFine' and 'CurrENT_loan' to 'currentLoan'
		
		ui.display("Inspecting");//Changed 'Ui' to 'ui' and 'DiSpLaY' to 'display'
		ui.display(currentBook.toString());//Changed 'Ui' to 'ui' and 'DiSpLaY' to 'display' and 'cUrReNt_bOoK' to 'currentBook'
		ui.display(currentLoan.toString());//Changed 'Ui' to 'ui' and 'DiSpLaY' to 'display' and 'CurrENT_loan' to 'currentLoan'
		
		if (currentLoan.isOverDue()) //Changed variable name 'CurrENT_loan' to 'currentLoan' and 'Is_OvEr_DuE' to 'isOverDue'
			ui.display(String.format("\nOverdue fine : $%.2f", overDueFine));// Changed 'Ui' to 'ui', 'DiSpLaY' to 'display and 'Over_Due_Fine' to 'overDueFine'
		
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
