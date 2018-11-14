//package com.mikehume;

import java.io.PrintWriter;
import java.util.Scanner;

public class SAAnswer extends Answer {
    protected String text;

    public SAAnswer(String text) {
        this.text = text;
    }

    public SAAnswer(Scanner questionFile) {
        this.text = questionFile.nextLine();
    }

    public void print(){
        System.out.println(this.text);
    }

    public double getCredit(Answer rightAnswer){
        this.text = this.text.toLowerCase();
        ((SAAnswer)rightAnswer).text = ((SAAnswer)rightAnswer).text.toLowerCase();
        if (this.text.equals(((SAAnswer)rightAnswer).text)){
            return 1.0;
        }
        else {
            return 0.0;
        }
    }

    public void save (PrintWriter pw){ pw.print(text + "\n"); }
}

