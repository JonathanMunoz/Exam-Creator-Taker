// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.Scanner; // for reading input from keyboard in public Answer getAnswerFromStudent()


public class MCSAQuestion extends MCQuestion{
  // MCSAQuestion constructor
  public MCSAQuestion( String txt, double maxVal ){
    super( txt, maxVal );
  }
  
  
  // this is just here because MCSAQuestion inherits Question so this method needs to exist
  public Answer getNewAnswer(){
    return new MCSAAnswer(text, maxValue);
  }
  
  
  // creates and returns a wrong MCSAAnswer with the given text
  public Answer getNewAnswer( String txt ){
    MCSAAnswer ans = new MCSAAnswer(txt, 0.0);
    return ans;
  }
  
  
  // creates and returns a MCSAAnswer with the given text and credit if chosen
  public Answer getNewAnswer( String txt, double creditIfChosen ){
    MCSAAnswer ans = new MCSAAnswer(txt, creditIfChosen);
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
    System.out.print("\n");
    
    studentAnswer = new MCSAAnswer( studentInput, 0.0 ); // how many points should the student's answer have if selected? Does this make sense to ask??
  }
  
  
  // returns how many points this question contributes to the total exam score
  // total points are based on the maximum value of the question and the correctness of the student's input
  public double getValue(){
    return studentAnswer.getCredit(rightAnswer) * maxValue;
  }
}