// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.Scanner; // for reading input from keyboard


public class MCSAAnswer extends MCAnswer{
  // MCSAAnswer constructor. The MCAnswer constructor is called here instead of rewriting all the code again
  public MCSAAnswer( String txt, double creditIfChosen ){
    super( txt, creditIfChosen );
  }
  
  
   // MCSAAnswer constructor, but with scanner
  public MCSAAnswer( Scanner scan ){
    super( scan );
  }
}