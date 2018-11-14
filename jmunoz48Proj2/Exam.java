// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.ArrayList;  // for arraylists
import java.util.*;          // for random reordering


public class Exam{
  private ArrayList<Question> questionList;  // arraylist which stores every question in this exam
  private String text;  // title of this exam

  
  // Exam constructor. New exams do not have questions until they're added
  // the input parameter is the title of the exam, which is printed before the questions
  public Exam( String s ){
    text = s;
    questionList = new ArrayList<Question>(5);
  }
  
  
  // prints the exam to the screen along with all of its questions and answers
  public void print( ){
    System.out.println( text + "\n" );
    
    for( int i = 0; i < questionList.size(); i++ ){
      System.out.print( (i+1) + ".) " );
      questionList.get(i).print();
    }
  }
  
  
   // add a question to an exam. Questions are added to the end of the list by default
  public void addQuestion( Question q ){
    questionList.add( q );
  }
  
  
  // randomly reorders the Qqestions within the exam
  public void reorderQuestions( ){
    Collections.shuffle( questionList );
  }
  
  
  // randomly reorders the answers for the specified multiple choice question
  // if the position parameter is negative, then all multiple choice questions get their answers reordered
  public void reorderMCAnswers( int position ){
    // reorder the answers for all MCQuestions if position is negative
    if( position < 0 ){
      for( int i = 0; i < questionList.size(); i++ ){
        if( questionList.get(i) instanceof MCQuestion )
          ((MCQuestion)questionList.get(i)).reorderAnswers();
      }
    }
    
    // otherwise, only reorder the specified question
    else{
      if( position < questionList.size() ){
        if( questionList.get(position) instanceof MCQuestion )
          ((MCQuestion)questionList.get(position)).reorderAnswers(); // only reorder answers if the position is valid and there is a MCQuestion at that position
        else
          System.out.println("ERROR in Exam public void reorderMCAnswers(int position) : Trying to reorder something other than a MCQuestion.");
      }
      else
        System.out.println("ERROR in Exam public void reorderMCAnswers(int position) : Called with an invalid parameter.");
    }
  }
  
  
  // prompts student to input an answer for the specified question
  // if int position is negative, then the student has to provide an answer for every question
  public void getAnswerFromStudent( int position ){
    // if position is negative, get an answer for every question
    if( position < 0 ){
      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      for( int i = 0; i < questionList.size(); i++ ){
        System.out.print( (i+1) + ".) " ); // for formatting the output
        
        // check if the current question is either a MCSAQuestion or a SAQuestion so it can be typecasted appropriately
        if( questionList.get(i) instanceof MCSAQuestion ){
          ((MCSAQuestion)questionList.get(i)).getAnswerFromStudent();
        }
        else if( questionList.get(i) instanceof SAQuestion ){
          ((SAQuestion)questionList.get(i)).getAnswerFromStudent();
        }
        else
          System.out.println("ERROR in Exam public void getAnswerFromStudent(int position) : Question is not an instance of either an MCSAQuestion or a SAQuestion.");
      }
    }
    
    // if position is positive, get an answer for that specific question
    else if( position < questionList.size() ){
      if( questionList.get(position) instanceof MCSAQuestion ){
          ((MCSAQuestion)questionList.get(position)).getAnswerFromStudent();
        }
        if( questionList.get(position) instanceof SAQuestion ){
          ((SAQuestion)questionList.get(position)).getAnswerFromStudent();
        }
    }
    
    else
      System.out.println("ERROR in Exam public void getAnswerFromStudent(int position) : Invalid input.");
  }
  
  
  // gets the overall score of the exam, regardless of how many Questions have been answered ( if any )
  public double getValue( ){
    double total = 0;
    
    for( Question i : questionList ){
      total += i.getValue();
    }
    return total;
  }
  
  
  // produces a table of the values for each question on the exam along with their total points
  public void reportQuestionValues(){
    System.out.println( "Total question values for " + text + ":" );
    
    for( int i = 0; i < questionList.size(); i++ ){
      System.out.print( (i+1) + ".) " );
      System.out.println( questionList.get(i).getValue() + "" );
    }
  }
}
