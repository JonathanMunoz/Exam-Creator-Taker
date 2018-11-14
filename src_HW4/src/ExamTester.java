import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExamTester {
    public static void main(String[] args) {

    System.out.println("Enter the file name.");
    Scanner sc = ScannerFactory.getKeyboardScanner();
    File file = new File(sc.nextLine());
    Exam ex1 = null;
    try {
        // read in from file
        sc = new Scanner(file);
        ex1= new Exam(sc);


    }
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }


    // print reorder print
    ex1.print();
    ex1.reorderQuestions();
    ex1.reorderMCAnswers(-1);
    ex1.print();

    // save output
    System.out.println("Enter file name to save.");

    PrintWriter pw = null;

    try {
        Scanner fo = new Scanner(System.in);
        String fileOut = fo.nextLine();
        pw = new PrintWriter(fileOut);
    }
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    ex1.save(pw);
    pw.close();

    //read in student answers
    System.out.println("Enter answers: (For Multiple Answer Questions enter '#' to go on to next question)");
    ex1.getAnswerFromStudent(1);
    ex1.getAnswerFromStudent(2);
    ex1.getAnswerFromStudent(3);
    ex1.getAnswerFromStudent(4);

    //save student answers
    System.out.println("Enter the file name to save student answers");
    PrintWriter spw = null;

    try {
      // Scanner sfo = new Scanner(System.in);
       Scanner sfo = ScannerFactory.getKeyboardScanner();
       String sfileOut = sfo.nextLine();
       spw = new PrintWriter(sfileOut);
    }
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }


    ex1.saveStudentAnswers(spw);
    spw.close();

    // clear exam
    ex1 = null;

    System.out.println("Enter Exam file name to recover");
    File file2 = new File(ScannerFactory.getKeyboardScanner().nextLine());
    try {
        // read in from file
        Scanner sc2 = new Scanner(file2);
        ex1= new Exam(sc2);
        sc2.close();
    }
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    System.out.println("Enter file name to recover answers");
    File file3 = new File(ScannerFactory.getKeyboardScanner().nextLine());
    try {
        // read in from file
        Scanner sc3 = new Scanner(file3);
        ex1.restoreStudentAnswers(sc3);
        sc3.close();
    }
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }


   // ex1.print();
    ex1.getValue();
    ScannerFactory.getKeyboardScanner().close();

    }
}
