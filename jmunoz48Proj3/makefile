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
	MCMAAnswer.java \
	Question.java \
	SAQuestion.java \
	MCQuestion.java \
	MCSAQuestion.java \
	MCMAQuestion.java \
	Exam.java \
	ExamTester.java \
	ScannerFactory.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
