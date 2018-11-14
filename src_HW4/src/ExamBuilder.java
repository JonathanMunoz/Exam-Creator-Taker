import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExamBuilder {
    public static void main(String[] args) {
        Exam ex1 = null;

        System.out.println("Welcome to Exam Builder!");


        while(true) {

            // Menu
            System.out.println("----------------------------------");
            System.out.println("Enter one of the following options");
            System.out.println("1) Load Exam from File");
            System.out.println("2) Add question to Exam");
            System.out.println("3) Remove Question from Exam");
            System.out.println("4) Reorder Questions");
            System.out.println("5) Reorder Answers");
            System.out.println("6) Reorder Questions and Answers");
            System.out.println("7) Print Exam");
            System.out.println("8) Save Exam");
            System.out.println("9) Quit");
            System.out.println("----------------------------------");
            System.out.println();

            // Read in option
            Scanner sc = ScannerFactory.getKeyboardScanner();
            int option = sc.nextInt();
            sc.nextLine();

            //execute desired option

            if(option == 1){ // Load Exam
                System.out.println("Enter Exam file name to Load");
                File file = new File(ScannerFactory.getKeyboardScanner().nextLine());
                try {
                    // read in from file
                    Scanner examReader = new Scanner(file);
                    ex1= new Exam(examReader);
                    examReader.close();
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else if(option == 2){ //Add Question

                // Create exam if one does not exist
                if(ex1 == null){
                    System.out.println("Enter Exam Title");
                    ex1 = new Exam(sc.nextLine());
                }

                String prompt = null;
                double maxVal = 0.0;

                System.out.println("What type of Question would you like to add?");
                System.out.println("1) Multiple Choice Single Answer Question");
                System.out.println("2) Multiple Choice Multiple Answers Question");
                System.out.println("3) Short Answer Question");
                System.out.println("4) Number Question");

                int choice = sc.nextInt();
                sc.nextLine();

                // reading in prompt and value
                System.out.println("Enter Question Prompt and value of question on a separate line");
                prompt = sc.nextLine();
                maxVal = sc.nextDouble();
                sc.nextLine();

                if(choice == 1){ //MCSA
                    MCSAQuestion mcsa = new MCSAQuestion(prompt,maxVal);
                    System.out.println("Enter the 5 Answers and below each answer their credit worth (1.0 - 0.0)");
                    MCSAAnswer answer = null;

                    //enter answers
                    for(int i = 0; i < 5; i++){
                        answer = new MCSAAnswer(sc.nextLine(),sc.nextDouble());
                        sc.nextLine();
                        mcsa.addAnswer(answer);
                        // set correct answer
                        if(answer.creditIfSelected == 1.0)
                            mcsa.rightAnswer = answer;
                    }
                    ex1.addQuestion(mcsa); // add question to exam
                }
                else if(choice == 2){ //MCMA
                    System.out.println("Enter Base Credit");

                    MCMAQuestion mcma = new MCMAQuestion(prompt,maxVal,sc.nextDouble());
                    sc.nextLine();
                    System.out.println("Enter the 5 Answers and below each answer their credit worth ");
                    MCMAAnswer answer = null;

                    //enter answers
                    for(int i = 0; i < 5; i++) {
                        answer = new MCMAAnswer(sc.nextLine(), sc.nextDouble());
                        sc.nextLine();
                        mcma.addAnswer(answer);
                    }
                    ex1.addQuestion(mcma); // add question to exam
                }
                else if(choice == 3 ){ // SA
                    SAQuestion sa = new SAQuestion(prompt,maxVal);
                    System.out.println("Enter the Correct answer text:");
                    SAAnswer saa = new SAAnswer(sc.nextLine());
                    sa.rightAnswer = saa;
                    ex1.addQuestion(sa);
                }
                else if(choice == 4){ // NUM
                    System.out.println("Enter tolerance:");
                    NumQuestion num = new NumQuestion(prompt,maxVal,sc.nextDouble());
                    sc.nextLine();
                    System.out.println("Enter the Correct answer value:");
                    NumAnswer numa = new NumAnswer(sc.nextDouble());
                    sc.nextLine();
                    num.rightAnswer = numa;
                    ex1.addQuestion(num);
                }
                else{ System.out.println("Not an option. Try again"); }

            }
            else if(option == 3){ // Remove Question
                System.out.println("Enter the number of the question you want to remove:");
                int position = sc.nextInt();
                sc.nextLine();
                ex1.removeQuestion(position);
            }
            else if(option == 4){ // Reorder Questions
                ex1.reorderQuestions();
            }
            else if(option == 5){ //Reorder Answers
                System.out.println("Enter the Multiple Choice Question you want the answers reordered. For all MCQuestions type in 0");
                int select = sc.nextInt();
                sc.nextLine();
                ex1.reorderMCAnswers(select);

            }
            else if(option == 6){ // Reorder Questions and Answers
                ex1.reorderQuestions();
                ex1.reorderMCAnswers(0);
            }
            else if(option == 7){ // Print exam
                if(ex1 != null) {
                    ex1.print();
                    System.out.println("---------------------------------------\n");
                }
                else {
                    System.out.println("Exam has not been created. Load or create and Exam to print.");
                }
            }
            else if(option == 8){ // Save Exam
                System.out.println("Enter file name to save to.");

                PrintWriter pw = null;

                try {
                    Scanner fo = new Scanner(System.in);
                    String fileOut = fo.nextLine();
                    pw = new PrintWriter(fileOut);
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                ex1.save(pw);
                pw.close();
            }
            else if(option == 9){ // Quit
                break;
            }
            else{
                System.out.println("Not a valid option. Try again");
            }

        }
        System.out.println("Good Bye!");
    }
}
