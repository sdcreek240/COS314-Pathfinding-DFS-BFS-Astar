# Compile Java files into the executable directory
compile:
	javac -d ./executable src/*.java

# Run the program from the executable directory
run: compile
	java -cp ./executable Main

all: ./src/*.java compile run

# Ensure Java files are recompiled only when changed
./executable/%.class: src/%.java
	javac -d ./executable $<

clean:
	rm -rf ./executable/*.class
	clear
