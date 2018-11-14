import java.io.PrintWriter;

public abstract class Answer {
    protected String label;

    protected Answer(){

    }

    abstract void print();

    abstract double getCredit(Answer rightAnswer);
    public abstract void save (PrintWriter pw);
}
