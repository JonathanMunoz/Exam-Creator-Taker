// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.Scanner; // for reading input from keyboard
import java.io.PrintWriter;  // for PrintWriter


public abstract class Question{
  protected String text;                          // the question itself
  protected Answer rightAnswer;       // the right answer for this question
  protected Answer studentAnswer; // the student's currently selected answer
  protected double maxValue;           // the most points you can get out of this question  
  
  
  // Question constructor
  protected Question( String s, double maxVal ){
    text = s;
    rightAnswer = null;
    studentAnswer = null;
    maxValue = maxVal;
  }
  
  
  // Question constructor, but with scanner
  public Question( Scanner scan ){
    maxValue = scan.nextDouble();
    scan.nextLine();
    text = scan.nextLine();
    studentAnswer = null;
  }
  
  
  // prints out the text for this question and nothing else
  public void print(){
    System.out.println( text );
  }
  
  
  // sets the given answer as the correct answer for this question
  public void setRightAnswer( Answer ans ){
    rightAnswer = ans;
  }
  
  
  // creates and returns a new answer
  public abstract Answer getNewAnswer();
  
  
  // prompts the student for an answer and then stores their input
  public abstract void getAnswerFromStudent();
  
  
  // returns how many points this question contributes to the total exam score
  public abstract double getValue();
  
  
  // saves this question to the given file
  public abstract void save( PrintWriter printy );
  
  
  // saves the student's inputted answer to the given file
  public void saveStudentAnswer( PrintWriter printy ){
    printy.println( studentAnswer );
  }
  
  
  // restores the student's inputted answer to what it was before
  public void restoreStudentAnswers( Scanner scan ){}
}
