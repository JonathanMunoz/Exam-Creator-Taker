import java.io.PrintWriter;
import java.util.Scanner;

public abstract class MCAnswer extends Answer{

    protected String text;
    protected boolean selected;
    protected double creditIfSelected;

    protected MCAnswer(String text, double creditifSelected){
        this.text = text;
        this.selected = false;
        this.creditIfSelected = creditifSelected;
        this.label = "MCAnswer";

    }

    public MCAnswer(Scanner sc){

        this.creditIfSelected = sc.nextDouble();
        this.text = sc.nextLine().trim();
        this.selected = false;
        this.label = "MCAnswer";

    }

    public void print(){
        System.out.println(this.text);
    }


    public void setSelected(Boolean selection){

        this.selected = selection;
    }

    public double getCredit(Answer rightAnswer){

        if (this.selected == true)
            return creditIfSelected;
        else
            return 0.0;
    }

    public void save(PrintWriter pw){
        pw.print(this.creditIfSelected);
        pw.print(" ");
        pw.println(this.text);
    }


}
