//package com.mikehume;

import java.io.PrintWriter;
import java.util.Scanner;

public class NumQuestion extends Question{
    protected double userEntry;
    private double answerTolerance;

    public NumQuestion(String text, double maxValue, double answerTolerance) {
        super(text, maxValue);
        this.answerTolerance = answerTolerance;
    }

    public NumQuestion(Scanner questionFile) {
        super(questionFile);
        Answer ans = new NumAnswer(questionFile);
        setRightAnswer(ans);
        answerTolerance = Double.parseDouble(questionFile.nextLine());
    }

    public Answer getNewAnswer(){
        Answer newAnswer = new NumAnswer();
        return newAnswer;
    }

    public Answer getNewAnswer(double number){
        Answer newAnswer = new NumAnswer(number);
        return newAnswer;
    }

    public void getAnswerFromStudent(){
        print();
        boolean bError = true;
        while (bError) {
            if (ScannerFactory.getKeyboardScanner().hasNextDouble()) {
                this.userEntry = ScannerFactory.getKeyboardScanner().nextDouble();
                super.studentAnswer = getNewAnswer(this.userEntry);
                ScannerFactory.getKeyboardScanner().nextLine();
                bError = false;
            }
            else {
                System.out.println("\nError! Please enter a Double value.");
                System.out.print("Enter answer:");
                ScannerFactory.getKeyboardScanner().next();
                continue;
            }
        }
    }

    public double getValue(){
        double studentA = ((NumAnswer)studentAnswer).number;
        double rightA = ((NumAnswer)rightAnswer).number;
        double highAnswer = rightA + answerTolerance;
        double lowAnswer = rightA - answerTolerance;

        // Compares the answer entered by the student with the right answer, returns maxValue if it is within tolerance range.
        if((studentA >= lowAnswer) && (studentA <= highAnswer)){
            return this.maxValue;
        }
        else{
            return 0.0;
        }
    }

    public void save (PrintWriter pw){
        pw.print("NumQuestion\n");
        pw.print(super.maxValue + "\n");
        pw.print(this.text + "\n");
        ((NumAnswer)this.rightAnswer).save(pw);
        pw.print(answerTolerance + "\n");
        pw.print("\n");
    }

    public void restoreStudentAnswers (Scanner answerFile){
        Answer tempAnswer = new NumAnswer(answerFile);
        super.studentAnswer = tempAnswer;
    }

    public String getQuestionType(){ return "NumQuestion\n"; }
}