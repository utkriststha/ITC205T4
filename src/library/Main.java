package library;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import library.borrowbook.BorrowBookUI;
import library.borrowbook.BorrowBookControl;		// Changed class name 'bORROW_bOOK_cONTROL' to 'BorrowBookControl'.
import library.entities.Book;
import library.entities.Calendar;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;
import library.fixbook.FixBookUI;
import library.fixbook.FixBookControl;			// Changed class name 'fIX_bOOK_cONTROL' to 'FixBookControl'.
import library.payfine.PayFineUI;
import library.payfine.PayFineControl;			// Changed class name 'pAY_fINE_cONTROL' to 'PayFineControl'.
import library.returnBook.ReturnBookUI;
import library.returnBook.ReturnBookControl;		// Changed class name 'rETURN_bOOK_cONTROL' to 'ReturnBookControl'.


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



    private static void listMembers() {						// Changed method name 'LIST_MEMBERS' to 'listMembers'.
	output("");
	for (Member member : library.listMembers()) {				// Changed method name 'LIB' to 'library' and 'LIST_MEMBERS' to 'listMembers'.
	    output(member + "\n");
	}		
    }



    private static void borrowBook() {						// Changed method name 'BORROW_BOOK' to 'borrowBook'.
	new BorrowBookUI(new BorrowBookControl()).run();			// Changed method name 'bORROW_bOOK_cONTROL' to 'BorrowBookControl' and 'RuN' to 'run'.
    }


    private static void returnBook() {						// Changed method name 'RETURN_BOOK' to 'returnBook'.
	new ReturnBookUI(new ReturnBookControl()).run();			// Changed method name 'rETURN_bOOK_cONTROL' to 'ReturnBookControl' and 'RuN' to 'run'.
    }


    private static void fixBooks() {						// Changed method name 'FIX_BOOKS' to 'fixBooks'.
	new FixBookUI(new FixBookControl()).run();				// Changed method name 'fIX_bOOK_cONTROL' to 'FixBookControl' and 'RuN' to 'run'.
    }


    private static void incrementDate() {						// Changed method name 'INCREMENT_DATE' to 'incrementDate'.
	try {
	    int days = Integer.valueOf(input("Enter number of days: ")).intValue();
	    calendar.incrementDate(days);						// Changed method name 'CAL' to 'calendar'.
	    library.checkCurrentLoans();						// Changed method name 'LIB' to 'library' and 'cHeCk_CuRrEnT_LoAnS' to 'checkCurrentLoans'.
	    output(simpleDateFormat.format(calendar.getDate()));			// Changed method name 'SDF' to 'simpleDateFormat' and 'CAL.gEt_DaTe' to 'calendar.getDate'.
			
	} catch (NumberFormatException exception) {					// Changed variable name 'e' to 'exception'.
	     output("\nInvalid number of days\n");
	}
       }


    private static void addBook() {					// Changed method name 'ADD_BOOK' to 'addBook'.
		
        String author = input("Enter author: ");			// Changed variable name 'AuThOr' to 'author'.
        String title  = input("Enter title: ");				// Changed variable name 'TiTlE' to 'title'.
        String callNumber = input("Enter call number: ");		// Changed variable name 'CaLl_NuMbEr' to 'callNumber'.
        Book book = library.addBook(author, title, callNumber);		/** Changed variable name 'BoOk' to 'book', 'LIB.aDd_BoOk' to 'library.addBook', 
									    'AuThOr' to 'author', 'TiTlE' to 'title', and 'CaLl_NuMbEr' to 'callNumber'.**/
       													
       output("\n" + book + "\n");					// Changed variable name 'BoOk' to 'book'.
		
    }

	
    private static void addMember() {					// Changed method name 'ADD_MEMBER' to 'addMember'.
	try {
	    String lastName = input("Enter last name: ");		// Changed variable name 'LaSt_NaMe' to 'lastName'.
	    String firstName  = input("Enter first name: ");		// Changed variable name 'FiRsT_NaMe' to 'firstName'.
	    String emailAddress = input("Enter email address: ");	// Changed variable name 'EmAiL_AdDrEsS' to 'emailAddress'.
	    int phoneNumber = Integer.valueOf(input("Enter phone number: ")).intValue();		// Changed variable name 'PhOnE_NuMbEr' to 'phoneNumber'.
	    Member member = library.addMember(lastName, firstName, emailAddress, phoneNumber);		/** Changed variable name 'MeMbEr' to 'member', 'LIB.aDd_MeMbEr' to 'library.addMember', 
									    				'LaSt_NaMe' to 'lastName', 'FiRsT_NaMe' to 'firstName', 'EmAiL_AdDrEsS' to 'emailAddress'
													and 'PhOnE_NuMbEr' to 'phoneNumber'.**/
		
	    output("\n" + member + "\n");		// Changed variable name 'MeMbEr' to 'member'.
			
	} catch (NumberFormatException exception) {	// Changed variable name 'e' to 'exception'.
	    output("\nInvalid phone number\n");
	}
		
    }


    private static String input(String prompt) {
	System.out.print(prompt);
	return input.nextLine();				// Changed variable name 'IN' to 'input'.
    }
	
	
	
    private static void output(Object object) {
	System.out.println(object);
    }

	
}
