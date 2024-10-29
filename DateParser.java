//Program Name: Parsing Dates
// Program Developers: Brian Eaton, David Gomez
// Program Date: October 16th, 2024
// Program version: 3.1

package parsingdata;
import java.util.Scanner;

public class DateParser {
	
   public static int getMonthAsInt(String monthString) {
      int monthInt;
      
      // Java switch/case statement                                                                
      switch (monthString) {
         case "January": 
            monthInt = 1; 
            break;
         case "February": 
            monthInt = 2; 
            break;
         case "March": 
            monthInt = 3; 
            break;
         case "April": 
            monthInt = 4; 
            break;
         case "May": 
            monthInt = 5; 
            break;
         case "June": 
            monthInt = 6; 
            break;
         case "July": 
            monthInt = 7; 
            break;
         case "August": 
            monthInt = 8; 
            break;
         case "September": 
            monthInt = 9; 
            break;
         case "October": 
            monthInt = 10; 
            break;
         case "November": 
            monthInt = 11; 
            break;
         case "December": 
            monthInt = 12; 
            break;
         default: 
            monthInt = 0;
      }
      
      return monthInt;
   }
   
   //Removes the extra space from the string leaving only the data
   public static String dateCleaner(String date) {
	   String cleanDate;
	   
	   cleanDate = date;
	   cleanDate = cleanDate.replace(" ", "");
	   cleanDate = cleanDate.replace(",", "");
	   return cleanDate;
   }
   
   //Counts the number of digits the date String has
   public static int numDigits(String digits) {
	   String date;
	   int indexEnd;
	   
	   date = digits;
	   indexEnd = 0; //Initializes indexEnd
	   
	   for (int i = 0; i < date.length(); i++) {
		   if(Character.isDigit(date.charAt(i))) {
   				indexEnd++;
   			}
   		}
	   
	   return indexEnd;
   }
   
   //Outputs whether the day is two digits or not
   public static boolean twoDigits(int digits) {
	   int index;
	   boolean isTwoDigits;
	   
	   index = digits;
	   isTwoDigits = false;
	   
	   if(index == 5) {
		   isTwoDigits = false;
   	   }
   	   else if(index == 6) {
   		   isTwoDigits = true;
   	   }
	   
	   return isTwoDigits;
   }
   
   //If input String has a "," or a "/" then it will ask for a new String input
   public static boolean correctFormat(String date) {
	   boolean isCorrect;
	   
	   isCorrect = true;
	   
	   	if(date.contains(",") != true) {
	   		isCorrect = false;
	   	}
   		if(date.contains("/") != false) {
   			isCorrect = false;
   		}
	   
   		return isCorrect;
   }
   
   //Gives the month of the given date String
   public static String isolateMonth(String date, int index, int indexEnd) {
	   String inputDate;
	   String month;
	   
	   inputDate = date;
	   month = inputDate.substring(0, index - indexEnd);
	   
	   return month;
   }
   
   //If the month is formatted incorrectly i.e. X/XX/XX and it ends up at the month stage then it will output false
   public static boolean correctMonth(int intMonth) {
	   boolean isCorrectMonth;
	   
	   isCorrectMonth = true;
	   
	   if(intMonth == 0) {
		   isCorrectMonth = false;
	   }
	   
	   return isCorrectMonth;
	   
   }
   
   //Saves the day of the given date String
   public static String isolateDay(String date, int index2, boolean isTwoDigits) {
	   String day;
	   
	   day = "";
	   
	   if(isTwoDigits == false) {
	   		day = date.substring(index2, index2 + 1);
	   		//System.out.println(day + " is the day int"); // testing
	   }
	   else if(isTwoDigits == true) {
	   		day = date.substring(index2, index2 + 2);
	   		//System.out.println(day + " is the day int"); // testing
	   }
	   
	   return day;
	   
   }
   
   //Saves the year of the given date String
   public static String isolateYear(String date, int index, int index2, boolean isTwoDigits) {
	   String year;
	   
	   year = "";
	   
	   if(isTwoDigits == false) {
   			year = date.substring(index2 + 1, index);
   			//System.out.println(year + " is the year int"); // testing
   	   }
   	   else if(isTwoDigits == true) {
   		   year = date.substring(index2 + 2, index);
   		   //System.out.println(year + " is the year int"); // testing
   	   }
	   
	   return year;
	   
   }

   public static void main(String[] args) {
	Scanner scnr = new Scanner(System.in);
    String date;
    String month;
    String day;
    String year;
    int index = 0; //Keeps track of the whole String
    int index2 = 0; //Keeps track of the month
    int indexEnd = 0; //Keeps track of the numbers
    int intMonth = 0;
    boolean isTwoDigits = false;
    boolean isCorrectDate = true;
    boolean isCorrectMonth = true;

    //date = scnr.nextLine();
    
    while (true) {
    	date = scnr.nextLine();
    	
    	if(date.equals("-1")) {
    		break;
    	}
    	
    	//If input String has a "," or a "/" then it will ask for a new String input
    	isCorrectDate = correctFormat(date);
    	
    	if(isCorrectDate == false) {
    		continue;
    	}

    	//System.out.println(isCorrectDate + " is correctly formatted date"); // testing
    	
    	//Removes the extra space from the string leaving only the data
    	date = dateCleaner(date);
    	
    	//Counts the numbers in the input string to determine indexes and if the string has a 1-9 day or 10-30 day
    	indexEnd = numDigits(date);
    	
    	//If the Date has a two digit day it will set isTwoDigits to true else it is false
    	isTwoDigits = twoDigits(indexEnd);
    	index = date.length(); //Saves the String length after cleaning as an index
    	
    	//System.out.println(isTwoDigits + " has two digits"); // testing
    	//System.out.println(indexEnd + " is the end index"); // testing
    	//System.out.println(date + " is cleaned string"); // testing
    	
    	//Isolates the month in the string to convert it to the month number
    	month = isolateMonth(date, index, indexEnd);
    	intMonth = getMonthAsInt(month);
    	isCorrectMonth = correctMonth(intMonth);
    	if(isCorrectMonth == false) {
    		date = scnr.nextLine();
    		
    		//If input String has a "," or a "/" then it will ask for a new String input
        	isCorrectDate = correctFormat(date);
        	
        	if(isCorrectDate == false) {
        		continue;
        	}

    	}
    	
    	//System.out.println(month + " is the month"); // testing
    	//System.out.println(intMonth + " is the month as an int"); // testing
    	
    	index2 = month.length();
    	
    	//System.out.println(index2 + " start index after month"); // testing
    	
    	//If the Date has a two digit day then it will save the variable with either only day between 1-9 or 10-30
    	day = isolateDay(date, index2, isTwoDigits);; //Initialized day variable
    	
    	//If the Date has a two digit day then it will save the variable based on that
    	year = isolateYear(date, index, index2, isTwoDigits); //Initialized year variable
    	

    	//Prints the date correctly formated
    	System.out.println(intMonth + "-" + day + "-" + year);
    	
    	index = 0;
        index2 = 0;
        indexEnd = 0;
        intMonth = 0;
        isTwoDigits = false;
        isCorrectDate = true;
        isCorrectMonth = true;
    }
    
    scnr.close();
    
 }
}
