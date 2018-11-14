import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MCMAQuestion extends MCQuestion {
    protected ArrayList <Answer> studentAnswer;
    public double baseCredit;
    private int numSelected;

    MCMAQuestion(String text, double maxValue, double baseCredit){
         super(text,maxValue);
         this.baseCredit = baseCredit;
         this.numSelected = 0;
         this.label = "MCMAQuestion";
    }

    MCMAQuestion(Scanner sc){
        super(sc);
        this.numSelected = 0;
        //base credit
        this.baseCredit = sc.nextDouble();
        sc.nextLine();

        //array size
        this.N = sc.nextInt();
        sc.nextLine();

        answers = new ArrayList<MCAnswer>(this.N);
        studentAnswer = new ArrayList<Answer>(this.N);
        this.label = "MCMAQuestion";
        //read in answers
        for(int i = 0 ; i < N; i++)
            addAnswer(new MCMAAnswer(sc));
    }

    @Override
    public Answer getNewAnswer() {
        return null;
    }

    public Answer getNewAnswer(String text, double creditifSelected){
        MCMAAnswer a = new MCMAAnswer(text, creditifSelected);

        return a;
    }

    public void getAnswerFromStudent(){
        print();
        System.out.println("Enter '#' to finish selecting answers");
        studentAnswer.clear();
        Scanner sc = new Scanner(System.in);
      //  Scanner sc = ScannerFactory.getKeyboardScanner();
        char name = '\0';
        while (name != '#') {
            name = sc.next().charAt(0);
            if (name == 'a' ){
                studentAnswer.add(answers.get(0));
                answers.get(0).selected = true;
                this.numSelected += 1;
            }
            else if (name == 'A'){
                studentAnswer.add(answers.get(0));
                answers.get(0).selected = true;
                this.numSelected += 1;
            }
            else if (name == 'b' ){
                studentAnswer.add(answers.get(1));
                answers.get(1).selected = true;
                this.numSelected += 1;
            }
            else if (name == 'B'){
                studentAnswer.add(answers.get(1));
                answers.get(1).selected = true;
                this.numSelected += 1;
            }
            else if (name == 'c' ){
                studentAnswer.add(answers.get(2));
                answers.get(2).selected = true;
                this.numSelected += 1;
            }
            else if (name == 'C'){
                studentAnswer.add(answers.get(2));
                answers.get(2).selected = true;
                this.numSelected += 1;
            }
            else if (name == 'd' ){
                studentAnswer.add(answers.get(3));
                answers.get(3).selected = true;
                this.numSelected += 1;
            }
            else if (name == 'D'){
                studentAnswer.add(answers.get(3));
                answers.get(3).selected = true;
                this.numSelected += 1;
            }
            else if (name == 'e' ){
                studentAnswer.add(answers.get(4));
                answers.get(4).selected = true;
                this.numSelected += 1;
            }
            else if (name == 'E'){
                studentAnswer.add(answers.get(4));
                answers.get(4).selected = true;
                this.numSelected += 1;
            }
            else if(name == 's'){
                this.studentAnswer.add(new MCMAAnswer("s",0.0));
            }
            else if (name == 'S'){
                this.studentAnswer.add(new MCMAAnswer("s",0.0));
            }
            else if(name == '#') {
                return;

            }
            else {
                System.out.println(name);
                System.out.println("ERROR: Student Answer invalid");
            }
        }



    }

    public double getValue(){
        double value = baseCredit;

        // Iterates through the question's answers and if they have been selected gets the answer's value.
        for(int i=0; i<super.answers.size(); i++){
            if(this.answers.get(i).selected == true){
                value = value + this.answers.get(i).creditIfSelected;
            }
        }
        value = ((value + baseCredit)  * super.maxValue);

        // This is less efficient and more error prone so is disabled.
        //for(int i=0; i<studentAnswer.size(); i++){
        //    value = value + super.getValue(studentAnswer.get(i));
        //}
        if(value > super.maxValue){
            value = 0;
        }
        return value;
    }

    public void save(PrintWriter pw){
        super.save(pw);
        pw.println(this.baseCredit);
        pw.println(this.N);
        for (MCAnswer a : answers){
            a.save(pw);
        }
        pw.println();  /////
    }
    @Override
    public void saveStudentAnswers(PrintWriter pw){
        pw.println(numSelected);
        for(MCAnswer mc : answers){
            if (mc instanceof MCMAAnswer && (mc.selected == true)){
                pw.println(mc.text.trim());
            }
        }
        pw.println();
    }

    public void restoreStudentAnswers(Scanner sc ){
        this.numSelected = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < numSelected; i++){
            String text = sc.nextLine();

            for(MCAnswer a : answers){
                if(a.text.contentEquals(text)) {
                    a.selected = true;
                    studentAnswer.add(a);
                }

            }


        }

    }

    //---------------------------------
    public String getQuestionType (){
        return null;
    }
}
