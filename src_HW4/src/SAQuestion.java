//package com.mikehume;

import java.io.PrintWriter;
import java.util.Scanner;

public class SAQuestion extends Question{
    protected String userEntry;

    public SAQuestion(String text, double maxValue) {
        super(text, maxValue);
        userEntry = null;
    }
    public SAQuestion(Scanner questionFile) {
        super(questionFile);
        Answer ans = new SAAnswer(questionFile);
        setRightAnswer(ans);
        userEntry = null;
    }

    public Answer getNewAnswer(){
        Answer newAnswer = new SAAnswer("No text provided");
        return newAnswer;
    }

    public Answer getNewAnswer(String text){
        Answer newAnswer = new SAAnswer(text);
        return newAnswer;
    }

    public void getAnswerFromStudent(){
        print();
        //ScannerFactory.getKeyboardScanner().nextLine();
        this.userEntry = ScannerFactory.getKeyboardScanner().nextLine();
        //System.out.println("");
        super.studentAnswer = getNewAnswer(this.userEntry);
    }

    public double getValue() {
        double value = 0;

        // Converts the student answer and right answer to all lowercase
        ((SAAnswer) studentAnswer).text = ((SAAnswer) studentAnswer).text.toLowerCase();
        ((SAAnswer) rightAnswer).text = ((SAAnswer) rightAnswer).text.toLowerCase();

        // Compares the answer entered by the student with the right answer, returns maxValue if they are equal.
        if ((((SAAnswer) studentAnswer).text.equals(((SAAnswer) rightAnswer).text))) {
            return this.maxValue;
        }
        else {
            return 0.0;
        }
    }

    public void save (PrintWriter pw){
        pw.print("SAQuestion\n");
        pw.print(super.maxValue + "\n");
        pw.print(this.text + "\n");
        ((SAAnswer) this.rightAnswer).save(pw);
        pw.print("\n");
    }

    public void restoreStudentAnswers (Scanner answerFile){
        Answer tempAnswer = new SAAnswer(answerFile);
        super.studentAnswer = tempAnswer;
    }

    public String getQuestionType(){ return "SAQuestion\n"; }
}
