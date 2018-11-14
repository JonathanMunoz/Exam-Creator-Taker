// Name: Jonathan Munoz
// ACCC account name: jmunoz


public class ExamTester{
  public static void main( String[] args ){
    System.out.println( "Name: Jonathan Munoz\nACCC account name: jmunoz\nnetID: jmunoz48" );
    System.out.println( "--------------------------------------------------------" );
    
    Exam test = new Exam( "Test test" );

    Question q1 = new Question( "Test Question #1" );
    Answer a1 = new Answer( "Test Answer A1" );
    a1.setValue( 1, -2 );
    Answer b1 = new Answer( "Test Answer B1" );
    Answer c1 = new Answer( "Test Answer C1" );
    Answer d1 = new Answer( "Test Answer D1" );
    Answer e1 = new Answer( "Test Answer E1" );
    q1.AddAnswer(a1);
    q1.AddAnswer(b1);
    q1.AddAnswer(c1);
    q1.AddAnswer(d1);
    q1.AddAnswer(e1);
    q1.selectAnswer(1);
    test.AddQuestion(q1);
    
    Question q2 = new Question( "Test Question #2" );
    Answer a2 = new Answer( "Test Answer A2" );
    Answer b2 = new Answer( "Test Answer B2" );
    Answer c2 = new Answer( "Test Answer C2" );
    c2.setValue( 2, -11000 );
    Answer d2 = new Answer( "Test Answer D2" );
    Answer e2 = new Answer( "Test Answer E2" );
    q2.AddAnswer(a2);
    q2.AddAnswer(b2);
    q2.AddAnswer(c2);
    q2.AddAnswer(d2);
    q2.AddAnswer(e2);
    q2.selectAnswer(3);
    test.AddQuestion(q2);

    Question q3 = new Question( "Test Question #3" );
    Answer a3 = new Answer( "Test Answer A3" );
    Answer b3 = new Answer( "Test Answer B3" );
    Answer c3 = new Answer( "Test Answer C3" );
    Answer d3 = new Answer( "Test Answer D3" );
    d3.setValue( 2000, -1.75 );
    Answer e3 = new Answer( "Test Answer E3" );
    q3.AddAnswer(a3);
    q3.AddAnswer(b3);
    q3.AddAnswer(c3);
    q3.AddAnswer(d3);
    q3.AddAnswer(e3);
    q3.selectAnswer(4);
    test.AddQuestion(q3);
    
    test.print();
    
    System.out.println( "--------------------------------------------------------" );
    System.out.println( "After shuffling the questions and answers: \n" );
    test.reorderQuestions();
    test.getQuestion(1).reorderAnswers();
    test.getQuestion(2).reorderAnswers();
    test.getQuestion(3).reorderAnswers();
    test.print();
    
    System.out.println( "Total Score: " + test.getValue() + "." );
  }
}
