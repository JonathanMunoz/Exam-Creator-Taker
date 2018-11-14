// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.Scanner; // for reading input from keyboard in getAnswerFromStudent()

public class SAQuestion extends Question{
  // SAQuestion constructor
  public SAQuestion( String txt, double maxVal ){
    super( txt, maxVal );
    rightAnswer = null;
    studentAnswer = null;
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
    Scanner scan = new Scanner(System.in);
    String studentInput = "NO INPUT";
    
    System.out.print("Please enter your answer: ");
    if( scan.hasNextLine() ){
      studentInput = scan.nextLine(); // read in the next line from the keyboard
    }
    
    studentAnswer = new SAAnswer( studentInput );
  }
  
  
  // returns how many points this question contributes to the total exam score
  // total points are based on the maximum value of the question and the correctness of the student's input
  public double getValue(){
    return studentAnswer.getCredit(rightAnswer) * maxValue;
  }
}