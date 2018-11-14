// Name: Jonathan Munoz
// ACCC account name: jmunoz


public abstract class MCAnswer extends Answer{
  protected String text;
  protected boolean isSelected;
  protected double creditIfSelected;
  
  // MCAnswer constructor. New MCAnswers are unselected by default
  protected MCAnswer( String txt, double creditIfChosen ){
    text = txt;
    creditIfSelected = creditIfChosen;
    isSelected = false;
  }
  
  
  // prints out this answer
  public void print(){
    System.out.println( text );
  }
  
  
  // sets this answer as either selected or unselected
  public void setSelected( boolean selection ){
    isSelected = selection;
  }
}