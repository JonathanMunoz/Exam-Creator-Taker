To run the program, type "make" to compile everything and then type "java ExamTester".
This will only work on bert and NOT bertvm because bertvm doesn't recognize javac or java as commands.
You can type "make clean" to get rid of the class files you create when compiling.

I never used the parameter for the print( int position ) function in the Answer class and the Question class.
It made no sense for an Answer/Question to know the position of all other Answers/Questions so, the position
variable is never used. In my program, it's not possible for an Answer or a Question to print out any
information about any Answers/Questions except its own.

Also, in the Answer class, I used the primitive boolean type instead of the Boolean class. I didn't see any
reason to use the advanced methods that come with the Boolean class so I didn't use it.

Also also, the selectAnswer( int position ) and unselectAnswer( int position ) functions in the Question class
are 1-indexed instead of 0-indexed. So in order to access the first element, you input 1 instead of 0. Same
goes for the getQuestion( int position ) function in the Exam class.