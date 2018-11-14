JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Answer.java \
	SAAnswer.java \
	MCAnswer.java \
	MCSAAnswer.java \
	Question.java \
	SAQuestion.java \
	MCQuestion.java \
	MCSAQuestion.java \
	Exam.java \
	ExamTester.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
