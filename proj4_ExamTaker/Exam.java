// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.ArrayList;    // for arraylists
import java.util.*;                  // for random reordering and using Scanner
import java.time.*;
import java.io.PrintWriter;   // for PrintWriter


public class Exam{
  private ArrayList<Question> questionList;  // arraylist which stores every question in this exam
  private String text;  // title of this exam
  private ArrayList<Integer> skippedQuestionsList; // arraylist which stores the index of every question in the questionList that had been unanswered

  
  // Exam constructor. New exams do not have questions until they're added
  // the input parameter is the title of the exam, which is printed before the questions
  public Exam( String s ){
    text = s;
    questionList = new ArrayList<Question>(5);
  }
  
  
  // Exam constructor, but with scanner
  public Exam( Scanner scan ){
    text = scan.nextLine();
    scan.nextLine(); // this is for skipping the time last saved info
    questionList = new ArrayList<Question>(5);
    skippedQuestionsList = new ArrayList<Integer>(5);
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
  
  
  // randomly reorders the questions within the exam
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
  // if input is negative, then the student has to provide an answer for every question
  // the student is allowed to skip and come back to a question if they input the appropriate keyword
  public void getAnswerFromStudent( int position ){
    // if position is negative, get an answer for every question
    if( position < 0 ){
      System.out.println("~~~~~~~~~~~ Type \"skip\" to skip SA questions ~~~~~~~~~~~");
      System.out.println("~~~~~~~~~~~~ Type \"S\" to skip MC questions ~~~~~~~~~~~~");
      System.out.println("~~~~~~~~~~~~ Type \"\" to skip Num questions ~~~~~~~~~~~~"); // <---------------------------------------------------------------------------what should the skip keyword be for num questions?
      for( int i = 0; i < questionList.size(); i++ ){
        System.out.print( (i+1) + ".) " ); // for formatting the output
        
        questionList.get( i ).getAnswerFromStudent();
      }
    }
    
    // if position is positive, get an answer for that specific question
    else if( position < questionList.size() ){
      System.out.print( (position+1) + ".) " ); // for formatting the output
      questionList.get(position).getAnswerFromStudent();
    }
    
    else
      System.out.println("ERROR in Exam public void getAnswerFromStudent(int position) : Invalid input.");
    
    checkSkippedQuestions();
  }
  
  
  // goes through every question in the exam and adds the index of the questions that have been skipped to the skippedQuestionsList arraylist
  // if a student answers a skipped question, this method will remove it from the skippedQuestionsList
  // if a student skips a skipped question, this method will push it to the end of the skippedQuestionsList
  public void checkSkippedQuestions(){
    Question q;
    Answer ans;
    String studentInput;
    
    for( int i =0; i < questionList.size(); i++ ){
      q = questionList.get( i );
      
      if( q instanceof SAQuestion ){
        ans = ((SAQuestion)q).studentAnswer;
        studentInput = ((SAAnswer)ans).text;
        
        // add index of question to the skippedQuestionsList if the student inputted "skip" and this question isn't already in the skippedQuestionsList
        // if this question is already in the skippedQuestionsList, then remove it and add it again, so that it gets pushed to the end of the list
        // make sure that you change the student's answer to "" or else you will be reordering this question every time this method is called, even if the student didn't input "skip" again
        if( studentInput.equalsIgnoreCase("skip") ){
          if( skippedQuestionsList.contains( i ) ){
            System.out.println( "before removal of SAQuestion: " + skippedQuestionsList );
            skippedQuestionsList.remove(  Integer.valueOf(i) ); 
            System.out.println( "after removal of SAQuestion: " + skippedQuestionsList );
          }
          System.out.println( "before adding SAQuestion: " + skippedQuestionsList );
          skippedQuestionsList.add( i );
          System.out.println( "after adding SAQuestion: " + skippedQuestionsList );
          ((SAAnswer)ans).text = "";
        }
        
        // remove index of question from the skippedQuestionsList if the student didn't input "skip" and this question is in the skippedQuestionList
        if( !studentInput.equalsIgnoreCase("skip") && !studentInput.equalsIgnoreCase("") && skippedQuestionsList.contains( i ) )
          skippedQuestionsList.remove(  Integer.valueOf(i) ); 
      }
      
      if( q instanceof MCSAQuestion ){
        ans = ((MCSAQuestion)q).studentAnswer;
        studentInput = ((MCSAAnswer)ans).text;
        
        if( studentInput.equalsIgnoreCase("S") ){
          if( skippedQuestionsList.contains( i ) ){
            System.out.println( "before removal of MCSAQuestion: " + skippedQuestionsList );
            skippedQuestionsList.remove(  Integer.valueOf(i) ); 
            System.out.println( "after removal of MCSAQuestion: " + skippedQuestionsList );
          }
          System.out.println( "before adding MCSAQuestion: " + skippedQuestionsList );
          skippedQuestionsList.add( i );
          System.out.println( "after adding MCSAQuestion: " + skippedQuestionsList );
          ((MCSAAnswer)ans).text = ""; // make sure that the "S" gets removed and replaced with "" until the student answers the question for real
        }
        
        if( !studentInput.equalsIgnoreCase("S") && !studentInput.equalsIgnoreCase("") && skippedQuestionsList.contains( i ) )
          skippedQuestionsList.remove( Integer.valueOf(i) );
      }
      
      if( q instanceof MCMAQuestion ){
        if( !((MCMAQuestion)q).studentAnswerList.isEmpty() ){ // don't even bother with any of this if the student answer arraylist is empty
          ans = ((MCMAQuestion)q).studentAnswerList.get( 0 ); // NOTE: this only checks if a student inputted "S" as their FIRST answer
          studentInput = ((MCMAAnswer)ans).text;
          
          if( studentInput.equalsIgnoreCase("S")  ){
            if( skippedQuestionsList.contains( i ) ){
              System.out.println( "before removal of MCMAQuestion: " + skippedQuestionsList );
              skippedQuestionsList.remove(  Integer.valueOf(i) );
              System.out.println( "after removal of MCMAQuestion: " + skippedQuestionsList );
            }
            System.out.println( "before adding MCMAQuestion: " + skippedQuestionsList );
            skippedQuestionsList.add( i );
            System.out.println( "after adding MCMAQuestion: " + skippedQuestionsList );
            ((MCMAQuestion)q).studentAnswerList.clear(); // make sure that the question remains answerless until the student actually answers it
          }
          
          if( !studentInput.equalsIgnoreCase("S") && skippedQuestionsList.contains( i ) )
            skippedQuestionsList.remove(  Integer.valueOf(i) );
        }
      }
      
      /*if( q instanceof NumQuestion ){
        ans = ((NumQuestion)q).studentAnswer;
        studentInput = ((NumAnswer)ans).text; <---------------------------------------------------------------------------how is the student answer for num questions stored? probably not as a string...
        
        if( studentInput.equalsIgnoreCase("") ){ <--------------------------------------------------------------------------what should a student input to skip a num question?
          if( skippedQuestionsList.contains( i ) ){
            System.out.println( "before removal of NumQuestion: " + skippedQuestionsList );
            skippedQuestionsList.remove(  Integer.valueOf(i) ); 
            System.out.println( "after removal of NumQuestion: " + skippedQuestionsList );
          }
          System.out.println( "before adding NumQuestion: " + skippedQuestionsList );
          skippedQuestionsList.add( i );
          System.out.println( "after adding NumQuestion: " + skippedQuestionsList );
          ((NumAnswer)ans).text = ""; <----------------------------------------------------------------------------------------reset the num answer to something other than a valid input or the skip keyword
          }*/
    }
  }
  
  
  // forces student to answer the questions that they skipped
  public void answerSkippedQuestions(){
    while( !skippedQuestionsList.isEmpty() ){
      System.out.println( "Forcing you to answer these questions in this order:  " + skippedQuestionsList );
      getAnswerFromStudent( skippedQuestionsList.get( 0 ) );
    }
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
    printy.println( text ); // save the exam title
    
    // save the current date
    printy.println(LocalDate.now());
    
    // save every question along with its answers
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
    // save the date
    printy.println(LocalDate.now());
    printy.println();
    
    // save every answer given for every question
    for( Question q : questionList ){
      if( q instanceof MCSAQuestion ){
        printy.println( "MCSAQuestion" );
        q.saveStudentAnswers( printy );
        printy.println();
      }
      if( q instanceof MCMAQuestion ){
        printy.println( "MCMAQuestion" );
        q.saveStudentAnswers( printy );
        printy.println();
      }
      if( q instanceof SAQuestion ){
        printy.println( "SAQuestion" );
        q.saveStudentAnswers( printy );
        printy.println();
      }
    }
  }
  
  
  // reads in student answers from a file
  public void restoreStudentAnswers( Scanner scan ){
    scan.nextLine(); // skip the student's name
    scan.nextLine(); // skip the exam file name
    scan.nextLine(); // skip the date
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
