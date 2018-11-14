// Name: Jonathan Munoz
// ACCC account name: jmunoz


public abstract class Answer{
  // Answer constructor
  protected Answer(){}
  
  
  // prints out this answer
  public abstract void print();
  
  
  // returns the credit earned for this question (1 point if correct, 0 otherwise)
  public abstract double getCredit( Answer correctAnswer );
}
