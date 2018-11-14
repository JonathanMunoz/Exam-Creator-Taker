// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.Scanner;    // for reading input from keyboard in getAnswerFromStudent()
import java.io.PrintWriter;  // for PrintWriter


public class SAQuestion extends Question{
  // SAQuestion constructor
  public SAQuestion( String txt, double maxVal ){
    super( txt, maxVal );
    rightAnswer = null;
    studentAnswer = null;
  }
  
  
  // SAQuestion constructor, but with scanner
  public SAQuestion( Scanner scan ){
    super( scan );
    rightAnswer = new SAAnswer( scan.nextLine() );
  }
  
  
  // creates and returns an instance of a SAAnswer
  public Answer getNewAnswer(){
    SAAnswer ans = new SAAnswer("");
    return ans;
  }
  
  
  // creates and returns an instance of a SAAnswer with the inputted text
  public Answer getNewAnswer( String txt ){
    SAAnswer ans = new SAAnswer( txt );
    return ans;
  }
  
  
  // prompts the student to enter an answer and then stores that answer
  public void getAnswerFromStudent(){
    print();
    Scanner scan = ScannerFactory.getKeyboardScanner();
    String studentInput = "NO INPUT";
    
    System.out.print( "Please enter your answer: " );
    if( scan.hasNextLine() ){
      studentInput = scan.nextLine();   // read in the next line from the keyboard
    }
    
    studentAnswer = new SAAnswer( studentInput );
  }
  
  
  // returns how many points this question contributes to the total exam score
  // total points are based on the maximum value of the question and the correctness of the student's input
  public double getValue(){
    if( studentAnswer != null )
      return studentAnswer.getCredit(rightAnswer) * maxValue;
    return 0.0;
  }
  
  
  // saves this question to the output file
  public void save( PrintWriter printy ){
    printy.println( maxValue );
    printy.println(text);
    printy.println( ((SAAnswer)rightAnswer).text );
  }
  
  
  // saves the student's answers to the output file
  public void saveStudentAnswer( PrintWriter printy ){
    printy.println( ((SAAnswer)studentAnswer).text );
  }
  
  
  // reads in a student answer from a file
  public void restoreStudentAnswers( Scanner scan ){
    studentAnswer = new SAAnswer( scan.nextLine() );
  }
}