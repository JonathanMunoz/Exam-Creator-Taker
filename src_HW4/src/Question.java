//package com.mikehume;

import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Question {
    protected String text;
    protected Answer rightAnswer;
    protected Answer studentAnswer;
    protected double maxValue;
    protected String label;

    protected Question(String text, double maxValue){
        this.text = text;
        this.maxValue = maxValue;
        this.rightAnswer = null;
    }

    public Question(Scanner questionFile){
        this.maxValue = questionFile.nextDouble();//Double.parseDouble(questionFile.nextLine());
        questionFile.nextLine();
        this.text = questionFile.nextLine();
        this.rightAnswer = null;
    }

    public void print (){ System.out.println(text); }
    public void setRightAnswer(Answer rightAnswer){
        this.rightAnswer = rightAnswer;
    }
    public abstract Answer getNewAnswer();
    public abstract void getAnswerFromStudent();
    public abstract double getValue ();
    public abstract void save(PrintWriter pw);

    public void saveStudentAnswers (PrintWriter pw){ // changed
       // studentAnswer.save(apw);
        if(this instanceof  SAQuestion){
            pw.println(((SAAnswer)studentAnswer).text.trim());
        }
        else if(this instanceof MCSAQuestion){
            pw.println("MCSAQuestion");
            pw.println(((MCSAAnswer)studentAnswer).text.trim());
        }
        else if(this instanceof NumQuestion ){
            pw.println("NumQuestion");
            pw.println(((NumAnswer)studentAnswer).number);
        }
        pw.println();
    }

    public void restoreStudentAnswers (Scanner sc){ // changed parameter name and inserted my code

        if(this instanceof MCSAQuestion){
            String line = sc.nextLine();
            studentAnswer = new MCSAAnswer(text,1);
            ((MCSAQuestion) this).restoreStudentAnswer(sc, line);

        }
        else if (this instanceof SAQuestion){
            String line = sc.nextLine();
            studentAnswer = new SAAnswer(text);

        }
        else if (this instanceof NumQuestion){
            Double val = sc.nextDouble();
            sc.nextLine();
            studentAnswer = new NumAnswer(val);
        }

        else {
            System.out.println("Something is wrong");
        }

    }

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    protected static int charToInt(char charInput){
        int intOutput=0;
        char input;
        input = Character.toUpperCase(charInput);
        switch (input) {
            case 'A':  intOutput = 0;
                break;
            case 'B':  intOutput = 1;
                break;
            case 'C':  intOutput = 2;
                break;
            case 'D':  intOutput = 3;
                break;
            case 'E':  intOutput = 4;
                break;
            case 'F':  intOutput = 5;
                break;
            case 'G':  intOutput = 6;
                break;
            case 'H':  intOutput = 7;
                break;
            case 'I':  intOutput = 8;
                break;
            case 'J':  intOutput = 9;
                break;
            default:   intOutput = -1;
        }
        return intOutput;
    }

    // This converts the integer label used by the class methods for each answer to return a char which the user sees.
    protected static char intTochar(int answerNumber){
        char answerLetter;
        switch (answerNumber) {
            case 0:  answerLetter = 'a';
                break;
            case 1:  answerLetter = 'b';
                break;
            case 2:  answerLetter = 'c';
                break;
            case 3:  answerLetter = 'd';
                break;
            case 4:  answerLetter = 'e';
                break;
            default: answerLetter = 'x';
                break;
        }
        return answerLetter;
    }
     // getQuestion type
}
