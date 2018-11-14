// Name: Jonathan Munoz
// ACCC account name: jmunoz


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;


public class ExamTester{
  public static void main( String[] args ) throws FileNotFoundException{
    System.out.println( "Name: Jonathan Munoz\nACCC account name: jmunoz\nnetID: jmunoz48" );
    System.out.println( "--------------------------------------------------------" );
    
    /*String currentDirectory = System.getProperty( "user.dir" );
     currentDirectory = currentDirectory.replace( "\\", "\\\\" );*/
    
    // create an exam from an input data file
    File sampleExam = new File( "sampleExam.txt" );
    Scanner scan = new Scanner( sampleExam );
    Exam testTest = new Exam( scan );
    
    // reorder exam
    testTest.reorderMCAnswers(-1);
    testTest.reorderQuestions(); 
    
    // save reordered exam into a different file
    File reorderedExam = new File( "reorderedExam.txt" );
    PrintWriter printOutput = new PrintWriter( reorderedExam );
    testTest.save( printOutput );
    
    // get student answers for all questions
    testTest.getAnswerFromStudent(-1);
    
    // store student answers in an answer file
    File sampleAnswers = new File( "sampleAnswers.txt" );
    PrintWriter printAnswers = new PrintWriter( sampleAnswers );
    printAnswers.println("Student Info I guess");
    printAnswers.println("reorderedExam.txt");
    testTest.saveStudentAnswers( printAnswers );
    
    // clear existing exam and student answers in memory
    testTest = null;
    scan.close();
    printOutput.close();
    printAnswers.close();
    
    // load the reordered exam and student answers from the files
    scan = new Scanner( reorderedExam );
    Scanner inputtedAnswers = new Scanner( sampleAnswers );
    testTest = new Exam( scan );
    testTest.restoreStudentAnswers( inputtedAnswers );
    
    // grade the exam and report the results
    testTest.reportQuestionValues();
    
    scan.close();   
  }
}
