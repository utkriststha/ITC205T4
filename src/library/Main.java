package library;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import library.borrowbook.BorrowBookUI;
import library.borrowbook.bORROW_bOOK_cONTROL;
import library.entities.Book;
import library.entities.Calendar;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;
import library.fixbook.FixBookUI;
import library.fixbook.fIX_bOOK_cONTROL;
import library.payfine.PayFineUI;
import library.payfine.pAY_fINE_cONTROL;
import library.returnBook.ReturnBookUI;
import library.returnBook.rETURN_bOOK_cONTROL;


public class Main {
	
    private static Scanner input;				// Changed variable name 'IN' to 'input'.
    private static Library library;				// Changed variable name 'LIB' to 'library'.
    private static String menu;					// Changed variable name 'MENU' to 'menu'.
    private static Calendar calendar;				// Changed variable name 'CAL' to 'calendar'.
    private static SimpleDateFormat simpleDateFormat; 		// Changed variable name 'SDF' to 'simpleDateFormat'.
	
    private static String getMenu() {				// Changed method name 'Get_menu' to 'getMenu'.
	StringBuilder stringbuilder = new StringBuilder();	// Changed method name 'sb' to 'stringbuilder'.
		
	stringbuilder.append("\nLibrary Main Menu\n\n")		// Changed method name 'sb' to 'stringbuilder'.
	    .append("  M  : add member\n")
            .append("  LM : list members\n")
	    .append("\n")
       	    .append("  B  : add book\n")
	    .append("  LB : list books\n")
	    .append("  FB : fix books\n")
	    .append("\n")
	    .append("  L  : take out a loan\n")
	    .append("  R  : return a loan\n")
	    .append("  LL : list loans\n")
	    .append("\n")
	    .append("  P  : pay fine\n")
	    .append("\n")
	    .append("  T  : increment date\n")
	    .append("  Q  : quit\n")
	    .append("\n")
	    .append("Choice : ");
		  
	return stringbuilder.toString();			// Changed method name 'sb' to 'stringbuilder'.
    }


    public static void main(String[] args) {		
	try {			
	    input = new Scanner(System.in);				// Changed variable name 'IN' to 'input'.					
	    library = Library.getInstance();				// Changed variable name 'LIB' to 'library' and 'GeTiNsTaNcE' to 'getInstance'.	
	    calendar = Calendar.getInstance();				// Changed variable name 'CAL' to 'calendar' and 'gEtInStAnCe' to 'getInstance'.
	    simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");	// Changed variable name 'SDF' to 'simpleDateFormat'.		
	
	    for (Member menu : library.listMembers()) {			// Changed variable name 'm' to 'menu' and 'LIB.lIsT_MeMbErS' to 'library.listMembers'.
		output(menu);						// Changed variable name 'm' to 'menu'.
	    }
		
	    output(" ");
		
	    for (Book book : library.listBooks()) {			// Changed variable name 'b' to 'book' and 'LIB.lIsT_BoOkS' to 'library.listBooks'.
		output(book);						// Changed variable name 'b' to 'book'.
	    }
						
	    menu = getMenu();						// Changed variable name 'MENU' to 'menu' and 'Get_menu' to 'getMenu'.
	    boolean exit = false;					// Changed variable name 'e' to 'exit'.
			
	    while (!exit) {						// Changed variable name 'e' to 'exit'.
				
	        output("\n" + simpleDateFormat.format(calendar.getDate()));	// Changed variable name 'SDF' to 'simpleDateFormat', 'CAL' to 'calendar' and 'gEt_DaTe' to 'getDate'.
		String choice = input(menu);					// Changed variable name 'c' to 'choice' and 'MENU' to 'menu'.
				
	        switch (choice.toUpperCase()) {					// Changed variable name 'c' to 'choice'.
				
		case "M": 
		    addMember();						// Changed method name 'ADD_MEMBER' to 'addMember'
		    break;
					
		case "LM": 
		    listMembers();						// Changed method name 'LIST_MEMBERS' to 'listMembers'
		    break;
					
		case "B": 
		    addBook();							// Changed method name 'ADD_BOOK' to 'addBook'
		    break;
					
		case "LB": 
		    listBooks();						// Changed method name 'LIST_BOOKS' to 'listBooks'
		    break;
					
		case "FB": 
		    fixBooks();							// Changed method name 'FIX_BOOKS' to 'fixBooks'
		    break;
					
		case "L": 
		    borrowBook();						// Changed method name 'BORROW_BOOK' to 'borrowBook'
		    break;
					
		case "R": 
		    returnBook();						// Changed method name 'RETURN_BOOK' to 'returnBook'
		    break;
					
		case "LL": 	
		    listCurrentLoans();						// Changed method name 'LIST_CURRENT_LOANS' to 'listCurrentLoans'
		    break;		
					
		case "P": 
		    payFines();							// Changed method name 'PAY_FINES' to 'payFines'
		    break;
					
		case "T": 
		    incrementDate();						// Changed method name 'INCREMENT_DATE' to 'incrementDate'
		    break;
					
		case "Q": 
		    exit = true;						// Changed variable name 'e' to 'exit'.
		    break;
					
		default: 
		    output("\nInvalid option\n");
		    break;
		}
				
	        library.save();
            }									// Changed variable name 'Library' to 'library' and 'SaVe' to 'save'.
	}  catch (RuntimeException exception) {					// Changed variable name 'e' to 'exception'.
	       output(exception);						// Changed variable name 'e' to 'exception'.
	     }		
	        output("\nEnded\n");
	}	

	
    private static void payFines() {						// Changed method name 'PAY_FINES' to 'payFines'.
	new PayFineUI(new PayFineComtrol()).run();				// Changed class name 'pAY_fINE_cONTROL' to 'PayFineComtrol' and 'RuN' to 'run'.
    }


    private static void listCurrentLoans() {					// Changed method name 'LIST_CURRENT_LOANS' to 'listCurrentLoans'.
	output("");
	for (Loan loan : library.listCurrentLoans()) {				// Changed method name 'LIB' to 'library' and 'LIST_CURRENT_LOANS' to 'listCurrentLoans'.
	    output(loan + "\n");
	}		
    }


    private static void listBooks() {						// Changed method name 'lIsT_BoOkS' to 'listBooks'.
	output("");
	for (Book book : library.listBooks()) {					// Changed method name 'LIB' to 'library' and 'lIsT_BoOkS' to 'listBooks'.
	    output(book + "\n");
	}		
    }



    private static void LIST_MEMBERS() {
	output("");
	for (Member member : LIB.lIsT_MeMbErS()) {
	    output(member + "\n");
	}		
    }



    private static void BORROW_BOOK() {
	new BorrowBookUI(new bORROW_bOOK_cONTROL()).RuN();		
    }


    private static void RETURN_BOOK() {
	new ReturnBookUI(new rETURN_bOOK_cONTROL()).RuN();		
    }


    private static void FIX_BOOKS() {
	new FixBookUI(new fIX_bOOK_cONTROL()).RuN();		
    }


    private static void INCREMENT_DATE() {
	try {
	    int days = Integer.valueOf(input("Enter number of days: ")).intValue();
	    CAL.incrementDate(days);
	    LIB.cHeCk_CuRrEnT_LoAnS();
	    output(SDF.format(CAL.gEt_DaTe()));
			
	} catch (NumberFormatException e) {
	     output("\nInvalid number of days\n");
	}
       }


    private static void ADD_BOOK() {
		
        String AuThOr = input("Enter author: ");
        String TiTlE  = input("Enter title: ");
        String CaLl_NuMbEr = input("Enter call number: ");
        Book BoOk = LIB.aDd_BoOk(AuThOr, TiTlE, CaLl_NuMbEr);
        output("\n" + BoOk + "\n");
		
    }

	
    private static void ADD_MEMBER() {
	try {
	    String LaSt_NaMe = input("Enter last name: ");
	    String FiRsT_NaMe  = input("Enter first name: ");
	    String EmAiL_AdDrEsS = input("Enter email address: ");
	    int PhOnE_NuMbEr = Integer.valueOf(input("Enter phone number: ")).intValue();
	    Member MeMbEr = LIB.aDd_MeMbEr(LaSt_NaMe, FiRsT_NaMe, EmAiL_AdDrEsS, PhOnE_NuMbEr);
	    output("\n" + MeMbEr + "\n");
			
	} catch (NumberFormatException e) {
	    output("\nInvalid phone number\n");
	}
		
    }


    private static String input(String prompt) {
	System.out.print(prompt);
	return IN.nextLine();
    }
	
	
	
    private static void output(Object object) {
	System.out.println(object);
    }

	
}
