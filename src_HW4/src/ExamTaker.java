import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;


public class ExamTaker{
  public static void main( String[] args ) throws FileNotFoundException{
    
    // get student information
    Scanner scanKeyboardInput = ScannerFactory.getKeyboardScanner();
    String studentName = "NO INPUT";
    
    System.out.print( "Please enter your name: " );
    if( scanKeyboardInput.hasNextLine() )
      studentName = scanKeyboardInput.nextLine(); 
    
    
    // load an arbitrary exam file and create an exam from it
    String filename = "";
    File sampleExamFile = new File( filename );
    
    while( !sampleExamFile.exists() ){
      System.out.println( "Enter the filename of the exam you want to take (Ex: \"sampleExam.txt\"): " );
      if( scanKeyboardInput.hasNextLine() )
        filename = scanKeyboardInput.nextLine(); 
      sampleExamFile = new File( filename );
    }
    Scanner scanExam = new Scanner( sampleExamFile );
    Exam testTest = new Exam( scanExam );
    
    
    // get answers from the student
    testTest.getAnswerFromStudent( -1 );
    testTest.answerSkippedQuestions();
    
    
    // ask if student wants to change any of their answers
    char regradingInput = '\0';  // used to determine whether or not the student wants to change their answers
    String rawInput;                   // holds entire line that will be read in from scanner that contains which questions the student wants to retry
    String[] parsedNumbers;  // stores the numbers (as Strings) from rawInput using spaces as a delimiter
    int[] retryQuestions;            // same thing as the parsedNumbers array, except this array stores the numbers as ints
    
    do{
      System.out.println( "Do you want to change any of your answers? Input \"y\" or \"n\"." );
      
      if( scanKeyboardInput.hasNextLine() ){
        rawInput = scanKeyboardInput.nextLine();
        if( rawInput.length() > 0 )     // only try reading the input if the student actually inputted something
          regradingInput = rawInput.charAt(0);   // then read in only the first char
        else
          regradingInput = '\0';
      }
      
      // if the student wants to change their answers, ask them which ones they want to retry and then make them reanswer those questions
      if( regradingInput == 'y' || regradingInput == 'Y' ){
        rawInput = "";
        System.out.println( "Input which questions you want to retry (Ex: \"1 2 3\"): " );
        if( scanKeyboardInput.hasNextLine() )
          rawInput = scanKeyboardInput.nextLine();
        
        if( rawInput.length() > 0 ){
          parsedNumbers = rawInput.split( " " ); // use a space as a delimiter to parse the inputted string
          retryQuestions = new int[ parsedNumbers.length ]; // then create an appropriately sized array
          
          for( int i = 0; i < parsedNumbers.length; i++ )
            retryQuestions[ i ] = Integer.parseInt( parsedNumbers[ i ] ); // convert the inputted strings to ints
          
          for( int i : retryQuestions )
            testTest.getAnswerFromStudent( i - 1 ); // make the student reanswer the questions that they asked to retry
          
          testTest.answerSkippedQuestions();
        }
      }
    } while( regradingInput == 'y' || regradingInput == 'Y' );
    
    
    // save student answers to a file
    File sampleAnswersFile = new File( "studentAnswers.txt" );
    PrintWriter sampleAnswers = new PrintWriter( sampleAnswersFile );
    sampleAnswers.println( studentName );
    sampleAnswers.println( filename );
    testTest.saveStudentAnswers( sampleAnswers );
    
    sampleAnswers.close(); // without this, none of the student answers will be saved to the file
    scanKeyboardInput.close();
    scanExam.close();
    System.exit(0);
  }
}