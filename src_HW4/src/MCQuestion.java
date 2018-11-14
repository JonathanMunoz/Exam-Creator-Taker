import java.io.PrintWriter;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class MCQuestion extends Question {


    protected ArrayList<MCAnswer> answers;
    protected int N; // num of elems at start

    protected MCQuestion(String text, double maxValue){
        super(text,maxValue);
        this.N = 5; //change was 5
        answers = new ArrayList<MCAnswer>(this.N);
        this.label = "MCQuestion";
    }

    protected MCQuestion(Scanner sc ){

        super(sc);
        this.label = "MCQuestion";

    }

    public void addAnswer(MCAnswer a) {
        answers.add(a);
    }

    public void reorderAnswers() {

        Collections.shuffle(answers); // change
    }

    public void print() {


        System.out.println(text);
        System.out.println("-----------------");

        char i = 'A';
        for (Answer a : answers) { // change
            System.out.print(i + ") ");
            a.print();
            i++;
        }
    }

    public double getValue(MCAnswer studentAnswer){
        for(int i=0; i<answers.size(); i++){
            //if(this.answers.get(i).text.equals(studentAnswer.text){     // This is less efficient and more error prone so is disabled.
            //this looks to see if the answer was the one selected and if so returns its creditIfSelected.
            if(this.answers.get(i).selected == true){
                return ((this.answers.get(i).creditIfSelected) * super.maxValue);
            }
        }
        return 0.0;
    }

    public void save(PrintWriter pw ){
        pw.println(this.maxValue);
        pw.println(this.text);
    }

}
