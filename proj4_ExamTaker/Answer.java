// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.Scanner;    // for Scanner
import java.io.PrintWriter;  // for PrintWriter


public abstract class Answer{
  // Answer constructor
  protected Answer(){}
  
  
   // Answer constructor, but with scanner
  public Answer( Scanner scan ){}
  
  
  // prints out this answer
  public abstract void print();
  
  
  // returns the credit earned for this question
  public abstract double getCredit( Answer correctAnswer );
  
  
  // saves this answer to an output file
  public abstract void save( PrintWriter printy );
}