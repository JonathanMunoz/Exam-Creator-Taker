// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.Scanner; // for reading input from keyboard


public class ScannerFactory{
  private static Scanner keyboardScanner = null;
  
  
  // creates a new instance of Scanner if there isn't one already
  // if there is one already, then just return that existing instance instead
  public static Scanner getKeyboardScanner(){
    if( keyboardScanner == null )
      keyboardScanner = new Scanner( System.in );
    
    return keyboardScanner;
  }
  
  
  // closes Scanner
  public void finalize(){
    keyboardScanner.close();
  }
}