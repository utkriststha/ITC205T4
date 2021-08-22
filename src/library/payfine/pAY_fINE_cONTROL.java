package library.payfine;
import library.entities.Library;
import library.entities.Member;

public class payFineControl { // changed method name 'pAY_fINE_cONTROL' to 'payFineControl'
	
    private payFineUi Ui; // changed variable name 'PayFineUI' to 'payFineUi'
    private enum controlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; // changed variable name 'cOnTrOl_sTaTe' to 'controlState'
    private controlState State; // changed variable name 'cOnTrOl_sTaTe' to 'controlState' , 'StAtE' to ' State'
	
    private Library libary; // changed variable name 'LiBrArY' to 'libary'
    private Member member; // changed variable name 'MeMbEr' to 'member'


	public pAY_fINE_cONTROL() { 
		this.LiBrArY = Library.GeTiNsTaNcE();
		StAtE = cOnTrOl_sTaTe.INITIALISED;
	}
	
	
	public void SeT_uI(PayFineUI uI) {
		if (!StAtE.equals(cOnTrOl_sTaTe.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.Ui = uI;
		uI.SeT_StAtE(PayFineUI.uI_sTaTe.READY);
		StAtE = cOnTrOl_sTaTe.READY;		
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
