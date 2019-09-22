#all: compile run clean
all: compile

compile:
	javac Task3.java

run:
	java -Xmx512m Task3

clean:
	rm -f expressions/*.class
	rm -f expressions/operations/*.class
	rm -f expressions/operations/binary/*.class
	rm -f expressions/operations/unary/*.class
	rm -f expressions/parser/*.class
	rm -f expressions/parser/exceptions/*.class
	rm -f kek/*.class
	rm -f proofs/*.class
	rm -f proofs/prover/*.class
	rm -f proofs/prover/exceptions/*.class
	rm -f proofs/utils/*.class
	rm -f proofs/utils/axioms/*.class
	rm -f proofs/utils/lems/*.class
	rm -f *.class