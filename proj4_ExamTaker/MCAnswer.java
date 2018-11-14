// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.Scanner;    // for Scanner
import java.io.PrintWriter;  // for PrintWriter


public abstract class MCAnswer extends Answer{
  protected String text;
  protected double creditIfSelected;
  
  
  // MCAnswer constructor
  protected MCAnswer( String txt, double creditIfChosen ){
    text = txt;
    creditIfSelected = creditIfChosen;
  }
  
  
   // MCAnswer constructor, but with scanner
  public MCAnswer( Scanner scan ){
    creditIfSelected = scan.nextDouble();
    text = scan.nextLine();
  }
  
  
  // prints out this answer
  public void print(){
    System.out.println( text );
  }
  
  
  // returns the credit earned for this question
  public double getCredit( Answer rightAnswer ){
    if( text.equalsIgnoreCase( ((MCAnswer)rightAnswer).text) ){
      return 1.0;
    }
    return 0.0;
  }
  
  
  // prints this answer to the output file
  public void save( PrintWriter printy ){
    printy.print( creditIfSelected );
    printy.println( " " + text );
  }
}