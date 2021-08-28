package library.borrowbook;
import java.util.ArrayList;
import java.util.List;

import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;

public class BorrowBookControl { //Changed 'bORROW_bOOK_cONTROL' to 'BorrowBookControl'

    private BorrowBookUI ui; //Changed 'uI' to 'ui'

    private Library library;//Changed 'lIbRaRy' to 'library'
    private Member member;//Changed 'mEmBeR' to 'member'
    private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED }; //Changed 'CONTROL_STATE' to 'ControlState'
    private ContoralState state; //Changed 'CONTROL_STATE' to 'ControlState' & 'sTaTe' to 'state'

    private List<Book> pendingList; //Changed 'pEnDiNg_LiSt' to 'pendingList'
    private List<Loan> completeList; //Changed 'cOmPlEtEd_LiSt' to 'completeList'
    private Book book; //Changed 'bOok' to 'book


    public BorrowBookControl() { //Changed 'bORROW_bOOK_cONTROL' to 'BorrowBookControl'
        this.library = Library.getInstance();//Changed 'lIbRaRy' to 'library' & 'GeTiNsTaNcE' to 'getInstance'
        state = ControlState.INITIALISED; //Changed 'CONTROL_STATE' to 'ControlState' & 'sTaTe' to 'state' 
    }
	
    public void setUI (BorrowBookUI ui) { //Changed 'SeT_Ui' to 'setUi' & 'Ui' to 'ui'
        if (!state.equals(ControlState.INITIALISED)) //Changed 'CONTROL_STATE' to 'ControlState' & 'sTaTe' to 'state'
            throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");

        this.ui = ui;//Changed 'Ui' to 'ui' & 'uI' to 'ui'
        ui.setState(BorrowBookUI.UIState.READY); //Changed 'uI' to 'ui' & 'SeT_StAtE' to 'setState' & 'uI_STaTe' to 'UIState'
        state = ControlState.READY;//Changed 'CONTROL_STATE' to 'ControlState' & 'sTaTe' to 'state'	
    }


    public void swiped(int memberId) { //Changed 'SwIpEd' to 'swiped' & 'mEmBeR_Id' to 'memberId'
        if (!state.equals(ConstrolState.READY)) //Changed 'CONTROL_STATE' to 'ControlState' & 'sTaTe' to 'state'
            throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");

        member = library.getMember(memberId); //Changed 'lIbRaRy' to 'library' & 'mEmBeR' to 'member' & 'mEmBeR_Id' to 'memberId' & 'getMember'
        if (member == null) { //Changed 'mEmBeR' to 'member'
            ui.display("Invalid memberId"); //Changed 'uI' to 'ui' & 'DiSpLaY' to 'display'
            return;
        }
        if (library.canMemberBorrow(member)) { //Changed 'lIbRaRy' to 'library' & 'mEmBeR' to 'member' & 'canMemberBorrow'
            pendingList = new ArrayList<>();//Changed 'pEnDiNg_LiSt' to 'pendingList'
            ui.setState(BorrowBookUI.UIState.SCANNING);//Changed 'uI' to 'ui' & 'SeT_StAtE' to 'setState' & 'uI_STaTe' to 'UIState'
            state = ControlState.SCANNING; //Changed 'CONTROL_STATE' to 'ControlState' & 'sTaTe' to 'state'
        }
        else {
            ui.display("Member cannot borrow at this time");//Changed 'uI' to 'ui' & 'DiSpLaY' to 'display'
            ui.setState(BorrowBookUI.UIState.RESTRICTED); //Changed 'uI' to 'ui' & 'SeT_StAtE' to 'setState' & 'uI_STaTe' to 'UIState'
        }
    }


    public void scanned(int bookId) {//Changed 'ScAnNeD' to 'scanned' & 'bOoKiD' to 'bookId'
        book = null; //Changed 'bOok' to 'book
        if (!state.equals(ContorlState.SCANNING)) //Changed 'CONTROL_STATE' to 'ControlState' & 'sTaTe' to 'state'
            throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");

        book = library.getBook(bookId); //Changed 'bOok' to 'book & 'lIbRaRy' to 'library' & 'bOoKiD' to 'bookId' & 'gEt_BoOk' to 'getBook'
        if (book == null) { //Changed 'bOok' to 'book
            ui.display("Invalid bookId"); //Changed 'uI' to 'ui' & 'DiSpLaY' to 'display'
            return;
        }
        if (!book.isAvailable()) { //Changed 'bOok' to 'book & 'iS_AvAiLaBlE' to 'isAvailable'
            ui.display("Book cannot be borrowed"); //Changed 'uI' to 'ui' & 'DiSpLaY' to 'display'
            return;
        }
        pendingList.add(book); //Changed 'bOok' to 'book & 'pEnDiNg_LiSt' to 'pendingList'
        for (Book book : pendingList) //Changed 'pEnDiNg_LiSt' to 'pendingList' & 'B' to 'book'
            ui.display(book.toString()); //Changed 'uI' to 'ui' & 'DiSpLaY' to 'display' & 'B' to 'book'

        if (library.getNumberOfLoansRemainingForMember(member) - pendingList.size() == 0) { //Changed 'lIbRaRy' to 'library' & 'mEmBeR' to 'member' & 'gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr' to 'getNumberOfLoansRemainingForMember' & 'pEnDiNg_LiSt' to 'pendingList'
            ui.display("Loan limit reached"); //Changed 'uI' to 'ui' & 'DiSpLaY' to 'display'
            complete();//Changed 'CoMpLeTe' to 'complete'
        }
    }


    public void complete() {//Changed 'CoMpLeTe' to 'complete'
        if (pendingList.size() == 0) //Changed 'pEnDiNg_LiSt' to 'pendingList'
            cancel();//Changed 'CaNcEl' to 'cancel'

        else {
            ui.display("\nFinal Borrowing List"); //Changed 'uI' to 'ui' & 'DiSpLaY' to 'display'
            for (Book book : pendingList) //Changed 'pEnDiNg_LiSt' to 'pendingList' & 'bOoK' to 'book'
                ui.display(book.toString());//Changed 'uI' to 'ui' & 'DiSpLaY' to 'display' & 'bOoK' to 'book'

            completedList = new ArrayList<Loan>(); //Changed 'cOmPlEtEd_LiSt' to 'completedList'
            ui.setState(BorrowBookUI.UIState.FINALISING);//Changed 'uI' to 'ui' & 'SeT_StAtE' to 'setState' & 'uI_STaTe' to 'UIState'
            state = ControlState.FINALISING;//Changed 'CONTROL_STATE' to 'ControlState' & 'sTaTe' to 'state'
        }
    }


    public void commitLoans() { //Changed 'CoMmIt_LoAnS' to 'commitLoans'
        if (!state.equals(ControlState.FINALISING)) //Changed 'CONTROL_STATE' to 'ControlState' & 'sTaTe' to 'state'
            throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");

        for (Book book : pendingList) { //Changed 'uI' to 'ui' & 'DiSpLaY' to 'display' & 'B' to 'book'
            Loan loan = library.issueLoan(book, member); //Changed 'lIbRaRy' to 'library' & 'mEmBeR' to 'member' & 'lOaN' to 'loan' & 'iSsUe_LoAn' to 'issueLoan'
            completeLoan.add(loan);//Changed 'cOmPlEtEd_LiSt' to 'completedList' & 'lOaN' to 'loan' 
        }
        ui.display("Completed Loan Slip"); //Changed 'uI' to 'ui' & 'DiSpLaY' to 'display'
        for (Loan loan : completedList) //Changed 'LOAN' to 'loan' & 'cOmPlEtEd_LiSt' to 'completedList'
            ui.display(loan.toString()); //Changed 'uI' to 'ui' & 'DiSpLaY' to 'display' & 'lOaN' to 'loan' 

        ui.setState(BorrowBookUI.UIState.COMPLETED); //Changed 'uI' to 'ui' & 'SeT_StAtE' to 'setState' & 'uI_STaTe' to 'UIState'
        state = ControlState.COMPLETED; //Changed 'CONTROL_STATE' to 'ControlState' & 'sTaTe' to 'state'
    }


    public void cancel() { //Changed 'CaNcEl' to 'cancel'
        ui.setState(BorrowBookUI.UIState.CANCELLED);//Changed 'uI' to 'ui' & 'SeT_StAtE' to 'setState' & 'uI_STaTe' to 'UIState'
        state = ControlState.CANCELLED; //Changed 'CONTROL_STATE' to 'ControlState' & 'sTaTe' to 'state'
    }


}
