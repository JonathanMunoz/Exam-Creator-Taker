// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.*;                 // for Collections.shuffle() in public void reorderAnswers() and also for Scanner and ArrayLists
import java.io.PrintWriter;  // for PrintWriter


public abstract class MCQuestion extends Question{
    protected ArrayList<MCAnswer> answers;  // the list of MC answers for this question
    
    // MCQuestion constructor
    public MCQuestion( String txt, double maxVal ){
      super( txt, maxVal );
      answers = new ArrayList<MCAnswer>(5);
    }
    
    
     // MCQuestion constructor, but with scanner
    public MCQuestion( Scanner scan ){
      super( scan );
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
    
    
    // checks all of this Question's answers and if any of them get credit, then return credit * maxValue
    public double getValue( MCAnswer answer ){
      double total = 0.0;
      
      for( MCAnswer ans : answers ){
        total += answer.getCredit( ans );
      }
      
      return total * maxValue;
    }
    
    
    // saves this question to the output file
    public void save( PrintWriter printy ){}
    
    
    // saves the student's answers to the output file
    public void saveStudentAnswer( PrintWriter printy ){    
      super.saveStudentAnswer( printy );
    }
}