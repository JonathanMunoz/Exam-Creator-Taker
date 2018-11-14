// Name: Jonathan Munoz
// ACCC account name: jmunoz


public class ExamTester{
  public static void main( String[] args ){
    System.out.println( "Name: Jonathan Munoz\nACCC account name: jmunoz\nnetID: jmunoz48" );
    System.out.println( "--------------------------------------------------------" );
    
    Exam sampleExam = new Exam( "CS 342 Midterm 2" );
    
    MCSAQuestion mscaQuestion1 = new MCSAQuestion( "How many hours do you study in a day?", 1.0 );
    MCAnswer rightAnswer1 = (MCAnswer)(mscaQuestion1.getNewAnswer( "9", 1.0 ));
    mscaQuestion1.addAnswer( rightAnswer1 );
    mscaQuestion1.addAnswer( (MCAnswer)(mscaQuestion1.getNewAnswer( "pi", 0.0 )) );
    mscaQuestion1.addAnswer( (MCAnswer)(mscaQuestion1.getNewAnswer( "e^ln(2)", 0.0 )) );
    mscaQuestion1.addAnswer( (MCAnswer)(mscaQuestion1.getNewAnswer( "2", 0.0 )) );
    mscaQuestion1.addAnswer( (MCAnswer)(mscaQuestion1.getNewAnswer( "4", 0.0 )) );
    mscaQuestion1.setRightAnswer( rightAnswer1 );
    sampleExam.addQuestion( (Question)mscaQuestion1 );
    
    MCSAQuestion mscaQuestion2 = new MCSAQuestion( "What is the radius of your bar of soap in meters in your bathroom?", 2.0 );
    mscaQuestion2.addAnswer( (MCAnswer)(mscaQuestion1.getNewAnswer( "3m", 0.0 )) );
    mscaQuestion2.addAnswer( (MCAnswer)(mscaQuestion1.getNewAnswer( "2m", 0.0 )) );
    mscaQuestion2.addAnswer( (MCAnswer)(mscaQuestion1.getNewAnswer( "10m", 0.0 )) );
    MCAnswer rightAnswer2 = (MCAnswer)(mscaQuestion1.getNewAnswer( "15ft", 2.0 ));
    mscaQuestion2.addAnswer( rightAnswer2 );
    mscaQuestion2.addAnswer( (MCAnswer)(mscaQuestion1.getNewAnswer( "red", 0.0 )) );
    mscaQuestion2.setRightAnswer( rightAnswer2 );
    sampleExam.addQuestion( mscaQuestion2 );
    
    SAQuestion saQuestion1 = new SAQuestion( "How much work is required to pump 4 feet of water out of a cylindrical tank with a spout exceeding 3m?", 3.0 );
    saQuestion1.setRightAnswer( saQuestion1.getNewAnswer("Soccer") );
    sampleExam.addQuestion( saQuestion1 );
    
    SAQuestion saQuestion2 = new SAQuestion( "Who is my dad?", 4.0 );
    saQuestion2.setRightAnswer( saQuestion2.getNewAnswer("Troy.Patrick{}") );
    sampleExam.addQuestion( saQuestion2 );
    
    sampleExam.print();
    sampleExam.reorderQuestions();
    sampleExam.reorderMCAnswers( -1 );
    System.out.println("--------------------------------------------------------");
    sampleExam.print();
    
    sampleExam.getAnswerFromStudent( -1 );
    System.out.println( "Your total Score: " + sampleExam.getValue() );
    sampleExam.reportQuestionValues();
  }
}
