// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.ArrayList;    // for arraylists
import java.util.*;                  // for random reordering and using Scanner
import java.io.PrintWriter;   // for PrintWriter


public class Exam{
  private ArrayList<Question> questionList;  // arraylist which stores every question in this exam
  private String text;  // title of this exam

  
  // Exam constructor. New exams do not have questions until they're added
  // the input parameter is the title of the exam, which is printed before the questions
  public Exam( String s ){
    text = s;
    questionList = new ArrayList<Question>(5);
  }
  
  
  // Exam constructor, but with scanner
  public Exam( Scanner scan ){
    text = scan.nextLine();
    questionList = new ArrayList<Question>(5);
    String questionType;
    
    while( scan.hasNextLine() ){
      scan.nextLine();
      questionType = scan.nextLine();
      
      if( questionType.equalsIgnoreCase( "MCSAQuestion" ) ){
        questionList.add( new MCSAQuestion(scan) );
      }
      else if( questionType.equalsIgnoreCase( "MCMAQuestion" ) ){
        questionList.add( new MCMAQuestion(scan) );
      }
      else if( questionType.equalsIgnoreCase( "SAQuestion" ) ){ 
        questionList.add( new SAQuestion( scan ) );
      }
      else
        System.out.println( "ERROR: Trying to add undefined question type to exam." );
    }
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
        
        questionList.get(i).getAnswerFromStudent();
      }
    }
    
    // if position is positive, get an answer for that specific question
    else if( position < questionList.size() ){
      questionList.get(position).getAnswerFromStudent();
    }
    
    else
      System.out.println("ERROR in Exam public void getAnswerFromStudent(int position) : Invalid input.");
  }
  
  
  // gets the overall score of the exam, regardless of how many Questions have been answered ( if any )
  public double getValue( ){
    double total = 0.0;
    
    for( Question i : questionList ){
      total += i.getValue();
    }
    
    return total;
  }
  
  
  // produces a table of the values for each question on the exam along with their total points
  public void reportQuestionValues(){
    System.out.println( "\nTotal question values for " + text + ":" );
    
    for( int i = 0; i < questionList.size(); i++ ){
      System.out.print( (i+1) + ".) " );
      System.out.println( questionList.get(i).getValue() + "" );
    }
  }
  
  
  // saves this test to an output file
  public void save( PrintWriter printy ){
    printy.println( text );
    
    for( Question q : questionList ){
      if( q instanceof MCSAQuestion ){
        printy.println();
        printy.println( "MCSAQuestion" );
        q.save( printy );
      }
      if( q instanceof MCMAQuestion ){
        printy.println();
        printy.println( "MCMAQuestion" );
        q.save( printy );
      }
      if( q instanceof SAQuestion ){
        printy.println();
        printy.println( "SAQuestion" );
        q.save( printy );
      }
    }
  }
  
  
  // saves the inputted student answers to an output file
  public void saveStudentAnswers( PrintWriter printy ){
    for( Question q : questionList ){
      if( q instanceof MCSAQuestion ){
        printy.println( "MCSAQuestion" );
        q.saveStudentAnswer( printy );
        printy.println();
      }
      if( q instanceof MCMAQuestion ){
        printy.println( "MCMAQuestion" );
        q.saveStudentAnswer( printy );
        printy.println();
      }
      if( q instanceof SAQuestion ){
        printy.println( "SAQuestion" );
        q.saveStudentAnswer( printy );
        printy.println();
      }
    }
  }
  
  
  // reads in student answers from a file
  public void restoreStudentAnswers( Scanner scan ){
    String questionType;
    int i = 0;
    int totalMCMAAnswers = 0;
    
    while( scan.hasNextLine() && i < questionList.size() ){
      questionType = scan.nextLine();
      
      if( questionType.equalsIgnoreCase( "MCSAQuestion" ) ){
        questionList.get(i).restoreStudentAnswers( scan );
      }
      else if( questionType.equalsIgnoreCase( "MCMAQuestion" ) ){
        totalMCMAAnswers = scan.nextInt();
        for( int x = 0; x < totalMCMAAnswers; x++ ){
          questionList.get(i).restoreStudentAnswers( scan );
        }
      }
      else if( questionType.equalsIgnoreCase( "SAQuestion" ) ){ 
        questionList.get(i).restoreStudentAnswers( scan );
      }
      
      i++;
      scan.nextLine();
    }
  }
}
