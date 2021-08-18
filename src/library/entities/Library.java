package library.entities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable {
	
	private static final String LIBRARY_FILE = "library.obj"; //Changed 'lIbRaRyFiLe' to 'LIBRARY_FILE'
	private static final int LOAN_LIMIT = 2; //Changed 'lOaNlImIt' to 'LOAN_LIMIT'
	private static final int LOAN_PERIOD = 2; //Changed 'loanPeriod' to 'LOAN_PERIOD'
	private static final double FINE_PER_DAY = 1.0; //Changed 'FiNe_PeR_DaY' to 'FINE_PER_DAY'
	private static final double MAX_FINES_OWED = 1.0; //Changed 'maxFinesOwed' to 'MAX_FINES_OWED'
	private static final double DAMAGE_FEE = 2.0; //Changed 'damageFee' to 'DAMAGE_FEE'
	
	private static Library self; //Changed 'SeLf' to 'self'
	private int bookId; //Changed 'bOoK_Id' to 'bookId'
	private int memberId; //Changed 'mEmBeR_Id' to 'memberId'
	private int loanId; //Changed 'lOaN_Id;' to 'loadId'
	private Date loanDate; //Changed 'lOaN_DaTe' to 'loanDate'
	
	private Map<Integer, Book> catalog; //Changed 'CaTaLoG' to 'catalog' 
	private Map<Integer, Member> members; //Changed 'MeMbErS' to 'members'
	private Map<Integer, Loan> loans; //Changed 'LoAnS' to 'loans'
	private Map<Integer, Loan> curentLoans; //Changed 'CuRrEnT_LoAnS' to 'currentLoans'
	private Map<Integer, Book> damagedBooks; //Changed 'DaMaGeD_BoOkS' to 'damagedBooks'
	

	private Library() {
		catalog = new HashMap<>(); //Changed 'CaTaLoG' to 'catalog' 
		members = new HashMap<>(); //Changed 'MeMbErS' to 'members'
		loans = new HashMap<>(); //Changed 'LoAnS' to 'loans'
		currentLoans = new HashMap<>(); //Changed 'CuRrEnT_LoAnS' to 'currentLoans'
		damagedBooks = new HashMap<>(); //Changed 'DaMaGeD_BoOkS' to 'damagedBooks'
		bookId = 1; //Changed 'bOoK_Id' to 'bookId'
		memberId = 1; //Changed 'mEmBeR_Id' to 'memberId'	
		loanId = 1; //Changed 'lOaN_Id;' to 'loadId'	
	}

	
	public static synchronized Library getInstance() { // Changes 'GeTiNsTaNcE()' to 'getInstance()'		
		if (self == null) { //Changed 'SeLf' to 'self'
			Path path = Paths.get(LIBRARY_FILE); //Changed 'lIbRaRyFiLe' to 'LIBRARY_FILE' & 'PATH' to 'path'		
			if (Files.exists(path)) { //Changed 'PATH' to 'path'	
				try (ObjectInputStream libraryFile = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) { //Changed 'lIbRaRyFiLe' to 'LIBRARY_FILE' & 'LiBrArY_FiLe' to 'libraryFile'
					self = (Library) libraryFile.readObject(); //Changed 'LiBrArY_FiLe' to 'libraryFile' & 'SeLf' to 'self'
					Calendar.getInstance().setDate(self.loanDate); //Changed 'gEtInStAnCe()' to 'getInstance' & 'SeT_DaTe' to 'setDate' & 'lOaN_DaTe' to 'loadDate' & 'SeLf' to 'self'
					libraryFile.close(); //Changed 'LiBrArY_FiLe' to 'libraryFile'
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new Library();  //Changed 'SeLf' to 'self'
		}
		return self;  //Changed 'SeLf' to 'self'
	}

	
	public static synchronized void save() { //Changed 'SaVe' to 'save'
		if (self != null) { //Changed 'SeLf' to 'self'
			self.loanDate = Calendar.getInstance().getDate(); //Changed 'SeLf' to 'self' & 'gEtInStAnCe()' to 'getInstance' & 'gEt_DaTe' to 'getDate' & 'lOaN_DaTe' to 'loadDate'
			try (ObjectOutputStream libraryFile = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE));) { //Changed 'LiBrArY_FiLe' to 'libraryFile' & 'lIbRaRyFiLe' to 'LIBRARY_FILE'
				libraryFile.writeObject(self); //Changed 'LiBrArY_FiLe' to 'libraryFile' & 'SeLf' to 'self'
				libraryFile.flush(); //Changed 'LiBrArY_FiLe' to 'libraryFile'
				libraryFile.close(); //Changed 'LiBrArY_FiLe' to 'libraryFile'
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int getBookId() { //Changed 'gEt_BoOkId' to 'getBookId'
		return bookId; //Changed 'bOoK_Id' to 'bookId'
	}
	
	
	public int getMemberId() {//Changed 'gEt_MeMbEr_Id' to 'getMemberId'
		return memberId; //Changed 'mEmBeR_Id' to 'memberId'
	}
	
	
	private int getNextBookId() {//Changed 'gEt_NeXt_BoOk_Id' to 'getNextBookId'
		return bookId++; //Changed 'bOoK_Id' to 'bookId'
	}

	
	private int getNextMemberId() { //Changed 'gEt_NeXt_MeMbEr_Id' to 'getNextMemberId'
		return memberId++; //Changed 'mEmBeR_Id' to 'memberId'
	}

	
	private int getNextLoanId() { //Changed 'gEt_NeXt_LoAn_Id' to 'getNextLoadId'
		return loanId++; //Changed 'lOaN_Id;' to 'loadId'
	}

	
	public List<Member> listMembers() { // Changed 'LIsT_MeMbErS' to 'listMembers'	
		return new ArrayList<Member>(member.values()); //Changed 'MeMbErS' to 'members'
	}


	public List<Book> listBooks() { // Changed 'lIsT_BoOkS' to 'listMembers'		
		return new ArrayList<Book>(CaTaLoG.values()); //Changed 'CaTaLoG' to 'catalog' 
	}


	public List<Loan> listCurentLoans() { // Changed 'lISt_CuRrEnT_LoAnS' to 'listMembers'	
		return new ArrayList<Loan>(curentLoans.values()); //Changed 'CuRrEnT_LoAnS' to 'currentLoans'
	}


	public Member addMember(String lastName, String firstName, String email, int phoneNo) { //Changed 'aDd_MeMbEr' to 'addMember'		
		Member member = new Member(lastName, firstName, email, phoneNo, getNextMemberId()); //Changed 'gEt_NeXt_MeMbEr_Id' to 'getNextMemberId'
		members.put(member.getId(), member);// Changed 'GeT_ID' to 'getId' & gEt_MeMbEr	
		return member;
	}

	
	public Book addBook(String a, String t, String c) {// Changed 'aDd_BoOk' to 'addBook'
		Book book = new Book(a, t, c, getNextBookId()); // Changed 'gEt_NeXt_BoOk_Id' to 'getNextBookId' & 'b' to 'book'
		catalog.put(b.getId(), b); //Changed 'CaTaLoG' to 'catalog' & 'gEtId' to 'getId'
		return book;
	}

	
	public Member getMember(int memberId) { //Changed 'gEt_MeMbEr' to 'getMember'
		if (members.containsKey(memberId)){ //Changed 'MeMbErS' to 'members' & added {}
			return members.get(memberId); //Changed 'MeMbErS' to 'members'
		}
		return null;
	}

	
	public Book getBook(int bookId) { //Changed 'gEt_BoOk' to 'getBook'
		if (catalog.containsKey(bookId)){ //Changed 'CaTaLoG' to 'catalog' & added {}
			return catalog.get(bookId); //Changed 'CaTaLoG' to 'catalog' 
		}
		return null;
	}

	
	public int getLoanLimit() { //Changed 'gEt_LoAn_LiMiT' to 'getLoanLimit'
		return LOAN_LIMIT; //Changed 'lOaNlImIt' to 'LOAN_LIMIT'
	}

	
	public boolean canMemberBorrow(Member member) {//Changed 'cAn_MeMbEr_BoRrOw' to 'canMemberBorrow'		
		if (member.getNumberOfCurrentLoans() == LOAN_LIMIT){ //Changed 'gEt_nUmBeR_Of_CuRrEnT_LoAnS' to 'getNumberOfCurrentLoans' & 'lOaNlImIt' to 'LOAN_LIMIT' & added {}
			return false;
		}	
		if (member.fineOwed() >= MAX_FINES_OWED){ //Changed 'FiNeS_OwEd' to 'fineOwed' & 'maxFinesOwed' to 'MAX_FINES_OWED' & added {}
			return false;
		}	
		for (Loan loan : member.getLoans()){ // Changed 'GeT_LoAnS' & 'getLoans' & added {}
			if (loan.isOverDue()){ //Changed 'Is_OvEr_DuE' & 'isOverDue' & added {}
				return false;
			}
		return true;
		}
	}

	
	public int getNumberOfLoansRemainingForMember(Member member) { //Changed 'MeMbErS' to 'members' & 'gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr' to 'getNumberOfLoansRemainingForMember'
		return LOAN_LIMIT - member.getNumberOfCurrentLoans(); //Changed 'MeMbErS' to 'members' & 'lOaNlImIt' to 'LOAN_LIMIT' & 'gEt_nUmBeR_Of_CuRrEnT_LoAnS' to 'getNumberOfCurrentLoans'
	}

	
	public Loan issueLoan(Book book, Member member) { //Changed 'iSsUe_LoAn' to 'issueLoan'
		Date dueDate = Calendar.getInstance().getDueDate(loanPeriod); //Changed 'gEtInStAnCe' to 'getInstance' & 'gEt_DuE_DaTe' to 'getDueDate'
		Loan loan = new Loan(gEt_NeXt_LoAn_Id(), book, member, dueDate);// Changed 'gEt_NeXt_LoAn_Id' to 'getNextLoanId'
		member.takeOutLoan(loan);//Changed 'TaKe_OuT_LoAn' to 'takeOutLoan
		book.borrow(); //Changed 'BoRrOw' & 'borrow' 
		loans.put(loan.getId(), loan); //Changed 'LoAnS' to 'loans' & 'GeT_Id' to 'getId'
		currentLoans.put(book.getId(), loan);//Changed 'CuRrEnT_LoAnS' to 'currentLoans' & 'gEtId' to 'getId'
		return loan;
	}
	
	
	public Loan getLoanByBookId(int bookId) { //Chnaged 'GeT_LoAn_By_BoOkId' to 'getLoanByBookId'
		if (currentLoans.containsKey(bookId)){ //Changed 'CuRrEnT_LoAnS' to ;currentLoans' & added {}
			return currentLoans.get(bookId); //Changed 'CuRrEnT_LoAnS' to ;currentLoans'
		}
		return null;
	}

	
	public double calculateOverDueFine(Loan loan) { //Changed 'CaLcUlAtE_OvEr_DuE_FiNe' to 'calculateOverDueFine' & 'LoAn' to 'loan'
		if (loan.isOverDue()) {//Changed 'Is_OvEr_DuE' to 'isOverDue' & 'LoAn' to 'loan'
			long daysOverDue = Calendar.getInstance().getDaysDifference(loan.getDueDate()); //Changed 'DaYs_OvEr_DuE' to 'dayOverDue' & 'gEtInStAnCe()' to 'getInstance' &  'GeT_DaYs_DiFfErEnCe' to 'getDayDifference' & 'GeT_DuE_DaTe' to 'getDueDate' & 'LoAn' to 'loan'
			double fine = daysOverDue * finePerDay; //Changed 'DaYs_OvEr_DuE' to 'dayOverDue' & 'FiNe_PeR_DaY' to 'FINE_PER_DAY' & 'fInE' to 'fine'
			return fine; //Changed 'fInE' to 'fine'
		}
		return 0.0;		
	}


	public void dischargeLoan(Loan currentLoan, boolean isDamaged) { //Changed 'DiScHaRgE_LoAn' to 'dischargeLoan' & 'cUrReNt_LoAn' to 'currentLoan' & 'iS_dAmAgEd' to 'isDamanged'
		Member member = currentLoan.getMember(); //Changed 'mEmBeR' to 'member' & 'cUrReNt_LoAn' to 'currentLoan' &  'GeT_MeMbEr' to 'getMember'
		Book book  = currentLoan.getBook(); //Changed 'cUrReNt_LoAn' to 'currentLoan' & 'GeT_BoOk' to 'getBook' & 'bOoK' to 'book'
		
		double overDueFine = calculateOverDueFine(currentLoan); //Changed 'CaLcUlAtE_OvEr_DuE_FiNe' to 'calculateOverDueFine' & 'cUrReNt_LoAn' to 'currentLoan' & 'oVeR_DuE_FiNe' to 'overDueFine'
		member.addFine(overDueFine); //Changed'oVeR_DuE_FiNe' to 'overDueFine' & 'AdD_FiNe' to 'addFine' & 'mEmBeR' to 'member'
		
		member.dischargeLoan(currentLoan);//Changed 'cUrReNt_LoAn' to 'currentLoan' & 'cUrReNt_LoAn' to 'currentLoan' & 'dIsChArGeLoAn' to 'dischargeLoan' & 'mEmBeR' to 'member'
		book.ReTuRn(isDamaged); //Changed 'bOoK' to 'book' & 'ReTuRn' to 'return' & 'iS_dAmAgEd' to 'isDamaged'
		if (isDamaged) { //Changed 'iS_dAmAgEd' to 'isDamaged'
			member.addFine(damageFee); //Changed 'mEmBeR' to 'member' & 'AdD_FiNe' to 'addFine' 
			damagedBook.put(book.getId(), book); //Changed 'bOoK' to 'book' & 'DaMaGeD_BoOkS' to 'damageBooks' & 'gEtId' to 'getId'
		}
		currentLoan.discharge(); //Changed 'cUrReNt_LoAn' to 'currentLoan' & 'DiScHaRgE' to 'discharge'
		currentLoan.remove(book.getId()); //Changed 'cUrReNt_LoAn' to 'currentLoan' & 'bOoK' to 'book & 'gEtId' to 'getId'
	}


	public void checkCurrentLoans() {//Changed 'cHeCk_CuRrEnT_LoAnS' to 'checkCurrentLoans'
		for (Loan loan : currentLoan.values()) //Changed 'lOaN' to 'loan' & 'CuRrEnT_LoAnS' to 'currentLoans'
			loan.checkOverDue(); //Changed 'lOaN' to 'loan' & 'cHeCk_OvEr_DuE' to 'checkOverDue'
				
	}


	public void repairBook(Book currentBook) { //Changed 'RePaIr_BoOk' to 'repairBook' & 'cUrReNt_BoOk' to 'currentBook'
		if (damagedBook.containsKey(currentBook.getId())) { //Changed 'DaMaGeD_BoOkS' to 'damagedBooks' & 'cUrReNt_BoOk' to 'currentBook' & 'gEtId' to 'getId'
			currentBook.repair(); //Chnaged 'cUrReNt_BoOk' to 'currentBook' & 'RePaIr' to 'repair'
			damagedBook.remove(currentBook.getId()); { //Changed 'DaMaGeD_BoOkS' to 'damagedBooks' & 'cUrReNt_BoOk' to 'currentBook' & 'gEtId' to 'getId'
		}
		else 
			throw new RuntimeException("Library: repairBook: book is not damaged");
		
		
	}
	
	
}
