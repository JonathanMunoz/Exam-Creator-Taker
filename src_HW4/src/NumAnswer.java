//package com.mikehume;
import java.io.PrintWriter;
import java.util.Scanner;

public class NumAnswer extends Answer {
    protected double number;
    public NumAnswer() { }

    public NumAnswer(double number) {
       this.number = number;
    }

    public NumAnswer(Scanner questionFile) {
        this.number = Double.parseDouble(questionFile.nextLine());
    }

    public void print(){ }
    public double getCredit(Answer rightAnswer){
        if (this.number == ((NumAnswer)rightAnswer).number) {
            return 1.0;
        }
        else{
            return 0.0;
        }
    }

    public void save (PrintWriter pw){ pw.print(number + "\n"); }
}
