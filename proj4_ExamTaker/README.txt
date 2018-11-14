Team members: Jonathan Munoz, Michael Hume, Gregorio Barbosa

Division of Labor:
Jonathan Munoz: ExamTaker, Answer, SAQuestion, SAAnswer, NumQuestion, and NumAnswer
Gregorio Barbosa: ExamBuilder, Exam, Question, and MCQuestion
Michael Hume: ExamGrader, MCAnswer, MCSAQuestion, MCSAAnswer, MCMAQuestion, MCMAAnswer,ScannerFactory

------------------------------------------------------ DESCRIPTION ------------------------------------------------------
To run the program, type "make" to compile everything and then type "java " followed by which main you want to run.
Ex: "java ExamTaker"
You can type "make clean" to get rid of the class files you create when compiling.

There are 3 main files in this program, which are:
ExamBuilder: Runs an infinite loop where you are prompted to choose between a list of options.
			 When an option is selected, the action associated with that option will be performed.
			 The program will only end when the Quit option is selected.
ExamTaker: Asks for a student's name and an exam file to load.
		   Then, a new exam is created from that exam file and the student is forced to complete it.
		   The student's answers are saved to a file when they're done answering questions.
ExamGrader: Loads an exam file and an answer file and checks if they're matched.
			Then, the answers are evaluated and the results are printed to the screen.
			The results are then also saved as a CSV file.

-------------------------------------------------------- NOTES ----------------------------------------------------------
- The ExamGrader will accept filenames as command line arguments in the following formats:
        1) answer file and exam file
        2) multiple answer files and an exam file
        3) multiple answer files
        4) no command line arguments
                - If no command line arguments are provided the user will be prompt for a 
                answer filename and the exam file will be loaded from the exam filename listed in the answer file.

- The grader will grade each exam and display the score, if multiple exam are included all exam will display with names and respective scores on a final scoreboard.

-------------------------------------------------------- CHANGES --------------------------------------------------------
Added variables to Exam: 
private ArrayList<Integer> skippedQuestionsList // for keeping track of which questions were marked as skipped

Edited methods to Exam:
public void main getAnswerFromStudent( int position ) // checkSkippedQuestions() is now called at the end of this method

Added methods to Exam: 
public void checkSkippedQuestions() // goes through every question and puts the indices of the skipped ones onto the skippedQuestionsList
public void answerSkippedQuestions() // forces the student to answer every question that they have skipped
public void getStudentScore() // gets each students score to post to a final scoreboard
public void getPointsPossible() // gets the points possible for each graded exam to post to the final socreb
