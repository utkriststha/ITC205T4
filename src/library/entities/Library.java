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
	private int loanid; //Changed 'lOaN_Id;' to 'loadId'
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

	
	public static synchronized Library GetInstance() { // Chnages 'GeTiNsTaNcE()' to 'GetInstance()'		
		if (self == null) { //Changed 'SeLf' to 'self'
			Path path = Paths.get(LIBRARY_FILE); //Changed 'lIbRaRyFiLe' to 'LIBRARY_FILE' & 'PATH' to 'path'		
			if (Files.exists(path)) { //Changed 'PATH' to 'path'	
				try (ObjectInputStream libraryFile = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) { //Changed 'lIbRaRyFiLe' to 'LIBRARY_FILE' & 'LiBrArY_FiLe' to 'libraryFile'
			    
					SeLf = (Library) libraryFile.readObject(); //Changed 'LiBrArY_FiLe' to 'libraryFile'
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
			self.loanDate = Calendar.getInstance().getDate(); //Changed 'SeLf' to 'self' 7 'gEtInStAnCe()' to 'getInstance' & 'gEt_DaTe' to 'getDate' & 'lOaN_DaTe' to 'loadDate'
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
		return mEmBeR_Id; //Changed 'mEmBeR_Id' to 'memberId'
	}
	
	
	private int getNextBookId() {//Changed 'gEt_NeXt_BoOk_Id' to 'getNextBookId'
		return bookId++; //Changed 'bOoK_Id' to 'bookId'
	}

	
	private int getNextMemberId() { //Changed 'gEt_NeXt_MeMbEr_Id' to 'getNextMemberId'
		return mEmBeR_Id++; //Changed 'mEmBeR_Id' to 'memberId'
	}

	
	private int getNextLoanId() { //Changed 'gEt_NeXt_LoAn_Id' to 'getNextLoadId'
		return loanid++; //Changed 'lOaN_Id;' to 'loadId'
	}

	
	public List<Member> lIsT_MeMbErS() {		
		return new ArrayList<Member>(MeMbErS.values()); 
	}


	public List<Book> lIsT_BoOkS() {		
		return new ArrayList<Book>(CaTaLoG.values()); 
	}


	public List<Loan> lISt_CuRrEnT_LoAnS() {
		return new ArrayList<Loan>(CuRrEnT_LoAnS.values());
	}


	public Member aDd_MeMbEr(String lastName, String firstName, String email, int phoneNo) {		
		Member member = new Member(lastName, firstName, email, phoneNo, gEt_NeXt_MeMbEr_Id());
		MeMbErS.put(member.GeT_ID(), member);		
		return member;
	}

	
	public Book aDd_BoOk(String a, String t, String c) {		
		Book b = new Book(a, t, c, gEt_NeXt_BoOk_Id());
		CaTaLoG.put(b.gEtId(), b);		
		return b;
	}

	
	public Member gEt_MeMbEr(int memberId) {
		if (MeMbErS.containsKey(memberId)) 
			return MeMbErS.get(memberId);
		return null;
	}

	
	public Book gEt_BoOk(int bookId) {
		if (CaTaLoG.containsKey(bookId)) 
			return CaTaLoG.get(bookId);		
		return null;
	}

	
	public int gEt_LoAn_LiMiT() {
		return lOaNlImIt;
	}

	
	public boolean cAn_MeMbEr_BoRrOw(Member member) {		
		if (member.gEt_nUmBeR_Of_CuRrEnT_LoAnS() == lOaNlImIt ) 
			return false;
				
		if (member.FiNeS_OwEd() >= maxFinesOwed) 
			return false;
				
		for (Loan loan : member.GeT_LoAnS()) 
			if (loan.Is_OvEr_DuE()) 
				return false;
			
		return true;
	}

	
	public int gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr(Member MeMbEr) {		
		return lOaNlImIt - MeMbEr.gEt_nUmBeR_Of_CuRrEnT_LoAnS();
	}

	
	public Loan iSsUe_LoAn(Book book, Member member) {
		Date dueDate = Calendar.gEtInStAnCe().gEt_DuE_DaTe(loanPeriod);
		Loan loan = new Loan(gEt_NeXt_LoAn_Id(), book, member, dueDate);
		member.TaKe_OuT_LoAn(loan);
		book.BoRrOw();
		LoAnS.put(loan.GeT_Id(), loan);
		CuRrEnT_LoAnS.put(book.gEtId(), loan);
		return loan;
	}
	
	
	public Loan GeT_LoAn_By_BoOkId(int bookId) {
		if (CuRrEnT_LoAnS.containsKey(bookId)) 
			return CuRrEnT_LoAnS.get(bookId);
		
		return null;
	}

	
	public double CaLcUlAtE_OvEr_DuE_FiNe(Loan LoAn) {
		if (LoAn.Is_OvEr_DuE()) {
			long DaYs_OvEr_DuE = Calendar.gEtInStAnCe().GeT_DaYs_DiFfErEnCe(LoAn.GeT_DuE_DaTe());
			double fInE = DaYs_OvEr_DuE * FiNe_PeR_DaY;
			return fInE;
		}
		return 0.0;		
	}


	public void DiScHaRgE_LoAn(Loan cUrReNt_LoAn, boolean iS_dAmAgEd) {
		Member mEmBeR = cUrReNt_LoAn.GeT_MeMbEr();
		Book bOoK  = cUrReNt_LoAn.GeT_BoOk();
		
		double oVeR_DuE_FiNe = CaLcUlAtE_OvEr_DuE_FiNe(cUrReNt_LoAn);
		mEmBeR.AdD_FiNe(oVeR_DuE_FiNe);	
		
		mEmBeR.dIsChArGeLoAn(cUrReNt_LoAn);
		bOoK.ReTuRn(iS_dAmAgEd);
		if (iS_dAmAgEd) {
			mEmBeR.AdD_FiNe(damageFee);
			DaMaGeD_BoOkS.put(bOoK.gEtId(), bOoK);
		}
		cUrReNt_LoAn.DiScHaRgE();
		CuRrEnT_LoAnS.remove(bOoK.gEtId());
	}


	public void cHeCk_CuRrEnT_LoAnS() {
		for (Loan lOaN : CuRrEnT_LoAnS.values()) 
			lOaN.cHeCk_OvEr_DuE();
				
	}


	public void RePaIr_BoOk(Book cUrReNt_BoOk) {
		if (DaMaGeD_BoOkS.containsKey(cUrReNt_BoOk.gEtId())) {
			cUrReNt_BoOk.RePaIr();
			DaMaGeD_BoOkS.remove(cUrReNt_BoOk.gEtId());
		}
		else 
			throw new RuntimeException("Library: repairBook: book is not damaged");
		
		
	}
	
	
}
