// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.ArrayList;  // for arraylists
import java.util.*;          // for random reordering in public void reorderAnswers()


public abstract class MCQuestion extends Question{
    protected ArrayList<MCAnswer> answers;  // the list of MC answers for this question
    
    // MCQuestion constructor
    public MCQuestion( String txt, double maxVal ){
      super( txt, maxVal );
      rightAnswer = null;
      studentAnswer = null;
      answers = new ArrayList<MCAnswer>(5);
    }
    
    // prints the Question to the screen along with all of its Answers
    public void print( ){
      char letter = 65;  // ASCII for 'A'
      System.out.println( text );
      
      for( int i = 0; i < answers.size(); i++ ){
        System.out.print( "\t" + (char)(letter + i) + ": " );  // I hope there aren't more than 26 choices for any given question
        answers.get(i).print(); 
      }
    }
    
    
    // adds the given answer to this question's answer arraylist
    public void addAnswer( MCAnswer ans ){
      answers.add(ans);
    }
    
    
    // randomly reorders the answers in this question
    public void reorderAnswers(){
      Collections.shuffle( answers );
    }    
}