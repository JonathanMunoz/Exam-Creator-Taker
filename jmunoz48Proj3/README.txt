To run the program, type "make" to compile everything and then type "java ExamTester".
I used bert32 to test this and it works fine there.
You can type "make clean" to get rid of the class files you create when compiling.

In MCMAQuestion, I changed the double baseValue variable to be private instead of public.

In the MCMAQuestion class, I made the protected studentAnswer arraylist be of type MCAnswer instead of just Answer.

I added a saveStudentAnswer method to MCSAQuestion, MCQuestion, and SAQuestion.

Also, I wasn't sure how to implement the getNewAnswer() function in MCSA question, but I needed to return an Answer...
So I just made a new Answer and returned that I guess. That function is never called anyway.