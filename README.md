## cucumber-training

This is a project which consists of Java Cucumber tests as part of my QA training.

## Setup (pre-requisites)

1. Java 11
2. Maven
3. JUnit 5
4. Plugins for
	- Maven
	- Cucumber for Java
	- Gherkin

## Run Tests

#### To run a single test:
- Navigate to the feature file that contains the test
- Next to the test select the green play button and hit "Run"

#### To run multiple tests:
- Navigate to src/test/java/runner/TestRunner.java class
- Use the "tags" option in @CucumberOptions annotation to run only tests under (a) certain group(s) -> e.g. tags = "@Negative" or tags = "@Login or @Positive"
- Right click -> Run 'TestRunner' or Ctrl+Shift+F10.