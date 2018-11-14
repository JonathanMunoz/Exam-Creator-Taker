// Name: Jonathan Munoz
// ACCC account name: jmunoz


public class Answer{
  private String answer;       // the answer itself
  private boolean isSelected;  // whether or not this Answer is selected
  private double selectedValue;    // how much this Answer is worth if selected
  private double unselectedValue;  // how much this Answer is worth if not selected
  
  
  // Answer constructor. By default, new Answers are unselected and have no value
  public Answer( String s ){
    answer = s;
    isSelected = false;
    selectedValue = 0;
    unselectedValue = 0;
  }
  
  
  // prints the Answer's answer to the screen 
  public void print( int position ){
    System.out.println( answer );
  }
  
  
  // Answers are selected when true
  // Answers become unselected ( false ) if the student changes their mind or selects another Answer when only one Answer is allowed
  public void setSelected( boolean bool ){
    isSelected = bool;
  }
  
  
  // sets the value(s) of this Answer if it is selected or unselected, respectively
  // the default for each is zero if unset. Set values can be positive or negative
  public void setValue( double selected, double unselected ){
    selectedValue = selected;
    unselectedValue = unselected;
  }
  
  
  // get the number of points that this Answer contributes to the Exam score, based on whether it is selected and its value when it is selected
  public double getValue( ){
    if( isSelected )
      return selectedValue;
    else
      return unselectedValue;
  }
}
