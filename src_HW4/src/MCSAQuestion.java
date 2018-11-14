import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Object;
public class MCSAQuestion extends MCQuestion {

    public MCSAQuestion(String text, double maxValue){
        super(text,maxValue);
        this.label = "MCSAQuestion";
    }
    public MCSAQuestion(Scanner sc){
        super(sc);
        this.N = sc.nextInt();
        sc.nextLine();
        answers = new ArrayList<MCAnswer>(this.N);
        this.label = "MCSAQuestion";

        // reading in answers
        for (int i = 0; i < this.N; i++)
            addAnswer(new MCSAAnswer(sc));

    }

    @Override
    public Answer getNewAnswer() {
        return null;
    }

    public Answer getNewAnswer(String text, double creditifSelected){
        MCSAAnswer a = new MCSAAnswer(text, creditifSelected);

        return a;
    }

    public void getAnswerFromStudent(){
        print();
        Scanner sc = new Scanner(System.in);
        char name =  sc.next().charAt(0);

        if (name == 'a' ){
            answers.get(0).selected = true;
            this.studentAnswer = answers.get(0);
            return;
        }
        else if (name == 'A'){
            answers.get(0).selected = true;
            this.studentAnswer = answers.get(0);
        }
        else if (name == 'b' ){
            answers.get(1).selected = true;
            this.studentAnswer = answers.get(1);
            return;
        }
        else if (name == 'B'){
            answers.get(1).selected = true;
            this.studentAnswer = answers.get(1);
        }
        else if (name == 'c' ){
            answers.get(2).selected = true;
            this.studentAnswer = answers.get(2);
        }
        else if (name == 'C'){
            answers.get(2).selected = true;
            this.studentAnswer = answers.get(2);
        }
        else if (name == 'd' ){
            answers.get(3).selected = true;
            this.studentAnswer = answers.get(3);
        }
        else if (name == 'D'){
            answers.get(3).selected = true;
            this.studentAnswer = answers.get(3);
        }
        else if (name == 'e' ){
            answers.get(4).selected = true;
            this.studentAnswer = answers.get(4);
        }
        else if (name == 'E'){
            answers.get(4).selected = true;
            this.studentAnswer = answers.get(4);
        }
        else if(name == 's'){
            this.studentAnswer = getNewAnswer("s", 0.0);
        }
        else if (name == 'S'){
            this.studentAnswer = getNewAnswer("S", 0.0);
        }
        else {
            System.out.println(name);
            System.out.println("ERROR: Student Answer invalid");
        }


    }

    public double getValue(){
        double value = 0;
        value = super.getValue((MCSAAnswer)studentAnswer);

        // Iterates through the question's answers and if they have been selected gets the answer's value.
        //for(int i=0; i<super.answers.size(); i++){
        //if(this.answers.get(i).selected == true){
        //value = this.answers.get(i).creditIfSelected;
        //}
        //}
        return value;
    }


    public void save(PrintWriter pw ){
        super.save(pw);
        pw.println(this.N);
        for (MCAnswer a : answers){
            a.save(pw);
        }
        pw.println(); //////
    }

    public String getQuestionType (){
        return null;
    }

    public void restoreStudentAnswer(Scanner answerFile, String curr){
        // Set all answers to NOT selected
        for(int i=0; i<answers.size(); i++){
            answers.get(i).selected = false;
        }

        //String currentAnswer = answerFile.nextLine();
        // Cycle through answers and mark those that match the answer in file as selected.
        for(int j=0; j<answers.size(); j++){
            if(answers.get(j).text.equals(curr)){
                answers.get(j).selected = true;
            }
        }
    }

}
