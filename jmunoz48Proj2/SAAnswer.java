// Name: Jonathan Munoz
// ACCC account name: jmunoz


public class SAAnswer extends Answer{
  protected String text;
  
  // SAAnswer constructor
  public SAAnswer( String txt ){
    text = txt;
  }
  
  
  // prints out this answer
  public void print(){
    System.out.println( text );
  }
  
  
  // returns the credit earned for this answer (1 point if correct, 0 otherwise)
  public double getCredit( Answer correctAnswer ){
    // this string comparison is case insensitive, but the strings must match exactly otherwise
    if( correctAnswer instanceof SAAnswer ){
      if( text.toLowerCase().equals((((SAAnswer)correctAnswer).text).toLowerCase()) )
        return 1.0;
    }
    else
      System.out.println("ERROR in SAAnswer public double getCredit(Answer correctAnswer) : Comparing a SAAnswer to an Answer of a different type.");
    
    return 0.0;
  }
}