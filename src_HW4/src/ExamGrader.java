import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExamGrader {
    ExamGrader(){}
    private static List<String> commandLineArgs = new ArrayList<String>();
    private static List<String> studentNames = new ArrayList<String>();
    private static List<Double> studentScores = new ArrayList<Double>();
    private static List<Double> pointsPossible = new ArrayList<Double>();
    private static List<String> dateAndTime = new ArrayList<String>();


    public static void main (String[] args) throws IOException {
        ExamGrader grader = new ExamGrader();
        // Sets the filepath
        String filepath = null;
        // filepath = "/nfsdirs/home2/home2/ugrad2/mhume/Documents/CS342/HW4/";
        // filepath = "/Users/mikehume/Desktop/";
        String inputA = null;
        String inputB = null;
        String answerFilename = null;
        String examFilename = null;
        String studentName = null;
        String dateAndTime = null;
        String answerFileExamName = null;
        String examFilepath = null;


        try {
            grader.printHeader();
            System.out.println("Enter the filepath of the Exam you wish to grade:\t(format: /nfsdirs/home2/.../ExamGrader/)\n-----> ");
            Scanner filepathInput = new Scanner(System.in);
            filepath = filepathInput.nextLine();

            System.out.println("filepath: " + filepath);
            if (args.length > 0) {
                // Create scanner for scanning answer file.
                Scanner answerFileScanner = new Scanner(System.in);

                // If there is only one command line argument it MUST be an answer file.
                if (args.length == 1) {
                    // answer file name is in first argument position.
                    answerFilename = args[0];
                    File answerFile = new File(filepath + answerFilename);

                    // Use scanner to begin scanning answer file.
                    answerFileScanner = new Scanner(answerFile);

                    // Pull the students name from the first line of the answer file (to be used for CSV export).
                    //studentName = answerFileScanner.nextLine();
                    studentNames.add(answerFileScanner.nextLine());

                    // Pull the name of the exam file from second line of the answer file.
                    examFilepath = filepath + answerFileScanner.nextLine();
                }
                // If there are two command line arguments
                else if (args.length == 2) {
                    // The section looks at both files and identifies which is the answer and which is the exam file.
                    inputA = args[0];
                    inputB = args[1];

                    Scanner fileTestScannerA = new Scanner(System.in);
                    Scanner fileTestScannerB = new Scanner(System.in);

                    File fileA = new File(filepath + inputA);
                    File fileB = new File(filepath + inputB);

                    fileTestScannerA = new Scanner(fileA);
                    fileTestScannerB = new Scanner(fileB);

                    fileTestScannerA.nextLine();
                    fileTestScannerB.nextLine();

                    String fileALine2 = fileTestScannerA.nextLine();
                    String fileBLine2 = fileTestScannerB.nextLine();

                    // If A is the Answer file, B is the Exam file
                    if(fileALine2.equals(inputB)){
                        // Set the Answer and Exam file name variable for calling the files in the next section
                        answerFilename = args[0];
                        examFilename = args[1];
                        fileTestScannerA.close();
                        fileTestScannerB.close();
                        System.out.println("File1 is Answer File, File2 is Exam File.");

                    }

                    // If B is the answer file, A is the exam file
                    else if(fileBLine2.equals(inputA)){
                        // Set the Exam and Answer file name variable for calling the files in the next section
                        examFilename = args[0];
                        answerFilename = args[1];
                        fileTestScannerA.close();
                        fileTestScannerB.close();
                        System.out.println("File1 is Exam File, File2 is Answer File.");
                    }

                    // If both files are Exam files or both are Answer files
                    else {
                        String testAForNull = fileTestScannerA.nextLine();
                        String testBForNull = fileTestScannerB.nextLine();

                        // If both command line arguments are Exam files, gives and error and exits program.
                        if(testAForNull.isEmpty() && testBForNull.isEmpty()){
                            // Both files are exam files
                            System.out.println("Error: File1 & File2 are both exam files!\n\nExiting program...");
                            System.exit(0);
                        }

                        // If both command line arguments are Answer files, loads first answer file, then second answer file.
                        else if(!(testAForNull.isEmpty()) && !(testBForNull.isEmpty())){
                            System.out.println("Two answer files detected.");
                            // Loop through answer files.
                            for(int i=0; i<2; i++) {
                                if(i>0){
                                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
                                }
                                System.out.println("\tReading answer file " + (i+1) + "...");
                                answerFilename = args[i];
                                File answerFile = new File(filepath + answerFilename);

                                // Use scanner to begin scanning answer file.
                                answerFileScanner = new Scanner(answerFile);

                                // Pull the students name from the first line of the answer file (to be used for CSV export).
                                //studentName = answerFileScanner.nextLine();
                                studentNames.add(answerFileScanner.nextLine());

                                // Pull the name of the exam file from second line of the answer file.
                                examFilepath = filepath + answerFileScanner.nextLine();

                                // Call the method to grade the student exam and display results.
                                grader.gradeStudentAnswers (grader, answerFileScanner, examFilepath);
                            }
                            System.exit(0);
                        }
                        // If the files are not formatted correctly gives an error and exits program.
                        else{
                            System.out.println("Error: File format not recognized.");
                            System.exit(0);
                        }
                    }

                    // This section uses the examFileName and answerFileName variable set above to call each file
                    File answerFile = new File(filepath + answerFilename);

                    // Create scanner to begin scanning answer file.
                    answerFileScanner = new Scanner(answerFile);

                    // Pull the students name from the first line of the answer file.
                    studentName = answerFileScanner.nextLine();
                    System.out.println("Student Name: " + studentName);

                    answerFileExamName = answerFileScanner.nextLine();

                    // Checks if the exam file argument match the file name in the answer file.
                    System.out.println("Exam Name from Answer File: " + answerFileExamName);
                    if (answerFileExamName.equals(examFilename)) {
                        examFilepath = filepath + examFilename;
                    }
                    else {
                        System.out.println("Exam file and answer file do not match, disregarding Exam file input.");
                        examFilepath = filepath + answerFileExamName;
                    }
                }

                // If more than two command line arguments are included.
                else if (args.length > 2) {
                    System.out.println(args.length + " answer files detected.");
                    int examPosition = 999;
                    int numExamFiles = 0;

                    // This block examines each command line argument and determines which (if any) are exam files, records file name and position.
                    Scanner fileTestScanner = new Scanner(System.in);
                    for(int i=0; i<args.length; i++) {
                        commandLineArgs.add(args[i]);
                        File file = new File(filepath + commandLineArgs.get(i));
                        fileTestScanner = new Scanner(file);

                        // Skip to line 3
                        fileTestScanner.nextLine();
                        fileTestScanner.nextLine();

                        // Pull line 3 contents
                        String fileLine3 = fileTestScanner.nextLine();

                        // If the 3rd line of the file is empty it is identified as an exam file and recorded.
                        if(fileLine3.isEmpty()){
                            examFilename = commandLineArgs.get(i);
                            examPosition = i;
                            numExamFiles++;

                            // If more than 1 exam file is detected, the program will exit.
                            if(numExamFiles > 1){
                                System.out.println("Error: Multiple exam files detected.");
                                System.exit(0);
                            }
                        }
                    }
                    fileTestScanner.close();

                    // Loop through answer files.
                    for(int i=0; i<args.length; i++) {
                        // Print a break line to separate exams
                        if(i>0){
                            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
                        }
                        System.out.println("\tReading answer file " + (i+1) + "...");

                        // Skips the position identified as the exam file.
                        if (!(i == examPosition)) {
                            answerFilename = args[i];
                            File answerFile = new File(filepath + answerFilename);

                            // Use scanner to begin scanning answer file.
                            answerFileScanner = new Scanner(answerFile);

                            // Pull the students name from the first line of the answer file (to be used for CSV export).
                            //studentName = answerFileScanner.nextLine();
                            studentNames.add(answerFileScanner.nextLine());

                            answerFileExamName = answerFileScanner.nextLine();

                            // Checks if the exam file argument match the file name in the answer file.
                            // If the filenames match, the exam file path is set by the argument filename.
                            if (answerFileExamName.equals(examFilename)) {
                                examFilepath = filepath + examFilename;
                            }
                            // If the filenames DO NOT match, the exam file path is set by exam name from the answer file, the exam name argument is disregarded.
                            else {
                                System.out.println("Exam file and answer file do not match, disregarding Exam file input.");
                                examFilepath = filepath + answerFileExamName;
                            }
                            grader.gradeStudentAnswers (grader, answerFileScanner, examFilepath);
                        }
                        else{
                            System.out.println("Skipping file " + (examPosition+1) + ", identified as Exam file.");
                        }
                    }
                        //grader.displayStudentScores();
                        System.exit(0);
                }
                // Call the method to grade the student exam and display results.
                grader.gradeStudentAnswers (grader, answerFileScanner, examFilepath);
            }
            else {
                // If no command line arguments were provided prompt the user to enter the student answer filename.
                System.out.println("\nNo filenames entered to grade in command line.\n");
                System.out.print("Enter filename of student answer file to be graded.\n-----> ");
                Scanner inputScanner = new Scanner(System.in);
                answerFilename = inputScanner.nextLine();
                //inputScanner.close();
                File answerFile = new File(filepath + answerFilename);
                Scanner answerFileScanner = new Scanner(answerFile);

                // Pull the students name from the first line of the answer file (to be used for CSV export).
                studentNames.add(answerFileScanner.nextLine());

                // Pull the name of the exam file from second line of the answer file.
                examFilepath = filepath + answerFileScanner.nextLine();

                // Call the method to grade the student exam and display results.
                grader.gradeStudentAnswers (grader, answerFileScanner, examFilepath);

                inputScanner.close();
                filepathInput.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void displayStudentScores (){
        System.out.println("\n\n\t\t\t\t***********************");
        System.out.println("\t\t\t\t* Student Exam Scores *");
        System.out.println("\t\t\t\t***********************\n\n");
        System.out.println("   Name\t\t\tScore\t\t\tMax Possible\t\tPercentage");
        System.out.println("  -----------------------------------------------------------------------------------------");
        for(int i=0; i<studentNames.size(); i++){
            System.out.println("   " + studentNames.get(i) + "\t\t"+ studentScores.get(i) + "\t\t\t" + pointsPossible.get(i) + "\t\t\t" + (studentScores.get(i)/pointsPossible.get(i))*100 + "%\n\n\n");
        }
    }

    public void gradeStudentAnswers (ExamGrader grader, Scanner answerFileScanner, String examFilepath){
        // This section uses the examFileName and answerFileName variable set above to call each file.
        try {
            File examFile = new File(examFilepath);
            Scanner examFileScanner = new Scanner(System.in);
            examFileScanner = new Scanner(examFile);

            // Pull Date & Time from Answer File (What is this needed for???).
            dateAndTime.add(answerFileScanner.nextLine());

            // Create the studentExam (questions unanswered at this point).
            Exam studentExam = new Exam(examFileScanner);

            // Load student answers from Answer file into studentExam.
            studentExam.restoreStudentAnswers(answerFileScanner);

            // Close scanners.
            examFileScanner.close();
            answerFileScanner.close();

            // Score the studentExam.
            studentExam.reportQuestionValues();
            studentScores.add(studentExam.getStudentScore());
            pointsPossible.add(studentExam.getPointsPossible());
            grader.displayStudentScores();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void printHeader (){
        System.out.println("\nAuthors: Mike Hume, Greg Barbosa, Jonathan Munoz");
        System.out.println("netID:  mhume3, gbarbo3, jmunoz48");
        System.out.println("Homework 4\n13 Apr 18\n\n\n");
    }
}