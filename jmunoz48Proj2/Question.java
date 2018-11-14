// Name: Jonathan Munoz
// ACCC account name: jmunoz


public abstract class Question{
  protected String text;          // the question itself
  protected Answer rightAnswer;   // the right answer for this question
  protected Answer studentAnswer; // the student's currently selected answer
  protected double maxValue;      // the most points you can get out of this question  
  
  
  // Question constructor
  protected Question( String s, double maxVal ){
    text = s;
    maxValue = maxVal;
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
}
