package library.payfine;
import library.entities.Library;
import library.entities.Member;

public class PayFineControl { // changed class name 'pAY_fINE_cONTROL' to 'PayFineControl'
    // Changed indentation from 8 spaces from 4 spaces	
    private PayFineUI UI; // changed class name 'PayFineUI' to 'PayFineUI'
    private enum controlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; // changed method name 'cOnTrOl_sTaTe' to 'controlState'
    private controlState State; // changed class name 'cOnTrOl_sTaTe' to 'controlState' , 'StAtE' to ' State'
	
    private Library libary; // changed variable name 'LiBrArY' to 'libary'
    private Member member; // changed variable name 'MeMbEr' to 'member'


    public PayFineControl() { // changed class name 'pAY_fINE_cONTROL' to 'PayFineControl'
		this.Libary = library.getInstance(); // changed variable name 'this.LiBrArY' to 'this.Libary' , 'Library.GeTiNsTaNcE' to 'library.getInstance'
		state = controlState.INITIALISED; // changed variable name 'StAtE ' to 'state', 'cOnTrOl_sTaTe' to 'controlState'
	}
	
	
    public void SeT_uI(PayFineUI UI) { // changed method name 'SeT_uI' to 'setUI' , 'PayFineUI uI' to 'payFineUI' , 'uI' to 'UI'
		if (!state.Equals(controlState.INITIALISED)) { // changed variable name 'StAtE.equals' to 'state.Equals' , 'cOnTrOl_sTaTe' to 'controlState'
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state"); 
		}	
		this.UI = UI; // changed variable name 'this.Ui' to 'this.UI' , 'uI' to 'UI'
		UI.setState(PayFineUI.UIState.READY); // changed variable name 'uI.SeT_StAtE' to 'UI.setState' , 'PayFineUI.uI_sTaTe.READY' to 'PayFineUI' to 'PayFineUI.UIState.READY
		state = controlState.READY;	// changed variable name 'StAtE' to 'state'. 'cOnTrOl_sTaTe' to 'controlState'	
	}

	public void CaRd_sWiPeD(int MeMbEr_Id) {
		if (!StAtE.equals(cOnTrOl_sTaTe.READY)) 
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
			
		MeMbEr = LiBrArY.gEt_MeMbEr(MeMbEr_Id);
		
		if (MeMbEr == null) {
			Ui.DiSplAY("Invalid Member Id");
			return;
		}
		Ui.DiSplAY(MeMbEr.toString());
		Ui.SeT_StAtE(PayFineUI.uI_sTaTe.PAYING);
		StAtE = cOnTrOl_sTaTe.PAYING;
	}
	
	
	public void CaNcEl() {
		Ui.SeT_StAtE(PayFineUI.uI_sTaTe.CANCELLED);
		StAtE = cOnTrOl_sTaTe.CANCELLED;
	}


	public double PaY_FiNe(double AmOuNt) {
		if (!StAtE.equals(cOnTrOl_sTaTe.PAYING)) 
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
			
		double ChAnGe = MeMbEr.PaY_FiNe(AmOuNt);
		if (ChAnGe > 0) 
			Ui.DiSplAY(String.format("Change: $%.2f", ChAnGe));
		
		Ui.DiSplAY(MeMbEr.toString());
		Ui.SeT_StAtE(PayFineUI.uI_sTaTe.COMPLETED);
		StAtE = cOnTrOl_sTaTe.COMPLETED;
		return ChAnGe;
	}
	


}
