import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exam {
    private String title;
    private ArrayList<Question> questions;
    private int numQuestions;
    private int numQuestionsAsked;
    private String filepath;
    private double studentScore;
    private double pointsPossible;
    private ArrayList<Integer> skippedQuestionsList; // arraylist which stores the index of every question in the questionList that had been unanswered

    Exam(String header){

        this.title = header;
        questions = new ArrayList<Question>();

    }

    Exam(Scanner sc){
        questions = new ArrayList<Question>();
        skippedQuestionsList = new ArrayList<Integer>(5);
        this.title = sc.nextLine();
        sc.nextLine();

        while(sc.hasNextLine()) {
            sc.nextLine();
            if (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (line.contentEquals("MCSAQuestion")) {
                    addQuestion(new MCSAQuestion(sc));
                    numQuestions++;
                }
                else if (line.contentEquals("SAQuestion")) {
                    addQuestion(new SAQuestion(sc));
                    numQuestions++;
                }
                else if (line.contentEquals("MCMAQuestion")) {
                    addQuestion(new MCMAQuestion(sc));
                    numQuestions++;
                }
                else if (line.contentEquals("NumQuestion")) {
                    addQuestion(new NumQuestion(sc));
                    numQuestions++;
                }
                else
                    System.out.println("File is not in correct format.");

                System.out.println();
            }
        }
    }

    public void addQuestion(Question q){
        questions.add(q);
    }

    public void print(){

        System.out.println(title);

        int i = 1; // might need to change
        for(Question q : questions){
            System.out.println("-----------------");
            System.out.print(i + ". ");
            q.print();
            i++;
        }
    }

    public void reorderQuestions(){

        Collections.shuffle(questions); // change
    }

    public void reorderMCAnswers(int position){

        // individual reorder
        if(position > 0) {
            if(questions.get(position-1) instanceof  MCSAQuestion) {
                MCSAQuestion a = (MCSAQuestion) questions.get(position - 1);
                a.reorderAnswers();
            }
            if (questions.get(position-1) instanceof  MCMAQuestion){
                MCMAQuestion a = (MCMAQuestion) questions.get(position - 1);
                a.reorderAnswers();
            }
        }
        //reorder all
        else{
            for(Question q: questions) {
                if(q instanceof MCSAQuestion) {
                    ((MCSAQuestion) q).reorderAnswers();
                }
                if(q instanceof MCMAQuestion) {
                    ((MCMAQuestion) q).reorderAnswers();
                }
            }
        }
    }

    public void getAnswerFromStudent(int position){
       // questions.get(position - 1).getAnswerFromStudent();

        // if position is negative, get an answer for every question
        if( position < 0 ){
            System.out.println("~~~~~~~~~~~ Type \"skip\" to skip SA questions ~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~ Type \"S\" to skip MC questions ~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~ Type \"-999\" to skip Num questions ~~~~~~~~~"); // <---------------------------------------------------------------------------what should the skip keyword be for num questions?
            for( int i = 0; i < questions.size(); i++ ){
                System.out.print( (i+1) + ".) " ); // for formatting the output

                questions.get( i ).getAnswerFromStudent();
            }
        }

        // if position is positive, get an answer for that specific question
        else if( position < questions.size() ){
            System.out.print( (position+1) + ".) " ); // for formatting the output
            questions.get(position).getAnswerFromStudent();
        }

        else
            System.out.println("ERROR in Exam public void getAnswerFromStudent(int position) : Invalid input.");

        checkSkippedQuestions();

    }

    public void checkSkippedQuestions() {
        Question q;
        Answer ans;
        String studentInput;
        Double studentInputD;

        for (int i = 0; i < questions.size(); i++) {
            q = questions.get(i);

            if (q instanceof SAQuestion) {
                ans = ((SAQuestion) q).studentAnswer;
                studentInput = ((SAAnswer) ans).text;

                // add index of question to the skippedQuestionsList if the student inputted "skip" and this question isn't already in the skippedQuestionsList
                // if this question is already in the skippedQuestionsList, then remove it and add it again, so that it gets pushed to the end of the list
                // make sure that you change the student's answer to "" or else you will be reordering this question every time this method is called, even if the student didn't input "skip" again
                if (studentInput.equalsIgnoreCase("skip")) {
                    if (skippedQuestionsList.contains(i)) {
                     //   System.out.println("before removal of SAQuestion: " + skippedQuestionsList);
                        skippedQuestionsList.remove(Integer.valueOf(i));
                    //    System.out.println("after removal of SAQuestion: " + skippedQuestionsList);
                    }
                   // System.out.println("before adding SAQuestion: " + skippedQuestionsList);
                    skippedQuestionsList.add(i);
                   // System.out.println("after adding SAQuestion: " + skippedQuestionsList);
                    ((SAAnswer) ans).text = "";
                }

                // remove index of question from the skippedQuestionsList if the student didn't input "skip" and this question is in the skippedQuestionList
                if (!studentInput.equalsIgnoreCase("skip") && !studentInput.equalsIgnoreCase("") && skippedQuestionsList.contains(i))
                    skippedQuestionsList.remove(Integer.valueOf(i));
            }

            if (q instanceof MCSAQuestion) {
                ans = ((MCSAQuestion) q).studentAnswer;
                studentInput = ((MCSAAnswer) ans).text;

                if (studentInput.equalsIgnoreCase("S")) {
                    if (skippedQuestionsList.contains(i)) {
                   //     System.out.println("before removal of MCSAQuestion: " + skippedQuestionsList);
                        skippedQuestionsList.remove(Integer.valueOf(i));
                  //      System.out.println("after removal of MCSAQuestion: " + skippedQuestionsList);
                    }
                 //   System.out.println("before adding MCSAQuestion: " + skippedQuestionsList);
                    skippedQuestionsList.add(i);
                //    System.out.println("after adding MCSAQuestion: " + skippedQuestionsList);
                    ((MCSAAnswer) ans).text = ""; // make sure that the "S" gets removed and replaced with "" until the student answers the question for real
                }

                if (!studentInput.equalsIgnoreCase("S") && !studentInput.equalsIgnoreCase("") && skippedQuestionsList.contains(i))
                    skippedQuestionsList.remove(Integer.valueOf(i));
            }

            if (q instanceof MCMAQuestion) {
                if (!((MCMAQuestion) q).studentAnswer.isEmpty()) { // don't even bother with any of this if the student answer arraylist is empty
                    ans = ((MCMAQuestion) q).studentAnswer.get(0); // NOTE: this only checks if a student inputted "S" as their FIRST answer
                    studentInput = ((MCMAAnswer) ans).text;

                    if (studentInput.equalsIgnoreCase("S")) {
                        if (skippedQuestionsList.contains(i)) {
                         //   System.out.println("before removal of MCMAQuestion: " + skippedQuestionsList);
                            skippedQuestionsList.remove(Integer.valueOf(i));
                       //     System.out.println("after removal of MCMAQuestion: " + skippedQuestionsList);
                        }
                     //   System.out.println("before adding MCMAQuestion: " + skippedQuestionsList);
                        skippedQuestionsList.add(i);
                   //     System.out.println("after adding MCMAQuestion: " + skippedQuestionsList);
                        ((MCMAQuestion) q).studentAnswer.clear(); // make sure that the question remains answerless until the student actually answers it
                    }

                    if (!studentInput.equalsIgnoreCase("S") && skippedQuestionsList.contains(i))
                        skippedQuestionsList.remove(Integer.valueOf(i));
                }
            }

            if (q instanceof NumQuestion) {
                ans = ((NumQuestion) q).studentAnswer;
                studentInputD = ((NumAnswer) ans).number;// <---------------------------------------------------------------------------how is the student answer for num questions stored? probably not as a string...

                if (studentInputD == -999) { //<--------------------------------------------------------------------------what should a student input to skip a num question?
                    if (skippedQuestionsList.contains(i)) {
                       // System.out.println("before removal of NumQuestion: " + skippedQuestionsList);
                        skippedQuestionsList.remove(Integer.valueOf(i));
                     //   System.out.println("after removal of NumQuestion: " + skippedQuestionsList);
                    }
                   // System.out.println("before adding NumQuestion: " + skippedQuestionsList);
                    skippedQuestionsList.add(i);
                 //   System.out.println("after adding NumQuestion: " + skippedQuestionsList);
                    ((NumAnswer) ans).number = -68;// <----------------------------------------------------------------------------------------reset the num answer to something other than a valid input or the skip keyword
                }

                if (studentInputD != -999 && studentInputD != -68 && skippedQuestionsList.contains(i))
                    skippedQuestionsList.remove(Integer.valueOf(i));
            }
        }
    }

    // forces student to answer the questions that they skipped
    public void answerSkippedQuestions(){
        while( !skippedQuestionsList.isEmpty() ){
          //  System.out.println( "Forcing you to answer these questions in this order:  " + skippedQuestionsList );
            getAnswerFromStudent( skippedQuestionsList.get( 0 ) );
        }
    }




    public double getValue () {
        double examScore = 0;
        double maxScore = 0;
        double percetageScore = 0;

        // Calculates the cumulative score earned and maximum score possible
        for(int i=0; i<numQuestionsAsked; i++) {
            examScore = examScore + questions.get(i).getValue();
            examScore = Double.parseDouble(new DecimalFormat("##.##").format(examScore));
            maxScore += questions.get(i).maxValue;
        }

        // Calculates the student's score percentage
        percetageScore = ((examScore/maxScore)*100);
        percetageScore = Double.parseDouble(new DecimalFormat("##.##").format(percetageScore));
        System.out.println("\n\tCurrent Score:\t" + examScore + "\t  " + maxScore + ", a " + percetageScore + "%\n");
        return examScore;
    }

    /*
    public void reportQuestionValues(){
        int i = 1;
        for(Question q : questions){
            System.out.println("Question " + i + ": Value: " + q.maxValue);
            i++;
        }
    }

    */
    // Prints the table of scores broken down by question
    public void reportQuestionValues(){
        double examScore = 0;
        double maxScore = 0;
        double percetageScore = 0;
        System.out.print("\n\t\t\tQuestion\tScore\tScore Possible\n");
        System.out.println("\t\t\t---------------------------------------");

        // Iterates through the questions and calculates the points awarded for each.
        for(int i=0; i<questions.size(); i++) {
            examScore = examScore + questions.get(i).getValue();
            examScore = Double.parseDouble(new DecimalFormat("##.##").format(examScore));
            maxScore = maxScore + questions.get(i).maxValue;
            double questionScore = questions.get(i).getValue();
            questionScore = Double.parseDouble(new DecimalFormat("##.##").format(questionScore));
            System.out.print(("\t\t\t" + (i+1) + "-->\t\t " + questionScore + "\t  5.0\n"));
        }
        percetageScore = (examScore/maxScore)*100;
        percetageScore = Double.parseDouble(new DecimalFormat("##.##").format(percetageScore));
        System.out.println("\t\t\t---------------------------------------");
        System.out.println("\t\t\tScore:\t\t " + examScore + "\t  " + maxScore);
        System.out.println("\n\nStudent's score was " + examScore + " out of " + maxScore + ", a " + percetageScore + "%\n");
        studentScore = examScore;
        pointsPossible = maxScore;
    }

    public void save(PrintWriter pw){
        pw.println(this.title);
        pw.println("" + LocalDateTime.now());
        pw.println();

        for (Question q : questions){
            if(q instanceof MCSAQuestion){
                pw.println("MCSAQuestion");
                ((MCSAQuestion) q).save(pw);
            }
            if (q instanceof MCMAQuestion){
                pw.println("MCMAQuestion");
                ((MCMAQuestion) q).save(pw);
            }
            if(q instanceof SAQuestion){
                ((SAQuestion) q).save(pw);
            }
            if(q instanceof NumQuestion){
                ((NumQuestion) q).save(pw);
            }

        }
    }

    public void saveStudentAnswers(PrintWriter pw){


        // Print to student answer file
       // pw.print(ScannerFactory.getKeyboardScanner().nextLine());
        pw.println(""+LocalDateTime.now());
        pw.println();

        for (Question q : questions)
            if (q instanceof SAQuestion) {
                pw.println("SAAnswer");
                q.saveStudentAnswers(pw);
            }
            else if(q instanceof MCSAQuestion){
                pw.println("MCSAAnswer");
                q.saveStudentAnswers(pw);
            }
            else if (q instanceof MCMAQuestion){
                pw.println("MCMAAnswer");
                ((MCMAQuestion) q).saveStudentAnswers(pw);
            }
            else if (q instanceof NumQuestion){
                pw.println("NumAnswer");
                ((NumQuestion) q).saveStudentAnswers(pw);
            }


    }

    // Loads a student's previous answers in to be scored.
    public void restoreStudentAnswers (Scanner answerFile){
        try {
            int questionNumber = 0;
            // Loads the file and sorts each line to its appropriate question/answer type
            answerFile.nextLine();
            while (answerFile.hasNextLine()) {
                String tmp = answerFile.nextLine();
                // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
                if (tmp.equals("MCSAAnswer")) {
                    questions.get(questionNumber).restoreStudentAnswers(answerFile);
                    answerFile.nextLine();
                    questionNumber++;
                }
                // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
                else if (tmp.equals("MCMAAnswer")) {
                    questions.get(questionNumber).restoreStudentAnswers(answerFile);
                    answerFile.nextLine();
                    questionNumber++;
                }
                // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
                else if (tmp.equals("SAAnswer")) {
                    questions.get(questionNumber).restoreStudentAnswers(answerFile);
                    answerFile.nextLine();
                    questionNumber++;
                }
                // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
                else if (tmp.equals("NumAnswer")) {
                    questions.get(questionNumber).restoreStudentAnswers(answerFile);
                    answerFile.nextLine();
                    questionNumber++;
                }
                // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
                else {
                    //System.out.println("Error! Question type not recognized!");
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /*
    public void restoreStudentAnswers(Scanner sc){
        int i = 0; // keeps track on what question we are on
        sc.nextLine();
        while(sc.hasNextLine()) {
            sc.nextLine();
            if(sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contentEquals("SAAnswer")) {
                    questions.get(i).restoreStudentAnswers(sc);
                }
                else if (line.contentEquals("NumAnswer")) {
                    ((NumQuestion) questions.get(i)).restoreStudentAnswers(sc);
                }
                else if (line.contentEquals("MCSAAnswer")) {
                    questions.get(i).restoreStudentAnswers(sc);
                    for(MCAnswer a :((MCSAQuestion)questions.get(i)).answers){
                        //if(a.text.contentEquals(((MCSAAnswer)questions.get(i).studentAnswer).text))
                            a.setSelected(true);
                    }
                } else if (line.contentEquals("MCMAAnswer")) {
                    ((MCMAQuestion) questions.get(i)).restoreStudentAnswers(sc);
                }
                i++;
            }
        }
    }
    */

    // added
    public void removeQuestion(int position ){
        questions.remove(position -1);
    }

    public Question printQuestionAt (int position){ return questions.get(position); }

    public String getExamTitle() { return title; }

    public int getNumQuestions() { return numQuestions; }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public double getStudentScore (){
        return studentScore;
    }

    public double getPointsPossible (){
        return pointsPossible;
    }
}
