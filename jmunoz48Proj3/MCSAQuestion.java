// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.Scanner;    // for reading input from keyboard in public Answer getAnswerFromStudent()
import java.util.ArrayList;
import java.io.PrintWriter;  // for PrintWriter


public class MCSAQuestion extends MCQuestion{
  // MCSAQuestion constructor
  public MCSAQuestion( String txt, double maxVal ){
    super( txt, maxVal );
  }
  
  
   // MCSAQuestion constructor, but with scanner
  public MCSAQuestion( Scanner scan ){
    super( scan );
    answers = new ArrayList<MCAnswer>(5);
    int numAnswers = scan.nextInt();
    scan.nextLine();
    
    for( int i = 0; i < numAnswers; i++ ){
      answers.add( new MCSAAnswer( scan ) );
    }
  }
  
  
  // this is really just here because MCSAQuestion inherits Question so this method (with no parameters) needs to exist
  public Answer getNewAnswer(){
    return new MCSAAnswer( "", 0.0 );
  }
  
  
  // creates and returns a MCSAAnswer with the given text and credit if chosen
  public Answer getNewAnswer( String txt, double creditIfChosen ){
    MCSAAnswer ans = new MCSAAnswer( txt, creditIfChosen );
    return ans;
  }
  
  
  // prompts the student to enter an answer and then stores that answer
  public void getAnswerFromStudent(){
    print();
    Scanner scan = ScannerFactory.getKeyboardScanner();
    String studentInput = "NO INPUT";
    
    System.out.print("Please enter your answer: ");
    if( scan.hasNextLine() ){
      studentInput = scan.nextLine();  // read in the next line from the keyboard
    }
    System.out.print("\n");
    
    studentAnswer = new MCSAAnswer( studentInput, 0.0 ); // how many points should the student's answer have if selected? Does this make sense to ask??
  }
  
  
  // returns how many points this question contributes to the total exam score
  // total points are based on the maximum value of the question and the correctness of the student's input
  public double getValue(){
   // return super.getValue( (MCAnswer)studentAnswer ) * maxValue;\
    return 0.0; // :(
  }
  
  
  // saves this question to the output file
  public void save( PrintWriter printy ){
    printy.println( maxValue );
    printy.println( text );
    printy.println( answers.size() );
    
    for( Answer ans : answers ){
      ans.save( printy );
    }
  }
  
  
  // saves the student's answer to the output file
  public void saveStudentAnswer( PrintWriter printy ){    
    printy.println( ((MCSAAnswer)studentAnswer).text );
  }
  
  
  // reads in a student answer from a file
  public void restoreStudentAnswers( Scanner scan ){
    studentAnswer = new MCSAAnswer( scan.nextLine(), 0.0 );
  }
}