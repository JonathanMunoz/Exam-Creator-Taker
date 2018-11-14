// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.Scanner; // for reading input from keyboard


public class MCMAAnswer extends MCAnswer{
  // MCMAAnswer constructor
  public MCMAAnswer( String txt, double creditIfChosen ){
        super( txt, creditIfChosen );
  }
  
  
   // MCMAAnswer constructor, but with scanner
  public MCMAAnswer( Scanner scan ){
    super( scan );
  }
}