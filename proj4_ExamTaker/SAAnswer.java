// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.Scanner;    // for Scanner
import java.io.PrintWriter;  // for PrintWriter


public class SAAnswer extends Answer{
  protected String text;
  
  
  // SAAnswer constructor
  public SAAnswer( String txt ){
    text = txt;
  }
  
  
   // SAAnswer constructor, but with scanner
  public SAAnswer( Scanner scan ){
    text = scan.nextLine();
  }
  
  
  // prints out this answer
  public void print(){
    System.out.println( text );
  }
  
  
  // returns the credit earned for this answer (1 point if correct, 0 otherwise)
  public double getCredit( Answer correctAnswer ){
    // this string comparison is case insensitive, but the strings must match exactly otherwise
    if( correctAnswer instanceof SAAnswer ){
      if( text.equalsIgnoreCase(((SAAnswer)correctAnswer).text) )
        return 1.0;
    }
    else
      System.out.println("ERROR in SAAnswer public double getCredit(Answer correctAnswer) : Comparing a SAAnswer to an Answer of a different type.");
    
    return 0.0;
  }
  
  
  // saves this question to the given output file
  public void save( PrintWriter printy ){
    printy.print( text );
  }
}