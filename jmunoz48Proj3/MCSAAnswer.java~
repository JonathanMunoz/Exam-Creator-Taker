// Name: Jonathan Munoz
// ACCC account name: jmunoz


public class MCSAAnswer extends MCAnswer{
  // MCSAAnswer constructor. The MCAnswer constructor is called here instead of rewriting all the code again
  public MCSAAnswer( String txt, double creditIfChosen ){
    super(txt, creditIfChosen);
  }
  
  
  // returns the credit earned for this question (1 point if correct, 0 otherwise)
  public double getCredit( Answer correctAnswer ){
    // this comparison is case sensitive and the strings must also match EXACTLY in order to get credit
    if(correctAnswer instanceof MCSAAnswer){
      if( text.toLowerCase().equals((((MCSAAnswer)correctAnswer).text).toLowerCase()) ){
        return 1.0;
      }
    }
    else
      System.out.println("ERROR in MCSAAnswer public double getCredit(Answer correctAnswer) : Comparing a MCSAAnswer to an Answer of a different type.");
    
    return 0.0;
  }
}