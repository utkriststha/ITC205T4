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
	    boolean e = false;
			
	    while (!e) {
				
	        output("\n" + simpleDateFormat.format(calendar.getDate()));	// Changed variable name 'SDF' to 'simpleDateFormat', 'CAL' to 'calendar' and 'gEt_DaTe' to 'getDate'.
		String c = input(MENU);
				
	        switch (c.toUpperCase()) {
				
		case "M": 
		    ADD_MEMBER();
		    break;
					
		case "LM": 
		    LIST_MEMBERS();
		    break;
					
		case "B": 
		    ADD_BOOK();
		    break;
					
		case "LB": 
		    LIST_BOOKS();
		    break;
					
		case "FB": 
		    FIX_BOOKS();
		    break;
					
		case "L": 
		    BORROW_BOOK();
		    break;
					
		case "R": 
		    RETURN_BOOK();
		    break;
					
		case "LL": 
		    LIST_CURRENT_LOANS();
		    break;
					
		case "P": 
		    PAY_FINES();
		    break;
					
		case "T": 
		    INCREMENT_DATE();
		    break;
					
		case "Q": 
		    e = true;
		    break;
					
		default: 
		    output("\nInvalid option\n");
		    break;
		}
				
	        Library.SaVe();
            }			
	}  catch (RuntimeException e) {
	       output(e);
	     }		
	        output("\nEnded\n");
	}	

	
    private static void PAY_FINES() {
	new PayFineUI(new pAY_fINE_cONTROL()).RuN();		
    }


    private static void LIST_CURRENT_LOANS() {
	output("");
	for (Loan loan : LIB.lISt_CuRrEnT_LoAnS()) {
	    output(loan + "\n");
	}		
    }


    private static void LIST_BOOKS() {
	output("");
	for (Book book : LIB.lIsT_BoOkS()) {
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
