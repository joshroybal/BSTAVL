JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
			BST.java \
			AVL.java \
			BSTDriver.java \
			AVLDriver.java \
			TestBST.java \
			TestAVL.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class *~

