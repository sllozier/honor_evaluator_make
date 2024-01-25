# Define Java compiler
JCC = javac

# Define Java runtime
JVM = java

# Specify the source, documentation, and bin directories
SRC_DIR = src/main
TEST_DIR = src/test
DOC_DIR = docs
BIN_DIR = bin

# Java source files
SOURCES = $(SRC_DIR)/Student.java \
          $(SRC_DIR)/Undergraduate.java \
          $(SRC_DIR)/Graduate.java \
          $(SRC_DIR)/Project2.java \
          $(SRC_DIR)/GenerateStudentData.java \
          $(TEST_DIR)/GraduateTest.java \
          $(TEST_DIR)/Project2Test.java \
          $(TEST_DIR)/StudentTest.java \
          $(TEST_DIR)/UndergraduateTest.java \
          $(TEST_DIR)/GenerateStudentTestData.java \
          $(TEST_DIR)/TestRunner.java

# Default target
all: generate_txt_files run_tests run_project

# Rule to compile and place .class files in the bin directory
compile:
	mkdir -p $(BIN_DIR)
	$(JCC) -d $(BIN_DIR) -cp src $(SOURCES)

# Rule to generate text files
generate_txt_files: compile
	cd $(BIN_DIR) && $(JVM) -cp .:../src main.GenerateStudentData
	cd $(BIN_DIR) && $(JVM) -cp .:../src test.GenerateStudentTestData

# Rule to compile and run tests
run_tests: generate_txt_files
	cd $(BIN_DIR) && $(JVM) -cp .:../src test.TestRunner

# Rule to compile and run project
run_project: generate_txt_files
	cd $(BIN_DIR) && $(JVM) -cp .:../src main.Project2

# Rule to clean up
clean:
	rm -rf $(DOC_DIR)/*.txt
	rm -rf $(BIN_DIR)

# Rest of the Makefile (if any other rules or configurations are needed)

