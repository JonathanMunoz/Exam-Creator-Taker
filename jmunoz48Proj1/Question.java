// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.util.*;  // for Random()


public class Question{
  private String q;   // the question itself
  private Answer[] answerList;  // the list of Answers for this Question
  private int index;  // the index at which the next Answer should be placed within the answerList array
  private int numAnswers = 5;   // how many Answers each Question should have
  
  
  // Question constructor. New Questions do not have Answers until they are added
  public Question( String s ){
    q = s;
    answerList = new Answer[numAnswers];
    index = 0;
  }
  
  
  // add an Answer to a Question. Answers are added to the end of the existing list by default
  public void AddAnswer( Answer a ){
    if( index < answerList.length ){
      answerList[index] = a;
      index++;
    }
    else
      System.out.println( "Error: Cannot add more Answers to " + q + "." );
  }
  
  
  // prints the Question to the screen along with all of its Answers
  public void print( int position ){
    char letter = 65;  // ASCII for 'A'
    System.out.println( q );
    
    for( int i = 0; i < answerList.length; i++ ){
      System.out.print( "\t" + (char)(letter + i) + ": " );  // I hope there aren't more than 26 choices for any given question
      answerList[i].print(1); 
    }
  }
  
  
  // selects the Answer in the given position and unselects all other Answers
  // input should be 1-indexed, NOT 0-indexed ( first Answer is 1, second Answer is 2, etc. )
  public void selectAnswer( int position ){
    for( int i = 0; i < answerList.length; i++ ){
      if( i == position-1 )
        answerList[i].setSelected( true );
      else 
        answerList[i].setSelected( false );
    }
  }
  
  
  // unselects the Answer in the given position
  // input should be 1-indexed, NOT 0-indexed ( first Answer is 1, second Answer is 2, etc. )
  public void unselectAnswer( int position ){
    answerList[position-1].setSelected( false );
  }
  
  
  // randomly reorders the Answers in the given Question
  public void reorderAnswers( ){
    Random random = new Random();
    int randomIndex;
    Answer temp;

    for( int i = answerList.length-1; i > 0; i-- ){
        randomIndex = random.nextInt(i + 1); // gets a random int between 0 and i

        // switch the ith element in answerList with the randomly chosen element
        temp = answerList[randomIndex];
        answerList[randomIndex] = answerList[i];
        answerList[i] = temp;
    }
  }
  
  
  // get the number of points that this Question contributes to the total Exam score based on the currently selected Answers
  public double getValue( ){
    double total = 0;
    
    for( int i = 0; i < answerList.length; i++ ){
      total += answerList[i].getValue(); 
    }
    return total;
  }
}
