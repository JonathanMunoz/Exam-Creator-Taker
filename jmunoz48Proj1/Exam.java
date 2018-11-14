// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.ArrayList;  // for arraylists
import java.util.*;          // for random reordering


public class Exam{
  private String title;  // title of this Exam
  private ArrayList<Question> questionList;  // arraylist which stores every Question in this Exam

  
  // Exam constructor. New Exams do not have Questions until they're added
  // the input parameter is the title of the Exam, which is printed before the Questions
  public Exam( String s ){
    title = s;
    questionList = new ArrayList<Question>(5);
  }
  
  
  // add a Question to an Exam. Questions are added to the end of the list by default
  public void AddQuestion( Question q ){
    questionList.add( q );
  }
  
  
  // prints the Exam to the screen along with all of its Questions and Answers
  public void print( ){
    System.out.println( title + "\n" );
    
    for( int i = 0; i < questionList.size(); i++ ){
      System.out.print( (i+1) + ".) " );
      questionList.get(i).print(1);
    }
  }
  
  
  // returns a reference to the Question in the given position within the Exam
  // input should be 1-indexed, NOT 0-indexed ( first Answer is 1, second Answer is 2, etc. )
  public Question getQuestion( int position ){
    return questionList.get( position-1 );
  }
  
  
  // randomly reorders the Questions within the Exam
  public void reorderQuestions( ){
    Collections.shuffle( questionList );
  }
  
  
  // gets the overall score of the Exam, regardless of how many Questions have been answered ( if any )
  public double getValue( ){
    double total = 0;
    
    for( int i = 0; i < questionList.size(); i++ ){
      total += questionList.get(i).getValue();
    }
    return total;
  }
}
